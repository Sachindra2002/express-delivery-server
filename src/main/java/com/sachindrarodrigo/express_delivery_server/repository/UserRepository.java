package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUserRoleEquals(String userRole);

    List<User> findByUserRoleEqualsAndDriverDetail_StatusAndServiceCentre(String userRole, String status, ServiceCentre serviceCentre);

    User findByDriverDetail_DriverId(int driverId);

    Optional<User> findByDriverDetail_NIC(String nic);

    Optional<User> findByPhoneNumberEquals(String phoneNumber);

    List<User> findAllByDriverDetail_Status(String status);

    List<User> findAllByServiceCentre(ServiceCentre serviceCentre);
}
