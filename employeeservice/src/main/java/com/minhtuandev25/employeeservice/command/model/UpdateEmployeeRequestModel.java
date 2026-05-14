package com.minhtuandev25.employeeservice.command.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequestModel {
	private String id;

	@Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
	private String firstName;

	@Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
	private String lastName;

	@Size(max = 64, message = "Kin must not exceed 64 characters")
	private String Kin;

	@NotNull(message = "isDisciplined is required for update")
	private Boolean isDisciplined;
}
