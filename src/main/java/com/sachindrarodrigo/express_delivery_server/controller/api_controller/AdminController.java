package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AdminController {

    private DriverService driverService;


}
