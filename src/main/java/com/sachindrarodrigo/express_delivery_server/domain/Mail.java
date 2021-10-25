package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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

    @NotEmpty(message = "Receiver address is required")
    @Column(nullable = false, length = 60)
    private String receiverAddress;

    @NotEmpty(message = "Sender phone number is required")
    @Column(nullable = false, length = 20)
    private String senderPhoneNumber;

    @NotEmpty(message = "Receiver Phone number is required")
    @Column(nullable = false, length = 20)
    private String receiverPhoneNumber;

    @NotEmpty(message = "Sender email number is required")
    @Column(nullable = false, length = 60)
    private String senderEmail;

    @NotEmpty(message = "Receiver email is required")
    @Column(nullable = false, length = 60)
    private String receiverEmail;

    @NotEmpty(message = "Sender city is required")
    @Column(nullable = false, length = 20)
    private String senderCity;

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

    private Date createdAt;

    public void openDispute(Dispute dispute) {
        if (!disputes.contains(dispute)) {
            disputes.add(dispute);
        }
    }
}
