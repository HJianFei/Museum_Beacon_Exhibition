package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

public class Collection implements Serializable {


    private String user_phone;
    private String post_id;
    private String post_type;
    private String created_time;

    public Collection() {
        super();
    }

    public Collection(String user_phone, String post_id, String post_type, String created_time) {
        super();
        this.user_phone = user_phone;
        this.post_id = post_id;
        this.post_type = post_type;
        this.created_time = created_time;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Collection [user_phone=" + user_phone + ", post_id=" + post_id + ", post_type=" + post_type
                + ", created_time=" + created_time + "]";
    }


}
