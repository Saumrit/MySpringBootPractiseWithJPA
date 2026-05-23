package com.saumrit.myspringbootwithjpa.repository;

import com.saumrit.myspringbootwithjpa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySubjectRepository extends JpaRepository<Subject,String> {
}
