package com.song.sweet.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.mapper.TestMapper;
import com.song.sweet.model.Test;
import com.song.sweet.repository.TestRepository;
import com.song.sweet.service.dto.TestDTO;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "test")
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestRepository testRepository;

    private ModelMapper mapper = new ModelMapper();

    /**
     * 保存方法
     *
     * @param testDTO
     * @return
     * @throws ServerException
     */
    @CachePut(value = "save", key = "#result.id", unless = "#result eq null")
    public TestDTO save(TestDTO testDTO) throws ServerException {
        Test test = mapper.map(testDTO, Test.class);
        Integer count = testMapper.save(test);
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
    @Cacheable(value = "findOne", key = "#p0", unless = "#result eq null")
    public TestDTO findOne(Long id) throws ServerException {
        Test test = testMapper.findOne(id);
        if (test == null) {
            logger.debug("The test is not exist! : {} ", id);
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
    @Cacheable(value = "findAll", unless = "#result eq null")
    public List<TestDTO> findAll(PageVM page) {
        Page<Test> pageResult;
        if (page.getPageNum() == null || page.getPageSize() == null) {
            pageResult = testMapper.findAll();
        } else {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            pageResult = testMapper.findAllByPage();
        }
        List<TestDTO> result = mapper.map(pageResult, new TypeToken<List<TestDTO>>() {
        }.getType());
        result.sort(Comparator.comparing(TestDTO::getLocalDateTime).reversed());
        return result;
    }

    /**
     * JPA查询列表
     *
     * @return
     */
    @Cacheable(value = "findAllJPA", unless = "#result eq null")
    public List<TestDTO> findAllByJPA(PageVM page) {
        Specification<Test> specification = this.buildSpecification(Boolean.FALSE);
        Pageable pageable;
        if (page != null && page.getPageNum() != null && page.getPageSize() != null) {
            pageable = PageRequest.of(page.getPageNum(), page.getPageSize(), Sort.Direction.DESC, "local_date_time");
        } else {
            pageable = Pageable.unpaged();
        }
        List<Test> tests = testRepository.findAll(specification, pageable).getContent();
        List<TestDTO> result = mapper.map(tests, new TypeToken<List<TestDTO>>() {
        }.getType());
        return result;
    }

    /**
     * 修改数据
     *
     * @param testDTO
     * @return
     * @throws ServerException
     */
    @CachePut(value = "update", key = "#result.id", unless = "#result eq null")
    public TestDTO update(TestDTO testDTO) throws ServerException {
        Test test = testMapper.findOne(testDTO.getId());
        if (test == null) {
            logger.debug("The test is not exist! : {} ", testDTO.getId());
            throw new ServerException("The test is not exist!");
        }
        test = mapper.map(testDTO, Test.class);
        Integer count = testMapper.update(test);
        if (count > 0) {
            return mapper.map(test, TestDTO.class);
        } else {
            logger.debug("update failed! : {} ", testDTO.getId());
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
    @CacheEvict(value = "delete", key = "#id", allEntries = true)
    public Boolean delete(Long id) throws ServerException {
        Integer count = testMapper.delete(id);
        if (count > 0) {
            return Boolean.TRUE;
        } else {
            logger.debug("delete failed! : {} ", id);
            throw new ServerException("delete failed!");
        }
    }

    /**
     * 构建查询条件
     *
     * @param flag
     * @return
     */
    private Specification<Test> buildSpecification(Boolean flag) {
        Specification<Test> specification = (Specification<Test>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate[] p = new Predicate[list.size()];
            if (StringUtils.isBlank(flag.toString())) {
                list.add(cb.equal(root.get("string").as(String.class), ""));
                list.add(cb.equal(root.get("integer").as(Integer.class), 0));
            }
            return cb.and(list.toArray(p));
        };
        return specification;
    }

    /**
     * 定时器定时清理数据
     */
    @Transactional
    public void testTimer() {
        final Integer total = 100;
        Integer count = testMapper.countTests();
        if (count > total) {
            Test test = testMapper.findFirstTest();
            Integer num = testMapper.delete(test.getId());
            if (num > 0) {
                logger.info(test.toString());
            }
        }
    }

}
