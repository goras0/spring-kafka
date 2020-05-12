package se.seb.test.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import se.seb.test.springkafka.config.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class SpringKafkaApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringKafkaApplication.class, args);
	}
}
