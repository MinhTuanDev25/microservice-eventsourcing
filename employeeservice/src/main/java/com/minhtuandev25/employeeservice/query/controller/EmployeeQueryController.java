package com.minhtuandev25.employeeservice.query.controller;

import com.minhtuandev25.employeeservice.query.model.EmployeeResponseModel;
import com.minhtuandev25.employeeservice.query.queries.GetAllEmployeesQuery;
import com.minhtuandev25.employeeservice.query.queries.GetEmployeeByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {

	@Autowired
	private QueryGateway queryGateway;

	@GetMapping
	public List<EmployeeResponseModel> getAllEmployees(@RequestParam(required = false) Boolean isDisciplined) {
		return queryGateway.query(new GetAllEmployeesQuery(isDisciplined), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class))
				.join();
	}

	@GetMapping("/{employeeId}")
	public EmployeeResponseModel getEmployeeById(@PathVariable String employeeId) {
		return queryGateway.query(
				new GetEmployeeByIdQuery(employeeId),
				ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
	}
}
