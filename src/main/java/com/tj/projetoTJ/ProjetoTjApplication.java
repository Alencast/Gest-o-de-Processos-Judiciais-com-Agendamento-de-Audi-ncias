package com.tj.projetoTJ;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "API do Projeto TJ",
                version = "1.0",
                description = "Sistema pra gestão dos processos",
                contact = @Contact(name = "Robson Alencastro", email = "binhoalves.14@hotmail.com")
        )
)
@SpringBootApplication
public class ProjetoTjApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoTjApplication.class, args);
	}

}
