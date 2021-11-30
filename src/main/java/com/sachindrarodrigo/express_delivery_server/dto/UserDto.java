package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String location;
    private String phoneNumber;
    private String password;
    private String userRole;
    private List<Mail> mailList;
    private List<Documents> documents;
    private ServiceCentre serviceCentre;
    private DriverDetail driverDetail;

    public UserDto(String email, String firstName, String lastName, String location, String phoneNumber, String userRole, ServiceCentre serviceCentre, DriverDetail driverDetail) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.serviceCentre = serviceCentre;
        this.driverDetail = driverDetail;
    }

    public UserDto(String email, String firstName, String lastName, String location, String phoneNumber, String password, String userRole, List<Mail> mailList, List<Documents> documents, ServiceCentre serviceCentre, DriverDetail driverDetail) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRole = userRole;
        this.mailList = mailList;
        this.documents = documents;
        this.serviceCentre = serviceCentre;
        this.driverDetail = driverDetail;
    }
}
