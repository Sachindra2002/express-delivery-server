package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class UserWebController {

    @GetMapping("/login")
    public ModelAndView login(String error) {
        ModelAndView mv =new ModelAndView();

        if(error != null) mv.addObject("error", "Invalid login credentials");
        System.out.println(error);

        mv.setViewName("login.jsp");
        return mv;
    }
}
