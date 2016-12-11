package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class Museum implements Serializable {


    /**
     * code : 200
     * museums : [{"museum_id":10,"museum_name":"福建博物馆","museum_img":"http://img155.poco.cn/mypoco/myphoto/20090706/21/40825267200907062114221517973508412_003.jpg","museum_title":"福建博物院坐落于西湖公园，新馆于2002年10月建成，占地面积6公顷，建筑面积3.6万平方米。主体建筑是数个具有福建文化特色元素的集合","view_count":321,"type":"[陶瓷器,玉石器,自然科技,书画,文献,竹木漆器,织绣,杂项,金属]"},{"museum_id":17,"museum_name":"安徽博物院","museum_img":"http://www.archreport.com.cn/uploadfile/2013/1106/20131106044307817.jpg","museum_title":"安徽省博物馆成立于1956年11 月14日，是当时全国四大样板馆之一。2010年12月28日更名为安徽博物院","view_count":223,"type":"[青銅器,陶瓷器,书画,金银玉器,文房四宝,古代工艺,古生物化石]"},{"museum_id":3,"museum_name":"旅顺博物馆","museum_img":"http://p.chanyouji.cn/95078/1389166758743p18docjqcqk4116s11j3b1mjm8shn.jpg","museum_title":"旅顺博物馆坐落于有\u201c半部中国近代史\u201d之称的大连市旅顺口区，是大连市属的历史艺术性博物馆","view_count":212,"type":"[古丝绸之路文物,佛教艺术品,金石文物,陶瓷器,书画,工艺品,善本档案,大连出土文物,古代日本陶瓷书画]"},{"museum_id":8,"museum_name":"陕西博物馆","museum_img":"http://img1.cache.netease.com/catchpic/E/E1/E18F731573E563088755A6E8C7E18B34.jpg","museum_title":"陕西历史博物馆位于西安大雁塔的西北侧，筹建于1983年，1991年6月20日落成开放，是中国第一座大型现代化国家级博物馆","view_count":122,"type":"[藏馆精品]"},{"museum_id":12,"museum_name":"广东省博物馆","museum_img":"http://www.chezhan168.com/userfiles/image/20151221/21133056edf7f86b0f0984.jpg","museum_title":"广东省博物馆于1957年开始筹建，1959年10月1日，广东省博物馆及其所辖的广州鲁迅纪念馆正式对外开放","view_count":95,"type":"[青花瓷之约,珍品鉴赏,自然标本,专题鉴赏]"},{"museum_id":4,"museum_name":"大连现代博物馆","museum_img":"http://www.qdexpo.cn/file/2012091216555173499.jpg","museum_title":"大连现代博物馆位于市区南部的星海湾，是世纪之交大连市政府斥资建设的大型文化设施","view_count":6}]
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

    public static class MuseumsBean {
        /**
         * museum_id : 10
         * museum_name : 福建博物馆
         * museum_img : http://img155.poco.cn/mypoco/myphoto/20090706/21/40825267200907062114221517973508412_003.jpg
         * museum_title : 福建博物院坐落于西湖公园，新馆于2002年10月建成，占地面积6公顷，建筑面积3.6万平方米。主体建筑是数个具有福建文化特色元素的集合
         * view_count : 321
         * type : [陶瓷器,玉石器,自然科技,书画,文献,竹木漆器,织绣,杂项,金属]
         */

        private int museum_id;
        private String museum_name;
        private String museum_img;
        private String museum_title;
        private int view_count;
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
