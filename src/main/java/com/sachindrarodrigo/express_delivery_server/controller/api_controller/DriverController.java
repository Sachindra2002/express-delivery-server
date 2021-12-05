package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DriverController {

    private final DriverService driverService;

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-assigned-packages")
    public ResponseEntity<Object> getUpcomingPackages(){
        try {
            List<MailDto> mailDto1 = driverService.getAllAssignedPackages();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-accepted-packages")
    public ResponseEntity<Object> getAcceptedPackages(){
        try {
            List<MailDto> mailDto1 = driverService.getAllAcceptedPackages();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/accept-package/{mailId}")
    public ResponseEntity<Object> acceptPackage(@PathVariable int mailId){
        try{
            driverService.acceptPackage(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Accepted Successfully", HttpStatus.OK), HttpStatus.OK);
        }catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }
}
