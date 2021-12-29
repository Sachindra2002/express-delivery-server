package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.DriverDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDto {
    private int vehicleId;
    private String vehicleNumber;
    private String vehicleType;
    private String status;
    private DriverDetail driverDetail;

    public VehicleDto(int vehicleId, String vehicleNumber, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }
}
