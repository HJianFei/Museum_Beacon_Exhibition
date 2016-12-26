package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class HistoryPeopleDetail implements Serializable {


    private int code;
    private ChinaHistoryPeopleDetailBean china_History_People_Detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ChinaHistoryPeopleDetailBean getChina_History_People_Detail() {
        return china_History_People_Detail;
    }

    public void setChina_History_People_Detail(ChinaHistoryPeopleDetailBean china_History_People_Detail) {
        this.china_History_People_Detail = china_History_People_Detail;
    }

    public static class ChinaHistoryPeopleDetailBean implements Serializable {


        private String detail_url;
        private String name;
        private String content;

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "ChinaHistoryPeopleDetailBean{" +
                    "detail_url='" + detail_url + '\'' +
                    ", name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HistoryPeopleDetail{" +
                "code=" + code +
                ", china_History_People_Detail=" + china_History_People_Detail +
                '}';
    }
}
