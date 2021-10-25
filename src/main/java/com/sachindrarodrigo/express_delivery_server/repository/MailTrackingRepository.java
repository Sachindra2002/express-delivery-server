package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailTrackingRepository extends JpaRepository<MailTracking, Integer> {

    MailTracking findByMail(Mail mail);
}
