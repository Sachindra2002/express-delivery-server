package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.LoginRequest;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        try{
            return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

}
