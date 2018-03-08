package org.dogsystem.config;

import org.dogsystem.component.ApplicationProperty;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.dogsystem" })
public class AppContext {
	
	@Bean(name = "applicationProperty")
	public ApplicationProperty getApplicationProperty() {
		return new ApplicationProperty();
	}
	
	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(getApplicationProperty().getSecret());
	}

}