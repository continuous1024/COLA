package com.huan.stream.domain.customer.gateway;

import com.huan.stream.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
