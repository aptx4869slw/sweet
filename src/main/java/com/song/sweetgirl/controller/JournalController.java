package com.song.sweetgirl.controller;

import com.song.sweetgirl.controller.vm.PageVM;
import com.song.sweetgirl.service.JournalService;
import com.song.sweetgirl.service.dto.JournalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JournalController {

    private final Logger logger = LoggerFactory.getLogger(JournalController.class);

    @Autowired
    private JournalService journalService;

    @GetMapping("/journals")
    public ResponseEntity<List<JournalDTO>> getJournals(PageVM page) {
        logger.debug("REST request to get Journals");
        try {
            List<JournalDTO> result = journalService.getJournals(page);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get Journals :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
