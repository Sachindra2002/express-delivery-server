package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.domain.Vehicle;
import com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto;
import com.sachindrarodrigo.express_delivery_server.dto.VehicleDto;
import com.sachindrarodrigo.express_delivery_server.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<VehicleDto> getAvailableVehicles(){
        return vehicleRepository.findByStatusEquals("available").stream().map(this::mapDto).collect(Collectors.toList());
    }

    private VehicleDto mapDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getVehicleId(), vehicle.getVehicleNumber(), vehicle.getVehicleType());
    }
}
