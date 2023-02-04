package com.assignment.dataapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private int statusCode;
    private String errMsg;
}
