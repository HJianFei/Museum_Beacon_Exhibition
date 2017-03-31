package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaHistoryBigThing implements Serializable {


    /**
     * code : 200
     * china_History_Big_Things : [{"id":3,"type":"上古","title":"什么是禅让制？ 上古时期的禅让制"},{"id":4,"type":"上古","title":"仓颉造字 仓颉造了哪些字？"},{"id":7,"type":"上古","title":"上古神话传说之神农尝百草"},{"id":12,"type":"上古","title":"上古神话传说之盘古开天辟地"},{"id":13,"type":"上古","title":"上古神话传说之女娲造人"},{"id":14,"type":"上古","title":"上古神话传说之燧人取火"},{"id":15,"type":"上古","title":"细数颛顼、帝喾的八大功德"},{"id":196,"type":"上古","title":"123"}]
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
         * id : 3
         * type : 上古
         * title : 什么是禅让制？ 上古时期的禅让制
         */

        private int id;
        private String type;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        @Override
        public String toString() {
            return "ChinaHistoryBigThingsBean{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChinaHistoryBigThing{" +
                "code=" + code +
                ", china_History_Big_Things=" + china_History_Big_Things +
                '}';
    }
}
