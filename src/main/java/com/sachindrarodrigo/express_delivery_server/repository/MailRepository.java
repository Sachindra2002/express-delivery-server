package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Integer> {

    List<Mail> findByReceiverEmailEquals(String receiverEmail);

    List<Mail> findByUserEquals(User user);

    List<Mail> findByServiceCentreAndStatusEquals(ServiceCentre serviceCentre, String status);

    List<Mail> findAllByServiceCentre(ServiceCentre serviceCentre);

    List<Mail> findByDriverDetailAndStatusEquals(DriverDetail driverDetail, String status);
}
