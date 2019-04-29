package com.song.sweetgirl.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TestDTO implements Serializable {

    private static final long serialVersionUID = -6267488977559750093L;

    private Long id;

    private Integer integer;

    private String string;

    private BigDecimal bigDecimal;

    private Boolean flag;

    private LocalDateTime localDateTime;

    private List<String> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "id=" + id +
                ", integer=" + integer +
                ", string='" + string + '\'' +
                ", bigDecimal=" + bigDecimal +
                ", flag=" + flag +
                ", localDateTime=" + localDateTime +
                ", list=" + list +
                '}';
    }
}
