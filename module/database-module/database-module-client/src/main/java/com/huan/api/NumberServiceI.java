package com.huan.api;

import com.alibaba.cola.dto.SingleResponse;
import com.huan.dto.NumberByPrefixQry;

public interface NumberServiceI {

    SingleResponse<String> getNumber(NumberByPrefixQry numberByPrefixQry);
}
