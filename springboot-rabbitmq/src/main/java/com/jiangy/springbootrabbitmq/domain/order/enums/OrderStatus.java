package com.jiangy.springbootrabbitmq.domain.order.enums;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public enum OrderStatus {
    CREATED,          // 订单已创建
    PAYMENT_PENDING,  // 待支付（预付款）
    PAID,             // 已支付
    SHIPPED,          // 已发货
    DELIVERED,        // 已签收
    CANCELLED,        // 已取消
    OVERDUE           // 逾期未付（后付款）
}
