package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCenterRepository extends JpaRepository<ServiceCentre, Integer> {

    ServiceCentre findByCenterEquals(String serviceCenter);

    ServiceCentre findByCityEquals(String city);
}
