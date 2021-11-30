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
    private Long fileSize;
    private User user;

    public DocumentsDto(int documentId, String description, String fileName, Long fileSize, User user) {
        this.documentId = documentId;
        this.description = description;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.user = user;
    }
}
