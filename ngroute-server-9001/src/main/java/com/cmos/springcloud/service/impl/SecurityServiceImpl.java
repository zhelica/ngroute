package com.cmos.springcloud.service.impl;

import com.cmos.springcloud.mapper.SecurityMapper;
import com.cmos.springcloud.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhe
 * @date 2021-08-11 14:47
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private SecurityMapper securityMapper;
    @Override
    public List<String> selectSecurity(String userId) {
        return securityMapper.selectSecurity(userId);
    }
}
