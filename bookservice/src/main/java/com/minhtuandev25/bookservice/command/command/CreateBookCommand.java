package com.minhtuandev25.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookCommand {

    @TargetAggregateIdentifier
    private String id;

    private String name;

    private String author;

    private Boolean isReady;
}
