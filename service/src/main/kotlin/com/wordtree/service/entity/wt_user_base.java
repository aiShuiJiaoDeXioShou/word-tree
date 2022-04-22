package com.wordtree.service.entity;


public class wt_user_base {
    private Integer user_id;
    private String user_name;
    private String user_img_url;
    private String user_autograph;
    private String user_update_time;
    private String user_create_time;

    public wt_user_base() {
    }

    @Override
    public String toString() {
        return "wt_user_base{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_img_url='" + user_img_url + '\'' +
                ", user_autograph='" + user_autograph + '\'' +
                ", user_update_time='" + user_update_time + '\'' +
                ", user_create_time='" + user_create_time + '\'' +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_img_url() {
        return user_img_url;
    }

    public void setUser_img_url(String user_img_url) {
        this.user_img_url = user_img_url;
    }

    public String getUser_autograph() {
        return user_autograph;
    }

    public void setUser_autograph(String user_autograph) {
        this.user_autograph = user_autograph;
    }

    public String getUser_update_time() {
        return user_update_time;
    }

    public void setUser_update_time(String user_update_time) {
        this.user_update_time = user_update_time;
    }

    public String getUser_create_time() {
        return user_create_time;
    }

    public void setUser_create_time(String user_create_time) {
        this.user_create_time = user_create_time;
    }

    public wt_user_base(Integer user_id, String user_name, String user_img_url, String user_autograph, String user_update_time, String user_create_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_img_url = user_img_url;
        this.user_autograph = user_autograph;
        this.user_update_time = user_update_time;
        this.user_create_time = user_create_time;
    }
}
