package ru.rstd.mindly.security.impl.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rstd.mindly.security.dao.user.UserMutationDao;
import ru.rstd.mindly.security.model.user.User;
import ru.rstd.mindly.security.service.api.user.UserMutationService;

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
