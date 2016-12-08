package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryCulture implements Serializable {

    /**
     * code : 200
     * china_History_Cultures : [{"detail_url":"http://www.todayonhistory.com/lishi/201510/19682.html","type":"上古","title":"上古神话四大凶兽vs四大神兽","author":"  作者:山南慕北","time":"时间: 2015-10-06","views":"10574"},{"detail_url":"http://www.todayonhistory.com/lishi/201602/30323.html","type":"上古","title":"上古三大奇书有哪些？上古三大奇书简介","author":"  作者:山南慕北","time":"时间: 2016-02-18","views":"1272"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/23831.html","type":"上古","title":"黄帝的传说故事 黄帝大战蚩尤","author":"  作者:浅草","time":"时间: 2015-11-05","views":"967"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/23804.html","type":"上古","title":"伏羲画八卦的传说 伏羲八卦亭在哪？","author":"  作者:浅草","time":"时间: 2015-11-05","views":"817"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/23701.html","type":"上古","title":"半坡人文化遗址","author":"  作者:浅草","time":"时间: 2015-11-04","views":"768"},{"detail_url":"http://www.todayonhistory.com/lishi/201602/30468.html","type":"上古","title":"红山文化中的墓葬习俗与文化","author":"  作者:山南慕北","time":"时间: 2016-02-19","views":"666"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25694.html","type":"上古","title":"盘点中国神话中的龙有哪些","author":"  作者:新之助","time":"时间: 2015-11-26","views":"650"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/23809.html","type":"上古","title":"共工的传说 共工怒触不周山的原因是什么","author":"  作者:浅草","time":"时间: 2015-11-05","views":"497"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19615.html","type":"上古","title":"仓颉造字 仓颉造了哪些字？","author":"  作者:山南慕北","time":"时间: 2015-10-06","views":"428"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/23827.html","type":"上古","title":"武战神蚩尤的传说故事","author":"  作者:浅草","time":"时间: 2015-11-05","views":"378"}]
     */

    private int code;
    private List<ChinaHistoryCulturesBean> china_History_Cultures;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryCulturesBean> getChina_History_Cultures() {
        return china_History_Cultures;
    }

    public void setChina_History_Cultures(List<ChinaHistoryCulturesBean> china_History_Cultures) {
        this.china_History_Cultures = china_History_Cultures;
    }

    public static class ChinaHistoryCulturesBean implements Serializable {
        /**
         * detail_url : http://www.todayonhistory.com/lishi/201510/19682.html
         * type : 上古
         * title : 上古神话四大凶兽vs四大神兽
         * author :   作者:山南慕北
         * time : 时间: 2015-10-06
         * views : 10574
         */

        private String detail_url;
        private String type;
        private String title;
        private String author;
        private String time;
        private String views;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }
    }
}
