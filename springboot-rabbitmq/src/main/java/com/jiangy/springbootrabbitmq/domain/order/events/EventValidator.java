package com.jiangy.springbootrabbitmq.domain.order.events;

import com.jiangy.springbootrabbitmq.domain.order.Order;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public interface EventValidator {
    void validate(Order order, OrderEvent event);
}
