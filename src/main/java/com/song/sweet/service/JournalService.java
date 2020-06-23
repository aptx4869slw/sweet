package com.song.sweet.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.song.sweet.controller.vm.PageVM;
import com.song.sweet.dao.JournalDAO;
import com.song.sweet.model.Journal;
import com.song.sweet.service.dto.JournalDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@CacheConfig(cacheNames = "journal")
public class JournalService {

    private final Logger logger = LoggerFactory.getLogger(JournalService.class);

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private JournalDAO journalDAO;

    /**
     * 保存日志
     *
     * @param content
     * @return
     * @throws ServerException
     */
    @CachePut(value = "save", key = "#result.id", unless = "#result eq null")
    public JournalDTO save(String content) throws ServerException {
        Journal journal = new Journal();
        journal.setContent(content);
        journal.setCreatedDate(LocalDateTime.now());
        String month = new SimpleDateFormat("MMM", Locale.ENGLISH).format(new Date());
        String date = journal.getCreatedDate().getYear() + " " + month + ". " + journal.getCreatedDate().getDayOfMonth();
        journal.setContentDate(date);
        Integer count = journalDAO.save(journal);
        if (count > 0) {
            return mapper.map(journal, JournalDTO.class);
        } else {
            logger.debug("Journal save failed!", journal);
            throw new ServerException("Journal save failed!");
        }
    }

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
