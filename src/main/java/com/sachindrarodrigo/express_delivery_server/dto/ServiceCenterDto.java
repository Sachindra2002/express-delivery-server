package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceCenterDto {
    private int centreId;
    private String city;
    private String center;
    private String address;

    public ServiceCenterDto(int centreId, String city, String center, String address) {
        this.centreId = centreId;
        this.city = city;
        this.center = center;
        this.address = address;
    }
}
