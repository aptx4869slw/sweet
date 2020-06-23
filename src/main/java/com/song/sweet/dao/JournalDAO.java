package com.song.sweet.dao;

import com.github.pagehelper.Page;
import com.song.sweet.model.Journal;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalDAO {

    Integer save(Journal journal);

    Page<Journal> findAll();

    Page<Journal> findAllByPage();

}
