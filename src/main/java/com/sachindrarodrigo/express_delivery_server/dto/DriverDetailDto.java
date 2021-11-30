package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import com.sachindrarodrigo.express_delivery_server.domain.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DriverDetailDto {
    private int driverId;
    private String status;
    private User user;
    private Vehicle vehicle;
    private String NIC;
    private String DOB;
    private String address;
    private List<Mail> mailList;

    public DriverDetailDto(int driverId, String status, User user, Vehicle vehicle, String NIC, String DOB, String address) {
        this.driverId = driverId;
        this.status = status;
        this.user = user;
        this.vehicle = vehicle;
        this.NIC = NIC;
        this.DOB = DOB;
        this.address = address;
    }

    public DriverDetailDto(int driverId, String status, User user, Vehicle vehicle, String NIC, String DOB, String address, List<Mail> mailList) {
        this.driverId = driverId;
        this.status = status;
        this.user = user;
        this.vehicle = vehicle;
        this.NIC = NIC;
        this.DOB = DOB;
        this.address = address;
        this.mailList = mailList;
    }
}
