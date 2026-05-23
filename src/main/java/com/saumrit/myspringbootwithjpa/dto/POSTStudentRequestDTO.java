package com.saumrit.myspringbootwithjpa.dto;


import com.saumrit.myspringbootwithjpa.model.TutorialCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class POSTStudentRequestDTO {

    public String name;
    public String standard;
    public Integer age;
    public AddressDTO addressDTO;
//    public Assignment assignment;
//    private BioData bioData;
//    private List<String> awardsOwned;
    //private List<TutorialCourse> tutorialCourses;



}
