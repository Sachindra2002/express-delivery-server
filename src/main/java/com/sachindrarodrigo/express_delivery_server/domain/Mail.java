package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(exclude = "disputes")
@ToString(exclude = "disputes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "mails")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int mailId;

    @NotEmpty(message = "Pickup address is required")
    @Column(nullable = false, length = 60)
    private String pickupAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email", nullable = false)
    private User user;

    @NotEmpty(message = "Receiver address is required")
    @Column(nullable = false, length = 60)
    private String receiverAddress;

    @NotEmpty(message = "Receiver Phone number is required")
    @Column(nullable = false, length = 20)
    private String receiverPhoneNumber;

    @NotEmpty(message = "Receiver email is required")
    @Column(nullable = false, length = 60)
    private String receiverEmail;

    @NotEmpty(message = "Receiver city is required")
    @Column(nullable = false, length = 20)
    private String receiverCity;

    @NotEmpty(message = "Parcel type is required")
    @Column(nullable = false, length = 20)
    private String parcelType;

    @NotEmpty(message = "Weight is required")
    @Column(nullable = false, length = 20)
    private String weight;

    @NotEmpty(message = "Pieces is required")
    @Column(nullable = false, length = 20)
    private String pieces;

    @NotEmpty(message = "Payment method is required")
    @Column(nullable = false, length = 20)
    private String paymentMethod;

    @NotEmpty(message = "Preferred date is required")
    @Column(nullable = false, length = 20)
    private String date;

    @NotEmpty(message = "Time is required")
    @Column(nullable = false, length = 20)
    private String time;

    @NotEmpty(message = "Total cost is required")
    @Column(nullable = false, length = 20)
    private String totalCost;

    @NotEmpty(message = "status is required")
    @Column(nullable = false, length = 20)
    private String status;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false, length = 200)
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "mail", fetch = FetchType.LAZY)
    private Set<Dispute> disputes;

    @OneToOne(mappedBy = "mail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MailTracking mailTracking;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "driverId")
    private DriverDetail driverDetail;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

}
