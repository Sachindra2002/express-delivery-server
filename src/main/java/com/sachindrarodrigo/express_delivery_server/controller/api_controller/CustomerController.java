package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.DisputeDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.DisputeService;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CustomerController {

    private MailService mailService;
    private DisputeService disputeService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/cancel-package/{mailId}")
    public ResponseEntity<Object> cancelPackage(@PathVariable int mailId) {
        try {
            mailService.cancelParcel(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Cancelled Successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/open-dispute")
    public ResponseEntity<Object> openDispute(@RequestBody DisputeDto disputeDto) {
        try {
            disputeService.openDispute(disputeDto, disputeDto.getMail().getMailId());
            return new ResponseEntity<>(new SimpleMessageDto("Dispute opened Successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }


}
