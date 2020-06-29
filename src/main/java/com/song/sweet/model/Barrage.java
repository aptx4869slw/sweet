package com.song.sweet.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "barrage")
public class Barrage implements Serializable {

    private static final long serialVersionUID = -1099189125315055134L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "img")
    private String img;

    @Column(name = "info")
    private String info;

    @Column(name = "href")
    private String href;

    @Column(name = "close")
    private boolean close;

    @Column(name = "speed")
    private int speed;

    @Column(name = "color")
    private String color;

    @Column(name = "old_ie_color")
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
