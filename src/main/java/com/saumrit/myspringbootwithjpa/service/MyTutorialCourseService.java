package com.saumrit.myspringbootwithjpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.myspringbootwithjpa.dto.TutorialCourseRequestDTO;
import com.saumrit.myspringbootwithjpa.dto.TutorialCourseResponseDTO;
import com.saumrit.myspringbootwithjpa.model.TutorialCourse;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.repository.MyTutorialCourseRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public class MyTutorialCourseService {

    private final MyTutorialCourseRepository myTutorialCourseRepository;
    private final ObjectMapper objectMapper;

    public MyTutorialCourseService(MyTutorialCourseRepository myTutorialCourseRepository, ObjectMapper objectMapper) {
        this.myTutorialCourseRepository = myTutorialCourseRepository;
        this.objectMapper = objectMapper;
    }

    public TutorialCourseResponseDTO addTutorialCourse(TutorialCourseRequestDTO tutorialCourseRequestDTO, CourseCategory category){
        TutorialCourse tutorialCourse= objectMapper.convertValue(tutorialCourseRequestDTO, TutorialCourse.class);
        tutorialCourse.setCategory(category);
        TutorialCourse result= myTutorialCourseRepository.save(tutorialCourse);
        return objectMapper.convertValue(result, TutorialCourseResponseDTO.class);
    }

    public TutorialCourse fetchCourse(String name){
        TutorialCourse tutorialCourse= myTutorialCourseRepository.findByCourseName(name);
        if(ObjectUtils.isEmpty(tutorialCourse))
            return new TutorialCourse();
        return tutorialCourse;
    }
}
