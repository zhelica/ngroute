package com.cmos.springcloud.service.impl;

import com.cmos.springcloud.common.constant.HttpStatus;
import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lizhe
 * @date 2021-05-08 9:07
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public AjaxResult selectUserByUserName(Map<String, Object> map) {
        return AjaxResult.error(HttpStatus.ERROR,"服务端繁忙，请稍后再试");
    }

    @Override
    public AjaxResult register(Map<String, Object> map) {
        return AjaxResult.error(HttpStatus.ERROR,"服务端繁忙，请稍后再试");
    }

    @Override
    public AjaxResult login(Map<String, Object> map) {
        return AjaxResult.error(HttpStatus.ERROR,"服务端繁忙，请稍后再试");
    }
}
