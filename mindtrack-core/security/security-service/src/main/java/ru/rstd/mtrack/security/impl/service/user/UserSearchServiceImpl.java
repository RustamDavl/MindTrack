package ru.rstd.mtrack.security.impl.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.dao.user.UserSearchDao;
import ru.rstd.mtrack.security.model.user.User;
import ru.rstd.mtrack.security.model.user.UserNotFoundException;
import ru.rstd.mtrack.security.service.api.user.UserSearchService;


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
