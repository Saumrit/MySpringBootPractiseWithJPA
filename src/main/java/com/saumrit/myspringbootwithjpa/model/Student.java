package com.saumrit.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "student")
@Access(AccessType.FIELD)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;
    public String standard;
    public Integer age;
    @OneToOne(orphanRemoval = true,cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    public Address address;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "assignments_student_id",referencedColumnName = "studentId"),
            @JoinColumn(name = "assignments_subject_id",referencedColumnName ="subjectId" )
    })
    public Assignment assignment;

    private BioData bioData;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "awardsList")
    private List<String> awardsOwned;




}
