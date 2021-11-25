package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailTrackingDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import com.sachindrarodrigo.express_delivery_server.service.MailTrackingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ParcelController {

    private final MailService mailService;
    private final MailTrackingService mailTrackingService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/send-package")
    public ResponseEntity<Object> sendMail(@RequestBody MailDto dto) throws ExpressDeliveryException {
        MailDto result = mailService.sendMail(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/get-package")
    public ResponseEntity<Object> getMail(@RequestBody MailTrackingDto dto) throws ExpressDeliveryException {
        MailTracking mailDto = mailTrackingService.getTrackingInfo(dto.getTrackingId());
        return new ResponseEntity<>(mailDto, HttpStatus.OK);
    }

}
