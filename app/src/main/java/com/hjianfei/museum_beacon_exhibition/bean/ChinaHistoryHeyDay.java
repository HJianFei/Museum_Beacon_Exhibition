package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHeyDay implements Serializable {

    /**
     * code : 200
     * china_History_Hey_Days : [{"title":"七一五反革命政变","img_url":"http://www.todayonhistory.com/uploadfile/2016/1118/thumb_324_216_20161118103649397.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/53284.html"},{"title":"七国之乱","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518093702640.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39730.html"},{"title":"三家分晋","img_url":"http://www.todayonhistory.com/uploadfile/2016/0516/thumb_324_216_20160516044614438.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39483.html"},{"title":"三藩之乱","img_url":"http://www.todayonhistory.com/uploadfile/2016/0519/thumb_324_216_20160519091440665.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39883.html"},{"title":"东北易帜","img_url":"http://www.todayonhistory.com/uploadfile/2016/0517/thumb_324_216_20160517035445114.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39671.html"},{"title":"义和团运动","img_url":"http://www.todayonhistory.com/uploadfile/2016/0517/thumb_324_216_20160517033239927.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39663.html"},{"title":"五胡乱华","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518110446242.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39761.html"},{"title":"仁宗盛治","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518033159380.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39819.html"},{"title":"仁宣之治","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518045255516.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39852.html"},{"title":"元和中兴","img_url":"http://www.todayonhistory.com/uploadfile/2016/0518/thumb_324_216_20160518031654301.jpg","detail_url":"http://www.todayonhistory.com/lishi/dsj/39810.html"}]
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

    public static class ChinaHistoryHeyDaysBean implements Serializable {
        /**
         * title : 七一五反革命政变
         * img_url : http://www.todayonhistory.com/uploadfile/2016/1118/thumb_324_216_20161118103649397.jpg
         * detail_url : http://www.todayonhistory.com/lishi/dsj/53284.html
         */

        private String title;
        private String img_url;
        private String detail_url;

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

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }
}
