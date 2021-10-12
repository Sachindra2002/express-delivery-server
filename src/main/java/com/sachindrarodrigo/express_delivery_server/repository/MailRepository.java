package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Integer> {

    List<Mail> findByReceiverEmailEquals(String receiverEmail);

    List<Mail> findBySenderEmailEquals(String senderEmail);

}
