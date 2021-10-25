package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
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

    public MailTrackingDto(Mail mail, int trackingId, String deliveryPartner, String driver, String status) {
    }
}
