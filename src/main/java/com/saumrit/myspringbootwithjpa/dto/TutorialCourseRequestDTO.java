package com.saumrit.myspringbootwithjpa.dto;

import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialCourseRequestDTO {

    private String courseName;
    private String tutorialWebsite;
    private String tutorName;
    private Boolean paidStatus;
}
