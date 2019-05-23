package com.song.sweetgirl.controller.vm;

import java.io.Serializable;

public class PageVM implements Serializable {

    private static final long serialVersionUID = 1794721557284887814L;

    private Integer pageNum;

    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
