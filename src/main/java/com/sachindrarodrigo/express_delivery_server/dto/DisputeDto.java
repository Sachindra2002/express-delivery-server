package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisputeDto {

    private int disputeId;
    private String disputeType;
    private String description;
    private Mail mail;

    public DisputeDto(int disputeId, String disputeType, String description, Mail mail) {
        this.disputeId = disputeId;
        this.disputeType = disputeType;
        this.description = description;
        this.mail = mail;
    }
}
