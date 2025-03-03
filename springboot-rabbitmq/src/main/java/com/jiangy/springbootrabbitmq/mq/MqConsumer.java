package com.jiangy.springbootrabbitmq.mq;

import com.jiangy.springbootrabbitmq.config.DelayedQueueConfiguration;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2025/1/9 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MqConsumer {
    @RabbitListener(queues = DelayedQueueConfiguration.DELAYED_QUEUE_NAME)
    public void receiveDelayedMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("[消息队列] 延时队列消息：{}  tag: {}", message, tag);
    }
}
