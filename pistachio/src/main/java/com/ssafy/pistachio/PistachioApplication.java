package com.ssafy.pistachio;

import com.ssafy.pistachio.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.web.DefaultSecurityFilterChain;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class PistachioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PistachioApplication.class, args);
	}

}
