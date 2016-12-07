package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaDynasty implements Serializable {


    /**
     * code : 200
     * chinaDynasties : [{"id":3,"dynasty":"上古","dynasty_detail":"http://www.todayonhistory.com/lishi/shanggu/","img_url":"pictures/china_dynasty_shanggu.png"},{"id":4,"dynasty":"夏朝","dynasty_detail":"http://www.todayonhistory.com/lishi/xiachao/","img_url":"pictures/china_dynasty_xiachao.png"},{"id":5,"dynasty":"商朝","dynasty_detail":"http://www.todayonhistory.com/lishi/shangchao/","img_url":"pictures/china_dynasty_zhouchao.png"},{"id":6,"dynasty":"周朝","dynasty_detail":"http://www.todayonhistory.com/lishi/zhouchao/","img_url":"pictures/china_dynasty_zhouchao.png"},{"id":7,"dynasty":"春秋战国","dynasty_detail":"http://www.todayonhistory.com/lishi/chunqiuzhanguo/","img_url":"pictures/china_dynasty_chunqiuzhanguo.png"},{"id":8,"dynasty":"秦朝","dynasty_detail":"http://www.todayonhistory.com/lishi/qinchao/","img_url":"pictures/china_dynasty_qinchao.png"},{"id":9,"dynasty":"汉朝","dynasty_detail":"http://www.todayonhistory.com/lishi/hanchao/","img_url":"pictures/china_dynasty_hanchao.png"},{"id":10,"dynasty":"三国","dynasty_detail":"http://www.todayonhistory.com/lishi/sanguo/","img_url":"pictures/china_dynasty_sanguo.png"},{"id":11,"dynasty":"晋朝","dynasty_detail":"http://www.todayonhistory.com/lishi/jinchao/","img_url":"pictures/china_dynasty_jinchao.png"},{"id":12,"dynasty":"南北朝","dynasty_detail":"http://www.todayonhistory.com/lishi/nanbeichao/","img_url":"pictures/china_dynasty_nanbeichao.png"}]
     */

    private int code;
    private List<ChinaDynastiesBean> chinaDynasties;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaDynastiesBean> getChinaDynasties() {
        return chinaDynasties;
    }

    public void setChinaDynasties(List<ChinaDynastiesBean> chinaDynasties) {
        this.chinaDynasties = chinaDynasties;
    }

    public static class ChinaDynastiesBean implements Serializable {
        /**
         * id : 3
         * dynasty : 上古
         * dynasty_detail : http://www.todayonhistory.com/lishi/shanggu/
         * img_url : pictures/china_dynasty_shanggu.png
         */

        private int id;
        private String dynasty;
        private String dynasty_detail;
        private String img_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDynasty() {
            return dynasty;
        }

        public void setDynasty(String dynasty) {
            this.dynasty = dynasty;
        }

        public String getDynasty_detail() {
            return dynasty_detail;
        }

        public void setDynasty_detail(String dynasty_detail) {
            this.dynasty_detail = dynasty_detail;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
