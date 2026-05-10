package ru.rstd.mtrack.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.service.api.token.AccessTokenService;
import ru.rstd.mtrack.security.impl.properties.JwtProperties;
import ru.rstd.mtrack.security.model.user.UserSecurityModel;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtProperties properties;

    @Override
    public String generate(UserSecurityModel user) {
        Instant now = Instant.now();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(properties.getIssuer())
                .issuedAt(now)
                .expiresAt(now.plus(properties.getAccessTokenTtl()))
                .subject(user.getUser().getId().toString())
                .claim("email", user.getUser().getEmail())
                .claim("roles", roles)
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
