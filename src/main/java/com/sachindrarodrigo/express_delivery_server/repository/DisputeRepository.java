package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Dispute;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisputeRepository extends JpaRepository<Dispute, Integer> {

    List<Dispute> findAllByStatusEquals(String status);

    List<Dispute> findByMail_User(User user);
}
