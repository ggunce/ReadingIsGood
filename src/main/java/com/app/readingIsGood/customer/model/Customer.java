package com.app.readingIsGood.customer.model;


import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {

    @Id
    private String id;
    @NotNull
    private String name;
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private String password;

}
