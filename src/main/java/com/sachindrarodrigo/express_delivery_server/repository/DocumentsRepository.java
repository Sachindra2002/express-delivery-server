package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Documents;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentsRepository extends JpaRepository<Documents, Integer> {

    List<Documents> findByUserEquals(User user);
}
