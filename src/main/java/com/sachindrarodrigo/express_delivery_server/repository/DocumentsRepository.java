package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepository extends JpaRepository<Documents, Integer> {
}
