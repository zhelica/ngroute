package com.cmos.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author lizhe
 * @date 2021-05-10 16:01
 */
@Mapper
public interface IMessageProductMapper {
    public int addProduct(Map<String, Object> map);
}
