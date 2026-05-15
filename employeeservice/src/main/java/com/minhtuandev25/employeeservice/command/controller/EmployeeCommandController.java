package com.minhtuandev25.employeeservice.command.controller;

import com.minhtuandev25.employeeservice.command.command.CreateEmployeeCommand;
import com.minhtuandev25.employeeservice.command.command.DeleteEmployeeCommand;
import com.minhtuandev25.employeeservice.command.command.UpdateEmployeeCommand;
import com.minhtuandev25.employeeservice.command.model.CreateEmployeeRequestModel;
import com.minhtuandev25.employeeservice.command.model.UpdateEmployeeRequestModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Employee - Command", description = "Create, update, and delete employees via Axon (CQRS command side)")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

	@Autowired
	private CommandGateway commandGateway;

	@Operation(
			summary = "Create employee",
			description = "Dispatches a create command. Returns the new `employeeId` (UUID).")
	@PostMapping
	public String createEmployee(@Valid @RequestBody CreateEmployeeRequestModel model) {
		CreateEmployeeCommand command = new CreateEmployeeCommand(
				UUID.randomUUID().toString(),
				model.getFirstName(),
				model.getLastName(),
				model.getKin(),
				Boolean.FALSE);
		return commandGateway.sendAndWait(command);
	}

	@Operation(
			summary = "Update employee",
			description = "Dispatches an update command for the given `employeeId`. Request body must include `isDisciplined`.")
	@PutMapping("/{employeeId}")
	public String updateEmployee(
			@Valid @RequestBody UpdateEmployeeRequestModel model,
			@Parameter(description = "Employee ID to update")
			@PathVariable String employeeId) {
		UpdateEmployeeCommand command = new UpdateEmployeeCommand(
				employeeId,
				model.getFirstName(),
				model.getLastName(),
				model.getKin(),
				model.getIsDisciplined());
		return commandGateway.sendAndWait(command);
	}

	@Operation(
			summary = "Delete employee",
			description = "Dispatches a delete command for the given `employeeId`.")
	@DeleteMapping("/{employeeId}")
	public String deleteEmployee(
			@Parameter(description = "Employee ID to delete")
			@PathVariable String employeeId) {
		DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
		return commandGateway.sendAndWait(command);
	}
}
