package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "disputes")
public class Dispute {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int disputeId;

    @NotEmpty(message = "Dispute type is required")
    @Column(nullable = false, length = 40)
    private String disputeType;

    @NotEmpty(message = "Description is required")
    @Column(nullable = false, length = 105)
    private String description;

    private String status;

    private String response;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @JsonBackReference(value = "dispute")
    @ManyToOne
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;
}
