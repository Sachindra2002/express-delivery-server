package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisputeDto {
    String mailId;
    String disputeType;
    String description;

    public DisputeDto(String mailId, String disputeType, String description) {
        this.mailId = mailId;
        this.disputeType = disputeType;
        this.description = description;
    }
}
