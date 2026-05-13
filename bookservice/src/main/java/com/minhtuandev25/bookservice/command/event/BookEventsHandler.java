package com.minhtuandev25.bookservice.command.event;


import com.minhtuandev25.bookservice.command.data.Book;
import com.minhtuandev25.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventsHandler {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event) {
        Optional<Book> oldBook = bookRepository.findById(event.getId());
        if (oldBook.isEmpty()) {
            throw new IllegalStateException("Book not found");
        }
        Book book = oldBook.get();
        book.setName(event.getName());
        book.setAuthor(event.getAuthor());
        book.setIsReady(event.getIsReady());
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        Optional<Book> oldBook = bookRepository.findById(event.getId());
        if (oldBook.isEmpty()) {
            throw new IllegalStateException("Cannot delete book. Book not found");
        }
        bookRepository.delete(oldBook.get());
    }
}
