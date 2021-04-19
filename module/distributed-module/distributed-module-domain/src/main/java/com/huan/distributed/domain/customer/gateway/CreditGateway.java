package com.huan.distributed.domain.customer.gateway;

import com.huan.distributed.domain.customer.Customer;
import com.huan.distributed.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
