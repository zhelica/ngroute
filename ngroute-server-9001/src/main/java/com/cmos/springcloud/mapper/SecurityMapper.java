package com.cmos.springcloud.mapper;

import com.cmos.springcloud.common.domain.T_User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2021-05-07 20:46
 */
@Mapper
public interface SecurityMapper {
    public List<String> selectSecurity(String userId);
}
