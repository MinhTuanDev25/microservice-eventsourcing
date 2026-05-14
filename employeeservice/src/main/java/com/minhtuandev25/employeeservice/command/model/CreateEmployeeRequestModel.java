package com.minhtuandev25.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestModel {

	@NotBlank(message = "First name is required")
	@Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
	private String lastName;

	private String Kin;
}
