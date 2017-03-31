package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistoryDetail implements Serializable {


    private int code;
    private ChinaHistoryHistoryDetailBean china_History_History_Detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ChinaHistoryHistoryDetailBean getChina_History_History_Detail() {
        return china_History_History_Detail;
    }

    public void setChina_History_History_Detail(ChinaHistoryHistoryDetailBean china_History_History_Detail) {
        this.china_History_History_Detail = china_History_History_Detail;
    }

    public static class ChinaHistoryHistoryDetailBean implements Serializable {


        private int id;
        private String title;
        private String img_url;
        private String content;

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

        @Override
        public String toString() {
            return "ChinaHistoryHistoryDetailBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChinaHistoryHistoryDetail{" +
                "code=" + code +
                ", china_History_History_Detail=" + china_History_History_Detail +
                '}';
    }
}
