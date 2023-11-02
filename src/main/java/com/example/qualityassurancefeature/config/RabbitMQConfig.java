package com.example.qualityassurancefeature.config;


import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    private ApplicationConfig applicationConfig;

    @Bean
    @Qualifier("changeConfiguration")
    public Queue qualityAssuranceRequest() {
        return new Queue(applicationConfig.getChangeConfigurationQueueName());
    }

    @Bean
    @Qualifier("changeConfiguration")
    public TopicExchange qualityAssuranceTopicExchange() {
        return new TopicExchange(applicationConfig.getChangeConfigurationTopicName());
    }

    @Bean
    @Qualifier("changeConfiguration")
    public Binding qualityAssuranceRequestBinding(@Qualifier("changeConfiguration") Queue queue,
                                                  @Qualifier("changeConfiguration") TopicExchange topicExchange) {

        return BindingBuilder.bind(queue).to(topicExchange).with(applicationConfig.getChangeConfigurationRoutingKey());
    }
}
