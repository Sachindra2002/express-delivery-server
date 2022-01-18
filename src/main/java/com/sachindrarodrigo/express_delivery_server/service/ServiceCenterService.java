package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceCenterService {

    private final ServiceCenterRepository serviceCenterRepository;

    public List<ServiceCenterDto> getAllServiceCenters() {
        return serviceCenterRepository.findAll().stream().map(this::mapDto).collect(Collectors.toList());
    }

    public ServiceCenterDto viewServiceCenter(ServiceCenterDto serviceCenterDto) throws ExpressDeliveryException {
        return serviceCenterRepository.findById(serviceCenterDto.getCentreId()).map(this::mapDto2).orElseThrow(() -> new ExpressDeliveryException("Center not found"));
    }

    public ServiceCenterDto addServiceCenter(ServiceCenterDto serviceCenterDto) throws ExpressDeliveryException {
        Optional<ServiceCentre> existing = Optional.ofNullable(serviceCenterRepository.findByCenterEquals(serviceCenterDto.getCenter()));

        if (existing.isPresent()) {
            throw new ExpressDeliveryException("Center Name already exists");
        }

        ServiceCentre serviceCentre = serviceCenterRepository.save(ServiceCentre.builder().address(serviceCenterDto.getAddress())
                .city(serviceCenterDto.getCity())
                .center(serviceCenterDto.getCenter()).build());


        ServiceCenterDto centerDto = new ServiceCenterDto();
        centerDto.setCentreId(serviceCentre.getCentreId());
        return centerDto;
    }

    public void removeServiceCenter(ServiceCenterDto serviceCenterDto) throws ExpressDeliveryException {
        ServiceCentre serviceCentre = serviceCenterRepository.findById(serviceCenterDto.getCentreId()).orElseThrow(() -> new ExpressDeliveryException("Center not found"));
        serviceCenterRepository.delete(serviceCentre);
    }

    private ServiceCenterDto mapDto(ServiceCentre serviceCentre) {
        return new ServiceCenterDto(serviceCentre.getCentreId(), serviceCentre.getCity(), serviceCentre.getCenter(), serviceCentre.getAddress());
    }

    private ServiceCenterDto mapDto2(ServiceCentre serviceCentre) {
        return new ServiceCenterDto(serviceCentre.getCentreId(), serviceCentre.getCity(), serviceCentre.getCenter(), serviceCentre.getAddress(), serviceCentre.getUsers(), serviceCentre.getMailList());
    }
}
