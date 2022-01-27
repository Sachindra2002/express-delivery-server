package com.sachindrarodrigo.express_delivery_server.dto;

import com.sachindrarodrigo.express_delivery_server.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class InquiryDto {

    private int inquiryId;
    private String inquiryType;
    private String description;
    private String status;
    private String response;
    private Date createdAt;
    private User user;

    public InquiryDto(int inquiryId, String inquiryType, String description, String status, Date createdAt, String response, User user) {
        this.inquiryId = inquiryId;
        this.inquiryType = inquiryType;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.response = response;
        this.user = user;
    }
}
