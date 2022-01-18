package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverDetailRepository extends JpaRepository<DriverDetail, Integer> {

    List<DriverDetail> findByStatusAndUserServiceCentre(String status, ServiceCentre serviceCentre);

    DriverDetail findByUserEquals(User user);

    Optional<DriverDetail> findByNICEquals(String nic);
}
