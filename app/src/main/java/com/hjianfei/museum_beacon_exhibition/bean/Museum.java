package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class Museum implements Serializable {


    /**
     * museums : [{"museum_id":1,"museum_name":"故宫博物院","museum_img":"http://images.quanjing.com/chineseview113/high/388-3022.jpg","museum_title":"北京故宫博物院建立于1925年10月10日，位于北京故宫紫禁城内。是在明朝、清朝两代皇宫及其收藏的基础上建立起来的中国综合性博物馆，也是中国最大的古代文化艺术博物馆","view_count":1},{"museum_id":2,"museum_name":"河北博物院","museum_img":"http://attach.bbs.china.com.cn/forum/201512/23/091753i1rgo30w1aadkumm.jpg","museum_title":"河北博物院位于石家庄市文化广场，北起中山路，南到范西路，东临东大街，西至西大街，是河北省省级综合性博物馆","view_count":0},{"museum_id":3,"museum_name":"旅顺博物馆","museum_img":"http://p.chanyouji.cn/95078/1389166758743p18docjqcqk4116s11j3b1mjm8shn.jpg","museum_title":"旅顺博物馆坐落于有\u201c半部中国近代史\u201d之称的大连市旅顺口区，是大连市属的历史艺术性博物馆","view_count":0},{"museum_id":4,"museum_name":"大连现代博物馆","museum_img":"http://www.qdexpo.cn/file/2012091216555173499.jpg","museum_title":"大连现代博物馆位于市区南部的星海湾，是世纪之交大连市政府斥资建设的大型文化设施","view_count":0},{"museum_id":5,"museum_name":"吉林省博物馆","museum_img":"http://p4.qhimg.com/t01ffa76943b63fc419.jpg","museum_title":"吉林省博物院，原名吉林省博物馆，1951年经吉林省人民政府批准建立，1952年在松花江畔的吉林市正式开放，1954年，随省政府迁至长春市","view_count":0},{"museum_id":6,"museum_name":"天津博物馆","museum_img":"http://www.archreport.com.cn/uploadfile/2013/1106/20131106043257757.jpg","museum_title":"天津博物馆是一座历史艺术类综合性博物馆，其前身可追溯到1918年成立的天津博物院，是国内较早建立的博物馆之一","view_count":0}]
     * status : 1
     */

    private String status;
    private List<MuseumsBean> museums;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MuseumsBean> getMuseums() {
        return museums;
    }

    public void setMuseums(List<MuseumsBean> museums) {
        this.museums = museums;
    }

    public static class MuseumsBean implements Serializable{
        /**
         * museum_id : 1
         * museum_name : 故宫博物院
         * museum_img : http://images.quanjing.com/chineseview113/high/388-3022.jpg
         * museum_title : 北京故宫博物院建立于1925年10月10日，位于北京故宫紫禁城内。是在明朝、清朝两代皇宫及其收藏的基础上建立起来的中国综合性博物馆，也是中国最大的古代文化艺术博物馆
         * view_count : 1
         */

        private int museum_id;
        private String museum_name;
        private String museum_img;
        private String museum_title;
        private int view_count;

        public int getMuseum_id() {
            return museum_id;
        }

        public void setMuseum_id(int museum_id) {
            this.museum_id = museum_id;
        }

        public String getMuseum_name() {
            return museum_name;
        }

        public void setMuseum_name(String museum_name) {
            this.museum_name = museum_name;
        }

        public String getMuseum_img() {
            return museum_img;
        }

        public void setMuseum_img(String museum_img) {
            this.museum_img = museum_img;
        }

        public String getMuseum_title() {
            return museum_title;
        }

        public void setMuseum_title(String museum_title) {
            this.museum_title = museum_title;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }
    }
}
