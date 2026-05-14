package com.minhtuandev25.employeeservice.command.aggregate;

import com.minhtuandev25.employeeservice.command.command.CreateEmployeeCommand;
import com.minhtuandev25.employeeservice.command.command.DeleteEmployeeCommand;
import com.minhtuandev25.employeeservice.command.command.UpdateEmployeeCommand;
import com.minhtuandev25.employeeservice.command.event.EmployeeCreatedEvent;
import com.minhtuandev25.employeeservice.command.event.EmployeeDeletedEvent;
import com.minhtuandev25.employeeservice.command.event.EmployeeUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {
	@AggregateIdentifier
	private String id;

	private String firstName;

	private String lastName;

	private String Kin;

	private Boolean isDisciplined;

	@CommandHandler
	public EmployeeAggregate(CreateEmployeeCommand command) {
		EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
		BeanUtils.copyProperties(command, employeeCreatedEvent);
		AggregateLifecycle.apply(employeeCreatedEvent);
	}

	@CommandHandler
	public void handle(UpdateEmployeeCommand command) {
		EmployeeUpdatedEvent employeeUpdatedEvent = new EmployeeUpdatedEvent(
				command.getId(),
				command.getFirstName() != null ? command.getFirstName() : this.firstName,
				command.getLastName() != null ? command.getLastName() : this.lastName,
				command.getKin() != null ? command.getKin() : this.Kin,
				command.getIsDisciplined() != null ? command.getIsDisciplined() : this.isDisciplined
		);
		AggregateLifecycle.apply(employeeUpdatedEvent);
	}

	@CommandHandler
	public void handle(DeleteEmployeeCommand command) {
		EmployeeDeletedEvent employeeDeletedEvent = new EmployeeDeletedEvent();
		BeanUtils.copyProperties(command, employeeDeletedEvent);
		AggregateLifecycle.apply(employeeDeletedEvent);
	}

	@EventSourcingHandler
	public void on(EmployeeCreatedEvent event) {
		this.id = event.getId();
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.Kin = event.getKin();
		this.isDisciplined = event.getIsDisciplined();
	}

	@EventSourcingHandler
	public void on(EmployeeUpdatedEvent event) {
		this.id = event.getId();
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.Kin = event.getKin();
		this.isDisciplined = event.getIsDisciplined();
	}

	@EventSourcingHandler
	public void on(EmployeeDeletedEvent event) {
		this.id = event.getId();
	}
}
