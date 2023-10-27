package com.hladchenko.sessionscopeinternals.controller;

import com.hladchenko.sessionscopeinternals.dto.User;
import com.hladchenko.sessionscopeinternals.service.SecurityContextServiceProxy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final SecurityContextServiceProxy securityContextService;

    @PostMapping("/signin")
    public void signIn(@RequestBody User user) {
        securityContextService.setCurrentUser(user);
    }

    @GetMapping("/current")
    public User getCurrent(HttpServletRequest request) {
        return securityContextService.getCurrentUser();
    }
}
