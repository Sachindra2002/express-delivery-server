package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.dto.ChangePasswordRequest;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.*;
import com.sachindrarodrigo.express_delivery_server.utils.ExtraUtilities;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class UserWebController {

    MailService mailService;
    CustomerService customerService;
    AdminService adminService;
    AgentService agentService;
    DriverService driverService;
    DocumentsService documentsService;
    private final AuthService authService;

    @GetMapping("/login")
    public ModelAndView login(String error) {
        ModelAndView mv = new ModelAndView();

        if (error != null) mv.addObject("error", "Invalid login credentials");
        System.out.println(error);

        mv.setViewName("login.jsp");
        return mv;
    }

    @GetMapping("/home-admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView homeAdmin() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("home_admin.jsp");
        mv.addObject("new_shipments", adminService.getAllNewShipmentsAdmin());
        mv.addObject("documents", documentsService.getDocuments());
        mv.addObject("drivers", driverService.getAvailableDrivers());
        try {
            mv.addObject("name", adminService.getName());
        } catch (ExpressDeliveryException e) {
            e.printStackTrace();
        }
        return mv;
    }

    @GetMapping("/home-customer")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ModelAndView homeCustomer() throws ExpressDeliveryException {
        //Direct customer to homepage

        ModelAndView mv = new ModelAndView();
        mv.setViewName("home_customer.jsp");
        mv.addObject("upcoming_packages", mailService.getAllRecentUpcomingPackages());
        mv.addObject("outgoing_packages", mailService.getAllRecentOutgoingPackages());
        try {
            mv.addObject("name", customerService.getName());
        } catch (ExpressDeliveryException e) {
            e.printStackTrace();
        }

        return mv;
    }

    @GetMapping("/home-agent")
    @PreAuthorize("hasAnyRole('AGENT')")
    public ModelAndView homeAgent() throws ExpressDeliveryException {
        //Direct agent to homepage

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/home_agent.jsp");
        mv.addObject("pending_packages", agentService.getAllNewShipmentsAdmin());
        mv.addObject("accepted_packages", agentService.getAllNewAcceptedShipmentsAdmin());
        mv.addObject("transit_packages", agentService.getTransitPackages());
        //Get list of available driver to the specific service center to be assigned
        mv.addObject("available_driver_list", agentService.getAllAvailableDrivers());
        try {
            mv.addObject("name", agentService.getName());
        } catch (ExpressDeliveryException e) {
            e.printStackTrace();
        }
        return mv;
    }

    @GetMapping("/home-driver")
    @PreAuthorize("hasAnyRole('DRIVER')")
    public ModelAndView homeDriver() throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/home_driver.jsp");
        mv.addObject("assigned_packages", driverService.getAllAssignedPackages());
        mv.addObject("accepted_packages", driverService.getAllAcceptedPackages());
        try {
            mv.addObject("name", driverService.getName());
        } catch (ExpressDeliveryException e) {
            e.printStackTrace();
        }
        return mv;
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'CUSTOMER', 'DRIVER')")
    public ModelAndView changePassword(ChangePasswordRequest dto, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();

        //Check user role
        boolean isAdmin = ExtraUtilities.hasRole("ROLE_ADMIN");
        boolean isCustomer = ExtraUtilities.hasRole("ROLE_CUSTOMER");
        boolean isAgent = ExtraUtilities.hasRole("ROLE_AGENT");
        boolean isDriver = ExtraUtilities.hasRole("ROLE_DRIVER");

        try {
            authService.changePassword(dto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Successfully changed password!"));
            if (isAdmin) {
                mv.setViewName("redirect:/home-admin");
            } else if (isCustomer) {
                mv.setViewName("redirect:/home-customer");
            } else if (isAgent) {
                mv.setViewName("redirect:/home-agent");
            } else if (isDriver) {
                mv.setViewName("redirect:/home-driver");
            }

        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            if (isAdmin) {
                mv.setViewName("redirect:/home-admin");
            } else if (isCustomer) {
                mv.setViewName("redirect:/home-customer");
            } else if (isAgent) {
                mv.setViewName("redirect:/home-agent");
            } else if (isDriver) {
                mv.setViewName("redirect:/home-driver");
            }
        }

        return mv;
    }
}
