package com.saumrit.myspringbootwithjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lecture")
@Access(AccessType.FIELD)
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private Subject subject;

    @OneToMany(mappedBy = "lecture")
    private List<Assignment> theAssignmentsList;
}
