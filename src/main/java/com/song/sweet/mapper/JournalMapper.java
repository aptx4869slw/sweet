package com.song.sweet.mapper;

import com.github.pagehelper.Page;
import com.song.sweet.model.Journal;
import org.springframework.stereotype.Repository;

public interface JournalMapper {

    Integer save(Journal journal);

    Page<Journal> findAll();

    Page<Journal> findAllByPage();

}
