package se.seb.test.springkafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class User {

    @NotBlank
    @NotEmpty
    private String name;
    @Max(65)
    private int age;

}