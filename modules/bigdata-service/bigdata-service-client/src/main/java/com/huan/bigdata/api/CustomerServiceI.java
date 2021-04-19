package com.huan.bigdata.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.huan.bigdata.dto.CustomerAddCmd;
import com.huan.bigdata.dto.CustomerListByNameQry;
import com.huan.bigdata.dto.data.CustomerDTO;

public interface CustomerServiceI {

    public Response addCustomer(CustomerAddCmd customerAddCmd);

    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
