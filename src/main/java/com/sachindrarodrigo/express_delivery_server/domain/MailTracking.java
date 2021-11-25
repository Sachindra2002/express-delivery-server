package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(length = 100)
    private String driver;

    @NotEmpty(message = "delivery partner is required")
    @Column(nullable = false, length = 200)
    private String deliveryPartner;

    @Column(length = 100)
    private String status1;

    @CreationTimestamp
    @Column(name = "status1date",length = 50)
    private Date status1Date;

    @Column(length = 100)
    private String status2;

    @Column(name = "status2date",length = 50)
    private Date status2Date;

    @Column(length = 100)
    private String status3;

    @Column(length = 50)
    private Date status3Date;

    @Column(length = 100)
    private String status4;

    @Column(length = 50)
    private Date status4Date;

    @Column(length = 100)
    private String status5;

    @Column(length = 50)
    private Date status5Date;

    @Column(length = 100)
    private String status6;

    @Column(length = 50)
    private Date status6Date;

    @Column(length = 100)
    private String status7;

    @Column(length = 50)
    private Date status7Date;

    @Column(length = 100)
    private String status8;

    @Column(length = 50)
    private Date status8Date;

    @Column(length = 100)
    private String status9;

    @Column(length = 50)
    private Date status9Date;

    @Column(length = 100)
    private String status10;

    @Column(length = 50)
    private Date status10Date;

    @Column(length = 100)
    private String status11;

    @Column(length = 50)
    private Date status11Date;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mailId")
    private Mail mail;

}
