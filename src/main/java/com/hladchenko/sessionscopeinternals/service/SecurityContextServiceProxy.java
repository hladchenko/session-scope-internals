package com.hladchenko.sessionscopeinternals.service;

import com.hladchenko.sessionscopeinternals.dto.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SecurityContextServiceProxy extends SecurityContextService {

    @Override
    public User getCurrentUser() {
        return getDelegate().getCurrentUser();
    }

    @Override
    public void setCurrentUser(User currentUser) {
        getDelegate().setCurrentUser(currentUser);
    }

    private SecurityContextService getDelegate() {
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var request = requestAttributes.getRequest();
        var session = request.getSession();
        SecurityContextService delegate;
        if (session.getAttribute("targetScope.securityContextService") != null) {
            delegate = (SecurityContextService) session.getAttribute("targetScope.securityContextService");
            return delegate;
        } else {
            delegate = new SecurityContextService();
            session.setAttribute("targetScope.securityContextService", delegate);
            return delegate;
        }
    }

    @Override
    public String toString() {
        return getDelegate().toString();
    }
}
