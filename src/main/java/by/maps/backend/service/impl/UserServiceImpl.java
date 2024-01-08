package by.maps.backend.service.impl;

import by.maps.backend.domain.User;
import by.maps.backend.security.util.AccountDetails;
import by.maps.backend.security.util.JwtUtil;
import by.maps.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtUtil util;
    @Override
    public User getUser() {
        AccountDetails ad = util.getAccountDetails();
        User user = new User();
        user.setEmail(ad.getEmail());
        user.setGiven_name(ad.getGiven_name());
        return user;
    }
}
