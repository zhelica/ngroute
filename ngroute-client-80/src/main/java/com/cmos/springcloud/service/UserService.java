package com.cmos.springcloud.service;

import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.config.FeignRequestConfig;
import com.cmos.springcloud.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author lizhe
 * @date 2021-05-08 9:07
 */
@Component
@FeignClient(value = "ngroute-server",fallback = UserServiceImpl.class)
public interface UserService {
    //注册
    @PostMapping(value = "/system/account/register")
    public AjaxResult register(@RequestBody Map<String,Object> map);
    @PostMapping(value = "/system/account/selectUserByUserName")
    public AjaxResult selectUserByUserName(@RequestBody Map<String,Object> map);
    @PostMapping(value = "/system/account/login")
    public AjaxResult login(@RequestBody Map<String,Object> map);
}
