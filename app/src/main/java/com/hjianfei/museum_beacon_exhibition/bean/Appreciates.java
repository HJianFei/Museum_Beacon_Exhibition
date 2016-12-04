package com.hjianfei.museum_beacon_exhibition.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创建时间： 2016/9/17.
 * 作者：HJianFei
 * 功能描述：
 */

public class Appreciates implements Serializable {

    /**
     * appreciates : [{"id":249,"img_url":"http://www.gdmuseum.com/attachment/201607/12/2_1468311146ohU4.jpg","content":"明漳州窑青花缠枝花卉纹大罐","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=11304&subgid=15&gid=160&title=�໨��֮Լ","type":"青花瓷之约","view_count":109},{"id":1247,"img_url":"http://www.gdmuseum.com/attachment/201012/7/2_1291690560WvTg.jpg","content":"女贞","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=2100&subgid=110&gid=66&title=�вݱ걾","type":"自然标本","view_count":93},{"id":2074,"img_url":"http://www.gdmuseum.com/attachment/201401/22/2_139035040817pQ.jpg","content":"金饼","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=6594&subgid=15&gid=148&title=����ӡ��","type":"专题鉴赏","view_count":40},{"id":568,"img_url":"http://www.gdmuseum.com/attachment/201209/24/2_13484505522Y24.jpg","content":"孙克弘 秋艳图轴","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=4191&subgid=4968&gid=1&title=�黭","type":"珍品鉴赏","view_count":39},{"id":738,"img_url":"http://www.gdmuseum.com/attachment/201602/19/2_1455862128HdHe.jpg","content":"木雕龙纹雀替","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=9473&subgid=90&gid=18&title=��ľ��","type":"珍品鉴赏","view_count":37},{"id":2067,"img_url":"http://www.gdmuseum.com/attachment/201401/22/2_1390350407E2hx.jpg","content":"陶五联罐","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=6590&subgid=8&gid=148&title=����ӡ��","type":"专题鉴赏","view_count":32}]
     * code : 200
     */

    private int code;
    private List<AppreciatesBean> appreciates;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AppreciatesBean> getAppreciates() {
        return appreciates;
    }

    public void setAppreciates(List<AppreciatesBean> appreciates) {
        this.appreciates = appreciates;
    }

    public static class AppreciatesBean {
        /**
         * id : 249
         * img_url : http://www.gdmuseum.com/attachment/201607/12/2_1468311146ohU4.jpg
         * content : 明漳州窑青花缠枝花卉纹大罐
         * detail_url : http://www.gdmuseum.com/curio_detail.php?picid=11304&subgid=15&gid=160&title=�໨��֮Լ
         * type : 青花瓷之约
         * view_count : 109
         */

        private int id;
        private String img_url;
        private String content;
        private String detail_url;
        private String type;
        private int view_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }
    }
}
