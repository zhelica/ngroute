package com.cmos.springcloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.common.domain.T_User;
import com.cmos.springcloud.common.utils.StringUtils;
import com.cmos.springcloud.domain.LoginUser;
import com.cmos.springcloud.service.SecurityService;
import com.cmos.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lizhe
 * @date 2021-05-18 8:56
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        T_User user = userService.selectUserByUserName(account);
        if(StringUtils.isNull(account)){
            throw new RuntimeException("异常");
        }
        return createLoginUser(user);
    }
    public UserDetails createLoginUser(T_User user)
    {
        LoginUser loginUser = new LoginUser(user);
        List<String> securityList = securityService.selectSecurity(user.getUserId());
        System.out.println("securityList===="+securityList);
        Set<String> permissions = new HashSet<String>();
        permissions.addAll(securityList);
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
