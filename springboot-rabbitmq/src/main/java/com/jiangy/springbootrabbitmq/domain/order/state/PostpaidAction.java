package com.jiangy.springbootrabbitmq.domain.order.state;

import com.jiangy.springbootrabbitmq.domain.order.Order;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Component
public class PostpaidAction {

//    @Autowired
//    private LogisticsService logisticsService;
//
//    @Autowired
//    private CollectionService collectionService;

    public Action<OrderStates, OrderEvents> shipWithoutPayment() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);
//            logisticsService.dispatch(order);
        };
    }

    public Action<OrderStates, OrderEvents> startPaymentTimer() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);
//            paymentTimerService.schedulePaymentReminder(order, 7); // 7天后提醒
        };
    }

    public Action<OrderStates, OrderEvents> initiateCollection() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);
//            collectionService.startCollectionProcess(order);
        };
    }
}
