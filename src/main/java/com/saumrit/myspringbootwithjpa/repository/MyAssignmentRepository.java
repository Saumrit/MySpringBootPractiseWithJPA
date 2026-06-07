package com.saumrit.myspringbootwithjpa.repository;

import com.saumrit.myspringbootwithjpa.model.Assignment;
import com.saumrit.myspringbootwithjpa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyAssignmentRepository extends JpaRepository<Assignment,String> {

}
