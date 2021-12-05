package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token ,email, userRole, firstName, lastName;
}
