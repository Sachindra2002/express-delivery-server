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
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ModelAndView sendUserHome() {
        //Check if user is an admin
        boolean isAdmin = ExtraUtilities.hasRole("ROLE_ADMIN");

        ModelAndView mv = new ModelAndView();

        if(isAdmin) {
            mv.setViewName("redirect:/home-admin");
        }else {
            mv.setViewName("redirect:/home-customer");
        }

        return mv;
    }
}
