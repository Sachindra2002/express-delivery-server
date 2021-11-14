package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDetailRepository extends JpaRepository<DriverDetail, Integer> {
}
