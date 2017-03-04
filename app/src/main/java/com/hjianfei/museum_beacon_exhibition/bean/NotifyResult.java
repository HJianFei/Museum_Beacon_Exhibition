package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class NotifyResult implements Serializable {

    /**
     * code : 200
     * notify : {"notify_id":"1","notify_title":"清初山水画的时代风格和地域特征","notify_img_url":"http://www.gdmuseum.com/memo_link/attachment/201702/17/2_14873134736v6T.jpg","notify_content":"时间：2月19日10:00\r\n 地点：粤博一楼学术报告厅\r\n 主讲人：林锐（广东省立中山图书馆副研究馆员、广东省古籍保护中心秘书长）\r\n 参与方式：无需预约，现场参与即可。\r\n 讲座概要：\r\n 清代初期，整个社会处于极度的动荡混乱之中。朝代的更替、权力的转移、民族矛盾的激化，不但影响了士人阶层的仕宦观念，也影响到他们的艺术倾向，这使得清初山水画坛流派林立，出现了诸如娄东派、虞山派、新安派、金陵派、江西派、黄山派、姑熟派等画派。而各画派又都以各自的师承和地域的不同，在风格、技法上呈现出不同的趣味。从时代和地域两个面向来看清初的山水画，可以深入分别画派流传和绘画技法的分殊异同，有助于我们更加切近地了解和欣赏中国绘画艺术。\r\n 主讲人简介：\r\n 林锐，1990年毕业于中山大学考古专业，先后任职于广东省文物管理委员会办公室、广东省文物鉴定站，从事文物保护与文物鉴定工作，主要研究方向为中国书画鉴定与研究、地方文献的整理与研究。现为广东省立中山图书馆副研究馆员、广东省古籍保护中心秘书长。合编有《张之洞致张佩纶未刊书札》《中国文化遗产研究院藏清代名人书札》《张荫桓诗文珍本集刊》等。","type":"【讲座预告】"}
     */

    private int code;
    private NotifyBean notify;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public NotifyBean getNotify() {
        return notify;
    }

    public void setNotify(NotifyBean notify) {
        this.notify = notify;
    }

    public static class NotifyBean implements Serializable{
        /**
         * notify_id : 1
         * notify_title : 清初山水画的时代风格和地域特征
         * notify_img_url : http://www.gdmuseum.com/memo_link/attachment/201702/17/2_14873134736v6T.jpg
         * notify_content : 时间：2月19日10:00
         地点：粤博一楼学术报告厅
         主讲人：林锐（广东省立中山图书馆副研究馆员、广东省古籍保护中心秘书长）
         参与方式：无需预约，现场参与即可。
         讲座概要：
         清代初期，整个社会处于极度的动荡混乱之中。朝代的更替、权力的转移、民族矛盾的激化，不但影响了士人阶层的仕宦观念，也影响到他们的艺术倾向，这使得清初山水画坛流派林立，出现了诸如娄东派、虞山派、新安派、金陵派、江西派、黄山派、姑熟派等画派。而各画派又都以各自的师承和地域的不同，在风格、技法上呈现出不同的趣味。从时代和地域两个面向来看清初的山水画，可以深入分别画派流传和绘画技法的分殊异同，有助于我们更加切近地了解和欣赏中国绘画艺术。
         主讲人简介：
         林锐，1990年毕业于中山大学考古专业，先后任职于广东省文物管理委员会办公室、广东省文物鉴定站，从事文物保护与文物鉴定工作，主要研究方向为中国书画鉴定与研究、地方文献的整理与研究。现为广东省立中山图书馆副研究馆员、广东省古籍保护中心秘书长。合编有《张之洞致张佩纶未刊书札》《中国文化遗产研究院藏清代名人书札》《张荫桓诗文珍本集刊》等。
         * type : 【讲座预告】
         */

        private String notify_id;
        private String notify_title;
        private String notify_img_url;
        private String notify_content;
        private String type;

        public String getNotify_id() {
            return notify_id;
        }

        public void setNotify_id(String notify_id) {
            this.notify_id = notify_id;
        }

        public String getNotify_title() {
            return notify_title;
        }

        public void setNotify_title(String notify_title) {
            this.notify_title = notify_title;
        }

        public String getNotify_img_url() {
            return notify_img_url;
        }

        public void setNotify_img_url(String notify_img_url) {
            this.notify_img_url = notify_img_url;
        }

        public String getNotify_content() {
            return notify_content;
        }

        public void setNotify_content(String notify_content) {
            this.notify_content = notify_content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "NotifyBean{" +
                    "notify_id='" + notify_id + '\'' +
                    ", notify_title='" + notify_title + '\'' +
                    ", notify_img_url='" + notify_img_url + '\'' +
                    ", notify_content='" + notify_content + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NotifyResult{" +
                "code=" + code +
                ", notify=" + notify +
                '}';
    }
}
