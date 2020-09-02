package com.song.sweet.mapper;

import com.github.pagehelper.Page;
import com.song.sweet.model.Barrage;
import org.springframework.stereotype.Repository;

public interface BarrageMapper {

    Integer save(Barrage barrage);

    Page<Barrage> findAll();

    Page<Barrage> findAllByPage();

}
