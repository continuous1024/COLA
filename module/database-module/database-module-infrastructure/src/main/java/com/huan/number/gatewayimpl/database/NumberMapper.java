package com.huan.number.gatewayimpl.database;

import com.huan.number.gatewayimpl.database.dataobject.NumberDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NumberMapper {
    NumberDO selectAndLock(String prefix);

    int create(NumberDO numberDO);

    int update(NumberDO numberDO);
}
