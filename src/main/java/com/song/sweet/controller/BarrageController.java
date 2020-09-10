package com.song.sweet.controller;

import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.service.BarrageService;
import com.song.sweet.service.dto.BarrageDTO;
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

@Api(tags = "弹幕页面管理")
@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class BarrageController {

    private final Logger logger = LoggerFactory.getLogger(BarrageController.class);

    @Autowired
    private BarrageService barrageService;

    @ApiOperation(value = "保存发送的弹幕", notes = "保存发送的弹幕")
    @PostMapping(path = {"/barrage"})
    public ResponseEntity saveBarrage(@RequestBody BarrageDTO barrageDTO) {
        logger.debug("REST request to save a barrage : {} ", barrageDTO.toString());
        try {
            barrageDTO = barrageService.save(barrageDTO);
            return ResponseEntity.created(new URI("/api/barrage/" + barrageDTO.getId())).body(barrageDTO);
        } catch (Exception e) {
            logger.error("REST request to save a barrage : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询弹幕列表", notes = "查询弹幕列表")
    @GetMapping("/barrages")
    public ResponseEntity<List<BarrageDTO>> getBarrages(PageVM page) {
        logger.debug("REST request to get barrages");
        try {
            List<BarrageDTO> result = barrageService.findBarrages(page);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get barrages : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
