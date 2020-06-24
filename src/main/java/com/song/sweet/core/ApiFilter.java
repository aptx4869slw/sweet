package com.song.sweet.core;

import com.song.sweet.service.utils.CommonUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String method = request.getMethod();
        UserAgent userAgent = CommonUtils.getUserAgent(request);
        logger.info("Http Request Info : {" +
                "\"Method\":\"" + method +
                "\",\"URL\":\"" + CommonUtils.getRequestURI(request) +
                "\",\"IpAddress\":\"" + CommonUtils.getIpAddress(request) +
                "\",\"Content-Type\":\"" + CommonUtils.getContentType(request) +
                "\",\"User-System\":\"" + userAgent.getOperatingSystem() +
                "\",\"User-Browser\":\"" + userAgent.getBrowser() + userAgent.getBrowserVersion().getMajorVersion() +
                "\"}");
        if (StringUtils.isNotBlank(method) && method.equalsIgnoreCase("options")) {
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,accept,authorization,content-type");
            response.setStatus(HttpStatus.ACCEPTED.value());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
