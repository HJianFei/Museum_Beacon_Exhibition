package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/9.
 */

public class HistoryCheck implements Serializable {

    /**
     * code : 200
     * checks : [{"title":"十大悲情奥运人物","img_url":"http://www.todayonhistory.com/uploadfile/2016/0808/thumb_324_216_20160808022012693.jpg","detail_url":"http://www.todayonhistory.com/lishi/201608/46674.html","time":270},{"title":"中国历代皇帝寿命","img_url":"http://www.todayonhistory.com/uploadfile/2016/0802/thumb_324_216_20160802052443765.jpg","detail_url":"http://www.todayonhistory.com/lishi/201608/46235.html","time":269},{"title":"盘点历史\u201c奇葩\u201d诺奖得主","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426052948816.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36569.html","time":268},{"title":"被被丑化的历史人物","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426051353832.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36555.html","time":267},{"title":"中国历史上十大贪官","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426050331653.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36552.html","time":266},{"title":"历史上的十大同性恋","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426044822856.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36547.html","time":265},{"title":"中国历史上十大文武双全的名人","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426043421260.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36543.html","time":264},{"title":"中国历史上怕老婆的十大名人 ","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426042851556.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36541.html","time":263},{"title":"中国历史上著名\u201c父子档\u201d","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426041204504.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36536.html","time":262},{"title":"史上最痴情的八位帝王","img_url":"http://www.todayonhistory.com/uploadfile/2016/0426/20160426040518619.jpg","detail_url":"http://www.todayonhistory.com/lishi/201604/36534.html","time":261}]
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

    public static class ChecksBean {
        /**
         * title : 十大悲情奥运人物
         * img_url : http://www.todayonhistory.com/uploadfile/2016/0808/thumb_324_216_20160808022012693.jpg
         * detail_url : http://www.todayonhistory.com/lishi/201608/46674.html
         * time : 270
         */

        private String title;
        private String img_url;
        private String detail_url;
        private int time;

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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
