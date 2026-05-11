package ru.rstd.mtrack.test.integration.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rstd.mtrack.security.model.user.User;
import ru.rstd.mtrack.security.model.user.UserRequest;
import ru.rstd.mtrack.security.service.api.user.UserAuthService;
import ru.rstd.mtrack.security.service.api.user.UserSearchService;
import ru.rstd.mtrack.test.integration.IntegrationTestBase;

class UserAuthServiceIT extends IntegrationTestBase {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserSearchService userSearchService;

    @Test
    void user_registration_should_pass() {
        userAuthService.register(new UserRequest("Anna", "anna@gmail.com", "12345"));
        User user = userSearchService.findByEmail("anna@gmail.com");

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getEmail()).isEqualTo("anna@gmail.com");

    }
}
