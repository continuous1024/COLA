package com.huan.number.gatewayimpl.database.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberDO {
    private String prefix;
    private long value;
}
