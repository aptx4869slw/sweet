package com.song.sweet.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.dao.BarrageDAO;
import com.song.sweet.model.Barrage;
import com.song.sweet.service.dto.BarrageDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@CacheConfig(cacheNames = "barrage")
public class BarrageService {

    private final Logger logger = LoggerFactory.getLogger(BarrageService.class);

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private BarrageDAO barrageDAO;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 保存弹幕
     *
     * @param barrageDTO
     * @return
     * @throws ServerException
     */
    @CachePut(value = "save", key = "#result.id", unless = "#result eq null")
    public BarrageDTO save(BarrageDTO barrageDTO) throws ServerException {
        Barrage barrage = mapper.map(barrageDTO, Barrage.class);
        Integer count = barrageDAO.save(barrage);
        if (count > 0) {
            Page<Barrage> pageResult = barrageDAO.findAll();
            List<BarrageDTO> result = mapper.map(pageResult, new TypeToken<List<BarrageDTO>>() {
            }.getType());
            redisTemplate.opsForValue().set("Barrages", result, 1, TimeUnit.SECONDS);
            return mapper.map(barrage, BarrageDTO.class);
        } else {
            logger.debug("Barrage save failed!", barrage);
            throw new ServerException("Barrage save failed!");
        }
    }

    /**
     * 查询弹幕列表
     *
     * @return
     */
    @Cacheable(value = "Barrages", key = "'Barrages'", unless = "#result eq null")
    public List<BarrageDTO> findBarrages(PageVM page) {
        Page<Barrage> pageResult;
        if (page.getPageNum() == null || page.getPageSize() == null) {
            pageResult = barrageDAO.findAll();
        } else {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            pageResult = barrageDAO.findAllByPage();
        }
        List<BarrageDTO> result = mapper.map(pageResult, new TypeToken<List<BarrageDTO>>() {
        }.getType());
        result.sort(Comparator.comparing(BarrageDTO::getCreatedDate));
        return result;
    }

}
