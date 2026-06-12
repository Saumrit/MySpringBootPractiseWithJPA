package com.saumrit.myspringbootwithjpa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saumrit.myspringbootwithjpa.dto.AssignmentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.GetStudentResponseDTO;
import com.saumrit.myspringbootwithjpa.dto.PostStudentRequestDTO;
import com.saumrit.myspringbootwithjpa.dto.StudentWithHouseNumberDetailDto;
import com.saumrit.myspringbootwithjpa.model.*;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.repository.MyAssignmentRepository;
import com.saumrit.myspringbootwithjpa.repository.MyStudentRepository;
import com.saumrit.myspringbootwithjpa.repository.MySubjectRepository;
import com.saumrit.myspringbootwithjpa.util.CommonConvertorUtil;
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
    public final MySubjectRepository mySubjectRepository;
    public final MyAssignmentRepository myAssignmentRepository;
    public final MyTutorialCourseService myTutorialCourseService;
    public final UniqueIdGeneratorUtil uniqueIdGeneratorUtil;
    public final ObjectMapper objectMapper;

    public MyStudentService(MyStudentRepository myStudentRepository, MySubjectRepository mySubjectRepository, MyAssignmentRepository myAssignmentRepository, MyTutorialCourseService myTutorialCourseService, UniqueIdGeneratorUtil uniqueIdGeneratorUtil, ObjectMapper objectMapper) {
        this.myStudentRepository=myStudentRepository;
        this.mySubjectRepository = mySubjectRepository;
        this.myAssignmentRepository = myAssignmentRepository;
        this.myTutorialCourseService = myTutorialCourseService;
        this.uniqueIdGeneratorUtil = uniqueIdGeneratorUtil;
        this.objectMapper = objectMapper;
    }

    public List<GetStudentResponseDTO> fetchAllStudent(){
        List<Student> students=  myStudentRepository.findAll();
        if(!ObjectUtils.isEmpty(students))
            return students.stream()
                    .map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class))
                    .toList();
        return null;
    }

    public List<GetStudentResponseDTO> fetchAllStudentSortedByAge(){
        Sort sort= Sort.by("age").descending();
        List<Student> students= myStudentRepository.findAll(sort);
        if(!ObjectUtils.isEmpty(students))
            return students.stream()
                    .map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class))
                    .toList();
        return null;
    }

    public List<GetStudentResponseDTO> fetchAStudentByNameOrRollId(String name , String roll){
        if(null== name && null == roll)
            return null;
        List<Student> students= myStudentRepository.findByNameOrRollId(name, roll);
        return students.stream().map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class)).toList();
    }

    public void deleteStudent(String id){
         myStudentRepository.deleteById(id);
    }

    public void addSingleStudent(PostStudentRequestDTO POSTStudentRequestDTO){
        Student student= createStudentFromStudentDTO(POSTStudentRequestDTO);
        student.setRollId(uniqueIdGeneratorUtil.generateByApacheText(6));
        myStudentRepository.save(student);
    }

    public List<GetStudentResponseDTO> getTheNRIStudentFromThisState(String state){
        List<Student> nriStudents= myStudentRepository.findByAddress_StateAndBioData_NriStatus(state,true);
        return nriStudents.stream()
                .map(x -> objectMapper.convertValue(x, GetStudentResponseDTO.class))
                .toList();
    }

    public Student updateSingleStudent(Student student){
        return myStudentRepository.save(student);
    }

    @Transactional
    public AssignmentResponseDTO updateSingleStudentWithAssignmentDetail(String rollId, String subjectName){
        Student student= myStudentRepository.findByRollId(rollId);
        if(ObjectUtils.isEmpty(student))
            return null;
        Subject subject = mySubjectRepository.findByName(subjectName);
        if(ObjectUtils.isEmpty(subject)){
            subject= new Subject();
            subject.setName(subjectName);
            subject=mySubjectRepository.save(subject);
        }
        Assignment assignment= new Assignment();
        assignment.setStudent(student);
        assignment.setSubject(subject);
        myAssignmentRepository.save(assignment);

        return CommonConvertorUtil.assignmentToAssignmentResponseDTO(assignment);
    }

    @Transactional
    public Student updateSingleStudentWithAwardDetail(String rollId, List<String> awards){
        Student student= myStudentRepository.findByRollId(rollId);
        if(ObjectUtils.isEmpty(student))
            return null;
        student.setAwardsOwned(awards);
        Student response= myStudentRepository.save(student);

        return response;
    }

    @Transactional
    public Student updateSingleStudentWithTutoriaCourseDetail(String rollId, String courseName, CourseCategory category){

        Student student= myStudentRepository.findByRollId(rollId);
        if(ObjectUtils.isEmpty(student))
            return null;

        TutorialCourse tutorialCourse= myTutorialCourseService.fetchCourse(courseName);
        if(null == tutorialCourse.getCourseName())
            return null;

        if(null == student.getTutorialCourses())
            student.setTutorialCourses(List.of(tutorialCourse));
        else
            student.getTutorialCourses().add(tutorialCourse);
        return  myStudentRepository.save(student);
    }

    @Transactional
    public int updateTheNRIStatusOFAnyStudent(String roll, boolean status){
        return myStudentRepository.updateTheNRIStatusOFAnyStudent(roll, status);
    }

    public GetStudentResponseDTO getStudentWithAdvanceNameSearch(String name){
        Student student= myStudentRepository.searchStudentWithAdvanceNameSearchWithSpecialCharacterSupport(name).get(0);
        return objectMapper.convertValue(student, GetStudentResponseDTO.class);
    }

    private Student createStudentFromStudentDTO(PostStudentRequestDTO POSTStudentRequestDTO){
        Student student= objectMapper.convertValue(POSTStudentRequestDTO, Student.class);
        student.setAddress(objectMapper.convertValue(POSTStudentRequestDTO.getAddressDTO(), Address.class));
        return student;
    }

    public boolean checkIfMinimumPercentForPassingAchievedInThisSubject(String subject){

        return false;
    }

    public void enrollStudentIntoTutorialCourse(){

    }

    public List<StudentWithHouseNumberDetailDto> fetchStudentWithHouseDetailsWithFetch(String city, String state){
        return myStudentRepository.getStudentsFromThisStateAndCity(city,state)
                .stream().map(x -> {
                    StudentWithHouseNumberDetailDto studentWithHouseNumberDetailDto= new StudentWithHouseNumberDetailDto();
                    studentWithHouseNumberDetailDto.setHouseNumber(x.getAddress().houseRegNumber.toString());
                    studentWithHouseNumberDetailDto.setName(x.getName());
                    studentWithHouseNumberDetailDto.setRoll(x.getRollId());
                    return studentWithHouseNumberDetailDto;
                }).toList();
    }

    /**
     * @param city
     * @param state
     * @return GetStudentResponseDTO
     * Here no FETCH is sued. Hence No Address details will be fetched.
     * AddressDetails are used only for the filtering.
     */
    public List<GetStudentResponseDTO> fetchStudentWithHouseDetailsLeftOuterJoinWithoutFetch(String city, String state){
        return myStudentRepository.getStudentsFromThisStateLeftOuterJoinWithoutFetch(city,state)
                .stream().map(x -> {GetStudentResponseDTO getStudentResponseDTO= new GetStudentResponseDTO();
                    getStudentResponseDTO.setName(x.getName());
                    return getStudentResponseDTO;
                })
                .toList();
    }


}
