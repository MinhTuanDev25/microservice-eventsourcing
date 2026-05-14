package com.minhtuandev25.employeeservice.query.projection;

import com.minhtuandev25.employeeservice.command.data.Employee;
import com.minhtuandev25.employeeservice.command.data.EmployeeRepository;
import com.minhtuandev25.employeeservice.query.model.EmployeeResponseModel;
import com.minhtuandev25.employeeservice.query.queries.GetAllEmployeesQuery;
import com.minhtuandev25.employeeservice.query.queries.GetEmployeeByIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeProjection {

	@Autowired
	private EmployeeRepository employeeRepository;

	@QueryHandler
	public List<EmployeeResponseModel> handle(GetAllEmployeesQuery query) {
		List<Employee> employees = query.getIsDisciplined() == null
				? employeeRepository.findAll()
				: employeeRepository.findByIsDisciplined(query.getIsDisciplined());
		return employees.stream().map(employee -> {
			EmployeeResponseModel dto = new EmployeeResponseModel();
			BeanUtils.copyProperties(employee, dto);
			return dto;
		}).toList();
	}

	@QueryHandler
	public EmployeeResponseModel handle(GetEmployeeByIdQuery query) throws Exception{
		Employee employee = employeeRepository.findById(query.getEmployeeId())
				.orElseThrow(() -> new Exception(
						"Employee not found: " + query.getEmployeeId()));
		EmployeeResponseModel dto = new EmployeeResponseModel();
		BeanUtils.copyProperties(employee, dto);
		return dto;
	}
}
