package com.cmos.springcloud.service;

import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.config.FeignRequestConfig;
import com.cmos.springcloud.service.impl.AlgorithmServcieImpl;
import com.cmos.springcloud.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author lizhe
 * @date 2021-05-08 22:16
 *
 */
@Component
@FeignClient(value = "ngroute-server",fallback = AlgorithmServcieImpl.class)
public interface AlgorithmServcie {
    @PostMapping("/algorithm/CollaborativeFiltering")
    AjaxResult filtering(@RequestBody TreeMap<String,Object> requestMap);
}
