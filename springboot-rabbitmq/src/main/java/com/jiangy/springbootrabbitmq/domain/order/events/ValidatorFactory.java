package com.jiangy.springbootrabbitmq.domain.order.events;

import com.jiangy.springbootrabbitmq.domain.order.enums.EventType;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public class ValidatorFactory {
    private static final Map<EventType, EventValidator> validators = new HashMap<>(16);

    static {
        validators.put(EventType.PAYMENT_CONFIRMED, new PaymentEventValidator());
        validators.put(EventType.CONFIRM_DELIVERY, new DeliveryEventValidator());
        // 注册其他事件验证器...
    }

    public static EventValidator getValidator(EventType eventType) {
        return validators.getOrDefault(eventType, new BaseEventValidator());
    }
}
