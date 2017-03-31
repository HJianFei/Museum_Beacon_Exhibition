package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by HJianFei on 2016/11/23.
 */

public class StepView implements Serializable {


    /**
     * code : 200
     * stepView : {"beacon_id":"1","step_name":"青铜馆","img_url":"http://www.chnmuseum.cn/Portals/0/web/exhibition/exhibitions/110325bronze/110325poster(0).jpg","content":"青铜，在中国古代主要是指铜与锡的合金，是人类技术发展史上的重要发明。远在5000多年前的马家窑文化时期，中国古人即开始使用青铜制品。夏、商、西周、春秋、战国是中国的青铜时代，青铜铸造达到鼎盛，辉煌灿烂，促进了当时生产力的进步。同时，作为礼乐文化的主要载体，青铜器用以\u201c明尊卑，别上下\u201d ，彰显、维护等级制度。  \r\n在青铜时代漫长历史时期的不同发展阶段，社会政治制度的变革，人们思想、习俗的转变与审美艺术自身的发展，使这一时期青铜器的形制、装饰花纹、器物组合与铭文也相应呈现不同的时代风貌，而这些阶段性的变化恰为我们清晰勾勒出中国古代青铜艺术之美的历程。  \r\n中国国家博物馆素以收藏中国古代青铜重器为世人称道。殷墟妇好墓青铜器、\u201c后母戊\u201d青铜鼎、\u201c子龙\u201d青铜鼎、\u201c盂\u201d青铜鼎、\u201c天亡\u201d青铜簋、春秋晚期蔡侯墓青铜器及战国辉县固围村青铜器，足以代表中国古代青铜艺术历程各时期的重要成就。从中我们可以体味造型艺术、装饰艺术发展的内在规律与书法艺术演变的轨迹，并继而探寻创造这些艺术元素的源泉，即我们民族上承远古、绵延不绝的审美体系。 "}
     */

    private int code;
    private StepViewBean stepView;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StepViewBean getStepView() {
        return stepView;
    }

    public void setStepView(StepViewBean stepView) {
        this.stepView = stepView;
    }

    public static class StepViewBean implements Serializable{
        /**
         * beacon_id : 1
         * step_name : 青铜馆
         * img_url : http://www.chnmuseum.cn/Portals/0/web/exhibition/exhibitions/110325bronze/110325poster(0).jpg
         * content : 青铜，在中国古代主要是指铜与锡的合金，是人类技术发展史上的重要发明。远在5000多年前的马家窑文化时期，中国古人即开始使用青铜制品。夏、商、西周、春秋、战国是中国的青铜时代，青铜铸造达到鼎盛，辉煌灿烂，促进了当时生产力的进步。同时，作为礼乐文化的主要载体，青铜器用以“明尊卑，别上下” ，彰显、维护等级制度。
         在青铜时代漫长历史时期的不同发展阶段，社会政治制度的变革，人们思想、习俗的转变与审美艺术自身的发展，使这一时期青铜器的形制、装饰花纹、器物组合与铭文也相应呈现不同的时代风貌，而这些阶段性的变化恰为我们清晰勾勒出中国古代青铜艺术之美的历程。
         中国国家博物馆素以收藏中国古代青铜重器为世人称道。殷墟妇好墓青铜器、“后母戊”青铜鼎、“子龙”青铜鼎、“盂”青铜鼎、“天亡”青铜簋、春秋晚期蔡侯墓青铜器及战国辉县固围村青铜器，足以代表中国古代青铜艺术历程各时期的重要成就。从中我们可以体味造型艺术、装饰艺术发展的内在规律与书法艺术演变的轨迹，并继而探寻创造这些艺术元素的源泉，即我们民族上承远古、绵延不绝的审美体系。
         */

        private String beacon_id;
        private String step_name;
        private String img_url;
        private String content;

        public String getBeacon_id() {
            return beacon_id;
        }

        public void setBeacon_id(String beacon_id) {
            this.beacon_id = beacon_id;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "StepViewBean{" +
                    "beacon_id='" + beacon_id + '\'' +
                    ", step_name='" + step_name + '\'' +
                    ", img_url='" + img_url + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "StepView{" +
                "code=" + code +
                ", stepView=" + stepView +
                '}';
    }
}
