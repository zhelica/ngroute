package com.cmos.springcloud.service.impl;

import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.service.AlgorithmServcie;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lizhe
 * @date 2021-05-08 22:16
 */
@Component
public class AlgorithmServcieImpl implements AlgorithmServcie {
    @Override
    public AjaxResult filtering(TreeMap<String, Object> requestMap) {
        return AjaxResult.error(444,"接口太火爆了,客户端繁忙！");
    }
}
