package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/8.
 */

public class ChinaHistoryOldenWar implements Serializable {


    /**
     * code : 200
     * china_History_Olden_Wars : [{"id":4,"name":"七国之乱(公元前154年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/thumb_266_181_20151015031421837.jpg","description":"七国之乱是发生在汉景帝时期的一次诸侯国叛乱。其根源是强大的诸侯王势力与专制皇权的矛盾，七国之乱的平定，标志着西汉诸侯王势力的威胁基本被清除，中央集权进一步得到加强。","type":"古代战争"},{"id":6,"name":"万历朝鲜战争(1592年4月\u20141593年7月（第一次），1597年2月\u20141598年12月（第二次）)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1016/thumb_266_181_20151016025507369.jpg","description":"万历朝鲜战争是指发生于十六世纪末于朝鲜半岛的局部战争，分为两段，前后持续七年。万历朝鲜战争与宁夏之役、播州之役合称为万历三大征。","type":"古代战争"},{"id":8,"name":"三峰山之战(1232年2月（金正大九年正月）)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1016/thumb_266_181_20151016111712812.jpg","description":"三峰山之战在1232年发生，是蒙古灭金的一个重大战役。是中国战争史上追歼战的典型战例，拖雷采取跟踪攻扰以牵制疲惫及围三阙一的战法，一举全歼金军精锐，取得决战的胜利。","type":"古代战争"},{"id":9,"name":"三征高句丽(612\u2014614年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1016/thumb_266_181_20151016101050625.jpg","description":"三征高句丽是指隋炀帝时，三次对高句丽的军事行动。由于高句丽的顽强抵抗，经过这三次大规模的征战，隋朝的国力空耗，百姓民不聊生，激起了阶级矛盾。最终导致隋末农民起义的爆发。","type":"古代战争"},{"id":10,"name":"三监之乱(约公元前1045年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/thumb_266_181_20151015113236190.jpg","description":"三监之乱是西周初期商王畿地区的三位统治者叛乱的事件，周王朝面临严峻的形势，周公东征，诛武庚，杀管叔而放蔡叔，废霍叔为庶民，平定了三监之乱 。","type":"古代战争"},{"id":11,"name":"三藩之乱(1673年\u20141681年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1016/thumb_266_181_20151016034618267.jpg","description":"三藩是指平西王吴三桂、平南王尚可喜、靖南王耿精忠。1681年，清军进入云贵省城，吴世璠自杀，历时8年的三藩之乱被平定。对于清廷来说，是确立稳定的皇朝统治的标志。","type":"古代战争"},{"id":14,"name":"东京之战(1125年\u20141127年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1016/thumb_266_181_20151016113537568.jpg","description":"东京之战即东京保卫战是两宋之际以宗泽等抗战派将领为首的宋朝军民抗击金军侵略、保卫首都开封的重要战争。最终金军攻破东京，俘虏了宋徽宗、宋钦宗父子，以及大量赵氏皇族、后宫妃嫔","type":"古代战争"},{"id":15,"name":"东汉统一战争(公元25年至公元36年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/thumb_266_181_20151015024922285.jpg","description":"东汉统一战争是光武帝刘秀削平关东、关中、陇右等地的割据势力，再次完成统一全国大业的战争。东汉统一战争堪称为中国古代封建统一战争中的一个范例。","type":"古代战争"},{"id":22,"name":"临安之战(1275年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1016/thumb_266_181_20151016014847848.jpg","description":"在大军压境形势下，南宋朝廷一片混乱，丞相陈宜中请太皇太后出海避敌，张世杰、文天祥主张决死一战。元军在建康休整后兵精粮足，战斗力更强盛，随时准备攻取临安，处在进攻的有利地位","type":"古代战争"},{"id":26,"name":"井陉之战(公元前204年)","img_url":"http://www.todayonhistory.com/uploadfile/2015/1015/thumb_266_181_20151015032234739.jpg","description":"井陉之战中，韩信表现出了\u201c连百万之军，战必胜，攻必取\u201d的卓越智谋和用兵韬略，其战绩堪称军事史上的奇观，井陉之战则是他战例中的代表作。","type":"古代战争"}]
     */

    private int code;
    private List<ChinaHistoryOldenWarsBean> china_History_Olden_Wars;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ChinaHistoryOldenWarsBean> getChina_History_Olden_Wars() {
        return china_History_Olden_Wars;
    }

    public void setChina_History_Olden_Wars(List<ChinaHistoryOldenWarsBean> china_History_Olden_Wars) {
        this.china_History_Olden_Wars = china_History_Olden_Wars;
    }

    public static class ChinaHistoryOldenWarsBean implements Serializable {
        /**
         * id : 4
         * name : 七国之乱(公元前154年)
         * img_url : http://www.todayonhistory.com/uploadfile/2015/1015/thumb_266_181_20151015031421837.jpg
         * description : 七国之乱是发生在汉景帝时期的一次诸侯国叛乱。其根源是强大的诸侯王势力与专制皇权的矛盾，七国之乱的平定，标志着西汉诸侯王势力的威胁基本被清除，中央集权进一步得到加强。
         * type : 古代战争
         */

        private int id;
        private String name;
        private String img_url;
        private String description;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "ChinaHistoryOldenWarsBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", description='" + description + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ChinaHistoryOldenWar{" +
                "code=" + code +
                ", china_History_Olden_Wars=" + china_History_Olden_Wars +
                '}';
    }
}
