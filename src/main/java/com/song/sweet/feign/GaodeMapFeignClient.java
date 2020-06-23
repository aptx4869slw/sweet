package com.song.sweet.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url = "${gaode.map.api.url}", name = "gaodeMap")
public interface GaodeMapFeignClient {

    @RequestMapping(value = "/v3/ip", method = RequestMethod.GET)
    JSONObject getProvinceByIP(@RequestParam("key") String key, @RequestParam("ip") String ip);

}
