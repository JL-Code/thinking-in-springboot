package com.jiangy.springbootrabbitmq.controller;

import com.jiangy.springbootrabbitmq.config.DelayedQueueConfiguration;
import com.jiangy.springbootrabbitmq.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>创建时间: 2025/1/9 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@RestController
@RequestMapping("/api/test/mq")
@RequiredArgsConstructor
public class TestController {

    private final AmqpTemplate amqpTemplate;

    @PostMapping("/hello")
    public ApiResponse<Object> sendHello(@RequestParam String message, @RequestParam long delay) {
        amqpTemplate.convertAndSend(DelayedQueueConfiguration.DELAYED_EXCHANGE_NAME,
                DelayedQueueConfiguration.DELAYED_ROUTING_KEY,
                message,
                messagePostProcessor -> {
                    messagePostProcessor.getMessageProperties().setDelayLong(delay * 1000L);
                    return messagePostProcessor;
                });
        log.info("send message :{} delay: {} s", message, delay);

        return ApiResponse.builder()
                .code("0")
                .message("success")
                .build();
    }
}
