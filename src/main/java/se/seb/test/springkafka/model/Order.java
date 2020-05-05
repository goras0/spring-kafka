package se.seb.test.springkafka.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Order {

	@NotEmpty(message="Name is required")
	private String name;
	@NotEmpty(message="City is required")
	private String city;

}