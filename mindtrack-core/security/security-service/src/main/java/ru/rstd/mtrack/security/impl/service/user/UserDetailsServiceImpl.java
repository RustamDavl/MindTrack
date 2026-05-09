package ru.rstd.mtrack.security.impl.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rstd.mtrack.security.dao.user.UserSearchDao;
import ru.rstd.mtrack.security.model.user.User;
import ru.rstd.mtrack.security.model.user.UserSecurityModel;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserSearchDao userSearchDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userSearchDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new UserSecurityModel(user);
    }
}