package com.minhtuandev25.bookservice.command.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDeletedEvent {
    private String id;
}
