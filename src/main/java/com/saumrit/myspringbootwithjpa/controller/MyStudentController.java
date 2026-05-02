package com.saumrit.myspringbootwithjpa.controller;

import com.saumrit.myspringbootwithjpa.model.Student;
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

    @Operation(summary = "Api to get All Students",
    description = "Api to get All Students")
    @GetMapping("/getAllStudents")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<Student> getAllStudents(){
        return myStudentService.fetchAllStudent();
    }

    @Operation(summary = "Api to get All Students with Sorting applied",
            description = "Api to get All Students in a sorted Order")
    @GetMapping("/getAllStudentsSortedBy")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public List<Student> getAllStudentsSortedBy(@RequestParam String sortPropertyName){
        return myStudentService.fetchAllStudentSortedBy(sortPropertyName);
    }

    @Operation(summary = "Api to add a Student",
            description = "Api to add a Student")
    @PostMapping("/addSingleStudent")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public void addSingleStudent(@RequestBody Student student){
        myStudentService.addSingleStudent(student);
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





}
