package ru.rstd.mtrack.test.integration.security;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.rstd.mtrack.security.model.token.AccessWithRefreshToken;
import ru.rstd.mtrack.security.model.token.MailConfirmationStatus;
import ru.rstd.mtrack.security.model.user.User;
import ru.rstd.mtrack.security.model.user.UserRequest;
import ru.rstd.mtrack.security.service.api.user.UserAuthService;
import ru.rstd.mtrack.security.service.api.user.UserSearchService;
import ru.rstd.mtrack.test.integration.IntegrationTestBase;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;

class UserAuthRestControllerIT extends IntegrationTestBase {

    private static final String PASSWORD = "12345";

    @LocalServerPort
    private int port;

    @Autowired
    private UserSearchService userSearchService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void user_registration_via_rest_should_save_user() {
        String email = "rest-register-user@gmail.com";

        registerUser(email);

        User user = userSearchService.findByEmail(email);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo("TestUser");
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getIsEmailVerified()).isFalse();
    }

    @Test
    void confirm_email_via_rest_should_verify_user_email() {
        User user = registerUser("rest-confirm-email-user@gmail.com");
        String token = findVerificationToken(user);

        RestAssured
                .given()
                .queryParam("token", token)
                .when()
                .get("/api/v1/auth/confirm-email")
                .then()
                .statusCode(204);

        User confirmedUser = userSearchService.findByEmail(user.getEmail());

        Assertions.assertThat(confirmedUser.getIsEmailVerified()).isTrue();
    }

    @Test
    void confirm_mail_via_rest_should_return_success_and_verify_user_email() {
        User user = registerUser("rest-confirm-mail-user@gmail.com");
        String token = findVerificationToken(user);

        RestAssured
                .given()
                .contentType(JSON)
                .body(Map.of("token", token))
                .when()
                .post("/api/v1/auth/confirm-mail")
                .then()
                .statusCode(200)
                .body("status", Matchers.equalTo(MailConfirmationStatus.SUCCESS.name()))
                .body("message", Matchers.equalTo("Email verified."));

        User confirmedUser = userSearchService.findByEmail(user.getEmail());

        Assertions.assertThat(confirmedUser.getIsEmailVerified()).isTrue();
    }

    @Test
    void login_via_rest_should_return_access_and_refresh_tokens() {
        String email = "rest-login-user@gmail.com";
        registerVerifiedUser(email);

        RestAssured
                .given()
                .contentType(JSON)
                .body(Map.of(
                        "email", email,
                        "password", PASSWORD
                ))
                .when()
                .post("/api/v1/auth/login")
                .then()
                .statusCode(200)
                .body("accessToken", Matchers.not(Matchers.blankOrNullString()))
                .body("refreshToken", Matchers.not(Matchers.blankOrNullString()));
    }

    @Test
    void refresh_token_via_rest_should_return_new_access_and_refresh_tokens() {
        String email = "rest-refresh-user@gmail.com";
        registerVerifiedUser(email);
        AccessWithRefreshToken tokens = userAuthService.login(new UserRequest(email, PASSWORD));

        ValidatableResponse response = RestAssured
                .given()
                .contentType(JSON)
                .body(Map.of("refreshToken", tokens.refreshToken()))
                .when()
                .post("/api/v1/auth/refresh")
                .then()
                .statusCode(200)
                .body("accessToken", Matchers.not(Matchers.blankOrNullString()))
                .body("refreshToken", Matchers.not(Matchers.blankOrNullString()));

        String refreshedRefreshToken = response.extract().path("refreshToken");

        Assertions.assertThat(refreshedRefreshToken).isNotEqualTo(tokens.refreshToken());
        Assertions.assertThat(isRefreshTokenRevoked(tokens.refreshToken())).isTrue();
        Assertions.assertThat(isRefreshTokenRevoked(refreshedRefreshToken)).isFalse();
    }

    private void registerVerifiedUser(String email) {
        User user = registerUser(email);
        userAuthService.confirm(findVerificationToken(user));
        userSearchService.findByEmail(email);
    }

    private User registerUser(String email) {
        RestAssured
                .given()
                .contentType(JSON)
                .body(Map.of(
                        "name", "TestUser",
                        "email", email,
                        "password", PASSWORD
                ))
                .when()
                .post("/api/v1/auth/register")
                .then()
                .statusCode(201);

        return userSearchService.findByEmail(email);
    }

    private String findVerificationToken(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", user.getId());

        return namedParameterJdbcTemplate.queryForObject(
                "SELECT token_hash FROM mtrack.email_verification_token WHERE user_id = :userId",
                parameters,
                String.class
        );
    }

    private Boolean isRefreshTokenRevoked(String token) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("token", token);

        return namedParameterJdbcTemplate.queryForObject(
                "SELECT is_revoked FROM mtrack.refresh_token WHERE token = :token",
                parameters,
                Boolean.class
        );
    }
}
