package com.minhtuandev25.bookservice.command.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestModel {
    private String id;

    private String name;

    private String author;

    private Boolean isReady;
}
