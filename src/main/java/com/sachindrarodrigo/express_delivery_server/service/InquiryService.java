package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Dispute;
import com.sachindrarodrigo.express_delivery_server.domain.Inquiry;
import com.sachindrarodrigo.express_delivery_server.dto.DisputeDto;
import com.sachindrarodrigo.express_delivery_server.dto.InquiryDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.InquiryRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InquiryService {

    private InquiryRepository inquiryRepository;
    private UserRepository userRepository;

    @Transactional
    public List<InquiryDto> getAllInquiries() {
        return inquiryRepository.findAllByStatusEquals("Pending").stream().map(this::mapInquiries).collect(Collectors.toList());
    }

    @Transactional
    public List<InquiryDto> getEveryInquiries() {
        return inquiryRepository.findAll().stream().map(this::mapInquiries).collect(Collectors.toList());
    }

    public void addInquiry(InquiryDto inquiryDto) throws ExpressDeliveryException {
        Inquiry inquiry = map(inquiryDto);
        inquiryRepository.save(inquiry);
    }

    public void respondInquiry(InquiryDto inquiryDto) throws ExpressDeliveryException {
        Inquiry inquiry = inquiryRepository.findById(inquiryDto.getInquiryId()).orElseThrow(() -> new ExpressDeliveryException("Inquiry not found"));
        inquiry.setResponse(inquiryDto.getResponse());
        inquiry.setStatus("responded");
        inquiryRepository.save(inquiry);
    }

    private Inquiry map(InquiryDto inquiryDto) throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));

        return Inquiry.builder().inquiryType(inquiryDto.getInquiryType())
                .description(inquiryDto.getDescription())
                .status("Pending")
                .user(_user)
                .build();
    }

    public List<InquiryDto> getCustomerInquiries() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        return inquiryRepository.findByUserEquals(_user).stream().map(this::mapInquiries).collect(Collectors.toList());
    }

    //Method to map data transfer object to domain class
    private InquiryDto mapInquiries(Inquiry inquiry) {
        return new InquiryDto(inquiry.getInquiryId(), inquiry.getInquiryType(), inquiry.getDescription(), inquiry.getStatus(), inquiry.getCreatedAt(), inquiry.getResponse(),inquiry.getUser());
    }
}
