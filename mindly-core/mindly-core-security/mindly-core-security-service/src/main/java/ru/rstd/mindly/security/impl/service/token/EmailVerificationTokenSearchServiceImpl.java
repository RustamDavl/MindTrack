package ru.rstd.mindly.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mindly.security.dao.token.EmailVerificationTokenSearchDao;
import ru.rstd.mindly.security.model.token.EmailVerificationToken;
import ru.rstd.mindly.security.service.api.token.EmailVerificationTokenSearchService;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenSearchServiceImpl implements EmailVerificationTokenSearchService {
    private final EmailVerificationTokenSearchDao emailVerificationTokenSearchDao;

    @Override
    public EmailVerificationToken findByToken(String token) {
        return emailVerificationTokenSearchDao.findByToken(token)
                .orElseThrow();
    }
}
