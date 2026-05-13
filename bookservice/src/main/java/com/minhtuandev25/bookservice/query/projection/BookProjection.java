package com.minhtuandev25.bookservice.query.projection;

import com.minhtuandev25.bookservice.command.data.Book;
import com.minhtuandev25.bookservice.command.data.BookRepository;
import com.minhtuandev25.bookservice.query.model.BookResponseModel;
import com.minhtuandev25.bookservice.query.queries.GetAllBookQuery;
import com.minhtuandev25.bookservice.query.queries.GetBookByIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query) {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> {
            BookResponseModel bookResponseModel = new BookResponseModel();
            BeanUtils.copyProperties(book, bookResponseModel);
            return bookResponseModel;
        }).toList();
    }

    @QueryHandler
    public BookResponseModel handle(GetBookByIdQuery query) {
        Book book = bookRepository.findById(query.getBookId())
                .orElseThrow(() -> new NoSuchElementException("Book not found: " + query.getBookId()));
        BookResponseModel bookResponseModel = new BookResponseModel();
        BeanUtils.copyProperties(book, bookResponseModel);
        return bookResponseModel;
    }
}
