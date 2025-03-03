package com.jiangy.springbootrabbitmq.domain.order.events;

import com.jiangy.springbootrabbitmq.domain.order.enums.EventType;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Getter
public class OrderEvent {
    // Getter 方法
    private final EventType eventType;
    private final LocalDateTime timestamp;
    private final Object payload; // 可携带额外数据

    public OrderEvent(EventType eventType) {
        this(eventType, LocalDateTime.now(), null);
    }

    public OrderEvent(EventType eventType, Object payload) {
        this(eventType, LocalDateTime.now(), payload);
    }

    public OrderEvent(EventType eventType, LocalDateTime timestamp, Object payload) {
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.payload = payload;
    }

}
