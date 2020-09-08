package com.song.sweet.controller;

import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.model.LandTrack;
import com.song.sweet.service.LandTrackService;
import com.song.sweet.service.dto.JournalDTO;
import com.song.sweet.service.dto.LandTrackDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Liwen
 * @Description // 博客访问轨迹
 * @Version 1.0.0
 * @create 2020-09-07 18:01
 */
@Api(tags = "博客访问轨迹管理")
@RestController
@RequestMapping("/api")
public class LandTrackController {
    private final Logger logger = LoggerFactory.getLogger(LandTrackController.class);

    @Autowired
    private LandTrackService landTrackService;

    @ApiOperation(value = "查询博客被访问的记录轨迹")
    @GetMapping(path = {"/landTracks"})
    public ResponseEntity<List<LandTrackDTO>> findLandTracks(PageVM page) {
        logger.debug("REST request to find all land track.");
        try {
            List<LandTrackDTO> landTracks = landTrackService.findLandTracks(page);
            return ResponseEntity.status(HttpStatus.OK).body(landTracks);
        } catch (Exception e) {
            logger.error("REST request to find all land track. : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
