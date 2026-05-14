package com.minhtuandev25.employeeservice.command.controller;

import com.minhtuandev25.employeeservice.command.command.CreateEmployeeCommand;
import com.minhtuandev25.employeeservice.command.command.DeleteEmployeeCommand;
import com.minhtuandev25.employeeservice.command.command.UpdateEmployeeCommand;
import com.minhtuandev25.employeeservice.command.model.CreateEmployeeRequestModel;
import com.minhtuandev25.employeeservice.command.model.UpdateEmployeeRequestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

	@Autowired
	private CommandGateway commandGateway;

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

	@PutMapping("/{employeeId}")
	public String updateEmployee(@Valid @RequestBody UpdateEmployeeRequestModel model, @PathVariable String employeeId) {
		UpdateEmployeeCommand command = new UpdateEmployeeCommand(
				employeeId,
				model.getFirstName(),
				model.getLastName(),
				model.getKin(),
				model.getIsDisciplined());
		return commandGateway.sendAndWait(command);
	}

	@DeleteMapping("/{employeeId}")
	public String deleteEmployee(@PathVariable String employeeId) {
		DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
		return commandGateway.sendAndWait(command);
	}
}
