package com.assignment.dataapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@SequenceGenerator(name = "sequence", sequenceName = "id_seq",allocationSize = 1)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    @Column(name = "customer_ref")
    private String customerRef;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "address_line1")
    private String addressLineOne;

    @Column(name = "address_line2")
    private String addressLineTwo;

    @Column(name = "town")
    private String town;

    @Column(name = "county")
    private String county;

    @Column(name = "country")
    private String country;

    @Column(name = "postcode")
    private String postcode;
}

