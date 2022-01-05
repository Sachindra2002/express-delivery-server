package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUserRoleEquals(String userRole);

    List<User> findByUserRoleEqualsAndDriverDetail_StatusAndServiceCentre(String userRole, String status, ServiceCentre serviceCentre);

    User findByDriverDetail_DriverId(int driverId);

    List<User> findAllByDriverDetail_Status(String status);

    List<User> findAllByServiceCentre(ServiceCentre serviceCentre);
}
