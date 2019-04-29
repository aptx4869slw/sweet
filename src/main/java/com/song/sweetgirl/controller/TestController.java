package com.song.sweetgirl.controller;

import com.song.sweetgirl.service.TestService;
import com.song.sweetgirl.service.dto.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @PostMapping(path = {"/test"})
    public ResponseEntity<TestDTO> save(@RequestBody TestDTO testDTO) {
        logger.debug("REST request to save Test : {}", testDTO.toString());
        try {
            TestDTO result = testService.save(testDTO);
            return ResponseEntity.created(new URI("/api/test/" + result.getId())).body(result);
        } catch (Exception e) {
            logger.error("REST request to save Test : {}" + e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/test/{id}"})
    public ResponseEntity<TestDTO> findOne(@PathVariable Long id) {
        logger.debug("REST request to get a test : {}", id);
        TestDTO result = new TestDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping(path = {"/test"})
    public ResponseEntity<List<TestDTO>> findAll() {
        logger.debug("REST request to get all tests");
        List<TestDTO> result = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(path = {"/test/{id}"})
    public ResponseEntity<TestDTO> delete(@PathVariable Long id) {
        logger.debug("REST request to delete Test : {}", id);
        TestDTO result = new TestDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(path = {"/test"})
    public ResponseEntity<TestDTO> update(@RequestBody TestDTO testDTO) {
        logger.debug("REST request to update Test : {}", testDTO.toString());
        TestDTO result = new TestDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
