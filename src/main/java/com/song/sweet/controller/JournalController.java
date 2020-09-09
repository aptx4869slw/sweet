package com.song.sweet.controller;

import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.service.JournalService;
import com.song.sweet.service.dto.JournalDTO;
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

@Api(tags = "更新日志管理")
@RestController
@RequestMapping(value = "/api", consumes = "application/json", produces = "application/json")
public class JournalController {

    private final Logger logger = LoggerFactory.getLogger(JournalController.class);

    @Autowired
    private JournalService journalService;

    @ApiOperation(value = "保存博客更新日志记录", notes = "保存博客更新日志记录")
    @PostMapping(path = {"/journal"})
    public ResponseEntity<JournalDTO> saveBarrage(@RequestParam String content) {
        logger.debug("REST request to save a journal : {} ", content);
        try {
            JournalDTO journalDTO = journalService.save(content);
            return ResponseEntity.created(new URI("/api/journal/" + journalDTO.getId())).body(journalDTO);
        } catch (Exception e) {
            logger.error("REST request to save a journal : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询博客更新日志记录", notes = "查询博客更新日志记录")
    @GetMapping("/journals")
    public ResponseEntity<List<JournalDTO>> getJournals(PageVM page) {
        logger.debug("REST request to get Journals");
        try {
            List<JournalDTO> results = journalService.getJournals(page);
            return ResponseEntity.status(HttpStatus.OK).body(results);
        } catch (Exception e) {
            logger.error("REST request to get Journals : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
