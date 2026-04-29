package com.saumrit.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long id;
    public String city;
    public String state;
    public Integer zipcode;
    public String country;
    @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL})
    @JoinColumn(name = "student_id")
    public StudentDTO theStudent;
}
