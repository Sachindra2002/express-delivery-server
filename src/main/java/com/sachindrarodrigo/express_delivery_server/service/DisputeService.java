package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Dispute;
import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.DisputeDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.DisputeRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DisputeService {
    private final DisputeRepository disputeRepository;
    private final UserRepository userRepository;
    private final MailRepository mailRepository;

    public DisputeDto openDispute(DisputeDto dto) throws ExpressDeliveryException {
        Dispute dispute = map(dto);
        disputeRepository.save(dispute);
        return dto;
    }

    private Dispute map(DisputeDto dto) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(dto.getMailId()).orElseThrow(()-> new ExpressDeliveryException("Mail not found"));
        return Dispute.builder().mail(mail)
                .description(dto.getDescription())
                .disputeType(dto.getDisputeType()).build();
    }
}
