package com.cmos.springcloud.service.impl;

import com.cmos.springcloud.common.domain.T_User;
import com.cmos.springcloud.mapper.UserMapper;
import com.cmos.springcloud.service.IMessageProduct;
import com.cmos.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lizhe
 * @date 2021-05-07 20:53
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IMessageProduct messageProduct;
    @Override
    public int UserRegister(T_User user) {
        int i = userMapper.UserRegister(user);
        if(i>0){
            //赠送商品
            messageProduct.send(user.getUserId());
        }
        return i;
    }

    @Override
    public T_User selectUserByUserName(String account) {
        return userMapper.selectUserByUserName(account);
    }
}
