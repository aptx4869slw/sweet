package com.song.sweetgirl.controller;

import com.song.sweetgirl.model.Test;
import com.song.sweetgirl.service.TestService;
import com.song.sweetgirl.service.dto.TestDTO;
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
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @PostMapping(path = {"/test"})
    public ResponseEntity<TestDTO> save(@RequestBody TestDTO testDTO) {
        logger.debug("REST request to save Test :{}", testDTO.toString());
        try {
            TestDTO result = testService.save(testDTO);
            return ResponseEntity.created(new URI("/api/test/" + result.getId())).body(result);
        } catch (Exception e) {
            logger.error("REST request to save Test :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/test/{id}"})
    public ResponseEntity<TestDTO> findOne(@PathVariable Long id) {
        logger.debug("REST request to get a test :{}", id);
        try {
            TestDTO result = testService.findOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get a test :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/test"})
    public ResponseEntity<List<TestDTO>> findAll() {
        logger.debug("REST request to get all tests");
        try {
            List<TestDTO> result = testService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get all tests :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = {"/test"})
    public ResponseEntity<TestDTO> update(@RequestBody TestDTO testDTO) {
        logger.debug("REST request to update Test :{}", testDTO.toString());
        try {
            TestDTO result = testService.update(testDTO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to update Test :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/test/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        logger.debug("REST request to delete Test :{}", id);
        try {
            Boolean result = testService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to delete Test :{}" + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
