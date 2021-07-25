package com.sachindrarodrigo.express_delivery_server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @NotEmpty(message = "Email is required")
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @NotEmpty(message = "First name is required")
    @Column(nullable = false, length = 20)
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Column(nullable = false, length = 20)
    private String lastName;

    @NotEmpty(message = "Location is required")
    private String location;

    @NotEmpty(message = "Phone number is required")
    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @NotEmpty(message = "Password is required")
    @Column(nullable = false, length = 30)
    private String password;

    @NotEmpty(message = "User role is required")
    @Column(nullable = false, length = 10)
    private String userRole;

}
