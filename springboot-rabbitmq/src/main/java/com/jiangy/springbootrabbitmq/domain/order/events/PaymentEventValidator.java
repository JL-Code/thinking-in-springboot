package com.jiangy.springbootrabbitmq.domain.order.events;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.exception.InvalidOrderEventException;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class PaymentEventValidator extends BaseEventValidator {
    @Override
    public void validate(Order order, OrderEvent event) {
        super.validate(order, event);

        // 支付金额校验
        if (event.getPayload() instanceof Map) {
            Map<?, ?> payload = (Map<?, ?>) event.getPayload();
            BigDecimal eventAmount = (BigDecimal) payload.get("amount");
            if (eventAmount.compareTo(order.getAmount()) != 0) {
                throw new InvalidOrderEventException("支付金额与订单金额不符");
            }
        }
    }
}
