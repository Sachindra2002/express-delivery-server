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
@Table(name = "inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int inquiryId;

    @NotEmpty(message = "Dispute type is required")
    @Column(nullable = false, length = 40)
    private String inquiryType;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false, length = 105)
    private String description;

    private String status;

    private String response;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "email", nullable = false)
    private User user;
}
