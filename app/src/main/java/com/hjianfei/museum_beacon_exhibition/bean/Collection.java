package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

public class Collection implements Serializable {


    /**
     * collections : [{"user_phone":"123","post_id":"广东省博物馆","post_type":"博物馆","created_time":"2016-11-20 00:08:05","img_url":"http://www.chezhan168.com/userfiles/image/20151221/21133056edf7f86b0f0984.jpg","detail_url":"广东省博物馆"}]
     * status : 1
     */

    private String status;
    private List<CollectionsBean> collections;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CollectionsBean> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionsBean> collections) {
        this.collections = collections;
    }

    public static class CollectionsBean implements Serializable{
        /**
         * user_phone : 123
         * post_id : 广东省博物馆
         * post_type : 博物馆
         * created_time : 2016-11-20 00:08:05
         * img_url : http://www.chezhan168.com/userfiles/image/20151221/21133056edf7f86b0f0984.jpg
         * detail_url : 广东省博物馆
         */

        private String user_phone;
        private String post_id;
        private String post_type;
        private String created_time;
        private String img_url;
        private String detail_url;

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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }
}
