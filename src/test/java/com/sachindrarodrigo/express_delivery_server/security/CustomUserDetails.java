package com.sachindrarodrigo.express_delivery_server.security;

import lombok.Data;

@Data
public class CustomUserDetails {
    private String name;
    private String username;

    public CustomUserDetails(String name, String username) {
        this.name = name;
        this.username = username;
    }

}
