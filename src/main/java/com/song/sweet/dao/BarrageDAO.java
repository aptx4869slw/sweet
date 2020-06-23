package com.song.sweet.dao;

import com.github.pagehelper.Page;
import com.song.sweet.model.Barrage;
import org.springframework.stereotype.Repository;

@Repository
public interface BarrageDAO {

    Integer save(Barrage barrage);

    Page<Barrage> findAll();

    Page<Barrage> findAllByPage();

}
