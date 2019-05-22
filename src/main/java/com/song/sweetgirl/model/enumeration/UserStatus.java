package com.song.sweetgirl.model.enumeration;

public enum UserStatus {

    LOGIN_SUCCESS("登陆成功"), LOGIN_FAILED("登陆失败"), USER_REGISTER_ERROR("账号注册失败"), USER_REGISTER_SUCCESS("账号注册成功"), USER_NON_EXIST("用户不存在"), USER_EXIST("该账号已存在");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
