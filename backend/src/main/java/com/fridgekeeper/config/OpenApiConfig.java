package com.fridgekeeper.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fridgeKeeperOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FridgeKeeper API 문서")
                        .description("냉장고지킴이(FridgeKeeper) 프로젝트의 백엔드 REST API 명세서입니다.\n\n" +
                                     "이 문서는 Spring Boot 3.5.6, MyBatis, Swagger(OpenAPI 3)를 기반으로 작성되었습니다.\n" +
                                     "아래에서 각 API를 직접 테스트할 수도 있습니다.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("홍휘창 (FridgeKeeper Developer)")
                                .email("honghcwng@naver.com")
                                .url("https://github.com/Hwichang-0222/fridge-keeper.git"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
