package com.song.sweetgirl;

import com.song.sweetgirl.controller.vm.PageVM;
import com.song.sweetgirl.service.TestService;
import com.song.sweetgirl.service.dto.TestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SweetgirlApplicationTests {

    @Autowired
    TestService testService;

    private final static Long id = 10086L;

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
        TestDTO testDTO = testService.findOne(id);
        assertThat(testDTO.getId()).isEqualTo(id);
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
        Boolean flag = testService.delete(id);
        assertThat(flag).isTrue();
    }

    @Test
    @Transactional
    public void update() throws Exception {
        TestDTO testDTO = createTestDTO();
        testDTO.setId(id);
        testDTO = testService.update(testDTO);
        assertThat(testDTO.getFlag()).isEqualTo(boo);
        assertThat(testDTO.getPrice()).isEqualTo(dec);
        assertThat(testDTO.getString()).isEqualTo(str);
        assertThat(testDTO.getNumber()).isEqualTo(num);
        assertThat(testDTO.getLocalDateTime()).isEqualTo(time);
    }

}
