package com.song.sweet.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "test")
public class Test extends BaseModel {

    private static final long serialVersionUID = 1289112090832266058L;

    @Column(name = "number")
    @ApiModelProperty(value = "数值", dataType = "Integer", example = "1")
    private Integer number;

    @Column(name = "string")
    @ApiModelProperty(value = "字符串", dataType = "String", example = "string")
    private String string;

    @Column(name = "price")
    @ApiModelProperty(value = "价格", dataType = "BigDecimal", example = "1.00")
    private BigDecimal price;

    @Column(name = "flag")
    @ApiModelProperty(value = "布尔值", dataType = "Integer", example = "0", notes = "0.未删除,1:已删除")
    private Boolean flag;

    @Column(name = "local_date_time")
    @ApiModelProperty(value = "日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime localDateTime;

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

}
