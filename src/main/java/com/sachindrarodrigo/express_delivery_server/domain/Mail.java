package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
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
    @JsonManagedReference(value = "user")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "email", nullable = false)
    private User user;

    @NotEmpty(message = "Receiver address is required")
    @Column(nullable = false, length = 60)
    private String receiverAddress;

    @NotEmpty(message = "Receiver First name is required")
    @Column(nullable = false, length = 40)
    private String receiverFirstName;

    @NotEmpty(message = "Receiver last name is required")
    @Column(nullable = false, length = 40)
    private String receiverLastName;

    @NotEmpty(message = "Receiver Phone number is required")
    @Column(nullable = false, length = 20)
    @Pattern(regexp="(^$|[0-9]{10})",message = "Incorrect Mobile Number")
    private String receiverPhoneNumber;

    @NotEmpty(message = "Receiver email is required")
    @Column(nullable = false, length = 60)
    @Email(message = "Email is not valid")
    private String receiverEmail;

    @NotEmpty(message = "Receiver city is required")
    @Column(nullable = false, length = 70)
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

    @OneToMany(mappedBy = "mail", fetch = FetchType.LAZY)
    private Set<Dispute> disputes;

    @JsonManagedReference(value = "tracking")
    @OneToOne(mappedBy = "mail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MailTracking mailTracking;

    @JsonIgnore
    @JsonManagedReference(value = "driver")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "driverId")
    private DriverDetail driverDetail;

    @Column(length = 20)
    private String transportationStatus;

    @JsonIgnore
    @JsonManagedReference(value = "center")
    @ManyToOne
    @JoinColumn(name = "centreId")
    private ServiceCentre serviceCentre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy")
    @Column(length = 50)
    private LocalDate dropOffDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

}
