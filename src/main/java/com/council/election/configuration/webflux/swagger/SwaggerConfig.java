package com.council.election.configuration.webflux.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.server.ServerWebExchange;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)//bean validation
public class SwaggerConfig {

    private final Set<String> paths = new HashSet<String>(Arrays.asList("/signin", "/signup"));

    // https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
    // https://www.baeldung.com/spring-boot-swagger-jwt
    // https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
    private ApiKey apiKey() {
        return new ApiKey("JWT Token", "Cookie", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("JWT Token",
                        new AuthorizationScope[]{
//                                new AuthorizationScope("global", "accessEverything"),
//                                new AuthorizationScope("read", "for read operations"),
//                                new AuthorizationScope("write", "for write operations"),
                                new AuthorizationScope("Persons", "Access Persons API")
                        }
                )))
                .operationSelector(operationContext -> {
                    return !paths.contains(operationContext.requestMappingPattern());
                })
                .build();
    }

    @Bean
    public Docket api() {

        List<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
        ApiInfo apiInfo = new ApiInfo("api-docs", "elections", "V1.0.0", "",
                new Contact("elections", "", ""), "", "", vendorExtensions);

        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(
                        AuthenticationPrincipal.class,
                        ServerWebExchange.class
                )
                .apiInfo(apiInfo)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("com.example.activemq"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.regex("/**"))
                .build()
                .apiInfo(apiInfo);
    }

/*    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.spring.boot.spring5webapp"))
                //.paths(PathSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build()
                .genericModelSubstitutes(Mono.class, Flux.class, Optional.class)
                //.pathMapping("/sgw/")
                //.apiInfo(new ApiInfo())
                ;
    }*/
}
