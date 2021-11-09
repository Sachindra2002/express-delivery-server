package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import com.sachindrarodrigo.express_delivery_server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MailDto {
    private int mailId;
    private String pickupAddress,
            receiverAddress,
            receiverPhoneNumber,
            receiverEmail,
            receiverCity,
            parcelType,
            weight,
            pieces,
            paymentMethod,
            date,
            time,
            totalCost,
            status,
            description;
    private User user;
    private MailTracking mailTracking;
    private Date createdAt;

    public MailDto(int mailId, String pickupAddress, String receiverAddress, String receiverPhoneNumber, String receiverEmail, String receiverCity, String parcelType, String weight, String pieces, String paymentMethod, String date, String time, String totalCost, String status, String description, User user, MailTracking mailTracking, Date createdAt) {
        this.mailId = mailId;
        this.pickupAddress = pickupAddress;
        this.receiverAddress = receiverAddress;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverEmail = receiverEmail;
        this.receiverCity = receiverCity;
        this.parcelType = parcelType;
        this.weight = weight;
        this.pieces = pieces;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.time = time;
        this.totalCost = totalCost;
        this.status = status;
        this.description = description;
        this.user = user;
        this.mailTracking = mailTracking;
        this.createdAt = createdAt;
    }
}

