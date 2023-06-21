package com.example.demotestcontainer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.example.demotestcontainer.customer.Customer;
import com.example.demotestcontainer.customer.CustomerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
public class CustomerIntegrationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Container
	public static PostgreSQLContainer<?> db = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

	@DynamicPropertySource
	static void dynamicProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", db::getJdbcUrl);
		registry.add("spring.datasource.username", db::getUsername);
		registry.add("spring.datasource.password", db::getPassword);
	}

	@Test
	void when_using_a_clean_db_this_should_be_empty() {
		List<Customer> customers = Streamable.of(customerRepository.findAll()).toList();
		assertThat(customers).hasSize(2);
	}
}
