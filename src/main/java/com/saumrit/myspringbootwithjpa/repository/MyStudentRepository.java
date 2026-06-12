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

    @Transactional(timeout = 10)
    public List<Student> findByName(String name);
    public Student findByRollId(String rollId);
    public List<Student> findByAddress_StateAndBioData_NriStatus(String stateName,boolean status);

    @Query("select s from Student s where s.name = :theName or s.rollId= :theRollId " )
    public List<Student> findByNameOrRollId(@Param("theName") String theName,@Param("theRollId") String theRollId);

    @Query("select s from Student s where s.name like %?#{escape([0])}% escape ?#{escapeCharacter()}" )
    public List<Student> searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(String name);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Student s set s.bioData.nriStatus= ?2 where s.rollId = ?1")
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    public int updateTheNRIStatusOFAnyStudent(@Param("roll") String rollId,@Param("nriStatusUpdate") Boolean status);

    @Query("SELECT s FROM Student s JOIN FETCH s.address a WHERE a.city= ?1 AND a.state= ?2")//Using FETCH to avoid N+1 problem
    public List<Student> getStudentsFromThisStateAndCity(String city, String state);

    @Query("SELECT s FROM Student s JOIN FETCH address a WHERE a.city= ?1 AND a.state= ?2")//Here i used address , not s.address , both are same
    public List<Student> getStudentsFromThisStateV2(String city, String state);


    //Here No fetch is used, meaning only Student detail is fetched here , no address
    //Address fields are used only for filtering
    @Query("SELECT s FROM Student s LEFT JOIN  address a WHERE  a.city= ?1 AND a.state= ?2")
    public List<Student> getStudentsFromThisStateLeftOuterJoinWithoutFetch(String city, String state);

    @Query("SELECT s FROM Student s LEFT JOIN FETCH  s.address a WHERE   a.city IN :cities")
    public List<Student> getStudentsFromTheseCities(List<String> cities);

    @Query("SELECT s FROM Student s LEFT JOIN  address a ON  a.city= ?1 AND a.state= ?2")
    public List<Student> getStudentsFromThisStateLeftOuterJoinNoFetch(String city, String state);//Here No FETCH is used, So ON can be used . But it will give N+1 issue

}
