package com.song.sweet.model.vo;

import java.io.Serializable;

public class ChinazResult implements Serializable {

    private static final long serialVersionUID = 5033378991137881584L;

    private String IP;

    private String Country;

    private String Province;

    private String City;

    private String District;

    private String Isp;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getIsp() {
        return Isp;
    }

    public void setIsp(String isp) {
        Isp = isp;
    }
}
