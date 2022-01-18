package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class DriverWebController {

    private final DriverService driverService;

    @PostMapping("/accept-parcel-driver")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView acceptParcel(@RequestParam int mailId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            driverService.acceptPackage(mailId);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package Accepted Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @PostMapping("/reject-parcel-driver")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView rejectParcel(@RequestParam int mailId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            driverService.rejectPackage(mailId);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package rejected Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @PostMapping("/start-shipment")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView startShipment(@RequestParam int mailId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            driverService.startPackage(mailId);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package started Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @PostMapping("/confirm-pickup")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView confirmPickup(@RequestParam int mailId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            driverService.confirmPickupPackage(mailId);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package picked up Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @PostMapping("/transit-package")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView transitPackage(@RequestParam int mailId, @RequestParam int centerId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            ServiceCentre serviceCentre = new ServiceCentre();
            serviceCentre.setCentreId(centerId);
            MailDto mailDto = new MailDto();
            mailDto.setMailId(mailId);
            mailDto.setServiceCentre(serviceCentre);
            driverService.transitPackage(mailDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package transited Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @PostMapping("/out-for-delivery-package")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView confirmOutForDelivery(@RequestParam int mailId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            MailDto mailDto = new MailDto();
            mailDto.setMailId(mailId);
            driverService.startDelivery(mailDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package out for delivery started Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @PostMapping("/confirm-delivery")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView confirmDelivered(@RequestParam int mailId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            driverService.confirmPackageDelivered(mailId);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package Delivered Successfully"));
            mv.setViewName("redirect:/home-driver");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }

    @GetMapping("/past-shipments")
    @PreAuthorize("hasRole('DRIVER')")
    public ModelAndView getPastShipments(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("mail_list", driverService.getAllDeliveredShipments());
            mv.setViewName("view-shipments.jsp");
        } catch (ExpressDeliveryException e) {
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
            mv.setViewName("redirect:/home-driver");
        }

        return mv;
    }
}
