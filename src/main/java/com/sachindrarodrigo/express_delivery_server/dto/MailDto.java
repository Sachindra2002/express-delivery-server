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
            receiverAddress,
            senderPhoneNumber,
            receiverPhoneNumber,
            senderEmail,
            receiverEmail,
            senderCity,
            receiverCity,
            parcelType,
            weight,
            pieces,
            paymentMethod,
            date,
            time,
            totalCost,
            status;

}

