package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/9/21.
 */

public class AppreciateDetail implements Serializable {


    /**
     * code : 200
     * appreciateDetail : {"id":235,"title":"明代景德镇窑青花花鸟纹瓣口折沿碗","content":"\u201c万历号\u201d沉船青花瓷器","img_url":"[http://www.gdmuseum.com/attachment/201607/12/2_1468309962hLlA.jpg]"}
     */

    private int code;
    private AppreciateDetailBean appreciateDetail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public AppreciateDetailBean getAppreciateDetail() {
        return appreciateDetail;
    }

    public void setAppreciateDetail(AppreciateDetailBean appreciateDetail) {
        this.appreciateDetail = appreciateDetail;
    }

    public static class AppreciateDetailBean implements Serializable{
        /**
         * id : 235
         * title : 明代景德镇窑青花花鸟纹瓣口折沿碗
         * content : “万历号”沉船青花瓷器
         * img_url : [http://www.gdmuseum.com/attachment/201607/12/2_1468309962hLlA.jpg]
         */

        private int id;
        private String title;
        private String content;
        private String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        @Override
        public String toString() {
            return "AppreciateDetailBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", img_url='" + img_url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppreciateDetail{" +
                "code=" + code +
                ", appreciateDetail=" + appreciateDetail +
                '}';
    }
}
