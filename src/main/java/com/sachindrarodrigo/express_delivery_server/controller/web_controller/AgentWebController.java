package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.dto.DriverDetailDto;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.AgentService;
import com.sachindrarodrigo.express_delivery_server.service.DriverService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@AllArgsConstructor
public class AgentWebController {

    private final AgentService agentService;
    private final DriverService driverService;

    @PostMapping("/accept-parcel")
    @PreAuthorize("hasRole('AGENT')")
    public ModelAndView acceptParcel(@RequestParam int mailId, RedirectAttributes redirectAttributes) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        agentService.acceptParcel(mailId);
        redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package Accepted Successfully"));
        mv.setViewName("redirect:/home-agent");
        return mv;
    }

    @PostMapping("/reject-parcel")
    @PreAuthorize("hasRole('AGENT')")
    public ModelAndView rejectParcel(@RequestParam int mailId, RedirectAttributes redirectAttributes) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        agentService.rejectParcel(mailId);
        redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package Rejected Successfully"));
        mv.setViewName("redirect:/home-agent");
        return mv;
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/assign-driver")
    public ModelAndView assignDriver(@RequestParam int mailId, @RequestParam int driverId, @RequestParam String eDate, RedirectAttributes redirectAttributes) throws ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        agentService.assignDriver(mailId, driverId, eDate);
        redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package Assigned Successfully"));
        mv.setViewName("redirect:/home-agent");
        return mv;
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/change-driver")
    public ModelAndView changeDriver(@RequestParam int mailId, @RequestParam int driverId, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            DriverDetail driverDetail = new DriverDetail();
            driverDetail.setDriverId(driverId);
            MailDto mailDto = new MailDto();
            mailDto.setMailId(mailId);
            mailDto.setDriverDetail(driverDetail);
            agentService.changeDriver(mailDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Driver changed Successfully"));
            mv.setViewName("redirect:/home-agent");
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/home-agent");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/center-drivers")
    public ModelAndView getAllServiceCenterDrivers(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("manage-drivers.jsp");
            mv.addObject("driver_list", agentService.getAllDrivers());
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/home-agent");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }
        return mv;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/new-shipments-center")
    public ModelAndView getAllNewShipments(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("new-shipments-center.jsp");
            mv.addObject("pending_packages", agentService.getAllNewShipmentsAdmin());
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/home-agent");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/accepted-shipments-center")
    public ModelAndView getAllAcceptedShipments(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("manage-accepted-shipments.jsp");
            mv.addObject("accepted_packages", agentService.getAllNewAcceptedShipmentsAdmin());
            //Get list of available driver to the specific service center to be assigned
            mv.addObject("available_driver_list", agentService.getAllAvailableDrivers());
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/home-agent");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/transit-shipments-center")
    public ModelAndView getAllTransitShipments(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("manage-in-transit.jsp");
            mv.addObject("transit_packages", agentService.getTransitPackages());
            //Get list of available driver to the specific service center to be assigned
            mv.addObject("available_driver_list", agentService.getAllAvailableDrivers());
        } catch (ExpressDeliveryException e) {
            mv.setViewName("redirect:/home-agent");
            redirectAttributes.addFlashAttribute("error", new APIException(e.getMessage()));
        }

        return mv;
    }

}
