package com.sachindrarodrigo.express_delivery_server.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class WithCustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomUser withCustomUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        CustomUserDetails principle = new CustomUserDetails(withCustomUser.name(), withCustomUser.username());
        Authentication authentication = new UsernamePasswordAuthenticationToken(principle.getUsername(), "password");

        context.setAuthentication(authentication);

        return context;
    }
}
