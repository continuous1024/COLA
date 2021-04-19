package com.huan.number.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import com.huan.domain.number.gateway.NumberGateway;
import com.huan.dto.NumberByPrefixQry;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NumberByPrefixQryExe {

    @Resource
    private NumberGateway numberGateway;

    public SingleResponse<String> execute(NumberByPrefixQry cmd) {
        long number = numberGateway.getNumber(cmd.getPrefix());
        String result = cmd.getPrefix() + number;
        return SingleResponse.of(result);
    }
}
