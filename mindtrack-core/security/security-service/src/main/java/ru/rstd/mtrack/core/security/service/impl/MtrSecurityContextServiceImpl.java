package ru.rstd.mtrack.core.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.security.service.api.MtrSecurityContextService;
import ru.rstd.mtrack.core.security.service.api.user.UserSearchService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MtrSecurityContextServiceImpl implements MtrSecurityContextService {
    private static final String EMAIL_KEY = "email";

    private final UserSearchService userSearchService;

    @Override
    public UUID getCurrentUserId() {
        Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (String) token.getClaims().get(EMAIL_KEY);
        return userSearchService
                .findByEmail(email)
                .getId();
    }
}
