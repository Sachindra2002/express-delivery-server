package com.sachindrarodrigo.express_delivery_server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    @NotEmpty(message = "status1 is required")
    @Column(nullable = false, length = 200)
    private String status1;

    @CreationTimestamp
    @Column(name = "status1date",nullable = false, length = 200)
    private Date status1Date;

    @NotEmpty(message = "status2 is required")
    @Column(nullable = false, length = 200)
    private String status2;

    @Column(name = "status2date",length = 200)
    private Date status2Date;

    @NotEmpty(message = "status3 is required")
    @Column(nullable = false, length = 200)
    private String status3;

    @Column(length = 200)
    private Date status3Date;

    @NotEmpty(message = "status4 is required")
    @Column(nullable = false, length = 200)
    private String status4;

    @Column(length = 200)
    private Date status4Date;

    @NotEmpty(message = "status5 is required")
    @Column(nullable = false, length = 200)
    private String status5;

    @Column(length = 200)
    private Date status5Date;

    @NotEmpty(message = "status6 is required")
    @Column(nullable = false, length = 200)
    private String status6;

    @Column(length = 200)
    private Date status6Date;

    @NotEmpty(message = "status7 is required")
    @Column(nullable = false, length = 200)
    private String status7;

    @Column(length = 200)
    private Date status7Date;

    @NotEmpty(message = "status8 is required")
    @Column(nullable = false, length = 200)
    private String status8;

    @Column(length = 200)
    private Date status8Date;

    @NotEmpty(message = "status9 is required")
    @Column(nullable = false, length = 200)
    private String status9;

    @Column(length = 200)
    private Date status9Date;

    @NotEmpty(message = "status10 is required")
    @Column(nullable = false, length = 200)
    private String status10;

    @Column(length = 200)
    private Date status10Date;

    @NotEmpty(message = "status11 is required")
    @Column(nullable = false, length = 200)
    private String status11;

    @Column(length = 200)
    private Date status11Date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mailId")
    private Mail mail;

}
