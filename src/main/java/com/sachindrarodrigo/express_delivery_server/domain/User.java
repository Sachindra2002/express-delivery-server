package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(exclude = "mails")
@ToString(exclude = "mails")
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
    @Email(message = "Email is not valid")
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
    @Pattern(regexp="(^$|[0-9]{10})",message = "Incorrect Mobile Number")
    private String phoneNumber;

    @NotEmpty(message = "Password is required")
    @Column(nullable = false, length = 200)
    @Length(min = 8, message = "Password must be at least contain 8 characters")
    private String password;

    @Transient
    private String matchingPassword;

    @NotEmpty(message = "User role is required")
    @Column(nullable = false, length = 10)
    private String userRole;

    @JsonBackReference(value = "user")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mail> mails;

    @JsonBackReference(value = "user-driver")
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DriverDetail driverDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Documents> documents;

    @JsonIgnore
    @JsonManagedReference(value = "user-center")
    @ManyToOne
    @JoinColumn(name = "centreId")
    private ServiceCentre serviceCentre;

    private Boolean isBanned;


}
