package com.coffee.gifu.web.rest;

import com.coffee.gifu.domain.User;
import com.coffee.gifu.security.SecurityUtils;
import com.coffee.gifu.service.UserService;
import com.coffee.gifu.web.rest.errors.CurrentUserLoginNotFound;

import java.util.Optional;

public class CheckUser {

    private final UserService userService;

    protected CheckUser(UserService userService) {
        this.userService = userService;
    }

    protected Optional<User> checkIfUserExists() {
        Optional<String> currentUserLogin = SecurityUtils.getCurrentUserLogin();
        if (currentUserLogin.isEmpty()) {
            throw new CurrentUserLoginNotFound("currentUserLogin is not found");
        }

        Optional<User> userWithAuthoritiesByLogin =
                userService.getUserWithAuthoritiesByLogin(currentUserLogin.get());
        if (userWithAuthoritiesByLogin.isEmpty()) {
            throw new CurrentUserLoginNotFound("userWithAuthoritiesByLogin is not found");
        }
        return userWithAuthoritiesByLogin;
    }
}
