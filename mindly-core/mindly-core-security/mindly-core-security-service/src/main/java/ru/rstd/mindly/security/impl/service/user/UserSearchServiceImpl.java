package ru.rstd.mindly.security.impl.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rstd.mindly.security.dao.user.UserSearchDao;
import ru.rstd.mindly.security.model.user.User;
import ru.rstd.mindly.security.model.user.UserNotFoundException;
import ru.rstd.mindly.security.service.api.user.UserSearchService;


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
