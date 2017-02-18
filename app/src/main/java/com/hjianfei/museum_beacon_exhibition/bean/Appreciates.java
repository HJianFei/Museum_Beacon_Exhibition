package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间： 2016/9/17.
 * 作者：HJianFei
 * 功能描述：
 */

public class Appreciates implements Serializable {


    /**
     * appreciates : [{"id":1031,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_1280299449V1UI.jpg","content":"黄嘴白鹭","type":"自然标本","view_count":1,"museum_name":"广东省博物馆"},{"id":1032,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_12802997872bLS.jpg","content":"黄腹鼬","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1033,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_1280299908435m.jpg","content":"小灵猫","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1034,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_12802999777THA.jpg","content":"豪猪","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1035,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_1280300431dHt3.jpg","content":"穿山甲","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1036,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_1280300543E14h.jpg","content":"地龟","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1037,"img_url":"http://www.gdmuseum.com/attachment/201007/28/2_1280300845BoOX.jpg","content":"猕猴","type":"自然标本","view_count":1,"museum_name":"广东省博物馆"},{"id":1038,"img_url":"http://www.gdmuseum.com/attachment/201203/29/2_1332982184mdyJ.jpg","content":"黑鹳","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1039,"img_url":"http://www.gdmuseum.com/attachment/201203/29/2_1332982190Q2Jj.jpg","content":"中华秋沙鸭","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"},{"id":1040,"img_url":"http://www.gdmuseum.com/attachment/201203/29/2_1332982201hDNZ.jpg","content":"黄腹角雉","type":"自然标本","view_count":0,"museum_name":"广东省博物馆"}]
     * code : 200
     */

    private int code;
    private List<AppreciatesBean> appreciates;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AppreciatesBean> getAppreciates() {
        return appreciates;
    }

    public void setAppreciates(List<AppreciatesBean> appreciates) {
        this.appreciates = appreciates;
    }

    public static class AppreciatesBean implements Serializable{
        /**
         * id : 1031
         * img_url : http://www.gdmuseum.com/attachment/201007/28/2_1280299449V1UI.jpg
         * content : 黄嘴白鹭
         * type : 自然标本
         * view_count : 1
         * museum_name : 广东省博物馆
         */

        private int id;
        private String img_url;
        private String content;
        private String type;
        private int view_count;
        private String museum_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public String getMuseum_name() {
            return museum_name;
        }

        public void setMuseum_name(String museum_name) {
            this.museum_name = museum_name;
        }

        @Override
        public String toString() {
            return "AppreciatesBean{" +
                    "id=" + id +
                    ", img_url='" + img_url + '\'' +
                    ", content='" + content + '\'' +
                    ", type='" + type + '\'' +
                    ", view_count=" + view_count +
                    ", museum_name='" + museum_name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Appreciates{" +
                "code=" + code +
                ", appreciates=" + appreciates +
                '}';
    }
}
