package com.jiangy.springbootrabbitmq;

import com.jiangy.springbootrabbitmq.domain.statemachine.OrderEvents;
import com.jiangy.springbootrabbitmq.domain.statemachine.OrderStateMachineService;
import com.jiangy.springbootrabbitmq.domain.statemachine.OrderStates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StateMachineTest {
    @Autowired
    private OrderStateMachineService orderService;

    @Test
    public void testOrderFlow() {
        // 初始状态应为 UNPAID
        Assertions.assertEquals(OrderStates.UNPAID, orderService.getCurrentState());

        // 触发支付事件
        orderService.processEvent(OrderEvents.PAY);
        Assertions.assertEquals(OrderStates.PAID, orderService.getCurrentState());

        // 触发发货事件
        orderService.processEvent(OrderEvents.SHIP);
        Assertions.assertEquals(OrderStates.SHIPPED, orderService.getCurrentState());

        // 触发确认收货事件
        orderService.processEvent(OrderEvents.CONFIRM_RECEIVE);
        Assertions.assertEquals(OrderStates.RECEIVED, orderService.getCurrentState());
    }
}

