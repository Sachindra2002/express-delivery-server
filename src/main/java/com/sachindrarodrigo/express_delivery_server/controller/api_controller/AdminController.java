package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.*;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AdminController {

    private ServiceCenterService serviceCenterService;
    private DriverService driverService;
    private VehicleService vehicleService;
    private AdminService adminService;
    private StorageService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-service-centers")
    public ResponseEntity<Object> getServiceCenters() {
        try {
            List<ServiceCenterDto> serviceCenterDtoList = serviceCenterService.getAllServiceCenters();
            return new ResponseEntity<>(serviceCenterDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-available-vehicles")
    public ResponseEntity<Object> getAvailableVehicles() {
        try {
            List<VehicleDto> vehicleDtoList = vehicleService.getAvailableVehicles();
            return new ResponseEntity<>(vehicleDtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-driver")
    public ResponseEntity<Object> addDriver(@RequestBody UserDto userDto) {
        try{
            driverService.addDriver(userDto, userDto.getServiceCentre().getCentreId());
            driverService.addDriverDetail(userDto.getDriverDetail(), userDto.getEmail(), userDto.getDriverDetail().getVehicle().getVehicleId());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ExpressDeliveryException | MessagingException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-drivers-admin")
    public ResponseEntity<Object> getDrivers(){
        try {
            List<UserDto> userDto = adminService.getAllDrivers();
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-agents")
    public ResponseEntity<Object> getAgents(){
        try {
            List<UserDto> userDto = adminService.getAllAgents();
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-driver-documents-admin/{email}")
    public ResponseEntity<Object> getDriverDocuments(@PathVariable String email){
        try {
            List<DocumentsDto> documentsDto = adminService.getDriverDocuments(email);
            return new ResponseEntity<>(documentsDto, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/download-admin/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName){
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}