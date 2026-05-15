package com.minhtuandev25.bookservice.command.controller;

import com.minhtuandev25.bookservice.command.command.CreateBookCommand;
import com.minhtuandev25.bookservice.command.command.DeleteBookCommand;
import com.minhtuandev25.bookservice.command.command.UpdateBookCommand;
import com.minhtuandev25.bookservice.command.model.BookRequestModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Book - Command", description = "Create, update, and delete books via Axon (CQRS command side)")
@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

	@Autowired
	private CommandGateway commandGateway;

	@Operation(
			summary = "Create book",
			description = "Dispatches a create command. New books are created with `isReady` set to `true`. Returns the new `bookId` (UUID).")
	@PostMapping
	public String addBook(@Valid @RequestBody BookRequestModel model) {
		CreateBookCommand command = new CreateBookCommand(
				UUID.randomUUID().toString(),
				model.getName(),
				model.getAuthor(),
				true);
		return commandGateway.sendAndWait(command);
	}

	@Operation(
			summary = "Update book",
			description = "Dispatches an update command for the given `bookId`. Optional body fields: `name`, `author`, `isReady`.")
	@PutMapping("/{bookId}")
	public String updateBook(
			@RequestBody BookRequestModel model,
			@Parameter(description = "Book ID to update")
			@PathVariable String bookId) {
		UpdateBookCommand command = new UpdateBookCommand(bookId, model.getName(), model.getAuthor(), model.getIsReady());
		return commandGateway.sendAndWait(command);
	}

	@Operation(
			summary = "Delete book",
			description = "Dispatches a delete command for the given `bookId`.")
	@DeleteMapping("/{bookId}")
	public String deleteBook(
			@Parameter(description = "Book ID to delete")
			@PathVariable String bookId) {
		DeleteBookCommand command = new DeleteBookCommand(bookId);
		return commandGateway.sendAndWait(command);
	}
}
