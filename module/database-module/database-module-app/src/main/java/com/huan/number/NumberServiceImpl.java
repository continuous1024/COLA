package com.huan.number;

import com.alibaba.cola.dto.SingleResponse;
import com.huan.api.NumberServiceI;
import com.huan.dto.NumberByPrefixQry;
import com.huan.number.executor.query.NumberByPrefixQryExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NumberServiceImpl implements NumberServiceI {

    @Resource
    private NumberByPrefixQryExe numberByPrefixQryExe;

    @Override
    public SingleResponse<String> getNumber(NumberByPrefixQry numberByPrefixQry) {
        return numberByPrefixQryExe.execute(numberByPrefixQry);
    }
}
