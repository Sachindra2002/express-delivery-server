package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DisputeDto {

    private int disputeId;
    private String disputeType;
    private String description;
    private String status;
    private String response;
    private Date createdAt;
    private Mail mail;

    public DisputeDto(int disputeId, String disputeType, String description, Mail mail) {
        this.disputeId = disputeId;
        this.disputeType = disputeType;
        this.description = description;
        this.mail = mail;
    }

    public DisputeDto(int disputeId, String disputeType, String description, String status, String response, Date createdAt, Mail mail) {
        this.disputeId = disputeId;
        this.disputeType = disputeType;
        this.description = description;
        this.status = status;
        this.response = response;
        this.createdAt = createdAt;
        this.mail = mail;
    }
}
