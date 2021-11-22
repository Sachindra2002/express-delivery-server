package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @ManyToOne
    @JoinColumn(name = "mailId", nullable = false)
    private Mail mail;
}
