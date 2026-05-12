package ru.rstd.mtrack.core.security.service.impl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.core.security.dao.api.user.UserSearchDao;
import ru.rstd.mtrack.core.security.model.user.User;
import ru.rstd.mtrack.core.security.model.user.UserNotFoundException;
import ru.rstd.mtrack.core.security.service.api.user.UserSearchService;


@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {
    private final UserSearchDao userSearchDao;

    @Override
    public User findByEmail(String email) {
        return userSearchDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
