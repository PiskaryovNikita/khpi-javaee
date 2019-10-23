package com.khpi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Supplier {

    private final Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
}
