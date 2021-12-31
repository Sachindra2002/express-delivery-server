package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Inquiry;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer> {

    List<Inquiry> findByUserEquals(User user);

    List<Inquiry> findAllByStatusEquals(String status);
}
