package com.jiangy.springbootrabbitmq.domain.order.events;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.exception.InvalidOrderEventException;

import java.time.LocalDateTime;

/**
 * // 基础事件验证（通用规则）
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class BaseEventValidator implements EventValidator {
    @Override
    public void validate(Order order, OrderEvent event) {
        // 基础规则校验
        if (event.getEventType() == null) {
            throw new InvalidOrderEventException("事件类型不能为空");
        }

        if (event.getTimestamp().isAfter(LocalDateTime.now())) {
            throw new InvalidOrderEventException("事件时间不能晚于当前时间");
        }

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new InvalidOrderEventException("已取消订单不能处理事件");
        }
    }
}
