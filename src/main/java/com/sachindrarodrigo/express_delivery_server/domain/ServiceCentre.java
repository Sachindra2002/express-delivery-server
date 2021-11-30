package com.sachindrarodrigo.express_delivery_server.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "service_centre")
public class ServiceCentre {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 45)
    private int centreId;

    @NotEmpty(message = "City is required")
    @Column(nullable = false, length = 20)
    private String city;

    @Column(length = 20)
    private String centre;

    @Column(length = 150)
    private String address;

    @JsonBackReference(value = "user-center")
    @OneToMany(mappedBy = "serviceCentre")
    private Set<User> users;

    @JsonBackReference(value = "center")
    @OneToMany(mappedBy = "serviceCentre")
    private List<Mail> mailList;

}
