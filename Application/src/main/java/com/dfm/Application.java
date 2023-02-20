package com.dfm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application
{
    @Bean
	public ModelMapper model_mapper()
    {
		return new ModelMapper();
	}

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
