package cc.mrbird.febs.server.system.properties;

import lombok.Data;

/**
 * @ProjectName: micro_project_perfect
 * @Description:
 * @Author: qiguohui
 * @Date: 2021/1/8 12:38
 */
@Data
public class FebsSwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;

    private String grantUrl;
    private String name;
    private String scope;
}
