package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(exclude = "mails")
@ToString(exclude = "mails")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "driver_detail")
public class DriverDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int driverId;

    @Column(length = 50)
    private String status;

    @JsonManagedReference(value = "user-driver")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private User user;

    @JsonManagedReference(value = "driver-vehicle")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;

    @NotEmpty(message = "NIC cannot be empty")
    @Column(length = 15, unique = true, nullable = false)
//    @Pattern(regexp="(^([0-9]{9}[x|XV]|[0-9]{12}))",message = "Incorrect NIC Number")
    private String NIC;

    @NotEmpty(message = "Date of birth cannot be empty")
    @Column(length = 10, nullable = false)
    private String DOB;

    @NotEmpty(message = "Address cannot be empty")
    @Column(length = 200, nullable = false)
    private String address;

    @JsonBackReference(value = "driver")
    @OneToMany(mappedBy = "driverDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mail> mails;


}
