package com.discoveybank.balancedispensing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String title = "Bank System";
    private String description = "API";
    private String termsOfServiceUrl = "http:discovery.com";
    // private String version = "1.0";
    // private String basePackage = "com.discoveybank.balancedispensing";

    @Bean
    public Docket api() {
       
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    // private ApiInfo metaData() {
    //     return new ApiInfoBuilder()
    //             .title(title)
    //             .description(description)
    //             .termsOfServiceUrl(termsOfServiceUrl)
    //             .version(version).build();
    // }
  
   
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact("Discovery", "http://www.dicovery.com", "livhuwanimatsigila@gmail.com"))
                .license("Open Source").licenseUrl("https://www.discovery.com").version("1.0.0").build();
    }

}
