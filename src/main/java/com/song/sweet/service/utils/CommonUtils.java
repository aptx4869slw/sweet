package com.song.sweet.service.utils;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    // 获取服务器名
    public static String getServerName(HttpServletRequest request) {
        return request.getServerName();
    }

    // 获取服务器端口号
    public static Integer getServerPort(HttpServletRequest request) {
        return request.getServerPort();
    }

    // 获取项目名
    public static String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    // 获取项目名
    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    // 获取访问内容类型
    public static String getContentType(HttpServletRequest request) {
        return request.getContentType();
    }

    // 获取用户软件信息
    public static UserAgent getUserAgent(HttpServletRequest request) {
        String agentHeader = request.getHeader("User-Agent");
        return UserAgent.parseUserAgentString(agentHeader);
    }

    // 获取请求的IP地址
    public static String getIpAddress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }
        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    // 获取请求的浏览器和操作系统信息
    public static String getSystemAndBrowser(HttpServletRequest request) {
        String system = "";
        String browser = "";
        String userAgent = request.getHeader("User-Agent").toLowerCase();

        //=================================System Info=====================================
        if (userAgent.toLowerCase().contains("windows")) {
            system = "Windows";
        } else if (userAgent.toLowerCase().contains("mac")) {
            system = "Mac";
        } else if (userAgent.toLowerCase().contains("x11")) {
            system = "Unix";
        } else if (userAgent.toLowerCase().contains("android")) {
            system = "Android";
        } else if (userAgent.toLowerCase().contains("iphone")) {
            system = "IPhone";
        } else {
            system = "UnKnown, More-Info: " + userAgent;
        }

        //=================================Browser Info=====================================
        if (userAgent.contains("edge")) {
            browser = (userAgent.substring(userAgent.indexOf("edge")).split(" ")[0]).replace("/", "-");
        } else if (userAgent.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("msie")).split(";")[0];
            browser = substring.split(" ")[0].replace("msie", "IE") + "-" + substring.split(" ")[1];
        } else {
            String[] version = userAgent.substring(userAgent.indexOf("version")).split(" ");
            if (userAgent.contains("safari") && userAgent.contains("version")) {
                browser = (userAgent.substring(userAgent.indexOf("safari")).split(" ")[0]).split("/")[0]
                        + "-" + (version[0]).split("/")[1];
            } else if (userAgent.contains("opr") || userAgent.contains("opera")) {
                if (userAgent.contains("opera")) {
                    browser = (userAgent.substring(userAgent.indexOf("opera")).split(" ")[0]).split("/")[0]
                            + "-" + (version[0]).split("/")[1];
                } else if (userAgent.contains("opr")) {
                    browser = ((userAgent.substring(userAgent.indexOf("opr")).split(" ")[0]).replace("/", "-"))
                            .replace("opr", "opr");
                }
            } else if (userAgent.contains("chrome")) {
                browser = (userAgent.substring(userAgent.indexOf("chrome" +
                        "")).split(" ")[0]).replace("/", "-");
            } else if ((userAgent.contains("mozilla/7.0")) || (userAgent.contains("netscape6")) ||
                    (userAgent.contains("mozilla/4.7")) || (userAgent.contains("mozilla/4.78")) ||
                    (userAgent.contains("mozilla/4.08")) || (userAgent.contains("mozilla/3"))) {
                browser = "Netscape-?";
            } else if (userAgent.contains("firefox")) {
                browser = (userAgent.substring(userAgent.indexOf("firefox")).split(" ")[0]).replace("/", "-");
            } else if (userAgent.contains("rv")) {
                String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
                browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
            } else {
                browser = "UnKnown, More-Info: " + userAgent;
            }
        }
        return system + browser;
    }

    // 获取请求的所在省份城市
    public static String getProvinceInformation(HttpServletRequest request) {
        String ipAddress = getIpAddress(request);
        return ipAddress;
    }

}
