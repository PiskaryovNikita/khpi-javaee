package com.khpi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Human {

    private Long id;
    private String name;
    private String surname;
}
