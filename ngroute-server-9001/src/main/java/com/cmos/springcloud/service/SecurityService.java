package com.cmos.springcloud.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lizhe
 * @date 2021-05-07 20:46
 */
public interface SecurityService {
    public List<String> selectSecurity(String userId);
}
