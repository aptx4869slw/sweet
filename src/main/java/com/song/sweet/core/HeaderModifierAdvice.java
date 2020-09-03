package com.song.sweet.core;

import com.song.sweet.utils.JWTUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Liwen
 * @Description // 用于ResponseBody注解的controller方法处理的响应中自定义响应头（添加token信息）
 * @Version 1.0.0
 * @create 2020-09-03 13:20
 */
@ControllerAdvice
public class HeaderModifierAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ServletServerHttpRequest SSHRequest = (ServletServerHttpRequest) request;
        ServletServerHttpResponse SSHResponse = (ServletServerHttpResponse) response;
        if (SSHRequest == null || SSHResponse == null || SSHRequest.getServletRequest() == null || SSHResponse.getServletResponse() == null) {
            return body;
        }
        // 响应头添加token
        HttpServletRequest req = SSHRequest.getServletRequest();
        HttpServletResponse resp = SSHResponse.getServletResponse();
        String tokenHeader = JWTUtils.TOKEN_HEADER;
        Object token = req.getAttribute(tokenHeader);
        //响应头中不包含token，且request域中有token值
        if (resp.containsHeader(tokenHeader) == false && token != null) {
            resp.setHeader(tokenHeader, JWTUtils.TOKEN_PREFIX + token);
        }
        return body;
    }
}
