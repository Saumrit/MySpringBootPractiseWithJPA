package com.saumrit.myspringbootwithjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GETStudentResponseDTO {

    public String name;
    public String standard;
    public Integer age;
}
