package com.jiangy.springbootrabbitmq.domain.order.state;

/**
 * <p>创建时间: 2025/3/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">蒋勇</a>
 */
public enum OrderEvents {
    INITIATE_PAYMENT,   // 发起支付
    PAYMENT_CONFIRMED,  // 支付确认
    PAYMENT_TIMEOUT,    // 支付超时
    SHIP_GOODS,         // 发货操作
    CONFIRM_DELIVERY,   // 确认签收
    TRIGGER_OVERDUE,    // 触发逾期
    CANCEL_ORDER        // 取消订单
}
