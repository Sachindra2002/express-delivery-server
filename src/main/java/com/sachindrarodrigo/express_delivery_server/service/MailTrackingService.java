package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import com.sachindrarodrigo.express_delivery_server.dto.MailTrackingDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailTrackingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class MailTrackingService {

    private final MailTrackingRepository mailTrackingRepository;
    private final MailRepository mailRepository;

    @Transactional
    public MailTracking getTrackingInfo(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(()-> new ExpressDeliveryException("Mail not found"));
        return mailTrackingRepository.findByMail(mail);
    }

    private MailTrackingDto mapDto(MailTracking mailTracking){
        return new MailTrackingDto(mailTracking.getMail(), mailTracking.getTrackingId(), mailTracking.getDeliveryPartner(), mailTracking.getDriver(), mailTracking.getStatus());
    }

}
