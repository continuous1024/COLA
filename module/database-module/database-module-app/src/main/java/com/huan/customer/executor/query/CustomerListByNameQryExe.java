package com.huan.customer.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import com.huan.domain.customer.Customer;
import com.huan.domain.customer.gateway.CustomerGateway;
import com.huan.dto.CustomerListByNameQry;
import com.huan.dto.data.CustomerDTO;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class CustomerListByNameQryExe{

    @Resource
    private CustomerGateway customerGateway;

    public MultiResponse<CustomerDTO> execute(CustomerListByNameQry cmd) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerGateway.getByById(cmd.getName());
        if (customer != null) {
            BeanUtils.copyProperties(customer, customerDTO);
        }
        customerDTOList.add(customerDTO);

        return MultiResponse.of(customerDTOList);
    }
}
