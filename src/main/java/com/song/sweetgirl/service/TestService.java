package com.song.sweetgirl.service;

import com.song.sweetgirl.dao.TestDao;
import com.song.sweetgirl.model.Test;
import com.song.sweetgirl.service.dto.TestDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestDao testDao;

    private ModelMapper mapper = new ModelMapper();

    /**
     * 保存方法
     *
     * @param testDTO
     * @return
     * @throws ServerException
     */
    public TestDTO save(TestDTO testDTO) throws ServerException {
        Test test = mapper.map(testDTO, Test.class);
        Integer count = testDao.save(test);
        if (count > 0) {
            return mapper.map(test, TestDTO.class);
        } else {
            logger.debug("save failed!", test);
            throw new ServerException("save failed!");
        }
    }

    /**
     * 查询单条数据
     *
     * @param id
     * @return
     * @throws ServerException
     */
    public TestDTO findOne(Long id) throws ServerException {
        Test test = testDao.findOne(id);
        if (test == null) {
            logger.debug("The test is not exist! :{}", id);
            throw new ServerException("The test is not exist!");
        }
        TestDTO result = mapper.map(test, TestDTO.class);
        // BigDecimal精度
        BigDecimal decimal = result.getPrice().setScale(2, BigDecimal.ROUND_DOWN);
        result.setPrice(decimal);
        return result;
    }

    /**
     * 查询列表
     *
     * @return
     */
    public List<TestDTO> findAll() {
        List<Test> list = testDao.findAll();
        List<TestDTO> result = mapper.map(list, new TypeToken<List<TestDTO>>() {
        }.getType());
        result.sort(Comparator.comparing(TestDTO::getLocalDateTime));
        return result;
    }

    /**
     * 修改数据
     *
     * @param testDTO
     * @return
     * @throws ServerException
     */
    public TestDTO update(TestDTO testDTO) throws ServerException {
        Test test = testDao.findOne(testDTO.getId());
        if (test == null) {
            logger.debug("The test is not exist! :{}", testDTO.getId());
            throw new ServerException("The test is not exist!");
        }
        test = mapper.map(testDTO, Test.class);
        Integer count = testDao.update(test);
        if (count > 0) {
            return mapper.map(test, TestDTO.class);
        } else {
            logger.debug("update failed! :{}", testDTO.getId());
            throw new ServerException("update failed!");
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws ServerException
     */
    public Boolean delete(Long id) throws ServerException {
        Integer count = testDao.delete(id);
        if (count > 0) {
            return Boolean.TRUE;
        } else {
            logger.debug("delete failed! :{}", id);
            throw new ServerException("delete failed!");
        }
    }

    @Transactional
    public void testTimer() {
        Test test = testDao.findOne(1L);
        test.setNumber(test.getNumber() + 1);
        test.setLocalDateTime(LocalDateTime.now());
        testDao.update(test);
    }

}
