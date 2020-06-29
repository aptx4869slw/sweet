package com.song.sweet.controller;

import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.service.TestService;
import com.song.sweet.service.dto.TestDTO;
import com.song.sweet.service.utils.ImageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@Api(tags = "测试API接口管理")
@RestController
@RequestMapping("/api")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private ImageUtils imageUtils;

    @Autowired
    private StringEncryptor encryptor;

    @ApiOperation(value = "创建测试数据")
    @PostMapping(path = {"/test"})
    public ResponseEntity<TestDTO> save(@RequestBody TestDTO testDTO) {
        logger.debug("REST request to save Test : {} ", testDTO.toString());
        try {
            TestDTO result = testService.save(testDTO);
            return ResponseEntity.created(new URI("/api/test/" + result.getId())).body(result);
        } catch (Exception e) {
            logger.error("REST request to save Test : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据ID查找测试数据")
    @GetMapping(path = {"/test/{id}"})
    public ResponseEntity<TestDTO> findOne(@PathVariable Long id) {
        logger.debug("REST request to get a test : {} ", id);
        try {
            TestDTO result = testService.findOne(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get a test : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查找所有测试数据", notes = "Mybatis查询")
    @GetMapping(path = {"/test"})
    public ResponseEntity<List<TestDTO>> findAll(PageVM page) {
        logger.debug("REST request to get all tests");
        try {
            List<TestDTO> result = testService.findAll(page);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to get all tests : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查找所有测试数据", notes = "JPA查询")
    @GetMapping(path = {"/tests"})
    public ResponseEntity<List<TestDTO>> findAllByJPA(PageVM page) {
        logger.debug("REST request to get all tests by jpa");
        try {
            List<TestDTO> result = testService.findAllByJPA(page);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("REST request to get all tests : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "修改测试数据")
    @PutMapping(path = {"/test"})
    public ResponseEntity<TestDTO> update(@RequestBody TestDTO testDTO) {
        logger.debug("REST request to update Test : {} ", testDTO.toString());
        try {
            TestDTO result = testService.update(testDTO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to update Test : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "删除测试数据")
    @DeleteMapping(path = {"/test/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        logger.debug("REST request to delete Test : {} ", id);
        try {
            Boolean result = testService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to delete Test : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "上传图片数据", notes = "上传至阿里云OSS服务")
    @PostMapping("/image_upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            logger.debug("REST request to upload Images : {} , {} ", file.getName(), file.getContentType());
            String url = imageUtils.upload(file.getContentType(), file.getBytes());
            return ResponseEntity.created(new URI("/api/image_upload")).body(url);
        } catch (Exception e) {
            logger.error("REST request to upload Images : {} " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 加密方法
     *
     * @param pass 需加密文本
     */
    @ApiOperation(value = "测试配置文件加密算法", notes = "将数据库账号密码进行加密保存")
    @GetMapping(path = {"/encrypt"})
    public void testEncrypt(String pass) {
        String encryptPass = encryptor.encrypt(pass);
        logger.debug("The encrypted password is : {}---{}", pass, encryptPass);
    }

}
