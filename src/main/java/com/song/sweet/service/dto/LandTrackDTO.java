package com.song.sweet.service.dto;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author Liwen
 * @Description // 访问轨迹
 * @Version 1.0.0
 * @create 2020-09-07 18:14
 */
@ApiModel
public class LandTrackDTO implements Serializable {
    private static final long serialVersionUID = 1642344819086615524L;

    private String name;

    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
