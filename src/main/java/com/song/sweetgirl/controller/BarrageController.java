package com.song.sweetgirl.controller;

import com.song.sweetgirl.controller.vm.PageVM;
import com.song.sweetgirl.service.BarrageService;
import com.song.sweetgirl.service.dto.BarrageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BarrageController {

    private final Logger logger = LoggerFactory.getLogger(BarrageController.class);

    @Autowired
    private BarrageService barrageService;

    @PostMapping(path = {"/barrage"}, consumes = "application/json")
    public ResponseEntity<BarrageDTO> saveBarrage(@RequestBody BarrageDTO barrageDTO) {
        logger.debug("REST request to save a barrage :{}", barrageDTO.toString());
        try {
            barrageDTO = barrageService.save(barrageDTO);
            return ResponseEntity.created(new URI("/api/barrage/" + barrageDTO.getId())).body(barrageDTO);
        } catch (Exception e) {
            logger.error("REST request to save a barrage :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/barrages")
    public ResponseEntity<List<BarrageDTO>> getBarrages(PageVM page) {
        logger.debug("REST request to get barrages");
        try {
            List<BarrageDTO> result = barrageService.findBarrages(page);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get barrages :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
