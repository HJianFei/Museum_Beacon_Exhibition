package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class Museum implements Serializable {


    /**
     * code : 200
     * museums : [{"museum_id":12,"museum_name":"广东省博物馆","museum_img":"http://www.chezhan168.com/userfiles/image/20151221/21133056edf7f86b0f0984.jpg","museum_title":"广东省博物馆于1957年开始筹建，1959年10月1日，广东省博物馆及其所辖的广州鲁迅纪念馆正式对外开放","view_count":57},{"museum_id":1,"museum_name":"故宫博物院","museum_img":"http://images.quanjing.com/chineseview113/high/388-3022.jpg","museum_title":"北京故宫博物院建立于1925年10月10日，位于北京故宫紫禁城内。是在明朝、清朝两代皇宫及其收藏的基础上建立起来的中国综合性博物馆，也是中国最大的古代文化艺术博物馆","view_count":27},{"museum_id":8,"museum_name":"陕西博物馆","museum_img":"http://img1.cache.netease.com/catchpic/E/E1/E18F731573E563088755A6E8C7E18B34.jpg","museum_title":"陕西历史博物馆位于西安大雁塔的西北侧，筹建于1983年，1991年6月20日落成开放，是中国第一座大型现代化国家级博物馆","view_count":6},{"museum_id":3,"museum_name":"旅顺博物馆","museum_img":"http://p.chanyouji.cn/95078/1389166758743p18docjqcqk4116s11j3b1mjm8shn.jpg","museum_title":"旅顺博物馆坐落于有\u201c半部中国近代史\u201d之称的大连市旅顺口区，是大连市属的历史艺术性博物馆","view_count":5},{"museum_id":4,"museum_name":"大连现代博物馆","museum_img":"http://www.qdexpo.cn/file/2012091216555173499.jpg","museum_title":"大连现代博物馆位于市区南部的星海湾，是世纪之交大连市政府斥资建设的大型文化设施","view_count":5},{"museum_id":10,"museum_name":"福建博物馆","museum_img":"http://img155.poco.cn/mypoco/myphoto/20090706/21/40825267200907062114221517973508412_003.jpg","museum_title":"福建博物院坐落于西湖公园，新馆于2002年10月建成，占地面积6公顷，建筑面积3.6万平方米。主体建筑是数个具有福建文化特色元素的集合","view_count":5}]
     */

    private int code;
    private List<MuseumsBean> museums;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MuseumsBean> getMuseums() {
        return museums;
    }

    public void setMuseums(List<MuseumsBean> museums) {
        this.museums = museums;
    }

    public static class MuseumsBean implements Serializable{
        /**
         * museum_id : 12
         * museum_name : 广东省博物馆
         * museum_img : http://www.chezhan168.com/userfiles/image/20151221/21133056edf7f86b0f0984.jpg
         * museum_title : 广东省博物馆于1957年开始筹建，1959年10月1日，广东省博物馆及其所辖的广州鲁迅纪念馆正式对外开放
         * view_count : 57
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
