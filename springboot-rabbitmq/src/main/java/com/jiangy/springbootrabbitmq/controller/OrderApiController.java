package com.jiangy.springbootrabbitmq.controller;

import com.jiangy.springbootrabbitmq.domain.statemachine.OrderStateMachineService;
import com.jiangy.springbootrabbitmq.domain.statemachine.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@RequestMapping("/api")
@RestController
public class OrderApiController {
    @Autowired
    private OrderStateMachineService orderService;

    @RequestMapping("/orders")
    public OrderStates processEvent() {
        return orderService.getCurrentState();
    }
}
