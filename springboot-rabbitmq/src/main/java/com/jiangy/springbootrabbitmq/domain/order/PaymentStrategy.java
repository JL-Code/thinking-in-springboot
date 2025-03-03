package com.jiangy.springbootrabbitmq.domain.order;

import com.jiangy.springbootrabbitmq.domain.order.exception.PaymentException;
import com.jiangy.springbootrabbitmq.domain.order.exception.ValidationException;

/**
 * 支付策略接口
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public interface PaymentStrategy {
    void validate(Order order) throws ValidationException;

    void processPayment(Order order) throws PaymentException;

    void handleTimeout(Order order);
}
