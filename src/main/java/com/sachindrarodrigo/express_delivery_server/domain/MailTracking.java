package com.sachindrarodrigo.express_delivery_server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "mail_tracking")
public class MailTracking {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int trackingId;

    @NotEmpty(message = "driver name is required")
    @Column(nullable = false, length = 200)
    private String driver;

    @NotEmpty(message = "delivery partner is required")
    @Column(nullable = false, length = 200)
    private String deliveryPartner;

    @NotEmpty(message = "status is required")
    @Column(nullable = false, length = 200)
    private String status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "mailId")
    private Mail mail;

}
