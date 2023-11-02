package com.example.qualityassurancefeature.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@Getter
public class ApplicationConfig {

    @Value("${rabbitMq.changeConfiguration.queueName}")
    private String changeConfigurationQueueName;

    @Value("${rabbitMq.changeConfiguration.topicName}")
    private String changeConfigurationTopicName;

    @Value("${rabbitMq.changeConfiguration.routingKey}")
    private String changeConfigurationRoutingKey;
}
