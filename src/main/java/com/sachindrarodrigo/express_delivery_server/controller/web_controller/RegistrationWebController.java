package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class RegistrationWebController {

    private final RegistrationService registrationService;

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        mv.addObject("user", user);
        mv.setViewName("register.jsp");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("register.jsp");
            System.out.println("errors" + bindingResult.hasErrors() + bindingResult.getAllErrors());
        } else if (!Objects.equals(user.getPassword(), user.getMatchingPassword())) {
            mv.setViewName("register.jsp");
            mv.addObject("passwordError", "Passwords do not match!");
        } else if (!bindingResult.hasErrors()) {
            try {

                registrationService.registerUserWeb(user);
                redirectAttributes.addFlashAttribute("success", "Successfully Registered");
                mv.setViewName("redirect:/login");
            } catch (ExpressDeliveryException e) {
                mv.setViewName("register.jsp");
                mv.addObject("emailError", "Email already in use");
            }

        }

        return mv;

    }
}
