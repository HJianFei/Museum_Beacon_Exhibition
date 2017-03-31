package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/11/5.
 */

public class MuseumDetail implements Serializable {


    /**
     * museum_Detail : {"id":2,"museum_detail_name":"河北博物院","museum_detail_content":"河北博物院简介\r\n  河北博物院位于石家庄市文化广场，北起中山路，南到范西路，东临东大街，西至西大街，是河北省省级综合性博物馆、全国爱国主义教育示范基地、国家一级博物馆。\r\n  河北博物院的前身是河北省博物馆，成立于1953年4月，馆址设在保定古莲花池院内，1982年搬迁到省会石家庄，借用河北省展览馆办公并举办展览。1986年展览馆、博物馆合并改建为河北省博物馆，次年正式开馆。随着博物馆事业的不断发展，2006年河北省委、省政府决定改扩建河北省博物馆，同年开工建设。2013年6月8日对外试开放，2014年6月9日正式开放，同时河北博物院揭牌。河北博物院的正式开放，是河北省博物馆事业发展新的历史起点，必将开启河北文博事业的新篇章。\r\n  现馆舍建筑分为南北两区，北区建于1968年，原为\u201c毛泽东思想胜利万岁展览馆\u201d，外观仿北京人民大会堂的廊柱式建筑，总建筑面积20028平方米，建筑风格体现了鲜明的时代特点，2001年被河北省人民政府公布为河北省文物保护单位。南区为新建区，总建筑面积33100平方米，总投资6.28亿元。新建筑周围环以高大的廊柱，与北区建筑形制和主色调相呼应。河北博物院总建筑面积53128平方米，展览面积22000余平方米，建筑巍峨壮丽，和谐统一，是石家庄市标志性文化景观。\r\n  河北博物院有文物藏品15万件，其中一级品334件（套），二级品1910件（套），三级品16313件（套）。以满城汉墓出土文物、河北古代四大名窑瓷器、元青花、石刻佛教造像、明清地方名人字画以及抗日战争时期文物最具特色。另外，馆内藏书5万余册，不少是明清善本图书，为河北省地方志主要收藏单位之一。\r\n  河北博物院集合省直文博单位文物藏品优势，撷取河北历史上最为精彩的篇章，推出新馆基本陈列体系，共9个常设陈列：《石器时代的河北》、《河北商代文明》、《慷慨悲歌\u2014\u2014燕赵故事》、《战国雄风\u2014\u2014古中山国》、《大汉绝唱\u2014\u2014满城汉墓》、《抗日烽火\u2014\u2014英雄河北》、《北朝壁画》、《曲阳石雕》、《名窑名瓷》。通过5000余件（套）精美的文物和现代化的展示手段，记录了河北200万年来承先启后的人类发展史。河北博物院每年还举办多个有关当代文化艺术、社会热点透视、国内外文物交流及其它各类型的临时展览，极大丰富了人们的精神文化生活。\r\n  60年来，河北博物院在文物的收藏、研究、陈列展览、宣传教育以及国内外交流等方面做出了积极的贡献。新的河北博物院正凭借其厚重的历史文化底蕴，现代化的陈列展览，洁净、舒适的参观环境，以崭新的面貌迎接观众的到来。","museum_detail_imgs":"[http://attach.bbs.china.com.cn/forum/201512/23/091753i1rgo30w1aadkumm.jpg]"}
     * status : 1
     */

    private MuseumDetailBean museum_Detail;
    private String status;

    public MuseumDetailBean getMuseum_Detail() {
        return museum_Detail;
    }

