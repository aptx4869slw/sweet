package com.song.sweetgirl.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Test implements Serializable {

    private static final long serialVersionUID = 1289112090832266058L;

    private Long id;

    private Integer integer;

    private String string;

    private BigDecimal bigDecimal;

    private Boolean flag;

    private LocalDateTime localDateTime;

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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", integer=" + integer +
                ", string='" + string + '\'' +
                ", bigDecimal=" + bigDecimal +
                ", flag=" + flag +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
