package br.csi.sistema_review;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Sistema de Reviews",
				version = "1.0",
				description = "Sistema para gerenciamento de reviews de obras da cultura pop."
		)
)

@SpringBootApplication
public class SistemaDeReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeReviewsApplication.class, args);
	}

}
