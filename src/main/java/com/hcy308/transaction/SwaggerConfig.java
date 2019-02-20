package com.hcy308.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket main() {
        final ApiInfo apiInfo = new ApiInfoBuilder().title("Aladdin and the Magic Lamp").version("1.0").build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("main")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hcy308.transaction"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo);
    }

}

