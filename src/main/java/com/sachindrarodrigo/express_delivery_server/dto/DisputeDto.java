package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisputeDto {
    int mailId;
    String disputeType;
    String description;

    public DisputeDto(int mailId, String disputeType, String description) {
        this.mailId = mailId;
        this.disputeType = disputeType;
        this.description = description;
    }
}
