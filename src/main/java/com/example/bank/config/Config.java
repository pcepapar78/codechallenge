package com.example.bank.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Config 
{	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
        	.apis(RequestHandlerSelectors.basePackage("com.example.bank.controller"))
		    .paths(PathSelectors.ant("/transactions/*"))  
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder()
            	.code(500)
                .message("500 message")
                .responseModel(new ModelRef("Error"))
                .build(),
                new ResponseMessageBuilder()
                	.code(403)
                    .message("Forbidden!")
                    .build()));            
    }
	
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Bank - Microservice", 
	      "Bank transactions", 
	      null, 
	      null, 
	      new Contact("Bank", "www.com.example.bank", "bank.microservice@example.com"), 
	      null, null);
	}
	   

}
