package com.assignment.dataapi.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CustomerDto {
    private static final String NAME_REGEX = "^[A-Za-z\\s-']*$";

    @NotNull
    @Length(max = 10)
    private String customerRef;

    @NotNull
    @Length(max = 30)
    @Pattern(regexp = NAME_REGEX)
    private String customerName;

    @NotNull
    @Length(max = 30)
    private String addressLineOne;
    @NotNull
    @Length(max = 30)
    private String addressLineTwo;

    @NotNull
    @Length(max = 30)
    private String town;

    @NotNull
    @Length(max = 30)
    private String county;

    @NotNull
    @Length(max = 30)
    private String country;
    @NotNull
    @Length(max = 10) // could have a postcode reg here
    private String postcode;
}
