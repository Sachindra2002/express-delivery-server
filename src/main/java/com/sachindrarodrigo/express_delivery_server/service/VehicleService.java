package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Vehicle;
import com.sachindrarodrigo.express_delivery_server.dto.VehicleDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleDto addVehicle(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Optional<Vehicle> existing = vehicleRepository.findByVehicleNumberEquals(vehicleDto.getVehicleNumber());

        if (existing.isPresent()) {
            throw new ExpressDeliveryException("Vehicle number already exists");
        }

        Vehicle vehicle = map(vehicleDto);
        vehicleRepository.save(vehicle);
        VehicleDto dto = new VehicleDto();
        dto.setVehicleId(vehicle.getVehicleId());
        return dto;
    }

    private Vehicle map(VehicleDto vehicleDto) {

        return Vehicle.builder().vehicleType(vehicleDto.getVehicleType())
                .vehicleNumber(vehicleDto.getVehicleNumber())
                .status("available").build();
    }

    public void deleteVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressionException("Vehicle not found"));
        vehicleRepository.delete(vehicle);
//        vehicleRepository.deleteById(vehicleDto.getVehicleId());
    }

    public VehicleDto setBlacklist(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle Not found!"));

        vehicle.setStatus("Blacklisted");
        vehicleRepository.save(vehicle);
        return vehicleDto;
    }

    public VehicleDto removeBlacklist(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle Not found!"));

        vehicle.setStatus("available");
        vehicleRepository.save(vehicle);
        return vehicleDto;
    }

    public VehicleDto setVehicleAvailable(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle Not found!"));

        vehicle.setStatus("available");
        vehicleRepository.save(vehicle);
        return vehicleDto;
    }

    public VehicleDto setVehicleUnAvailable(VehicleDto vehicleDto) throws ExpressDeliveryException {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.getVehicleId()).orElseThrow(() -> new ExpressDeliveryException("Vehicle Not found!"));

        vehicle.setStatus("taken");
        vehicleRepository.save(vehicle);
        VehicleDto dto = new VehicleDto();
        dto.setVehicleId(vehicle.getVehicleId());
        return dto;
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
