package com.song.sweet.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -313168953581601338L;

    private Long id;

    private String username;

    private String password;

    private String conPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", conPassword='" + conPassword + '\'' +
                '}';
    }

}
