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
public class PrepaidAction {
//    @Autowired
//    private PaymentService paymentService;
//
//    @Autowired
//    private InventoryService inventoryService;

    public Action<OrderStates, OrderEvents> initializePayment() {
        return context -> {
            Order order = context.getMessage().getHeaders().get("order", Order.class);
//            paymentService.createPaymentSession(order);
        };
    }

    public Action<OrderStates, OrderEvents> processPayment() {
        return context -> {
//            PaymentResult result = context.getMessage()
//                    .getHeaders().get("paymentResult", PaymentResult.class);
//            paymentService.confirmPayment(result);
        };
    }

    public Action<OrderStates, OrderEvents> releaseInventory() {
        return context -> {
//            Order order = context.getMessage().getHeaders().get("order", Order.class);
//            inventoryService.releaseStock(order.getItems());
        };
    }
}
