package com.song.sweet.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.model.Journal;
import com.song.sweet.model.LandTrack;
import com.song.sweet.repository.LandTrackRepository;
import com.song.sweet.service.dto.JournalDTO;
import com.song.sweet.service.dto.LandTrackDTO;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liwen
 * @Description // 博客访问轨迹业务层
 * @Version 1.0.0
 * @create 2020-09-07 18:02
 */
@Service
@Transactional
@CacheConfig(cacheNames = "land_track")
public class LandTrackService {

    private final Logger logger = LoggerFactory.getLogger(LandTrackService.class);

    @Autowired
    private LandTrackRepository landTrackRepository;

    /**
     * @return
     * @Title findLandTracks
     * @Description // 查询所有访问记录，便于地图展示
     * @Author liwen
     * @Date 2020/9/7 18:19
     * @Param
     */
    @Cacheable(value = "LandTracks", unless = "#result eq null")
    public List<LandTrackDTO> findLandTracks(PageVM page) {
        List<LandTrackDTO> result = new ArrayList<>();
        List<LandTrack> landTracks = landTrackRepository.findAll();
        Map<String, List<LandTrack>> landTrackMap = landTracks.stream()
                .filter(landTrack -> landTrack.getCountry() != null)
                .collect(Collectors.groupingBy(LandTrack::getCountry));
        for (Map.Entry<String, List<LandTrack>> entry : landTrackMap.entrySet()) {
            LandTrackDTO landTrack = new LandTrackDTO();
            landTrack.setName(entry.getKey());
            Integer visits = entry.getValue().stream().mapToInt(LandTrack::getVisits).sum();
            landTrack.setValue(visits);
            result.add(landTrack);
        }
        return result;
    }

}
