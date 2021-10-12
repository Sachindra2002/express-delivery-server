package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.CustomerService;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ParcelWebController {

    private final MailService mailService;
    private final CustomerService customerService;

    @PostMapping("/send-package")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView sendPackage(@RequestParam String pickupAddress, @RequestParam String receiverAddress, @RequestParam String receiverPhoneNumber, @RequestParam String receiverEmail, @RequestParam String receiverCity, @RequestParam String parcelType, @RequestParam String weight, @RequestParam String pieces, @RequestParam String paymentMethod, @RequestParam String date, @RequestParam String time, @RequestParam String totalCost, @RequestParam String description){

        // Create mail dto with user input
        MailDto dto = new MailDto();
        dto.setPickupAddress(pickupAddress);
        dto.setReceiverAddress(receiverAddress);
        dto.setReceiverPhoneNumber(receiverPhoneNumber);
        dto.setReceiverEmail(receiverEmail);
        dto.setReceiverCity(receiverCity);
        dto.setParcelType(parcelType);
        dto.setWeight(weight);
        dto.setPieces(pieces);
        dto.setPaymentMethod(paymentMethod);
        dto.setDate(date);
        dto.setTime(time);
        dto.setTotalCost(totalCost);
        dto.setDescription(description);

        mailService.sendMail(dto);

        //Return user to "Home" page
        ModelAndView mv = HomePage();
        mv.addObject("success", new SimpleMessageDto("Order added successfully!"));

        return mv;
    }

    private ModelAndView HomePage() {
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

}
