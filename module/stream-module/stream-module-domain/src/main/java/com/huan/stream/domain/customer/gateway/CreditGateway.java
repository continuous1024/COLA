package com.huan.stream.domain.customer.gateway;

import com.huan.stream.domain.customer.Customer;
import com.huan.stream.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
