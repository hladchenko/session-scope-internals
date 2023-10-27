package com.hladchenko.sessionscopeinternals.service;

import com.hladchenko.sessionscopeinternals.dto.User;
public class SecurityContextService {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
