package com.huan.bigdata.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.huan.bigdata.api.CustomerServiceI;
import com.huan.bigdata.dto.CustomerAddCmd;
import com.huan.bigdata.dto.CustomerListByNameQry;
import com.huan.bigdata.dto.data.CustomerDTO;
import org.springframework.stereotype.Service;

import com.huan.bigdata.customer.executor.CustomerAddCmdExe;
import com.huan.bigdata.customer.executor.query.CustomerListByNameQryExe;

import javax.annotation.Resource;

@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}
