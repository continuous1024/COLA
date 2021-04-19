package com.huan.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

@Data
public class NumberByPrefixQry extends Query {
    private String prefix;
}
