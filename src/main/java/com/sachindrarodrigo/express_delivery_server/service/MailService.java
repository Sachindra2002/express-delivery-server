package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    @Transactional
    public MailDto sendMail(MailDto dto) throws ExpressDeliveryException {

        Mail mail = map(dto);

        mailRepository.save(mail);
        return dto;
    }

    private Mail map(MailDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //Find user from database
        Optional<User> userOptional = userRepository.findById(auth.getPrincipal().toString());
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Mail.builder().pickupAddress(dto.getPickupAddress())
                .receiverAddress(dto.getSenderAddress())
                .senderPhoneNumber(user.getPhoneNumber())
                .receiverPhoneNumber(dto.getReceiverNumber())
                .senderEmail(auth.getPrincipal().toString())
                .receiverEmail(dto.getReceiverEmail())
                .senderCity(user.getLocation())
                .receiverCity(dto.getReceiverCity())
                .parcelType(dto.getParcelType())
                .weight(dto.getWeight())
                .pieces(dto.getPieces())
                .paymentMethod(dto.getPaymentMethod())
                .date(dto.getDate())
                .time(dto.getTime())
                .totalCost(dto.getTotalCost())
                .status("Processing").build();
    }
}
