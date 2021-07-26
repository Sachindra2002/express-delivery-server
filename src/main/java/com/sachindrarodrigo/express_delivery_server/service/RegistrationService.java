package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public UserDto registerUser(UserDto userDto) throws ExpressDeliveryException {

        Optional existing = userRepository.findById(userDto.getEmail());

        if(existing.isPresent()){
            throw new ExpressDeliveryException("Email already in use");
        }
        User user = map(userDto);
        userDto.setPassword(user.getPassword());

        //save the new user
        userRepository.save(user);

        return userDto;
    }

    private User map(UserDto userDto){
        return User.builder().firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .location(userDto.getLocation())
                .userRole("customer")
                .password(passwordEncoder.encode(userDto.getPassword())).build();
    }
}
