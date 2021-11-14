package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "documents")
public class Documents {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int documentId;

    @Column(length = 15)
    private String description;

    @Column(length = 15)
    private String documentType;

    @Column(length = 15)
    private String documentPath;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email", nullable = false)
    private User user;

}
