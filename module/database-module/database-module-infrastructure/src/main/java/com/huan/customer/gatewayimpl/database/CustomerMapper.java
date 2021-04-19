package com.huan.customer.gatewayimpl.database;

import com.huan.customer.gatewayimpl.database.dataobject.CustomerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper{

  public CustomerDO getById(String customerId);
}