    public void setMuseum_Detail(MuseumDetailBean museum_Detail) {
        this.museum_Detail = museum_Detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MuseumDetailBean implements Serializable{
        /**
         * id : 2
         * museum_detail_name : 河北博物院
         * museum_detail_content : 河北博物院简介
         河北博物院位于石家庄市文化广场，北起中山路，南到范西路，东临东大街，西至西大街，是河北省省级综合性博物馆、全国爱国主义教育示范基地、国家一级博物馆。
         河北博物院的前身是河北省博物馆，成立于1953年4月，馆址设在保定古莲花池院内，1982年搬迁到省会石家庄，借用河北省展览馆办公并举办展览。1986年展览馆、博物馆合并改建为河北省博物馆，次年正式开馆。随着博物馆事业的不断发展，2006年河北省委、省政府决定改扩建河北省博物馆，同年开工建设。2013年6月8日对外试开放，2014年6月9日正式开放，同时河北博物院揭牌。河北博物院的正式开放，是河北省博物馆事业发展新的历史起点，必将开启河北文博事业的新篇章。
         现馆舍建筑分为南北两区，北区建于1968年，原为“毛泽东思想胜利万岁展览馆”，外观仿北京人民大会堂的廊柱式建筑，总建筑面积20028平方米，建筑风格体现了鲜明的时代特点，2001年被河北省人民政府公布为河北省文物保护单位。南区为新建区，总建筑面积33100平方米，总投资6.28亿元。新建筑周围环以高大的廊柱，与北区建筑形制和主色调相呼应。河北博物院总建筑面积53128平方米，展览面积22000余平方米，建筑巍峨壮丽，和谐统一，是石家庄市标志性文化景观。
         河北博物院有文物藏品15万件，其中一级品334件（套），二级品1910件（套），三级品16313件（套）。以满城汉墓出土文物、河北古代四大名窑瓷器、元青花、石刻佛教造像、明清地方名人字画以及抗日战争时期文物最具特色。另外，馆内藏书5万余册，不少是明清善本图书，为河北省地方志主要收藏单位之一。
         河北博物院集合省直文博单位文物藏品优势，撷取河北历史上最为精彩的篇章，推出新馆基本陈列体系，共9个常设陈列：《石器时代的河北》、《河北商代文明》、《慷慨悲歌——燕赵故事》、《战国雄风——古中山国》、《大汉绝唱——满城汉墓》、《抗日烽火——英雄河北》、《北朝壁画》、《曲阳石雕》、《名窑名瓷》。通过5000余件（套）精美的文物和现代化的展示手段，记录了河北200万年来承先启后的人类发展史。河北博物院每年还举办多个有关当代文化艺术、社会热点透视、国内外文物交流及其它各类型的临时展览，极大丰富了人们的精神文化生活。
         60年来，河北博物院在文物的收藏、研究、陈列展览、宣传教育以及国内外交流等方面做出了积极的贡献。新的河北博物院正凭借其厚重的历史文化底蕴，现代化的陈列展览，洁净、舒适的参观环境，以崭新的面貌迎接观众的到来。
         * museum_detail_imgs : [http://attach.bbs.china.com.cn/forum/201512/23/091753i1rgo30w1aadkumm.jpg]
         */

        private int id;
        private String museum_detail_name;
        private String museum_detail_content;
        private String museum_detail_imgs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMuseum_detail_name() {
            return museum_detail_name;
        }

        public void setMuseum_detail_name(String museum_detail_name) {
            this.museum_detail_name = museum_detail_name;
        }

        public String getMuseum_detail_content() {
            return museum_detail_content;
        }

        public void setMuseum_detail_content(String museum_detail_content) {
            this.museum_detail_content = museum_detail_content;
        }

        public String getMuseum_detail_imgs() {
            return museum_detail_imgs;
        }

        public void setMuseum_detail_imgs(String museum_detail_imgs) {
            this.museum_detail_imgs = museum_detail_imgs;
        }

        @Override
        public String toString() {
            return "MuseumDetailBean{" +
                    "id=" + id +
                    ", museum_detail_name='" + museum_detail_name + '\'' +
                    ", museum_detail_content='" + museum_detail_content + '\'' +
                    ", museum_detail_imgs='" + museum_detail_imgs + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MuseumDetail{" +
                "museum_Detail=" + museum_Detail +
                ", status='" + status + '\'' +
                '}';
    }
}
