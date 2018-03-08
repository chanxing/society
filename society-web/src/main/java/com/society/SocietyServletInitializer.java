package com.society;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.society.util.UtilConfiguration;
import com.society.web.base.WebMVCConfiguration;

@Configuration
@Import(value = { UtilConfiguration.class, WebMVCConfiguration.class })
@EnableAutoConfiguration
@ComponentScan
@ServletComponentScan
@PropertySource("classpath:application-${ENV}.properties")
public class SocietyServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SocietyServletInitializer.class);
	}

	// @Bean
	// public EmbeddedServletContainerFactory servletContainer() {
	// JettyEmbeddedServletContainerFactory factory = new
	// JettyEmbeddedServletContainerFactory();
	// return factory;
	// }
}
