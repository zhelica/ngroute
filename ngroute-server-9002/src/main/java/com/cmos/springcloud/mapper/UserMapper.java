package com.cmos.springcloud.mapper;

import com.cmos.springcloud.common.domain.T_User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lizhe
 * @date 2021-05-07 20:46
 */
@Mapper
public interface UserMapper {
    public int UserRegister(T_User user);
    public T_User selectUserByUserName(String account);
}
