package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverDetailRepository extends JpaRepository<DriverDetail, Integer> {

    List<DriverDetail> findByStatusAndUserServiceCentre(String status, ServiceCentre serviceCentre);
}
