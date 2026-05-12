package com.minhtuandev25.bookservice.command.event;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
