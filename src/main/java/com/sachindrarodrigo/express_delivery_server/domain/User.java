package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

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
    @Column(nullable = false, length = 20)
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

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Mail> mails;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DriverDetail driverDetail;

    @ManyToOne
    @JoinColumn(name = "centreId")
    private ServiceCentre serviceCentre;

}
