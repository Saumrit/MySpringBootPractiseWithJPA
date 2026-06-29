package com.saumrit.myspringbootwithjpa.controller;

import com.saumrit.myspringbootwithjpa.dto.*;
import com.saumrit.myspringbootwithjpa.model.Student;
import com.saumrit.myspringbootwithjpa.model.enums.CourseCategory;
import com.saumrit.myspringbootwithjpa.service.MyStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/students")
public class MyStudentController {
    public MyStudentService myStudentService;

    public MyStudentController(MyStudentService myStudentService) {
        this.myStudentService = myStudentService;
    }

    @Operation(summary = "Circular Exception in Bidirectional Mapping + @JsonManagedReference + @JsonBackReference",
            description = "Api to add a Student+  Here check the annotation to tackle stackOverFlow Exception ")
    @PostMapping("/singleStudent")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addSingleStudent(@RequestBody PostStudentRequestDTO postStudentRequestDTO){
        myStudentService.addSingleStudent(postStudentRequestDTO);
    }

    @Operation(summary = "Circular Exception in Bidirectional Mapping + @JsonManagedReference + @JsonBackReference",
            description = "Api to add multiple Students +  Here check the annotation to tackle stackOverFlow Exception ")
    @PostMapping("/multipleStudents")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addMultipleStudent(@RequestBody AddMultipleStudentsRequestDTO addMultipleStudentsRequestDTO){
        myStudentService.addMultipleStudent(addMultipleStudentsRequestDTO);
    }

    @Operation(summary = "Api to get All Students",
    description = "Api to get All Students")
    @GetMapping("/getAllStudents")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllStudents(){
        return myStudentService.fetchAllStudent();
    }



    @Operation(summary = "Api to get All Students From A certain city and state combination",
            description = "Api to show LEFT JOIN without FETCH usages")
    @GetMapping("/{city}/{state}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllStudentsWithAddressDetailLeftOuterJoin(@PathVariable("city") String city, @PathVariable("state") String state){
        return myStudentService.fetchStudentWithHouseDetailsLeftOuterJoinWithoutFetch(city,state);
    }


    @Operation(summary = "Api to get All Student's House-Detail From A certain certain city and state combination",
            description = "Api to show usages of LEFT JOIN with FETCH")
    @GetMapping("/{state}/{city}/houseDetail")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<StudentWithHouseNumberDetailDto> getAllStudentsWithAddressDetail(@RequestParam("city") String city, @RequestParam("state") String state){
        return myStudentService.fetchStudentWithHouseDetailsWithFetch(city,state);
    }


    @Operation(summary = "Api to get All Students with ",
            description = "Api to fetch data with boolean field and String field combination")
    @GetMapping("/allNRIStudents")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllNRIStudentsOfSpecificState(@RequestParam String stateName){
        return myStudentService.getTheNRIStudentFromThisState(stateName);
    }

    @Operation(summary = "Api to get All Students with Sorting applied on Their Age",
            description = "Api to show usage of SORT")
    @GetMapping("/allStudentsSortedByAge")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAllStudentsSortedByAge(){
        return myStudentService.fetchAllStudentSortedByAge();
    }

    @Operation(summary = "Search student by name having special character in their name",
            description = "Api to show Special character Support in Spring data JPA")
    @GetMapping("/advanceSearchForStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public GetStudentResponseDTO advanceSearchForStudent(@RequestParam String name){
        return myStudentService.getStudentWithAdvanceNameSearch(name);
    }

    @Operation(summary = "Api to get a Student by searching with name or rollID",
            description = "Api to get a Student by searching with name or rollID")
    @GetMapping("/getAStudentByNameOrRollId")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<GetStudentResponseDTO> getAStudentByNameORRollNumber(@RequestParam(required = false) String name, @RequestParam(required = false) String roll){
        return myStudentService.fetchAStudentByNameOrRollId(name,roll);
    }

    @Operation(summary = "Api to Update NRI Status of a Student",
            description = "Api to Update NRI Status of a Student")
    @PutMapping("/NRI-status")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public int  updateNRIStatusForSingleStudent(@RequestParam String roll,@RequestParam boolean status){
        return myStudentService.updateTheNRIStatusOFAnyStudent(roll, status);
    }

    @Operation(summary = "Api to remove a Student",
            description = "Api to remove a Student")
    @DeleteMapping("/{id}/removeStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void deleteSingleStudent(@PathVariable String id){
        myStudentService.deleteStudent(id);
    }

    @Operation(summary = "Api to Update a Student",
            description = "Api to Update a Student")
    @PutMapping("/updateStudent")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student updateSingleStudent(@RequestBody Student student){
        return myStudentService.updateSingleStudent(student);
    }

    @Operation(summary = "Api to patch update a Student",
            description = "Api to patch update a Student")
    @PatchMapping("/patchStudentInformation")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student patchUpdateSingleStudent(@RequestBody Student student){
        return myStudentService.updateSingleStudent(student);
    }

    @Operation(summary = "Here composite Primary Key is explained through Assignment",
            description = "Api to give assignments to a Student")
    @PatchMapping("/assignment/{rollId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public AssignmentResponseDTO assignmentToStudent(@PathVariable("rollId") String rollId, @RequestParam("subject") String subject){
        return myStudentService.updateSingleStudentWithAssignmentDetail(rollId,subject);
    }


    @Operation(summary = "Here @ElementCollection/@CollectionTable is used",
            description = "Api to give assignments to a Student")
    @PatchMapping("/award/{rollId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student awardsToStudent(@PathVariable("rollId") String rollId,@RequestBody List<String> awards){
        return myStudentService.updateSingleStudentWithAwardDetail(rollId,awards);
    }

    @Operation(summary = "Here @Jointable is used",
            description = "Api to give assignments to a Student")
    @PatchMapping("/tutorialCourse/{rollId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public Student enrollStudentToTutorialCourse(@PathVariable("rollId") String rollId, @PathVariable("name") String courseName,
                                                 @RequestParam("category")CourseCategory category){
        return myStudentService.updateSingleStudentWithTutoriaCourseDetail(rollId,courseName,category);
    }







}
