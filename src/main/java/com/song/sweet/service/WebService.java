package com.song.sweet.service;


import com.song.sweet.service.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WebService {

    private final Logger logger = LoggerFactory.getLogger(WebService.class);

    @Value("${gaode.map.api.key}")
    private String gaodeMapKey;

    /**
     * 保存方法
     *
     * @param request
     * @return
     */
    public void getProvinceByIP(HttpServletRequest request){
        String ipAddress = CommonUtils.getIpAddress(request);
        logger.info("Send a request to get province data ! key: {} ip : {} ", gaodeMapKey, ipAddress);
//        JSONObject data = gaodeMapFeignClient.getProvinceByIP(gaodeMapKey, ipAddress);
//        logger.info("Data obtained according to IP ! data: {} ", JSONObject.toJSONString(data));
    }
}
