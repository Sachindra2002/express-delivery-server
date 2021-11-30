package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ServiceCenterDto {
    private int centreId;
    private String city;
    private String center;
    private String address;
    private Set<User> users;
    private List<Mail> mailList;

    public ServiceCenterDto(int centreId, String city, String center, String address) {
        this.centreId = centreId;
        this.city = city;
        this.center = center;
        this.address = address;
    }
}
