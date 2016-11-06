package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/9/21.
 */

public class AppreciateDetail implements Serializable {

    /**
     * detail_url : http://www.gdmuseum.com/curio_detail.php?picid=10003&subgid=149&gid=159&title=�㶫��ʷ����
     * title : 带藤盒粉彩茶壶
     * content :
     * img_url : [http://www.gdmuseum.com/attachment/201602/22/2_14561046445VRP.jpg]
     */

    private AppreciateDetailBean appreciateDetail;
    /**
     * appreciateDetail : {"detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=10003&subgid=149&gid=159&title=�㶫��ʷ����","title":"带藤盒粉彩茶壶","content":"","img_url":"[http://www.gdmuseum.com/attachment/201602/22/2_14561046445VRP.jpg]"}
     * status : 1
     */

    private String status;

    public AppreciateDetailBean getAppreciateDetail() {
        return appreciateDetail;
    }

    public void setAppreciateDetail(AppreciateDetailBean appreciateDetail) {
        this.appreciateDetail = appreciateDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class AppreciateDetailBean {
        private String detail_url;
        private String title;
        private String content;
        private String img_url;

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
    }
}
