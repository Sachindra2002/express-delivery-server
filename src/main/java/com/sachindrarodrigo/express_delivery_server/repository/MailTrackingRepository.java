package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTrackingRepository extends JpaRepository<MailTracking, Integer> {
}
