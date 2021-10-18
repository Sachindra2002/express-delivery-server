package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.dto.*;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.CustomerService;
import com.sachindrarodrigo.express_delivery_server.service.DisputeService;
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
    private final DisputeService disputeService;

    @PostMapping("/send-package")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView sendPackage(@RequestParam String pickupAddress, @RequestParam String receiverAddress, @RequestParam String receiverPhoneNumber, @RequestParam String receiverEmail, @RequestParam String receiverCity, @RequestParam String parcelType, @RequestParam String weight, @RequestParam String pieces, @RequestParam String paymentMethod, @RequestParam String date, @RequestParam String time, @RequestParam String totalCost, @RequestParam String description) throws ExpressDeliveryException {

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

    @PostMapping("/open-dispute")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView openDispute(@RequestParam int mailId, @RequestParam String disputeType, @RequestParam String description) throws ExpressDeliveryException {
        ModelAndView mv = HomePage();

        DisputeDto dto = new DisputeDto();
        dto.setMailId(mailId);
        dto.setDescription(description);
        dto.setDisputeType(disputeType);
        disputeService.openDispute(dto);

        mv.addObject("success", new SimpleMessageDto("Dispute opened Successfully for package "+ mailId));

        return mv;
    }

    @PostMapping("/initiate-return")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView initiateReturn(@RequestParam int mailId, @RequestParam int trackingId, @RequestParam String date, @RequestParam String time, @RequestParam String returnType, @RequestParam String reason, @RequestParam String description) throws ExpressDeliveryException{
        ModelAndView mv = HomePage();

        MailTrackingDto dto = new MailTrackingDto();
        mv.addObject("success", new SimpleMessageDto("Return initiated Successfully"));

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
