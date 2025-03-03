package com.jiangy.springbootrabbitmq.domain.order.handler;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.enums.EventType;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.events.OrderEvent;
import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class PrepaidPendingHandler implements OrderStateHandler {
    @Override
    public boolean canHandle(OrderStatus status, EventType eventType) {
        return false;
    }

    @Override
    public void handle(Order order, OrderEvent event) {
        switch (event.getEventType()) {
            case PAYMENT_CONFIRMED:
                order.setStatus(OrderStatus.PAID);
                // 触发发货逻辑
                break;
            case PAYMENT_TIMEOUT:
                order.setStatus(OrderStatus.CANCELLED);
                // 释放库存
                break;
        }
    }
}
