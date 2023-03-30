package com.project.BookingCar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@OpenAPIDefinition(
		info =@Info(
				title = "User API",
				version = "version 1.1.0",
				contact = @Contact(
						name = "Baeldung", email = "user-apis@baeldung.com", url = "https://www.baeldung.com"
				),
				license = @License(
						name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		)
)
public class BookingCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingCarApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
