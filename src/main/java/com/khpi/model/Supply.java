package com.khpi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
public class Supply {

    private final Long id;
    private String name;
    private Integer amount;
    private Double optPrice;
    private Double charge;
    private Double retailPrice;
    private OffsetDateTime deliveryDate;
    private Long supplierId;
}
