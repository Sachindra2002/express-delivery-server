package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class AdminWebController {

    private final AdminService adminService;

    @GetMapping("/manage-drivers")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView manageDrivers() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage-drivers.jsp");
        mv.addObject("driver_list", adminService.getAllDrivers());
        return mv;
    }


}
