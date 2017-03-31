package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHeyDay implements Serializable {


    /**
     * code : 200
     * china_History_Hey_Days : [{"id":1,"title":"七一五反革命政变","img_url":"http://www.todayonhistory.com/uploadfile/2016/1118/thumb_324_216_20161118103649397.jpg"},{"id":2,"title":"七国之乱","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518093702640.jpg"},{"id":3,"title":"三家分晋","img_url":"http://www.todayonhistory.com/uploadfile/2016/0516/thumb_324_216_20160516044614438.jpg"},{"id":4,"title":"三藩之乱","img_url":"http://www.todayonhistory.com/uploadfile/2016/0519/thumb_324_216_20160519091440665.jpg"},{"id":5,"title":"东北易帜","img_url":"http://www.todayonhistory.com/uploadfile/2016/0517/thumb_324_216_20160517035445114.jpg"},{"id":6,"title":"义和团运动","img_url":"http://www.todayonhistory.com/uploadfile/2016/0517/thumb_324_216_20160517033239927.jpg"},{"id":7,"title":"五胡乱华","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518110446242.jpg"},{"id":8,"title":"仁宗盛治","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518033159380.jpg"},{"id":9,"title":"仁宣之治","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518045255516.jpg"},{"id":10,"title":"元和中兴","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518031654301.jpg"}]
     */

    private int code;
    private List<ChinaHistoryHeyDaysBean> china_History_Hey_Days;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryHeyDaysBean> getChina_History_Hey_Days() {
        return china_History_Hey_Days;
    }

    public void setChina_History_Hey_Days(List<ChinaHistoryHeyDaysBean> china_History_Hey_Days) {
        this.china_History_Hey_Days = china_History_Hey_Days;
    }

    public static class ChinaHistoryHeyDaysBean implements Serializable{
        /**
         * id : 1
         * title : 七一五反革命政变
         * img_url : http://www.todayonhistory.com/uploadfile/2016/1118/thumb_324_216_20161118103649397.jpg
         */

        private int id;
        private String title;
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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        @Override
        public String toString() {
            return "ChinaHistoryHeyDaysBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", img_url='" + img_url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChinaHistoryHeyDay{" +
                "code=" + code +
                ", china_History_Hey_Days=" + china_History_Hey_Days +
                '}';
    }
}
