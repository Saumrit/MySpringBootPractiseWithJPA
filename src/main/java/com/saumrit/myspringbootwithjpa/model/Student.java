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

    @ManyToMany
    @JoinTable(name = "ONLINE_COURSE_ENROLLMENT",
    joinColumns = @JoinColumn(name = "student_ID", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_Name", referencedColumnName = "courseName"))
    private List<TutorialCourse> tutorialCourses;
    //As i can see here , for courses, i used the name instead of ID
    //Since name and Id Both are Unique in my case
    //No Course pf same category will have same name
    //for eg. Two SpringBoot courses are there :
    //1> SpringBoot By Mr. Saumrit
    //2> Chappar-Faad SpringBoot By Ms. DunDunLisa



}
