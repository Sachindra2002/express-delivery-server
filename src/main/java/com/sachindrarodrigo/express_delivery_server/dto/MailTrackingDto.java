package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MailTrackingDto {
    private Mail mailId;
    private int trackingId;
    private String driver;
    private String status1;
    private Date status1Date;
    private String status2;
    private Date status2Date;
    private String status3;
    private Date status3Date;
    private String status4;
    private Date status4Date;
    private String status5;
    private Date status5Date;
    private String status6;
    private Date status6Date;
    private String status7;
    private Date status7Date;
    private String status8;
    private Date status8Date;
    private String status9;
    private Date status9Date;
    private String status10;
    private Date status10Date;
    private String status11;
    private Date status11Date;
    private String deliveryPartner;

    public MailTrackingDto(Mail mailId, int trackingId, String driver, String status1, Date status1Date, String status2, Date status2Date, String status3, Date status3Date, String status4, Date status4Date, String status5, Date status5Date, String status6, Date status6Date, String status7, Date status7Date, String status8, Date status8Date, String status9, Date status9Date, String deliveryPartner) {
        this.mailId = mailId;
        this.trackingId = trackingId;
        this.driver = driver;
        this.status1 = status1;
        this.status1Date = status1Date;
        this.status2 = status2;
        this.status2Date = status2Date;
        this.status3 = status3;
        this.status3Date = status3Date;
        this.status4 = status4;
        this.status4Date = status4Date;
        this.status5 = status5;
        this.status5Date = status5Date;
        this.status6 = status6;
        this.status6Date = status6Date;
        this.status7 = status7;
        this.status7Date = status7Date;
        this.status8 = status8;
        this.status8Date = status8Date;
        this.status9 = status9;
        this.status9Date = status9Date;
        this.deliveryPartner = deliveryPartner;
    }
}
