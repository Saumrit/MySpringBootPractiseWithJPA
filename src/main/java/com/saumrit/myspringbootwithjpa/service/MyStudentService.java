package com.saumrit.myspringbootwithjpa.service;

import com.saumrit.myspringbootwithjpa.model.Student;
import com.saumrit.myspringbootwithjpa.repository.MyStudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyStudentService {

    public final MyStudentRepository myStudentRepository ;

    public MyStudentService( MyStudentRepository myStudentRepository) {
        this.myStudentRepository=myStudentRepository;
    }

    public List<Student> fetchAllStudent(){
        return myStudentRepository.findAll();
    }

    public List<Student> fetchAllStudentSortedBy(String sort_property_name){
        Sort sort= Sort.by(sort_property_name);
        return myStudentRepository.findAll(sort);
    }

    public void deleteStudent(String id){
         myStudentRepository.deleteById(id);
    }

    public void addSingleStudent(Student student){
        myStudentRepository.save(student);
    }

    public Student updateSingleStudent(Student student){
        return myStudentRepository.save(student);
    }


}
