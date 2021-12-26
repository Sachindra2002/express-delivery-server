package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class MailDto {
    private int mailId;
    private String pickupAddress,
            receiverAddress,
            receiverFirstName,
            receiverLastName,
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
    private Set<Dispute> disputes;
    private User user;
    private MailTracking mailTracking;
    private DriverDetail driverDetail;
    private String transportationStatus;
    private ServiceCentre serviceCentre;
    private String dropOffDate;
    private Date createdAt;

    public MailDto(int mailId, String pickupAddress, String receiverAddress, String receiverFirstName, String receiverLastName, String receiverPhoneNumber, String receiverEmail, String receiverCity, String parcelType, String weight, String pieces, String paymentMethod, String date, String time, String totalCost, String status, String description, User user, MailTracking mailTracking, ServiceCentre serviceCentre, String dropOffDate, Date createdAt) {
        this.mailId = mailId;
        this.pickupAddress = pickupAddress;
        this.receiverAddress = receiverAddress;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
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
        this.serviceCentre = serviceCentre;
        this.dropOffDate = dropOffDate;
        this.createdAt = createdAt;
    }

    public MailDto(int mailId, String pickupAddress, String receiverAddress, String receiverFirstName, String receiverLastName, String receiverPhoneNumber, String receiverEmail, String receiverCity, String parcelType, String weight, String pieces, String paymentMethod, String date, String time, String totalCost, String status, String description,  User user, MailTracking mailTracking, DriverDetail driverDetail, ServiceCentre serviceCentre, String dropOffDate, Date createdAt) {
        this.mailId = mailId;
        this.pickupAddress = pickupAddress;
        this.receiverAddress = receiverAddress;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
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
        this.driverDetail = driverDetail;
        this.serviceCentre = serviceCentre;
        this.dropOffDate = dropOffDate;
        this.createdAt = createdAt;
    }

    public MailDto(int mailId, String pickupAddress, String receiverAddress, String receiverFirstName, String receiverLastName, String receiverPhoneNumber, String receiverEmail, String receiverCity, String parcelType, String weight, String pieces, String paymentMethod, String date, String time, String totalCost, String status, String description, User user, MailTracking mailTracking, DriverDetail driverDetail, String transportationStatus, ServiceCentre serviceCentre, String dropOffDate, Date createdAt) {
        this.mailId = mailId;
        this.pickupAddress = pickupAddress;
        this.receiverAddress = receiverAddress;
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
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
        this.driverDetail = driverDetail;
        this.transportationStatus = transportationStatus;
        this.serviceCentre = serviceCentre;
        this.dropOffDate = dropOffDate;
        this.createdAt = createdAt;
    }
}

