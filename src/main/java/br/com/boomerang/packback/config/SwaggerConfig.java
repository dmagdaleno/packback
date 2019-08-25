package br.com.boomerang.packback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Value("${info.build.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.boomerang.packback.controller"))
                .paths(regex("(/usuarios.*|/login.*|/embalagens.*|/movimentacoes.*|/pontuacoes.*)"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        String name = "BOOMERANG - PackBack";
        String url = "";
        String email = "diegommagdaleno@gmail.com";
        Contact contact = new Contact(name, url, email);

        String desc = "API para registro e consultas de materiais recicláveis disponíveis para coleta do sistema PackBack.";

        return new ApiInfoBuilder()
                .title("API Rest PackBack")
                .description(desc)
                .version(this.version)
                .contact(contact)
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}