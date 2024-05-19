package com.ssafy.pistachio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class PistachioApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(PistachioApplication.class);

	@Value("${spring.data.redis.host}")
	private String redisHost;

	@Value("${spring.data.redis.port}")
	private int redisPort;

	public static void main(String[] args) {
		SpringApplication.run(PistachioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Redis Host: {}", redisHost);
		logger.info("Redis Port: {}", redisPort);
	}
}
