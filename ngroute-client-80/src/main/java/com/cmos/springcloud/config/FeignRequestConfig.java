package com.cmos.springcloud.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author lizhe
 * @date 2021-08-10 10:00
 */
@Configuration
public class FeignRequestConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {

        HttpServletRequest request = getServletRequest();
        if (null == request) {
            return;
        }

        template.header("Authorization", getHeaders(request));
    }

    private HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private String getHeaders(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }


}
