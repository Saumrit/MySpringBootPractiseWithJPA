package com.saumrit.myspringbootwithjpa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMultipleStudentsRequestDTO {

    public List<PostStudentRequestDTO> studentRequestDTOList;



}
