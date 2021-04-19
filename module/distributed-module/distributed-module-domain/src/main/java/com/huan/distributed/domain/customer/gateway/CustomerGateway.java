package com.huan.distributed.domain.customer.gateway;

import com.huan.distributed.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
