package com.huan.web;

import com.alibaba.cola.dto.SingleResponse;
import com.huan.api.NumberServiceI;
import com.huan.dto.NumberByPrefixQry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class NumberController {

    @Resource
    private NumberServiceI numberService;

    @GetMapping(value = "/number")
    public SingleResponse<String> getNumber(@RequestParam(required = true) String prefix){
        NumberByPrefixQry numberByPrefixQry = new NumberByPrefixQry();
        numberByPrefixQry.setPrefix(prefix);
        return numberService.getNumber(numberByPrefixQry);
    }
}
