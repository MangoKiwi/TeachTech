package com.mangokiwi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
public class TeachTechApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TeachTechApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TeachTechApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner
//	private MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setMaxFileSize("128KB");
//		factory.setMaxRequestSize("128KB");
//		factory.setLocation(System.getProperty("java.io.tmpdir"));
//		return factory.createMultipartConfig();
//	}
}
