package com.huan.bigdata.domain.customer.gateway;

import com.huan.bigdata.domain.customer.Customer;
import com.huan.bigdata.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
