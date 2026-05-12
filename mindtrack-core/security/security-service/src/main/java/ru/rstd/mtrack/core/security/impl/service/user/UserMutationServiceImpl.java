package ru.rstd.mtrack.core.security.impl.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rstd.mtrack.core.security.dao.user.UserMutationDao;
import ru.rstd.mtrack.core.security.model.user.User;
import ru.rstd.mtrack.core.security.service.api.user.UserMutationService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMutationServiceImpl implements UserMutationService {
    private final UserMutationDao userMutationDao;

    @Override
    @Transactional
    public User save(User user) {
        return userMutationDao.save(user);
    }

    @Override
    @Transactional
    public void verifyEmail(UUID userId) {
        userMutationDao.verifyEmail(userId);
    }

    @Override
    @Transactional
    public User update(User user) {
        return null;
    }
}
