package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.ServiceCentre;
import com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto;
import com.sachindrarodrigo.express_delivery_server.repository.ServiceCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceCenterService {

    private final ServiceCenterRepository serviceCenterRepository;

    public List<ServiceCenterDto> getAllServiceCenters(){
        return serviceCenterRepository.findAll().stream().map(this::mapDto).collect(Collectors.toList());
    }

    private ServiceCenterDto mapDto(ServiceCentre serviceCentre) {
        return new ServiceCenterDto(serviceCentre.getCentreId(), serviceCentre.getCity(), serviceCentre.getCenter(), serviceCentre.getAddress());
    }
}
