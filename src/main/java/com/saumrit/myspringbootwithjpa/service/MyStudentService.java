package com.saumrit.myspringbootwithjpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.myspringbootwithjpa.dto.GETStudentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.POSTStudentRequestDTO;
import com.saumrit.myspringbootwithjpa.dto.StudentWithHouseNumberDetailDto;
import com.saumrit.myspringbootwithjpa.model.Address;
import com.saumrit.myspringbootwithjpa.model.Student;
import com.saumrit.myspringbootwithjpa.repository.MyStudentRepository;
import com.saumrit.myspringbootwithjpa.util.UniqueIdGeneratorUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyStudentService {

    public final MyStudentRepository myStudentRepository ;
    public final UniqueIdGeneratorUtil uniqueIdGeneratorUtil;
    public final ObjectMapper objectMapper;

    public MyStudentService(MyStudentRepository myStudentRepository, UniqueIdGeneratorUtil uniqueIdGeneratorUtil, ObjectMapper objectMapper) {
        this.myStudentRepository=myStudentRepository;
        this.uniqueIdGeneratorUtil = uniqueIdGeneratorUtil;
        this.objectMapper = objectMapper;
    }

    public List<GETStudentResponseDTO> fetchAllStudent(){
        List<Student> students=  myStudentRepository.findAll();
        if(!ObjectUtils.isEmpty(students))
            return students.stream()
                    .map(x -> objectMapper.convertValue(x, GETStudentResponseDTO.class))
                    .toList();
        return null;
    }

    public List<GETStudentResponseDTO> fetchAllStudentSortedBy(String sort_property_name){
        Sort sort= Sort.by("age").descending();
        List<Student> students= myStudentRepository.findAll(sort);
        if(!ObjectUtils.isEmpty(students))
            return students.stream()
                    .map(x -> objectMapper.convertValue(x, GETStudentResponseDTO.class))
                    .toList();
        return null;
    }

    public GETStudentResponseDTO fetchAStudentByNameOrRollId(String name , String roll){
        if(null== name && null == roll)
            return null;
        Student student= myStudentRepository.findByNameOrRollId(name, roll, Limit.of(1));
        return objectMapper.convertValue(student, GETStudentResponseDTO.class);
    }

    public void deleteStudent(String id){
         myStudentRepository.deleteById(id);
    }

    public void addSingleStudent(POSTStudentRequestDTO POSTStudentRequestDTO){
        Student student= createStudentFromStudentDTO(POSTStudentRequestDTO);
        student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
        myStudentRepository.save(student);
    }

    public List<GETStudentResponseDTO> getTheNRIStudentFromThisState(String state){
        List<Student> nriStudents= myStudentRepository.findNRIStudentsFromGivenState(state);
        return nriStudents.stream()
                .map(x -> objectMapper.convertValue(x, GETStudentResponseDTO.class))
                .toList();
    }

    public Student updateSingleStudent(Student student){
        return myStudentRepository.save(student);
    }

    @Transactional
    public int updateTheNRIStatusOFAnyStudent(String roll, boolean status){
        return myStudentRepository.updateTheNRIStatusOFAnyStudent(roll, status);
    }

    public GETStudentResponseDTO getStudentWithAdvanceNameSearch(String name){
        Student student= myStudentRepository.searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(name, Limit.of(1));
        return objectMapper.convertValue(student, GETStudentResponseDTO.class);
    }

    private Student createStudentFromStudentDTO(POSTStudentRequestDTO POSTStudentRequestDTO){
        Student student= objectMapper.convertValue(POSTStudentRequestDTO, Student.class);
        student.setAddress(objectMapper.convertValue(POSTStudentRequestDTO.getAddressDTO(), Address.class));
        return student;
    }

    public boolean checkIfMinimumPercentForPassingAchievedInThisSubject(String subject){

        return false;
    }

    public void enrollStudentIntoTutorialCourse(){

    }

    public List<StudentWithHouseNumberDetailDto> fetchStudentWithHouseDetails(String city, String state){
        return myStudentRepository.getStudentsFromThisState(city,state)
                .stream().map(x -> {
                    StudentWithHouseNumberDetailDto studentWithHouseNumberDetailDto= new StudentWithHouseNumberDetailDto();
                    studentWithHouseNumberDetailDto.setHouseNumber(x.getAddress().houseRegNumber.toString());
                    studentWithHouseNumberDetailDto.setName(x.getName());
                    studentWithHouseNumberDetailDto.setRoll(x.getRollId());
                    return studentWithHouseNumberDetailDto;
                }).toList();
    }


}
