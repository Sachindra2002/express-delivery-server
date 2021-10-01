package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SimpleMesageDto {
    private String message;
    private HttpStatus type;

    public SimpleMesageDto(String message) {
        this.message = message;
    }

}
