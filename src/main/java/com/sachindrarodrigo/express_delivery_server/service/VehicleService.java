package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Vehicle;
import com.sachindrarodrigo.express_delivery_server.dto.VehicleDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public void addVehicle(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Optional<Vehicle> existing = vehicleRepository.findByVehicleNumberEquals(vehicleDto.getVehicleNumber());

        if (existing.isPresent()) {
            throw new ExpressDeliveryException("Vehicle number already exists");
        }

        Vehicle vehicle = map(vehicleDto);
        vehicleRepository.save(vehicle);
    }

    private Vehicle map(VehicleDto vehicleDto) {

        return Vehicle.builder().vehicleType(vehicleDto.getVehicleType())
                .vehicleNumber(vehicleDto.getVehicleNumber())
                .status("available").build();
    }

    public void deleteVehicle(VehicleDto vehicleDto){
        vehicleRepository.deleteById(vehicleDto.getVehicleId());
    }

    public void setBlacklist(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle Not found!"));

        vehicle.setStatus("Blacklisted");
        vehicleRepository.save(vehicle);
    }

    public void removeBlacklist(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle Not found!"));

        vehicle.setStatus("available");
        vehicleRepository.save(vehicle);
    }

    public List<VehicleDto> getAvailableVehicles() {
        return vehicleRepository.findByStatusEquals("available").stream().map(this::mapDto).collect(Collectors.toList());
    }

    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(this::mapDto).collect(Collectors.toList());
    }

    private VehicleDto mapDto(Vehicle vehicle) {
        return new VehicleDto(vehicle.getVehicleId(), vehicle.getVehicleNumber(), vehicle.getVehicleType(), vehicle.getStatus(), vehicle.getDriverDetail());
    }
}
