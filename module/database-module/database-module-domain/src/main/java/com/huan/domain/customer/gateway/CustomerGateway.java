package com.huan.domain.customer.gateway;

import com.huan.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
