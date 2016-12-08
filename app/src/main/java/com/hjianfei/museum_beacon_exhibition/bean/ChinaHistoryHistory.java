package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryHistory implements Serializable {

    /**
     * code : 200
     * china_History_Histories : [{"detail_url":"http://www.todayonhistory.com/lishi/201511/25706.html","type":"上古","title":"黄帝的妻子是谁 黄帝有几个妻子","author":"  作者:新之助","time":"时间: 2015-11-26","views":"5349"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25719.html","type":"上古","title":"黄帝陵之谜 黄帝的陵墓在哪里","author":"  作者:新之助","time":"时间: 2015-11-26","views":"2299"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25413.html","type":"上古","title":"如何区分旧石器时代和新石器时代","author":"  作者:新之助","time":"时间: 2015-11-23","views":"2134"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/23818.html","type":"上古","title":"黄帝和炎帝阪泉之战原因及始末","author":"  作者:浅草","time":"时间: 2015-11-05","views":"1662"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25716.html","type":"上古","title":"炎帝陵之谜 为何有两个炎帝陵","author":"  作者:新之助","time":"时间: 2015-11-26","views":"1446"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25712.html","type":"上古","title":"尧陵之谜 帝尧的葬处到底在哪里","author":"  作者:新之助","time":"时间: 2015-11-26","views":"821"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19701.html","type":"上古","title":"细数颛顼、帝喾的八大功德","author":"  作者:山南慕北","time":"时间: 2015-10-06","views":"600"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25718.html","type":"上古","title":"女娲陵之谜 女娲的陵墓到底在何处","author":"  作者:新之助","time":"时间: 2015-11-26","views":"584"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19602.html","type":"上古","title":"盘点上古时期的十大神器","author":"  作者:山南慕北","time":"时间: 2015-10-06","views":"556"},{"detail_url":"http://www.todayonhistory.com/lishi/201511/25553.html","type":"上古","title":"如何划分旧石器时代的人类进化阶段","author":"  作者:新之助","time":"时间: 2015-11-24","views":"418"}]
     */

    private int code;
    private List<ChinaHistoryHistoriesBean> china_History_Histories;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryHistoriesBean> getChina_History_Histories() {
        return china_History_Histories;
    }

    public void setChina_History_Histories(List<ChinaHistoryHistoriesBean> china_History_Histories) {
        this.china_History_Histories = china_History_Histories;
    }

    public static class ChinaHistoryHistoriesBean implements Serializable {
        /**
         * detail_url : http://www.todayonhistory.com/lishi/201511/25706.html
         * type : 上古
         * title : 黄帝的妻子是谁 黄帝有几个妻子
         * author :   作者:新之助
         * time : 时间: 2015-11-26
         * views : 5349
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
