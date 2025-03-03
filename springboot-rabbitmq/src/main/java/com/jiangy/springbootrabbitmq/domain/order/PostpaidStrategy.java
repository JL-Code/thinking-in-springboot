package com.jiangy.springbootrabbitmq.domain.order;

import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;

import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class PostpaidStrategy implements PaymentStrategy {
    @Override
    public void validate(Order order) {
        // 后付款需要信用检查
//        CreditService creditService = new CreditService();
//        if (!creditService.checkCredit(order.getCustomerId(), order.getAmount())) {
//            throw new ValidationException("Credit check failed");
//        }
    }

    @Override
    public void processPayment(Order order) {
        // 后付款订单不会立即支付
        order.setStatus(OrderStatus.PAYMENT_PENDING);
        order.setPaymentDueDate(LocalDateTime.now().plusDays(7));
    }

    @Override
    public void handleTimeout(Order order) {
        if (LocalDateTime.now().isAfter(order.getPaymentDueDate())) {
            order.setStatus(OrderStatus.OVERDUE);
            // 触发催收流程
        }
    }
}
