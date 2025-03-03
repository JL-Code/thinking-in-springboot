package com.jiangy.springbootrabbitmq.domain.order.state;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderType;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;


/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class PostpaidGuard implements Guard<OrderStates, OrderEvents> {

    @Override
    public boolean evaluate(StateContext<OrderStates, OrderEvents> context) {
        Order order = context.getMessage().getHeaders().get("order", Order.class);
        assert order != null;
        return order.getOrderType() == OrderType.POSTPAID;
    }
}