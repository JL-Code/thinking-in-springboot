package com.jiangy.springbootrabbitmq.domain.order;

import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.exception.ValidationException;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class PrepaidStrategy implements PaymentStrategy {
    @Override
    public void validate(Order order) {
        // 预付款订单需要立即验证支付能力
        if (order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Invalid amount for prepaid order");
        }
    }

    @Override
    public void processPayment(Order order) {
        // 调用支付网关接口
//        PaymentGatewayResponse response = PaymentGateway.process(
//                order.getAmount(),
//                "PREPAID_" + order.getOrderId()
//        );
//
//        order.setPaymentTransactionId(response.getTransactionId());
//        order.setStatus(OrderStatus.PAID);
//        startPaymentTimeoutTimer(order, Duration.ofMinutes(15));
    }

    @Override
    public void handleTimeout(Order order) {
        if (order.getStatus() == OrderStatus.PAYMENT_PENDING) {
            order.setStatus(OrderStatus.CANCELLED);
            // 触发库存释放等后续操作
        }
    }
}
