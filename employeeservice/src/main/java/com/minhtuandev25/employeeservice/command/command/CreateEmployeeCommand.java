package com.minhtuandev25.employeeservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @TargetAggregateIdentifier
    private String id;

    private String firstName;

    private String lastName;
    
    private String Kin;
    
    private Boolean isDisciplined;
}
