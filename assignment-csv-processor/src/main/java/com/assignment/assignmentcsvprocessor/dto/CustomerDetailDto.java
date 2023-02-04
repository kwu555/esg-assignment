package com.assignment.assignmentcsvprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailDto {

    private String customerRef;
    private String customerName;
    private String addressLineOne;
    private String addressLineTwo;
    private String town;
    private String county;
    private String country;
    private String postcode;
}
