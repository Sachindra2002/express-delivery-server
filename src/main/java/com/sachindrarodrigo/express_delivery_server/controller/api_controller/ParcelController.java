package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ParcelController {
    private final MailService mailService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/send-package")
    public ResponseEntity<Object> sendMail(@RequestBody MailDto dto) {
        MailDto result = mailService.sendMail(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
