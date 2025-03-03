package com.jiangy.springbootrabbitmq.domain.order.handler;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.enums.EventType;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.events.OrderEvent;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public interface OrderStateHandler {
    boolean canHandle(OrderStatus status, EventType eventType);
    void handle(Order order, OrderEvent event);
}
