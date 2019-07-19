package com.aequilibrium.springboot.transformers;

/**
 * Class responsible for starting SpringBoot
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.aequilibrium.springboot.controller.TransformerController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackageClasses = TransformerController.class)
@EnableSwagger2
public class TransformersApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransformersApplication.class, args);
	}
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.aequilibrium.springboot.controller")).build();
	   }
}
