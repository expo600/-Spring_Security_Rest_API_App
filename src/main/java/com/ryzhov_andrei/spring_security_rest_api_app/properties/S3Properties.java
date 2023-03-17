package com.ryzhov_andrei.spring_security_rest_api_app.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class S3Properties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
}
