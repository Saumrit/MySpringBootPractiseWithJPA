package com.saumrit.myspringbootwithjpa.repository;

import com.saumrit.myspringbootwithjpa.model.Student;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MyStudentRepository extends JpaRepository<Student, String> {

    public List<Student> findByName(String name);
    public List<Student> findNRIStudentsFromGivenState(String stateName);

    @Query("select s from Student s where s.name = :theName or s.rollId= :theRollId " )
    public Student findByNameOrRollId(@Param("theName") String theName,@Param("theRollId") String theRollId, Limit limit);

    @Query("select s from Student s where s.name LIKE %?#{escape[0]}% escape ?#{escapeCharacter()} " )
    public Student searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(String name, Limit limit);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Student s set s.bioData.nriStatus= ?2 where s.rollId = ?1")
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    public int updateTheNRIStatusOFAnyStudent(@Param("roll") String rollId,@Param("nriStatusUpdate") Boolean status);

    @Query("SELECT s FROM Student s JOIN address a WHERE a.city= ?1 AND a.state= ?2")
    public List<Student> getStudentsFromThisState(String city, String state);

}
