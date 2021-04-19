package com.huan.customer.gatewayimpl;

import com.huan.customer.convertor.CustomerConvertor;
import com.huan.customer.gatewayimpl.database.CustomerMapper;
import com.huan.customer.gatewayimpl.database.dataobject.CustomerDO;
import com.huan.domain.customer.Customer;
import com.huan.domain.customer.gateway.CustomerGateway;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomerGatewayImpl implements CustomerGateway {
    @Resource
    private CustomerMapper customerMapper;

    public Customer getByById(String customerId){
      CustomerDO customerDO = customerMapper.getById(customerId);
      if (customerDO == null) {
          return null;
      }
      //Convert to Customer
      return CustomerConvertor.toEntity(customerDO);
    }
}
