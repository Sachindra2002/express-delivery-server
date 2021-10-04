package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private int mailId;
    private String pickupAddress,
            senderAddress,
            receiverNumber,
            senderNumber,
            receiverEmail,
            senderEmail,
            receiverCity,
            senderCity,
            parcelType,
            weight,
            pieces,
            paymentMethod,
            date,
            time,
            totalCost,
            status;

}

