package com.sachindrarodrigo.express_delivery_server.dto;


import com.sachindrarodrigo.express_delivery_server.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentsDto {
    private int documentId;
    private String description;
    private String fileName;
    private String fileSize;
    private User user;
}
