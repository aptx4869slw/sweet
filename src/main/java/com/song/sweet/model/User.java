package com.song.sweet.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseModel{

    private static final long serialVersionUID = -313168953581601338L;

    @Column(name = "username")
    @ApiModelProperty(value = "用户名", dataType = "String", example = "username")
    private String username;

    @Column(name = "password")
    @ApiModelProperty(value = "密码", dataType = "String", example = "password")
    private String password;

    @Column(name = "con_password")
    @ApiModelProperty(value = "备用密码", dataType = "String", example = "conPassword")
    private String conPassword;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ApiModelProperty(value = "登陆轨迹", dataType = "Set", example = "[a,b,c]")
    private Set<LandTrack> landTracks = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public Set<LandTrack> getLandTracks() {
        return landTracks;
    }

    public void setLandTracks(Set<LandTrack> landTracks) {
        this.landTracks = landTracks;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", conPassword='" + conPassword + '\'' +
                ", landTracks=" + landTracks +
                '}';
    }
}
