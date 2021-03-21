package cc.mrbird.febs.server.system.configure;

import cc.mrbird.febs.server.system.properties.FebsServerSystemProperties;
import cc.mrbird.febs.server.system.properties.FebsSwaggerProperties;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: micro_project_perfect
 * @Description: MyBatis Plus分页插件配置
 * @Author: qiguohui
 * @Date: 2021/1/7 11:41
 */
@Configuration
@EnableSwagger2
public class FebsWebConfigure  {

    @Autowired
    private FebsServerSystemProperties properties;

    @Bean
    public Docket swaggerApi() {
        FebsSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 表示将cc.mrbird.febs.server.system.controller路径下的所有Controller都添加进去
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                // 表示Controller里的所有方法都纳入
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger))
                .securitySchemes(Collections.singletonList(securityScheme(swagger)))
                .securityContexts(Collections.singletonList(securityContext(swagger)));
    }

    private ApiInfo apiInfo(FebsSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    private SecurityScheme securityScheme(FebsSwaggerProperties swagger) {
//        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8301/auth/oauth/token");
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());
        return new OAuthBuilder()
//                .name("febs_oauth_swagger")
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private SecurityContext securityContext(FebsSwaggerProperties swagger) {
        return SecurityContext.builder()
//                .securityReferences(Collections.singletonList(new SecurityReference("febs_oauth_swagger", scopes(swagger))))
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(FebsSwaggerProperties swagger) {
        return new AuthorizationScope[]{
//                new AuthorizationScope("test", "")
                new AuthorizationScope(swagger.getScope(), "")
        };
    }
}
