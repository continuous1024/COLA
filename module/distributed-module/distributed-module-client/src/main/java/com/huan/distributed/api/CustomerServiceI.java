package com.huan.distributed.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.huan.distributed.dto.CustomerAddCmd;
import com.huan.distributed.dto.CustomerListByNameQry;
import com.huan.distributed.dto.data.CustomerDTO;

public interface CustomerServiceI {

    public Response addCustomer(CustomerAddCmd customerAddCmd);

    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
