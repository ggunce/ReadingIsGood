package com.app.readingIsGood.book.model;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Data
public class Book {

    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private Integer stockCount;
    @Version
    private Long rowVersion;

}
