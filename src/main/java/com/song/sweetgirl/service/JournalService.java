package com.song.sweetgirl.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.song.sweetgirl.controller.vm.PageVM;
import com.song.sweetgirl.dao.JournalDAO;
import com.song.sweetgirl.model.Journal;
import com.song.sweetgirl.service.dto.JournalDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "journal")
public class JournalService {

    private final Logger logger = LoggerFactory.getLogger(JournalService.class);

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private JournalDAO journalDAO;

    /**
     * 查询日志列表
     *
     * @return
     */
    @Cacheable(value = "Journals", unless = "#result eq null")
    public List<JournalDTO> getJournals(PageVM page) {
        Page<Journal> pageResult;
        if (page.getPageNum() == null || page.getPageSize() == null) {
            pageResult = journalDAO.findAll();
        } else {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            pageResult = journalDAO.findAllByPage();
        }
        List<JournalDTO> result = mapper.map(pageResult, new TypeToken<List<JournalDTO>>() {
        }.getType());
        result.sort(Comparator.comparing(JournalDTO::getCreatedDate).reversed());
        return result;
    }
}
