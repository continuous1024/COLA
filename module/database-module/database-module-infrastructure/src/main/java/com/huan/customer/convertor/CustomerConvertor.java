package com.huan.customer.convertor;

import com.huan.customer.gatewayimpl.database.dataobject.CustomerDO;
import com.huan.domain.customer.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerConvertor {
    public static Customer toEntity(CustomerDO customerDO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDO, customer);
        return customer;
    }
}
