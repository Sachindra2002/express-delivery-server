package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.dto.UserDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DriverController {

    private final DriverService driverService;

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-assigned-packages")
    public ResponseEntity<Object> getUpcomingPackages() {
        try {
            List<MailDto> mailDto1 = driverService.getAllAssignedPackages();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-accepted-packages")
    public ResponseEntity<Object> getAcceptedPackages() {
        try {
            List<MailDto> mailDto1 = driverService.getAllAcceptedPackages();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-started-packages")
    public ResponseEntity<Object> getStartedPackages() {
        try {
            List<MailDto> mailDto1 = driverService.getAllStartedPackages();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-picked-up-packages")
    public ResponseEntity<Object> getPickedUpPackages() {
        try {
            List<MailDto> mailDto1 = driverService.getPickedUpPackages();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-transit-packages-driver")
    public ResponseEntity<Object> getTransitPackages() {
        try {
            List<MailDto> mailDtoList = driverService.getTransitPackages();
            return new ResponseEntity<>(mailDtoList, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-out-for-delivery-packages-driver")
    public ResponseEntity<Object> getOutForDeliveryPackages() {
        try {
            List<MailDto> mailDtoList = driverService.getOutForDeliveryPackages();
            return new ResponseEntity<>(mailDtoList, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-all-packages-driver")
    public ResponseEntity<Object> getAllDriverPackages() {
        try {
            List<MailDto> mailDtoList = driverService.getAllDriverPackages();
            return new ResponseEntity<>(mailDtoList, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/get-delivered-packages-driver")
    public ResponseEntity<Object> getDeliveredPackages() {
        try {
            List<MailDto> mailDtoList = driverService.getDeliveredPackages();
            return new ResponseEntity<>(mailDtoList, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/accept-package/{mailId}")
    public ResponseEntity<Object> acceptPackage(@PathVariable int mailId) {
        try {
            driverService.acceptPackage(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Accepted Successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/reject-package/{mailId}")
    public ResponseEntity<Object> rejectPackage(@PathVariable int mailId) {
        try {
            driverService.rejectPackage(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Rejected Successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/start-package/{mailId}")
    public ResponseEntity<Object> startPackage(@PathVariable int mailId) {
        try {
            driverService.startPackage(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Delivery started Successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/confirm-pickup-package/{mailId}")
    public ResponseEntity<Object> confirmPickupPackage(@PathVariable int mailId) {
        try {
            driverService.confirmPickupPackage(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Delivery started Successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/confirm-delivered-package/{mailId}")
    public ResponseEntity<Object> confirmPackageDelivered(@PathVariable int mailId) {
        try {
            driverService.confirmPackageDelivered(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Package delivered successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/transit-package")
    public ResponseEntity<Object> transitPackage(@RequestBody MailDto mailDto) {
        try {
            driverService.transitPackage(mailDto);
            return new ResponseEntity<>(new SimpleMessageDto("Package transit successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/out-for-delivery")
    public ResponseEntity<Object> startDelivery(@RequestBody MailDto mailDto) {
        try {
            driverService.startDelivery(mailDto);
            return new ResponseEntity<>(new SimpleMessageDto("Delivery started successfully", HttpStatus.OK), HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('DRIVER, CUSTOMER')")
    public ResponseEntity<Object> getUser() {
        try {
            Optional<UserDto> user = driverService.getUserDetails();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-status")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<Object> updateDriverStatus(@RequestBody UserDto userDto) {
        try {
            driverService.updateStatus(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExpressDeliveryException e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

    }
}
