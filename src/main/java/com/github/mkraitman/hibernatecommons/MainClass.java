package com.github.mkraitman.hibernatecommons;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EntityScan( basePackages = {"ar.com.bancogalicia.clrm.hibernatecore"} )
@SpringBootApplication(scanBasePackages = {"ar.com.bancogalicia.clrm.hibernatecore"})
public class MainClass {
	public static void main(String[] args) {
		SpringApplication.run(MainClass.class, args);
	}
	
	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
	    return beanFactory -> beanFactory.getBeanDefinition(
                DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME).getPropertyValues().add("loadOnStartup", 1);
	}
}
