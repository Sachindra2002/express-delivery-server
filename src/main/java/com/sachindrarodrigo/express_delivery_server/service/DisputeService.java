package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Dispute;
import com.sachindrarodrigo.express_delivery_server.domain.Inquiry;
import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.DisputeDto;
import com.sachindrarodrigo.express_delivery_server.dto.InquiryDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DisputeRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DisputeService {

    private final DisputeRepository disputeRepository;
    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<DisputeDto> getAllDisputes() {
        return disputeRepository.findAllByStatusEquals("Pending").stream().map(this::mapDisputes).collect(Collectors.toList());
    }

    @Transactional
    public List<DisputeDto> getEveryDispute() {
        return disputeRepository.findAll().stream().map(this::mapDisputes).collect(Collectors.toList());
    }

    public void openDispute(DisputeDto disputeDto, int mailId) throws ExpressDeliveryException {
        Dispute dispute = map(disputeDto, mailId);
        disputeRepository.save(dispute);
    }

    public void respondDispute(DisputeDto disputeDto) throws ExpressDeliveryException {
        Dispute dispute = disputeRepository.findById(disputeDto.getDisputeId()).orElseThrow(() -> new ExpressDeliveryException("Dispute not found"));
        dispute.setResponse(disputeDto.getResponse());
        dispute.setStatus("responded");
        disputeRepository.save(dispute);
    }

    public List<DisputeDto> getCustomerDisputes() throws ExpressDeliveryException {
        //User object from security context holder to obtain current user
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //If user is not found
        com.sachindrarodrigo.express_delivery_server.domain.User _user = userRepository.findById(user.getUsername()).orElseThrow(() -> new ExpressDeliveryException("User not found"));
        return disputeRepository.findByMail_User(_user).stream().map(this::mapDisputes).collect(Collectors.toList());
    }

    private Dispute map(DisputeDto disputeDto, int mailId) throws ExpressDeliveryException {

        Mail mail = mailRepository.findById(mailId).orElseThrow(() -> new ExpressDeliveryException("Mail not found"));

        return Dispute.builder().disputeType(disputeDto.getDisputeType())
                .description(disputeDto.getDescription())
                .status("Pending")
                .mail(mail)
                .build();
    }

    //Method to map data transfer object to domain class
    private DisputeDto mapDisputes(Dispute dispute) {
        return new DisputeDto(dispute.getDisputeId(), dispute.getDisputeType(), dispute.getDescription(), dispute.getStatus(), dispute.getResponse(), dispute.getCreatedAt(), dispute.getMail());
    }
}

