package br.com.dominio.personapi.config;

import org.springframework.context.annotation.Configuration;

import br.com.dominio.personapi.docs.BaseSwaggerConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	public SwaggerConfig() {
		super("br.com.dominio.personapi.controller");
	}

}
