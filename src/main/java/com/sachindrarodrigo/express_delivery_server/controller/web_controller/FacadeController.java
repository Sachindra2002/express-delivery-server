package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.utils.ExtraUtilities;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FacadeController {

    //Facade design pattern to direct user to respective controller to load home page
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN', 'AGENT')")
    public ModelAndView sendUserHome() {
        ModelAndView mv = new ModelAndView();

        if (ExtraUtilities.hasRole("ROLE_ADMIN")) {
            mv.setViewName("redirect:/home-admin");
        } else if (ExtraUtilities.hasRole("ROLE_CUSTOMER")) {
            mv.setViewName("redirect:/home-customer");
        } else if (ExtraUtilities.hasRole("ROLE_AGENT")) {
            mv.setViewName("redirect:/home-agent");
        } else if (ExtraUtilities.hasRole("ROLE_DRIVER")) {
            mv.setViewName("/");
        }

        return mv;
    }
}
