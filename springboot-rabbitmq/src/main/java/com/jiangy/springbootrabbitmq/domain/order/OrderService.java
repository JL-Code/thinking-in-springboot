package com.jiangy.springbootrabbitmq.domain.order;

import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderType;
import com.jiangy.springbootrabbitmq.domain.order.events.EventValidator;
import com.jiangy.springbootrabbitmq.domain.order.events.OrderEvent;
import com.jiangy.springbootrabbitmq.domain.order.events.ValidatorFactory;
import com.jiangy.springbootrabbitmq.domain.order.exception.InvalidOrderEventException;
import com.jiangy.springbootrabbitmq.domain.order.handler.StateHandlerRegistry;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Slf4j
@Service
public class OrderService {
    private final Map<OrderType, PaymentStrategy> strategies;

    @Resource
    private StateHandlerRegistry stateHandlerRegistry;

    public OrderService() {
        strategies = new EnumMap<>(OrderType.class);
        strategies.put(OrderType.PREPAID, new PrepaidStrategy());
        strategies.put(OrderType.POSTPAID, new PostpaidStrategy());
    }

    public Order createOrder(OrderType type, BigDecimal amount) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderType(type);
        order.setAmount(amount);
        order.setStatus(OrderStatus.CREATED);

        PaymentStrategy strategy = strategies.get(type);
        strategy.validate(order); // 先验证

        // 保存到数据库
//        orderRepository.save(order);

        // 处理支付逻辑
        strategy.processPayment(order);
//        orderRepository.update(order);

        return order;
    }

    // 状态机处理
    public void handleOrderStateTransition(Order order, OrderEvent event) {
        try {
            // 获取对应验证器
            EventValidator validator = ValidatorFactory.getValidator(event.getEventType());

            // 执行验证
            validator.validate(order, event);

            // 处理状态转换
            stateHandlerRegistry.processEvent(order, event);

        } catch (InvalidOrderEventException e) {
            log.error("事件验证失败: {}", e.getMessage());
            // 触发异常处理流程
            handleInvalidEvent(order, event, e);
        }
    }

    private void handleInvalidEvent(Order order, OrderEvent event, Exception e) {
        // 记录异常事件
//        auditService.logInvalidEvent(order, event, e);

        // 发送告警通知
//        alertService.notify(
//                "非法事件告警",
//                String.format("订单 %s 收到非法事件: %s", order.getOrderId(), event)
//        );

        // 特殊处理：自动锁定可疑订单
//        if (e.getMessage().contains("支付金额异常")) {
//            fraudDetectionService.markSuspicious(order);
//        }
    }
}
