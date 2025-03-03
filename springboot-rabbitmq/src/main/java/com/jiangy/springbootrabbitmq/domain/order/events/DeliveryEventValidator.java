package com.jiangy.springbootrabbitmq.domain.order.events;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.exception.InvalidOrderEventException;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class DeliveryEventValidator extends BaseEventValidator {
    @Override
    public void validate(Order order, OrderEvent event) {
        super.validate(order, event);

        // 物流单号校验
        if (event.getPayload() == null) {
            throw new InvalidOrderEventException("物流事件必须包含快递单号");
        }

        if (!isValidTrackingNumber(event.getPayload().toString())) {
            throw new InvalidOrderEventException("无效的快递单号格式");
        }
    }

    private boolean isValidTrackingNumber(String trackingNumber) {
        return trackingNumber.matches("[A-Z]{2}\\d{9}[A-Z]");
    }
}
