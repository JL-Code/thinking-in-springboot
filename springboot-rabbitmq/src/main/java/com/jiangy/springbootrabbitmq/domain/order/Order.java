package com.jiangy.springbootrabbitmq.domain.order;

import com.jiangy.springbootrabbitmq.domain.order.enums.OrderStatus;
import com.jiangy.springbootrabbitmq.domain.order.enums.OrderType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
@Data
public class Order {
    private String orderId;
    private OrderType orderType;
    private OrderStatus status;
    private BigDecimal amount;
    private LocalDateTime paymentDueDate;
    private String paymentTransactionId;
}
