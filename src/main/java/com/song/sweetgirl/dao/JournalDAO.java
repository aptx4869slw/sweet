package com.song.sweetgirl.dao;

import com.github.pagehelper.Page;
import com.song.sweetgirl.model.Journal;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalDAO {

    Integer save(Journal journal);

    Page<Journal> findAll();

    Page<Journal> findAllByPage();

}
