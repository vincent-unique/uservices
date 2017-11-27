package org.trump.vincent.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
		EmbeddedServletContainerFactory containerFactory = new JettyEmbeddedServletContainerFactory();
		return containerFactory ;
	}
}
