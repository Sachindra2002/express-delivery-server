package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MailTrackingDto {
    int mailId;
    int trackingId;
    String driver;
    String status1;
    Date status1Date;
    String status2;
    Date status2Date;
    String status3;
    String status3Date;
    String status4;
    String status4Date;
    String status5;
    String status5Date;
    String status6;
    String status6Date;
    String deliveryPartner;

    public MailTrackingDto(Mail mail, int trackingId, String driver, String status1, Date status1Date, String status2, Date status2Date, String status3, String status3Date, String status4, String status4Date, String status5, String status5Date, String status6, String status6Date, String deliveryPartner) {

    }
}
