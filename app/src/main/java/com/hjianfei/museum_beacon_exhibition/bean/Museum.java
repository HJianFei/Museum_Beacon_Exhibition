package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class Museum implements Serializable {

    /**
     * museums : [{"museum_id":2,"museum_name":"河北博物院","museum_img":"http://attach.bbs.china.com.cn/forum/201512/23/091753i1rgo30w1aadkumm.jpg","museum_title":"河北博物院位于石家庄市文化广场，北起中山路，南到范西路，东临东大街，西至西大街，是河北省省级综合性博物馆"}]
     * status : 1
     */

    private String status;
    /**
     * museum_id : 1
     * museum_name : 故宫博物院
     * museum_img : http://images.quanjing.com/chineseview113/high/388-3022.jpg
     * museum_title : 北京故宫博物院建立于1925年10月10日，位于北京故宫紫禁城内。是在明朝、清朝两代皇宫及其收藏的基础上建立起来的中国综合性博物馆，也是中国最大的古代文化艺术博物馆
     */

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

    public static class MuseumsBean {
        private int museum_id;
        private String museum_name;
        private String museum_img;
        private String museum_title;

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

        @Override
        public String toString() {
            return "MuseumsBean{" +
                    "museum_id=" + museum_id +
                    ", museum_name='" + museum_name + '\'' +
                    ", museum_img='" + museum_img + '\'' +
                    ", museum_title='" + museum_title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Museum{" +
                "status='" + status + '\'' +
                ", museums=" + museums +
                '}';
    }
}
