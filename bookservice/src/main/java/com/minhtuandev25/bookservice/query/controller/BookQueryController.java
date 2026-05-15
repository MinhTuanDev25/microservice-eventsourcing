package com.minhtuandev25.bookservice.query.controller;

import com.minhtuandev25.bookservice.query.model.BookResponseModel;
import com.minhtuandev25.bookservice.query.queries.GetAllBookQuery;
import com.minhtuandev25.bookservice.query.queries.GetBookByIdQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Book - Query", description = "Read book data from the projection (CQRS query side)")
@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {

	@Autowired
	private QueryGateway queryGateway;

	@Operation(
			summary = "List books",
			description = "Returns all books from the read model.")
	@GetMapping
	public List<BookResponseModel> getAllBooks() {
		GetAllBookQuery query = new GetAllBookQuery();
		return queryGateway.query(query, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
	}

	@Operation(
			summary = "Get book by ID",
			description = "Returns a single book by `bookId`. Responds with 404 if not found.")
	@GetMapping("/{bookId}")
	public BookResponseModel getBookById(
			@Parameter(description = "Book ID (UUID)")
			@PathVariable String bookId) {
		return queryGateway.query(new GetBookByIdQuery(bookId), ResponseTypes.instanceOf(BookResponseModel.class)).join();
	}
}
