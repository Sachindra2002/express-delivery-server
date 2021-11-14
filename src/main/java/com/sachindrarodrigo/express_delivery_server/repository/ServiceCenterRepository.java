package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCenterRepository extends JpaRepository<ServiceCentre, Integer> {

    ServiceCentre findByCentreEquals(String serviceCenter);
}
