package com.ex.meetings.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ex.meetings"})
public class MeetingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingsApplication.class, args);
	}

}
