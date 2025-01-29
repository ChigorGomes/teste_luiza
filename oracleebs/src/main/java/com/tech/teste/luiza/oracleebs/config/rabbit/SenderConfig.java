package com.tech.teste.luiza.oracleebs.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do RabbitMQ para criação das filas e do bean {@link RabbitTemplate}.
 *
 * <p>Define as filas de erro e sucesso com base em propriedades externas
 * e configura o conversor de mensagens para o formato JSON.</p>
 *
 * @author Cicero Higor Gomes
 */
@Configuration
public class SenderConfig {


    @Value("${queue.name.error}")
    private String errorQueue;
    @Value("${queue.name.success}")
    private String sucessQueue;


    @Bean
    public Queue errorQueue() {
        return new Queue(errorQueue, true);
    }

    @Bean
    public Queue successQueue() {
        return new Queue(sucessQueue, true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}