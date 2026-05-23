package com.saumrit.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "address")//just fpr bak-chodi , i used this annotation
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public String city;

    @Column(unique = true)
    public Long houseRegNumber;
    public String state;
    public Integer zipcode;
    public String country;
    @OneToOne(orphanRemoval = false,cascade = {CascadeType.DETACH},mappedBy = "address")
    public Student theStudent;
}
