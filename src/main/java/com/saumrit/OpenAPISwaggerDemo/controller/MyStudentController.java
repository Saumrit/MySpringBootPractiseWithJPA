package com.saumrit.OpenAPISwaggerDemo.controller;

import com.saumrit.OpenAPISwaggerDemo.model.StudentDTO;
import com.saumrit.OpenAPISwaggerDemo.service.MyStudentService;
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
    public List<StudentDTO> getAllStudents(){
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
    public List<StudentDTO> getAllStudentsSortedBy(@RequestParam String sortPropertyName){
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
    public void addSingleStudent(@RequestBody StudentDTO studentDTO){
        myStudentService.addSingleStudent(studentDTO);
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
    public StudentDTO updateSingleStudent(@RequestBody StudentDTO studentDTO){
        return myStudentService.updateSingleStudent(studentDTO);
    }

    @Operation(summary = "Api to patch update a Student",
            description = "Api to patch update a Student")
    @PatchMapping("/patchStudentInformation")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "500",description = "Internal Server Error") })
    public StudentDTO patchUpdateSingleStudent(@RequestBody StudentDTO studentDTO){
        return myStudentService.updateSingleStudent(studentDTO);
    }





}
