package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.dto.DisputeDto;
import com.sachindrarodrigo.express_delivery_server.dto.InquiryDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.DisputeService;
import com.sachindrarodrigo.express_delivery_server.service.InquiryService;
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
public class CustomerWebController {

    private InquiryService inquiryService;
    private DisputeService disputeService;

    @GetMapping("/inquiries")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView viewInquiries(){
        ModelAndView mv = new ModelAndView();
        try{
            mv.addObject("inquiry_list", inquiryService.getCustomerInquiries());
            mv.setViewName("inquiries.jsp");
        }catch (ExpressDeliveryException e){
            mv.setViewName("inquiries.jsp");
        }
        return mv;
    }

    @GetMapping("/disputes")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView viewDisputes(){
        ModelAndView mv = new ModelAndView();
        try{
            mv.addObject("dispute_list", disputeService.getCustomerDisputes());
            mv.setViewName("disputes.jsp");
        }catch (ExpressDeliveryException e){
            mv.setViewName("disputes.jsp");
        }
        return mv;
    }

    @PostMapping("/add-inquiry")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView addInquiry(@RequestParam String inquiryType, @RequestParam String description, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView();
        try{
            InquiryDto inquiryDto = new InquiryDto();
            inquiryDto.setInquiryType(inquiryType);
            inquiryDto.setDescription(description);
            inquiryService.addInquiry(inquiryDto);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Inquiry added successfully"));
            mv.setViewName("redirect:/inquiries");
        }catch (ExpressDeliveryException e){
            redirectAttributes.addFlashAttribute("error", new SimpleMessageDto("Something went wrong!"));
            mv.setViewName("redirect:/inquiries");
        }
        return mv;
    }

    @PostMapping("/open-dispute")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView openDispute(@RequestParam int mailId, @RequestParam String disputeType, @RequestParam String description, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView();
        try{
            DisputeDto disputeDto = new DisputeDto();
            disputeDto.setDisputeType(disputeType);
            disputeDto.setDescription(description);
            disputeService.openDispute(disputeDto, mailId);
            redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Dispute opened successfully"));
            mv.setViewName("redirect:/home-customer");
        }catch (ExpressDeliveryException e){
            redirectAttributes.addFlashAttribute("error", new SimpleMessageDto("Something went wrong!"));
            mv.setViewName("redirect:/home-customer");
        }
        return mv;
    }
}
