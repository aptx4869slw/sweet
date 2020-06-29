package com.song.sweet.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.song.sweet.model.GaodeMapData;
import com.song.sweet.model.LandTrack;
import com.song.sweet.repository.LandTrackRepository;
import com.song.sweet.service.utils.CommonUtils;
import com.song.sweet.service.utils.HttpClientUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class WebService {

    private final Logger logger = LoggerFactory.getLogger(WebService.class);

    @Value("${gaode.map.api.key}")
    private String gaodeMapKey;

    @Value("${gaode.map.api.url}")
    private String gaodeMapUrl;

    @Autowired
    private LandTrackRepository landTrackRepository;

    /**
     * 保存方法
     *
     * @param request
     * @return
     */
    public void getProvinceByIP(HttpServletRequest request) {
        String ipAddress = CommonUtils.getIpAddress(request);
        UserAgent userAgent = CommonUtils.getUserAgent(request);
        logger.info("Send a request to get province data ! key: {} ip : {} ", gaodeMapKey, ipAddress);
        String url = gaodeMapUrl + "?ip=" + ipAddress + "&key=" + gaodeMapKey;
        String result = HttpClientUtils.doGet(url, "UTF-8");
        GaodeMapData gaodeMapData = JSON.parseObject(result, new TypeReference<GaodeMapData>() {
        });
        LandTrack landTrack = new LandTrack();
        landTrack.setIp(ipAddress);
        landTrack.setLoginDate(LocalDateTime.now());
        if (userAgent != null) {
            landTrack.setSystem(userAgent.getOperatingSystem().getName());
            landTrack.setBrowser(userAgent.getBrowser().getName() + userAgent.getBrowserVersion().getMajorVersion());
        }
        if (gaodeMapData != null) {
            if (gaodeMapData.getProvince().equalsIgnoreCase("[]")) {
                gaodeMapData.setProvince("未知");
            }
            if (gaodeMapData.getCity().equalsIgnoreCase("[]")) {
                gaodeMapData.setCity("未知");
            }
            if (gaodeMapData.getRectangle().equalsIgnoreCase("[]")) {
                gaodeMapData.setRectangle("未知");
            }
            landTrack.setProvince(gaodeMapData.getProvince());
            landTrack.setCity(gaodeMapData.getCity());
            landTrack.setRectangle(gaodeMapData.getRectangle());
        }
        landTrackRepository.save(landTrack);
    }
}
