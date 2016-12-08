package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaHistoryBigThing implements Serializable {

    /**
     * code : 200
     * china_History_Big_Things : [{"detail_url":"http://www.todayonhistory.com/lishi/201510/19614.html","type":"上古","title":"什么是禅让制？ 上古时期的禅让制"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19615.html","type":"上古","title":"仓颉造字 仓颉造了哪些字？"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19673.html","type":"上古","title":"上古神话传说之神农尝百草"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19692.html","type":"上古","title":"上古神话传说之盘古开天辟地"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19694.html","type":"上古","title":"上古神话传说之女娲造人"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19697.html","type":"上古","title":"上古神话传说之燧人取火"},{"detail_url":"http://www.todayonhistory.com/lishi/201510/19701.html","type":"上古","title":"细数颛顼、帝喾的八大功德"}]
     */

    private int code;
    private List<ChinaHistoryBigThingsBean> china_History_Big_Things;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryBigThingsBean> getChina_History_Big_Things() {
        return china_History_Big_Things;
    }

    public void setChina_History_Big_Things(List<ChinaHistoryBigThingsBean> china_History_Big_Things) {
        this.china_History_Big_Things = china_History_Big_Things;
    }

    public static class ChinaHistoryBigThingsBean implements Serializable {
        /**
         * detail_url : http://www.todayonhistory.com/lishi/201510/19614.html
         * type : 上古
         * title : 什么是禅让制？ 上古时期的禅让制
         */

        private String detail_url;
        private String type;
        private String title;

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
    }
}
