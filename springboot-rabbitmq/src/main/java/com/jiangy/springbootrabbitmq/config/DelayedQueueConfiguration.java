package com.jiangy.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延时队列配置
 * <p>创建时间: 2024/12/5 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Configuration
public class DelayedQueueConfiguration {

    public final static String DELAYED_EXCHANGE_NAME = "test.delayed.exchange";
    public final static String DELAYED_QUEUE_NAME = "test.delayed.queue";
    public final static String DELAYED_ROUTING_KEY = "test.delayed.routing.key";

    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>(16);
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME,
                // 消息类型
                "x-delayed-message",
                // 是否持久化
                true,
                // 是否自动删除
                false,
                args);
    }

    @Bean
    public Queue delayedQueue() {
        return QueueBuilder.durable(DELAYED_QUEUE_NAME)
                .withArgument("x-delayed-type", "direct")
                .build();
    }

    @Bean
    public Binding delayedBinding() {
        return BindingBuilder
                .bind(delayedQueue())
                .to(delayedExchange())
                .with(DELAYED_ROUTING_KEY).noargs();
    }

}

