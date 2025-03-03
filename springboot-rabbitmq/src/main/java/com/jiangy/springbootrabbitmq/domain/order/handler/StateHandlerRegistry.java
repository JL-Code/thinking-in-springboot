package com.jiangy.springbootrabbitmq.domain.order.handler;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.events.OrderEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态处理器注册中心
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class StateHandlerRegistry {
    private final List<OrderStateHandler> handlers = new ArrayList<>();

    public void registerHandler(OrderStateHandler handler) {
        handlers.add(handler);
    }

    public void processEvent(Order order, OrderEvent event) {
        handlers.stream()
                .filter(h -> h.canHandle(order.getStatus(), event.getEventType()))
                .findFirst()
                .ifPresent(h -> h.handle(order, event));
    }
}
