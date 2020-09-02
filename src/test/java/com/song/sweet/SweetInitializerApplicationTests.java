package com.song.sweet;

import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.mapper.TestMapper;
import com.song.sweet.service.TestService;
import com.song.sweet.service.dto.TestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SweetInitializerApplicationTests {

    @Autowired
    TestService testService;

    @Autowired
    TestMapper testMapper;

    private final static Boolean boo = Boolean.TRUE;

    private final static BigDecimal dec = BigDecimal.ZERO;

    private final static String str = "测试";

    private final static Integer num = 0;

    private final static LocalDateTime time = LocalDateTime.of(2008, 1, 1, 0, 0, 0);

    private static TestDTO createTestDTO() {
        TestDTO testDTO = new TestDTO();
        testDTO.setFlag(boo);
        testDTO.setPrice(dec);
        testDTO.setString(str);
        testDTO.setNumber(num);
        testDTO.setLocalDateTime(time);
        return testDTO;
    }

    @Test
    @Transactional
    public void save() throws Exception {
        TestDTO testDTO = createTestDTO();
        testDTO = testService.save(testDTO);
        testDTO = testService.findOne(testDTO.getId());
        assertThat(testDTO.getFlag()).isEqualTo(boo);
        assertThat(testDTO.getPrice()).isEqualTo(dec.setScale(2, BigDecimal.ROUND_DOWN));
        assertThat(testDTO.getString()).isEqualTo(str);
        assertThat(testDTO.getNumber()).isEqualTo(num);
        assertThat(testDTO.getLocalDateTime()).isEqualTo(time);
    }

    @Test
    @Transactional
    public void findOne() throws Exception {
        com.song.sweet.model.Test test = testMapper.findFirstTest();
        assertThat(test.getId()).isEqualTo(test.getId());
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        List<TestDTO> testDTOS = testService.findAll(new PageVM());
        assertThat(testDTOS.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        com.song.sweet.model.Test test = testMapper.findFirstTest();
        Boolean flag = testService.delete(test.getId());
        assertThat(flag).isTrue();
    }

    @Test
    @Transactional
    public void update() throws Exception {
        TestDTO testDTO = createTestDTO();
        com.song.sweet.model.Test test = testMapper.findFirstTest();
        testDTO.setId(test.getId());
        testDTO = testService.update(testDTO);
        assertThat(testDTO.getFlag()).isEqualTo(boo);
        assertThat(testDTO.getPrice()).isEqualTo(dec);
        assertThat(testDTO.getString()).isEqualTo(str);
        assertThat(testDTO.getNumber()).isEqualTo(num);
        assertThat(testDTO.getLocalDateTime()).isEqualTo(time);
    }

}
