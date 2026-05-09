package ru.rstd.mtrack.security.impl.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.dao.token.EmailVerificationTokenSearchDao;
import ru.rstd.mtrack.security.model.token.EmailVerificationToken;
import ru.rstd.mtrack.security.service.api.token.EmailVerificationTokenSearchService;

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
