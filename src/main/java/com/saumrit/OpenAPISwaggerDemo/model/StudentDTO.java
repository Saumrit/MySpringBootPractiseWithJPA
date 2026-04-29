package com.saumrit.OpenAPISwaggerDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")
public class StudentDTO {

    @Id
    public String id;

    public String name;
    public String standard;
    public Integer age;
    public Address address;

}
