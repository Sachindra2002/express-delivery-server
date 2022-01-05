package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.AuthResponse;
import com.sachindrarodrigo.express_delivery_server.dto.ChangePasswordRequest;
import com.sachindrarodrigo.express_delivery_server.dto.LoginRequest;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import com.sachindrarodrigo.express_delivery_server.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public AuthResponse login(LoginRequest request) throws ExpressDeliveryException{
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        //Find user from database or send an error
        User user = userRepository.findById(request.getEmail()).orElseThrow(() -> new ExpressDeliveryException("User not found!"));

        return new AuthResponse(token, request.getEmail(), user.getUserRole(), user.getFirstName(), user.getLastName());
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));

        if(!passwordEncoder.matches(request.getOldPassword(), _user.getPassword())){
            throw new ExpressDeliveryException("Old password is incorrect");
        }

        //Change Password
        _user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        //Save new password
        userRepository.save(_user);

        new SimpleMessageDto("Password Changed Successfully", HttpStatus.OK);
    }
}
