package com.saumrit.myspringbootwithjpa.repository;

import com.saumrit.myspringbootwithjpa.model.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyStudentRepository extends JpaRepository<StudentDTO, String> {

    List<StudentDTO> findByName(String name);
}
