package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;

    public List<UserDto> getAllCustomers() {
        return userRepository.findByUserRoleEquals("customer").stream().map(this::mapUsers).collect(Collectors.toList());
    }

    public UserDto getCustomer(UserDto userDto) throws ExpressDeliveryException {
        return userRepository.findById(userDto.getEmail()).map(this::mapUsers2).orElseThrow(() -> new ExpressDeliveryException("User not found"));
    }

    public UserDto toggleBlacklist(UserDto userDto) throws ExpressDeliveryException {
        User user = userRepository.findById(userDto.getEmail()).orElseThrow(() -> new ExpressDeliveryException("User not found"));

        user.setIsBanned(!user.getIsBanned());

        userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        return dto;
    }

    //Method to map data transfer object to domain class
    private UserDto mapUsers(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLocation(), user.getPhoneNumber(), user.getUserRole(), user.getIsBanned());
    }

    //Method to map data transfer object to domain class
    private UserDto mapUsers2(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLocation(), user.getPhoneNumber(), user.getMails(), user.getIsBanned());
    }

    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        return _user.getFirstName();
    }
}
