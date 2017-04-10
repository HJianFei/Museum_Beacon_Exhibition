package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/9/20.
 */

public class ExhibitionDetail implements Serializable {


    private ExhibitionDetailBean exhibitionDetail;

    private String status;

    public ExhibitionDetailBean getExhibitionDetail() {
        return exhibitionDetail;
    }

    public void setExhibitionDetail(ExhibitionDetailBean exhibitionDetail) {
        this.exhibitionDetail = exhibitionDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ExhibitionDetailBean {
        private String detail_url;
        private String title;
        private String show_time;
        private String address;
        private String img_url;
        private String content;

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShow_time() {
            return show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
            return "ExhibitionDetailBean{" +
                    "detail_url='" + detail_url + '\'' +
                    ", title='" + title + '\'' +
                    ", show_time='" + show_time + '\'' +
                    ", address='" + address + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ExhibitionDetail{" +
                "exhibitionDetail=" + exhibitionDetail +
                ", status='" + status + '\'' +
                '}';
    }
}
