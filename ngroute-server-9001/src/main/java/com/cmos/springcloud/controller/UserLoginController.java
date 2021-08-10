package com.cmos.springcloud.controller;

import cn.hutool.core.lang.UUID;
import com.cmos.springcloud.common.constant.HttpStatus;
import com.cmos.springcloud.common.domain.AjaxResult;
import com.cmos.springcloud.common.domain.T_User;
import com.cmos.springcloud.common.utils.RedisCache;
import com.cmos.springcloud.domain.LoginUser;
import com.cmos.springcloud.service.TokenService;
import com.cmos.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lizhe
 * @date 2021-05-07 20:40
 */
@RestController
@RequestMapping("/system/account")
@Slf4j
public class UserLoginController {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @PostMapping(value = "/register")
    public AjaxResult register(@RequestBody Map<String,Object> map) {
        Map<String, Object> params = (Map<String, Object>) map.get("params");
        String userName = params.get("userName") == null ? "" : String.valueOf(params.get("userName"));
        String account = params.get("account") == null ? "" : String.valueOf(params.get("account"));
        String password = params.get("password") == null ? "" : String.valueOf(params.get("password"));
        String userId = UUID.randomUUID().toString();
        T_User user = new T_User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setAccount(account);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        int i = userService.UserRegister(user);
        if (i>0){
            return AjaxResult.success("注册成功");
        }else {
            return AjaxResult.error(HttpStatus.ERROR,"注册失败");
        }
    }
    @PostMapping(value = "/selectUserByUserName")
    public AjaxResult selectUserByUserName(@RequestBody Map<String,Object> map) {
        Map<String, Object> params = (Map<String, Object>) map.get("params");
        String account = params.get("account") == null ? "" : String.valueOf(params.get("account"));
        T_User t_user = userService.selectUserByUserName(account);
        //redisCache.setCacheObject(account,t_user);
        return AjaxResult.success("查询成功",t_user);
    }
    @PostMapping(value = "/login")
    public AjaxResult login(@RequestBody Map<String,Object> map) {
        // 用户验证
        Authentication authentication = null;
        Map<String, Object> params = (Map<String, Object>) map.get("params");
        String username = params.get("account") == null ? "" : String.valueOf(params.get("account"));
        String password = params.get("password") == null ? "" : String.valueOf(params.get("password"));
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return AjaxResult.success(tokenService.createToken(loginUser));
    }
}
