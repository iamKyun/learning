package com.iamkyun.rocketmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaidEvent implements Serializable {
    private String orderId;

    private BigDecimal paidMoney;
}
