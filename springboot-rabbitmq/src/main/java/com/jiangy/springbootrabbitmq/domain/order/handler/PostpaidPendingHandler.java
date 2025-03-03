package com.jiangy.springbootrabbitmq.domain.order.handler;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.enums.EventType;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.events.OrderEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class PostpaidPendingHandler implements OrderStateHandler {
    @Override
    public boolean canHandle(OrderStatus status, EventType eventType) {
        return false;
    }

    @Override
    public void handle(Order order, OrderEvent event) {
        switch (event.getEventType()) {
            case CONFIRM_DELIVERY:
                // 启动支付倒计时
                order.setPaymentDueDate(LocalDateTime.now().plusDays(7));
                break;
            case PAYMENT_CONFIRMED:
                order.setStatus(OrderStatus.PAID);
                break;
            case TRIGGER_OVERDUE:
                // 启动催收流程
                break;
        }
    }
}
