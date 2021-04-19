package com.huan.number.gatewayimpl;

import com.huan.domain.number.gateway.NumberGateway;
import com.huan.number.gatewayimpl.database.NumberMapper;
import com.huan.number.gatewayimpl.database.dataobject.NumberDO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class NumberGatewayImpl implements NumberGateway {

    @Resource
    private NumberMapper numberMapper;

    @Override
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = Exception.class)
    public long getNumber(String prefix) {
        NumberDO numberDO = numberMapper.selectAndLock(prefix);
        int result = 0;
        if (numberDO == null) {
            numberDO = new NumberDO(prefix, 1);
            result = numberMapper.create(numberDO);
        } else {
            numberDO.setValue(numberDO.getValue() + 1);
            result = numberMapper.update(numberDO);
        }

        if (result != 1) {
            throw new RuntimeException("添加失败");
        }

        return numberDO.getValue();
    }
}
