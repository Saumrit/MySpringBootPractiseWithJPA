package com.saumrit.myspringbootwithjpa.repository;

import com.saumrit.myspringbootwithjpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyStudentRepository extends JpaRepository<Student, String> {

    List<Student> findByName(String name);
}
