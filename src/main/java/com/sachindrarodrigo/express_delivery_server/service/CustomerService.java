package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;


    public String getName() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(()->new ExpressDeliveryException("User not found"));

        return _user.getFirstName();
    }
}
