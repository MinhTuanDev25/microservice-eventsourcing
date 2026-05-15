package com.minhtuandev25.employeeservice.query.controller;

import com.minhtuandev25.employeeservice.query.model.EmployeeResponseModel;
import com.minhtuandev25.employeeservice.query.queries.GetAllEmployeesQuery;
import com.minhtuandev25.employeeservice.query.queries.GetEmployeeByIdQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee - Query", description = "Read employee data from the projection (CQRS query side)")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {

	@Autowired
	private QueryGateway queryGateway;

	@Operation(
			summary = "List employees",
			description = "Returns all employees. Omit `isDisciplined` to return everyone; pass `true` or `false` to filter by discipline status.")
	@GetMapping
	public List<EmployeeResponseModel> getAllEmployees(
			@Parameter(description = "Filter by discipline status. Omit to return all employees.")
			@RequestParam(required = false) Boolean isDisciplined) {
		return queryGateway.query(new GetAllEmployeesQuery(isDisciplined), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class))
				.join();
	}

	@Operation(
			summary = "Get employee by ID",
			description = "Returns a single employee by `employeeId`. Responds with 404 if not found.")
	@GetMapping("/{employeeId}")
	public EmployeeResponseModel getEmployeeById(
			@Parameter(description = "Employee ID (UUID)")
			@PathVariable String employeeId) {
		return queryGateway.query(
				new GetEmployeeByIdQuery(employeeId),
				ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
	}
}
