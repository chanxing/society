package com.society;

import org.springframework.boot.SpringApplication;

public class ServerRunner {

	public static void main(String[] args) {
		// System.setProperty("ENV", "dev");
		System.setProperty("server.port", "8088");
		SpringApplication.run(SocietyServletInitializer.class, args);
	}
}
