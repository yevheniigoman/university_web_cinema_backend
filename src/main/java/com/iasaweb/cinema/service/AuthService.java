package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.User;
import com.iasaweb.cinema.dto.LoginRequestBody;
import com.iasaweb.cinema.dto.LoginResponseBody;
import com.iasaweb.cinema.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public LoginResponseBody login(LoginRequestBody loginRequest) throws UserNotFoundException {
        User user = userService.findByName(loginRequest.username());
        String token = jwtService.token(user);
        return new LoginResponseBody(token);
    }
}
