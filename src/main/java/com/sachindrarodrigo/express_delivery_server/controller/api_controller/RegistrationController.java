package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        try {
            registrationService.registerUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

}
