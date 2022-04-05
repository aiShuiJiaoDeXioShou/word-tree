package com.wordtree.wt_physical;

import java.util.Date;

public class User {
    private int id;//主键，作为它的唯一标识，用户id
    private String biming;//笔名
    private String user;//用户名
    private String password;//用户密码
    private int zishu;//用户累计的字数
    private int xiugaicishu;//用户使用打字软件的次数
    private Date date;
    //用户主机的电脑名称userhost，跟用户主机的本地地址userid
    private String userHost;
    private String userId;

    public User(String biming, String user, String password, int zishu,int id) {
        this.id = id;
        this.biming = biming;
        this.user = user;
        this.password = password;
        this.zishu = zishu;
    }

    //带参构造函数
    public User(int id, String biming, String user, String password, int zishu, int xiugaicishu, Date date, String userHost, String userId) {
        this.id = id;
        this.biming = biming;
        this.user = user;
        this.password = password;
        this.zishu = zishu;
        this.xiugaicishu = xiugaicishu;
        this.date = date;
        this.userHost = userHost;
        this.userId = userId;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBiming() {
        return biming;
    }

    public void setBiming(String biming) {
        this.biming = biming;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getZishu() {
        return zishu;
    }

    public void setZishu(int zishu) {
        this.zishu = zishu;
    }

    public int getXiugaicishu() {
        return xiugaicishu;
    }

    public void setXiugaicishu(int xiugaicishu) {
        this.xiugaicishu = xiugaicishu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserHost() {
        return userHost;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
