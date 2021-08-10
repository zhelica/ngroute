package com.cmos.springcloud.service;

import com.cmos.springcloud.common.domain.T_User;
import org.springframework.stereotype.Service;

/**
 * @author lizhe
 * @date 2021-05-07 20:46
 */
public interface UserService {
    public int UserRegister(T_User user);
    public T_User selectUserByUserName(String account);
}
