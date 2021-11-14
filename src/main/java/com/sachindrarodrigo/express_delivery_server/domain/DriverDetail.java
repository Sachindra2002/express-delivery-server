package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private User user;

    @Column(length = 15)
    private String NIC;

    @Column(length = 10)
    private String DOB;

    @Column(length = 200)
    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "driverDetail", fetch = FetchType.LAZY)
    private List<Mail> mails;


}
