package com.sachindrarodrigo.express_delivery_server.repository;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import com.sachindrarodrigo.express_delivery_server.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByStatusEquals(String status);

    Optional<Vehicle> findByVehicleNumberEquals(String vehicleNumber);

    Vehicle findByDriverDetail(DriverDetail driverDetail);
}
