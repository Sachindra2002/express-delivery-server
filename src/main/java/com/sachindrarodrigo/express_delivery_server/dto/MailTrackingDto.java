package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailTrackingDto {
    int mailId;
    int trackingId;
    String driver;
    String status;
    String deliveryPartner;
}
