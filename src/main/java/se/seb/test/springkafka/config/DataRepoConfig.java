package se.seb.test.springkafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.seb.test.springkafka.repo.DataRepo;

@Configuration
public class DataRepoConfig {

	@Bean
	public DataRepo dataRepod() {
		return new DataRepo();
	}

}
