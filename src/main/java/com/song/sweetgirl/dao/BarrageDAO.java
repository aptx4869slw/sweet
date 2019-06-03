package com.song.sweetgirl.dao;

import com.github.pagehelper.Page;
import com.song.sweetgirl.model.Barrage;
import org.springframework.stereotype.Repository;

@Repository
public interface BarrageDAO {

    Integer save(Barrage barrage);

    Page<Barrage> findAll();

    Page<Barrage> findAllByPage();

}
