package com.song.sweet.core;

import com.song.sweet.model.User;
import com.song.sweet.utils.CommonUtils;
import com.song.sweet.utils.JWTUtils;
import com.song.sweet.utils.RedisUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*", filterName = "ApiFilter")
public class ApiFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ApiFilter.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String version = "1.0.0";
        String method = request.getMethod();
        UserAgent userAgent = CommonUtils.getUserAgent(request);
        if (userAgent.getBrowserVersion() != null) {
            version = userAgent.getBrowserVersion().getMajorVersion();
        }
        logger.info("Http Request Info : {" +
                "\"Method\":\"" + method +
                "\",\"URL\":\"" + CommonUtils.getRequestURI(request) +
                "\",\"IpAddress\":\"" + CommonUtils.getIpAddress(request) +
                "\",\"Content-Type\":\"" + CommonUtils.getContentType(request) +
                "\",\"User-System\":\"" + userAgent.getOperatingSystem() +
                "\",\"User-Browser\":\"" + userAgent.getBrowser() + version +
                "\"}");

        String token = request.getHeader(JWTUtils.TOKEN_HEADER);
        boolean invalid = redisUtils.hasKey("tokenBlackList:" + token);
        if (StringUtils.isNotBlank(token) && token.startsWith(JWTUtils.TOKEN_PREFIX) && JWTUtils.isExpiration(token) && !invalid) {
            token = token.substring(JWTUtils.TOKEN_PREFIX.length());
            if (JWTUtils.canRefresh(token)) {
                redisUtils.setCache("tokenBlackList:" + token, 1, JWTUtils.getExpireTime(token) / 1000);
                token = JWTUtils.refreshToken(token, false);
            }
            User user = JWTUtils.getUserInfo(token);
            if (user != null) {
                request.setAttribute("currentUser", user);
            }
            response.setStatus(HttpStatus.ACCEPTED.value());
            request.setAttribute(JWTUtils.TOKEN_HEADER, token);
            response.setContentType("application/json;charset=UTF-8");
            // 允许跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许自定义请求头token(允许head跨域)
            response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
            // 允许前端拿到的header
            response.setHeader("Access-Control-Expose-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
            filterChain.doFilter(request, response);
        } else if (StringUtils.isNotBlank(method) && method.equalsIgnoreCase("OPTIONS")) {
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,accept,authorization,content-type");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
