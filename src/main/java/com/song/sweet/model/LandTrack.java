package com.song.sweet.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "land_track")
public class LandTrack implements Serializable {

    private static final long serialVersionUID = 1289112090834466058L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", dataType = "Integer", example = "1")
    private Long id;

    @Column(name = "login_date")
    @ApiModelProperty(value = "登陆日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime loginDate = LocalDateTime.now();

    @Column(name = "ip")
    @ApiModelProperty(value = "ip地址", dataType = "String", example = "127.0.0.1")
    private String ip;

    @Column(name = "country")
    @ApiModelProperty(value = "国家", dataType = "String", example = "中国")
    private String country;

    @Column(name = "province")
    @ApiModelProperty(value = "省份", dataType = "String", example = "上海市")
    private String province;

    @Column(name = "city")
    @ApiModelProperty(value = "城市", dataType = "String", example = "上海市")
    private String city;

    @Column(name = "isp")
    @ApiModelProperty(value = "isp", dataType = "String", example = "中国上海市上海市")
    private String isp;

    @Column(name = "system")
    @ApiModelProperty(value = "操作系统", dataType = "String", example = "Windows")
    private String system;

    @Column(name = "browser")
    @ApiModelProperty(value = "浏览器", dataType = "String", example = "Chrome")
    private String browser;

    @Column(name = "visits")
    @ApiModelProperty(value = "访问次数", dataType = "Integer", example = "1")
    private Integer visits = 1;

    @Column(name = "last_visit_date")
    @ApiModelProperty(value = "上次访问日期", dataType = "datetime", example = "1970-01-01 00:00:00")
    private LocalDateTime lastVisitDate = LocalDateTime.now();

    @Column(name = "rectangle")
    @ApiModelProperty(value = "经纬度", dataType = "Integer", example = "rectangle")
    private String rectangle;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public LocalDateTime getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDateTime lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getRectangle() {
        return rectangle;
    }

    public void setRectangle(String rectangle) {
        this.rectangle = rectangle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
