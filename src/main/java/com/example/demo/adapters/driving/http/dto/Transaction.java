package com.example.demo.adapters.driving.http.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    private final String message;
    private final String transactionalID;
    private final String cellPhone;
    private final Integer value;

}
