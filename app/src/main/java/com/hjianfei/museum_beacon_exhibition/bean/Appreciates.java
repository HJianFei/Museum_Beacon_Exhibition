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
     * appreciates : [{"id":471,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_13474328427AqK.jpg","content":"广彩方罐形双耳花插","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3709&subgid=8911&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":472,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_13474328438qrT.jpg","content":"潮州窑青白釉佛像","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3712&subgid=8912&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":473,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_1347432843Yvn9.jpg","content":"釉里红菊花纹花口大盘","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3715&subgid=8913&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":474,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_1347432844uA9F.jpg","content":"磁州窑唐僧取经图枕","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3718&subgid=8914&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":475,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_1347432844zNe2.jpg","content":"钧窑紫斑盘","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3721&subgid=8915&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":476,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_134743284533pj.jpg","content":"白釉双龙耳尊","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3724&subgid=8916&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":477,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_1347432845i913.jpg","content":"三彩马","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3727&subgid=8917&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":478,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_1347432845S15g.jpg","content":"青花缠枝莲纹板沿大盘","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3730&subgid=8918&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":479,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_13474328465y6s.jpg","content":"祭红盘","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3731&subgid=8919&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0},{"id":480,"img_url":"http://www.gdmuseum.com/attachment/201209/12/2_13474328466R5n.jpg","content":"白釉暗花大盘","detail_url":"http://www.gdmuseum.com/curio_detail.php?picid=3733&subgid=8920&gid=8&title=�մ�","type":"珍品鉴赏","view_count":0}]
     * status : 1
     */

    private String status;
    private List<AppreciatesBean> appreciates;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AppreciatesBean> getAppreciates() {
        return appreciates;
    }

    public void setAppreciates(List<AppreciatesBean> appreciates) {
        this.appreciates = appreciates;
    }

    public static class AppreciatesBean implements Serializable {
        /**
         * id : 471
         * img_url : http://www.gdmuseum.com/attachment/201209/12/2_13474328427AqK.jpg
         * content : 广彩方罐形双耳花插
         * detail_url : http://www.gdmuseum.com/curio_detail.php?picid=3709&subgid=8911&gid=8&title=�մ�
         * type : 珍品鉴赏
         * view_count : 0
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
