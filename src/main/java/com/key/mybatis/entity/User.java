package com.key.mybatis.entity;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author Key
 * @date 2021/10/21/21:19
 **/
public class User implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6976094896694250242L;

    private Integer userid;
    private String username;
    private String password;

    public User() {
    }

    public User(Integer userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
