package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
@AllArgsConstructor
public class AgentWebController {

    private final AgentService agentService;

    @PostMapping("/accept-parcel")
    @PreAuthorize("hasRole('AGENT')")
    public ModelAndView cancelParcel(@RequestParam int mailId, RedirectAttributes redirectAttributes) throws ExpressDeliveryException {
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
    public ModelAndView assignDriver(@RequestParam int mailId, @RequestParam int driverId, @RequestParam String eDate, RedirectAttributes redirectAttributes) throws ParseException, ExpressDeliveryException {
        ModelAndView mv = new ModelAndView();
        agentService.assignDriver(mailId, driverId, eDate);
        redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Package Assigned Successfully"));
        mv.setViewName("redirect:/home-agent");
        return mv;
    }
}
