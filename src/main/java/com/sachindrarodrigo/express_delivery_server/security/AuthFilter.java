package com.sachindrarodrigo.express_delivery_server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Qualifier("appUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //Get bearer token
        String token = getTokenFromRequest(httpServletRequest);

        //Validate the token
        if(StringUtils.hasText(token) && jwtProvider.validateToken(token)){
            String email = jwtProvider.getEmailFromToken(token);

            UserDetails userDetails =userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //Set usernamePasswordAuthenticationToken object
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    //Utility function to get token from request
    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String bearer_token = httpServletRequest.getHeader("Authorization");

        if(StringUtils.hasText(bearer_token) && bearer_token.startsWith("Bearer ")){
            return bearer_token.substring(7); // Remove "Bearer " part
        }
        return bearer_token;
    }
}
