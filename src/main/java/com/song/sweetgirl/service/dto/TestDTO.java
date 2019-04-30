package com.song.sweetgirl.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TestDTO implements Serializable {

    private static final long serialVersionUID = -6267488977559750093L;

    private Long id;

    private Integer number;

    private String string;

    private BigDecimal price;

    private Boolean flag;

    private LocalDateTime localDateTime;

    private List<String> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
                ", number=" + number +
                ", string='" + string + '\'' +
                ", price=" + price +
                ", flag=" + flag +
                ", localDateTime=" + localDateTime +
                ", list=" + list +
                '}';
    }
}
