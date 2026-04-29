package com.saumrit.myspringbootwithjpa.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
public class StudentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long id;

    public String name;
    public String standard;
    public Integer age;
    @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL},mappedBy = "theStudent")
    public Address address;

}
