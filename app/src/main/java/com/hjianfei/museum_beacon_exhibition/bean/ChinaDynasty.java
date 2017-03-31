package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HJianFei on 2016/12/7.
 */

public class ChinaDynasty implements Serializable {


    /**
     * code : 200
     * chinaDynasties : [{"id":3,"dynasty":"上古","dynasty_detail":"http://www.todayonhistory.com/lishi/shanggu/","img_url":"pictures/china_dynasty_shanggu.png","description":"上古时期,指文字记载出现以前的历史时代,因为上古时代没有当时直接的文字记载，那个时候发生的事件或人物往往带有神话色彩。历史上的今天将详细讲述上古历史。"},{"id":4,"dynasty":"夏朝","dynasty_detail":"http://www.todayonhistory.com/lishi/xiachao/","img_url":"pictures/china_dynasty_xiachao.png","description":"夏朝是我国第一个奴隶制世袭朝代，禹传位给儿子启，由此开启\u201c家天下\u201d的世袭制先河。夏朝历史延续约471年，传17位帝王。由于历史久远，夏朝的存在性始终无法被证实。历史上的今天将详细叙述夏朝历史。"},{"id":5,"dynasty":"商朝","dynasty_detail":"http://www.todayonhistory.com/lishi/shangchao/","img_url":"pictures/china_dynasty_zhouchao.png","description":"商朝是青铜时代及奴隶制鼎盛时期，拥有600年左右的历史、31位帝王。商朝又称为殷商，是我国第一个有文字记载的朝代。历史上的今天将全面讲述商朝历史。"},{"id":6,"dynasty":"周朝","dynasty_detail":"http://www.todayonhistory.com/lishi/zhouchao/","img_url":"pictures/china_dynasty_zhouchao.png","description":"周朝历经867年历史，传37位帝王，是\u201c华夏\u201d一词的创造者与最初指代。周朝是最后一个奴隶制朝代，分为西周与东周两个时期。历史上的今天将为您详细叙述周朝历史。"},{"id":7,"dynasty":"春秋战国","dynasty_detail":"http://www.todayonhistory.com/lishi/chunqiuzhanguo/","img_url":"pictures/china_dynasty_chunqiuzhanguo.png","description":"春秋战国时期，社会动荡、战争不断、各诸侯国政权更迭频繁，同时也为秦朝一统天下奠定基础。春秋五霸与战国七雄等故事耳熟能详。历史上的今天将全面完善的讲述春秋战国历史。"},{"id":8,"dynasty":"秦朝","dynasty_detail":"http://www.todayonhistory.com/lishi/qinchao/","img_url":"pictures/china_dynasty_qinchao.png","description":"秦始皇一统六国，建立了中国历史上第一个多民族融合、中央集权的封建制国家\u2014\u2014秦朝。虽然秦朝国祚仅十五年，但其影响与历史意义是不容忽视的。历史上的今天将详细叙述秦朝历史。"},{"id":9,"dynasty":"汉朝","dynasty_detail":"http://www.todayonhistory.com/lishi/hanchao/","img_url":"pictures/china_dynasty_hanchao.png","description":"汉朝历史分为西汉与东汉两个时期，历经405年风霜，是当时的世界强国之一。汉朝在文化、科技等领域都做出了巨大贡献，汉族、儒家文化东亚文化圈也在该时期产生。历史上的今天将完整阐述汉朝历史。"},{"id":10,"dynasty":"三国","dynasty_detail":"http://www.todayonhistory.com/lishi/sanguo/","img_url":"pictures/china_dynasty_sanguo.png","description":"东汉末年分三国，三国分为曹魏、蜀汉、孙吴三个政权，拥有近60年的历史。三国历史因小说《三国演义》而驰名中外，但小说与历史不尽相同。历史上的今天将更专业全面的讲述三国历史。"},{"id":11,"dynasty":"晋朝","dynasty_detail":"http://www.todayonhistory.com/lishi/jinchao/","img_url":"pictures/china_dynasty_jinchao.png","description":"晋朝分为西晋与东晋，是上承三国下启南北朝的历史时期。虽然晋朝仅有154年历史，但其在哲学、文学、艺术、科技等方面都有重要发展。历史上的今天将详细叙述晋朝历史。"},{"id":12,"dynasty":"南北朝","dynasty_detail":"http://www.todayonhistory.com/lishi/nanbeichao/","img_url":"pictures/china_dynasty_nanbeichao.png","description":"南朝和北朝统称为南北朝，一百多年的历史中，社会动荡、朝代更迭不已。南北朝时期，虽国家处于分裂状态，但民族融合的脚步却未停止。历史上的今天将详细叙述南北朝历史。"}]
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

    public static class ChinaDynastiesBean {
        /**
         * id : 3
         * dynasty : 上古
         * dynasty_detail : http://www.todayonhistory.com/lishi/shanggu/
         * img_url : pictures/china_dynasty_shanggu.png
         * description : 上古时期,指文字记载出现以前的历史时代,因为上古时代没有当时直接的文字记载，那个时候发生的事件或人物往往带有神话色彩。历史上的今天将详细讲述上古历史。
         */

        private int id;
        private String dynasty;
        private String dynasty_detail;
        private String img_url;
        private String description;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
