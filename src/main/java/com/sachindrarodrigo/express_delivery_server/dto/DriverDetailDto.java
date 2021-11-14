package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverDetailDto {
    private int driverId;
    private String status;
    private User user;
    private String NIC;
    private String DOB;
    private String address;

    public DriverDetailDto(int driverId, String status, User user, String NIC, String DOB, String address) {
        this.driverId = driverId;
        this.status = status;
        this.user = user;
        this.NIC = NIC;
        this.DOB = DOB;
        this.address = address;
    }
}
