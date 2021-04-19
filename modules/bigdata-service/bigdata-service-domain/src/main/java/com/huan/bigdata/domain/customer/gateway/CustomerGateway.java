package com.huan.bigdata.domain.customer.gateway;

import com.huan.bigdata.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
