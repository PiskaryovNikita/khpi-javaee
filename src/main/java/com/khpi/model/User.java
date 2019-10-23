package com.khpi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private final Long id;
    private String userName;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long roleId;

}
