package com.example.qualityassurancefeature.sender;

import com.example.qualityassurancefeature.config.ApplicationConfig;
import com.example.qualityassurancefeature.data.dto.ChangeConfigurationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class QualityAssuranceSender {

    private RabbitTemplate rabbitTemplate;
    private ApplicationConfig applicationConfig;
    private ObjectMapper objectMapper;

    public void sendQualityAssuranceRequest(ChangeConfigurationDto changeConfigurationDto) {
        try {
            if (!Objects.isNull(changeConfigurationDto)) {
                Message msg = getMessage(changeConfigurationDto);
                rabbitTemplate.convertAndSend(applicationConfig.getChangeConfigurationTopicName(),
                        applicationConfig.getChangeConfigurationRoutingKey(),
                        msg);
                log.info("Successfully sent the ChangeConfigurationDto: " + changeConfigurationDto);
            }
        } catch (Exception e) {
            log.error("Error during publishing data to rabbit mq broker");
            log.error("Exception: " + e);
        }
    }

    private Message getMessage(ChangeConfigurationDto changeConfigurationDto) throws JsonProcessingException {
        byte[] bytes = objectMapper.writeValueAsBytes(changeConfigurationDto);
        return MessageBuilder.withBody(bytes).build();
    }
}
