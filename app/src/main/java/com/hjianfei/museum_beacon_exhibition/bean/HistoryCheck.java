package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryCheck implements Serializable {


    /**
     * code : 200
     * checks : [{"id":1,"title":"十大悲情奥运人物","img_url":"http://www.todayonhistory.com/uploadfile/2016/0808/thumb_324_216_20160808022012693.jpg","time":270},{"id":2,"title":"中国历代皇帝寿命","img_url":"http://www.todayonhistory.com/uploadfile/2016/0802/thumb_324_216_20160802052443765.jpg","time":269},{"id":3,"title":"盘点历史\u201c奇葩\u201d诺奖得主","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426052948816.jpg","time":268},{"id":4,"title":"被被丑化的历史人物","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426051353832.jpg","time":267},{"id":5,"title":"中国历史上十大贪官","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426050331653.jpg","time":266},{"id":6,"title":"历史上的十大同性恋","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426044822856.jpg","time":265},{"id":7,"title":"中国历史上十大文武双全的名人","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426043421260.jpg","time":264},{"id":8,"title":"中国历史上怕老婆的十大名人 ","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426042851556.jpg","time":263},{"id":9,"title":"中国历史上著名\u201c父子档\u201d","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426041204504.jpg","time":262},{"id":10,"title":"史上最痴情的八位帝王","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426040518619.jpg","time":261}]
     */

    private int code;
    private List<ChecksBean> checks;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChecksBean> getChecks() {
        return checks;
    }

    public void setChecks(List<ChecksBean> checks) {
        this.checks = checks;
    }

    public static class ChecksBean implements Serializable{
        /**
         * id : 1
         * title : 十大悲情奥运人物
         * img_url : http://www.todayonhistory.com/uploadfile/2016/0808/thumb_324_216_20160808022012693.jpg
         * time : 270
         */

        private int id;
        private String title;
        private String img_url;
        private int time;

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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "ChecksBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", time=" + time +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HistoryCheck{" +
                "code=" + code +
                ", checks=" + checks +
                '}';
    }
}
