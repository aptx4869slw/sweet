package com.song.sweet.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.song.sweet.model.ChinazData;
import com.song.sweet.model.ChinazResult;
import com.song.sweet.model.GaodeMapData;
import com.song.sweet.model.LandTrack;
import com.song.sweet.repository.LandTrackRepository;
import com.song.sweet.utils.CommonUtils;
import com.song.sweet.utils.HttpClientUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
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

    @Value("${chinaz.api.key}")
    private String chinazKey;

    @Value("${chinaz.api.url}")
    private String chinazUrl;

    @Autowired
    private LandTrackRepository landTrackRepository;

    /**
     * 根据当前登录获取IP,所在省市等信息
     *
     * @param request
     * @return
     */
    public void getProvinceByIP(HttpServletRequest request) {
        String ipAddress = CommonUtils.getIpAddress(request);
        UserAgent userAgent = CommonUtils.getUserAgent(request);
        String system = userAgent.getOperatingSystem().getName();
        String browser = userAgent.getBrowser().getName() + userAgent.getBrowserVersion().getMajorVersion();
        LandTrack landTrack = landTrackRepository.findFirstByIpAndSystemAndBrowser(ipAddress, system, browser);
        if (landTrack == null) {
            String unknown = "未知";
            // 准备需要保存的用户轨迹数据
            LandTrack newLandTrack = new LandTrack();
            newLandTrack.setIp(ipAddress);
            newLandTrack.setSystem(system);
            newLandTrack.setBrowser(browser);
            newLandTrack.setVisits(1);
            newLandTrack.setLoginDate(LocalDateTime.now());
            newLandTrack.setLastVisitDate(LocalDateTime.now());
            // 请求高德开放API，查询当前IP所在省市信息
            logger.info("Send a request to Gaode to get province data ! key: {} ip : {} ", gaodeMapKey, ipAddress);
            String url = gaodeMapUrl + "?ip=" + ipAddress + "&key=" + gaodeMapKey;
            String result = HttpClientUtils.doGet(url, "UTF-8");
            GaodeMapData gaodeMapData = JSON.parseObject(result, new TypeReference<GaodeMapData>() {
            });
            if (gaodeMapData == null || gaodeMapData.getProvince().equals("[]")) {
                // 如果高德获取不到，则IP为国外登录
                logger.info("Send a request to Chinaz to get province data ! key: {} ip : {} ", chinazKey, ipAddress);
                url = chinazUrl + "?ip=" + ipAddress + "&key=" + chinazKey;
                result = HttpClientUtils.doGet(url, "UTF-8");
                ChinazData chinazData = JSON.parseObject(result, new TypeReference<ChinazData>() {
                });
                if (chinazData != null && chinazData.getStateCode().equalsIgnoreCase("1")) {
                    ChinazResult chinazResult = chinazData.getResult();
                    newLandTrack.setCountry(chinazResult.getCountry());
                    if (StringUtils.isBlank(chinazResult.getProvince())) {
                        chinazResult.setProvince(unknown);
                    }
                    if (StringUtils.isBlank(chinazResult.getCity())) {
                        chinazResult.setCity(unknown);
                    }
                    if (StringUtils.isBlank(chinazResult.getIsp())) {
                        chinazResult.setIsp(unknown);
                    }
                    newLandTrack.setProvince(chinazResult.getProvince());
                    newLandTrack.setCity(chinazResult.getCity());
                    newLandTrack.setIsp(chinazResult.getIsp());
                    newLandTrack.setRectangle(unknown);
                }
            } else {
                String country = "中国";
                if (gaodeMapData.getProvince().equalsIgnoreCase("[]")) {
                    gaodeMapData.setProvince(unknown);
                }
                if (gaodeMapData.getCity().equalsIgnoreCase("[]")) {
                    gaodeMapData.setCity(unknown);
                }
                if (gaodeMapData.getRectangle().equalsIgnoreCase("[]")) {
                    gaodeMapData.setRectangle(unknown);
                }
                newLandTrack.setCountry(country);
                newLandTrack.setProvince(gaodeMapData.getProvince());
                newLandTrack.setCity(gaodeMapData.getCity());
                newLandTrack.setIsp(country + gaodeMapData.getProvince() + gaodeMapData.getCity());
                newLandTrack.setRectangle(gaodeMapData.getRectangle());
            }
            landTrackRepository.save(newLandTrack);
        } else {
            // 如果同一ip，系统和浏览器则访问次数+1
            landTrack.setVisits(landTrack.getVisits() + 1);
            landTrack.setLastVisitDate(LocalDateTime.now());
            landTrackRepository.save(landTrack);
        }
    }
}
