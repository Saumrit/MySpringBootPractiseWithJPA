package com.saumrit.myspringbootwithjpa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheLabExperimentId implements Serializable {


    private Student student;
    private Subject subject;
    private Lecture guide;




}
