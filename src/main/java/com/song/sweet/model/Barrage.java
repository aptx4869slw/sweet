package com.song.sweet.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "barrage")
public class Barrage implements Serializable {

    private static final long serialVersionUID = -1099189125315055134L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", dataType = "Integer", example = "1")
    private Long id;

    @Column(name = "created_date")
    @ApiModelProperty(value = "创建日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime createdDate;

    @Column(name = "img")
    @ApiModelProperty(value = "头像", dataType = "String", example = "img")
    private String img;

    @Column(name = "info")
    @ApiModelProperty(value = "评论", dataType = "String", example = "info")
    private String info;

    @Column(name = "href")
    @ApiModelProperty(value = "跳转链接", dataType = "String", example = "href")
    private String href;

    @Column(name = "close")
    @ApiModelProperty(value = "删除标识", dataType = "Integer", example = "0", notes = "0.未删除,1:已删除")
    private boolean close;

    @Column(name = "speed")
    @ApiModelProperty(value = "速度", dataType = "Integer", example = "1", notes = "1-10逐级加速")
    private int speed;

    @Column(name = "color")
    @ApiModelProperty(value = "颜色", dataType = "String", example = "color", notes = "十六进制颜色码")
    private String color;

    @Column(name = "old_ie_color")
    @ApiModelProperty(value = "兼任ie颜色", dataType = "String", example = "color", notes = "十六进制颜色码")
    private String old_ie_color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOld_ie_color() {
        return old_ie_color;
    }

    public void setOld_ie_color(String old_ie_color) {
        this.old_ie_color = old_ie_color;
    }

    @Override
    public String toString() {
        return "Barrage{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", img='" + img + '\'' +
                ", info='" + info + '\'' +
                ", href='" + href + '\'' +
                ", close=" + close +
                ", speed=" + speed +
                ", color='" + color + '\'' +
                ", old_ie_color='" + old_ie_color + '\'' +
                '}';
    }
}
