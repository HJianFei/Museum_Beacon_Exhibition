package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaHistoryPeople implements Serializable {

    /**
     * china_History_Peoples : [{"detail_url":"http://www.todayonhistory.com/people/201506/9618.html","type":"上古","name":"蚩尤","img_url":"http://www.todayonhistory.com/uploadfile/2015/0610/20150610050329589.jpg","views":795},{"detail_url":"http://www.todayonhistory.com/people/201506/9619.html","type":"上古","name":"黄帝","img_url":"http://www.todayonhistory.com/uploadfile/2015/0610/20150610052439915.jpg","views":795},{"detail_url":"http://www.todayonhistory.com/people/201506/9620.html","type":"上古","name":"炎帝","img_url":"http://www.todayonhistory.com/uploadfile/2015/0611/20150611092923996.jpg","views":795},{"detail_url":"http://www.todayonhistory.com/people/201506/9621.html","type":"上古","name":"伏羲","img_url":"http://www.todayonhistory.com/uploadfile/2015/0611/20150611094735617.jpg","views":795},{"detail_url":"http://www.todayonhistory.com/people/201506/9622.html","type":"上古","name":"颛顼","img_url":"http://www.todayonhistory.com/uploadfile/2015/0611/20150611101516372.jpg","views":795},{"detail_url":"http://www.todayonhistory.com/people/201510/11448.html","type":"上古","name":"仓颉","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/20151015033036576.jpg","views":795}]
     * code : 200
     */

    private int code;
    private List<ChinaHistoryPeoplesBean> china_History_Peoples;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryPeoplesBean> getChina_History_Peoples() {
        return china_History_Peoples;
    }

    public void setChina_History_Peoples(List<ChinaHistoryPeoplesBean> china_History_Peoples) {
        this.china_History_Peoples = china_History_Peoples;
    }

    public static class ChinaHistoryPeoplesBean implements Serializable{
        /**
         * detail_url : http://www.todayonhistory.com/people/201506/9618.html
         * type : 上古
         * name : 蚩尤
         * img_url : http://www.todayonhistory.com/uploadfile/2015/0610/20150610050329589.jpg
         * views : 795
         */

        private String detail_url;
        private String type;
        private String name;
        private String img_url;
        private int views;

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }
    }
}
