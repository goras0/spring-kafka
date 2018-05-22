package se.seb.test.springkafka.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;

@Data
public class Order {

	@NotBlank(message="Name is required")
	private String name;
	@NotBlank(message="City is required")
	private String city;

}