package com.example.castle.csite.bean;

import java.util.List;

/**
 * Created by castle on 16-9-25.
 * 推荐页面banner
 */
public class RecommendBanner {

    /**
     * code : 0
     * data : [{"title":"国漫振兴 青春助力","value":"http://www.bilibili.com/topic/v2/phone1543.html","image":"http://i0.hdslb.com/bfs/archive/fa881e70ab6faada14a6ada5a088e62a97c11660.jpg","type":2,"weight":1,"remark":"","hash":"efaf2eb4d8ab3fa70a908dc951a1b31d"},{"title":"阴阳师","value":"http://acg.tv/u1Ci","image":"http://i0.hdslb.com/bfs/archive/3f43fd6677cef409df0d27b6601c0555f54ed69b.jpg","type":2,"weight":2,"remark":"","hash":"2b86f7a812cb08da69058caa42701d2e"},{"title":"频道精选 生活区 No.31","value":"http://www.bilibili.com/topic/v2/phone1546.html","image":"http://i0.hdslb.com/bfs/archive/2f123c0b23f267c7e32d715a9e69ab4b88e2a0ee.jpg","type":2,"weight":2,"remark":"","hash":"a5c83b8cf8a2eb44c2fc528059f552b1"}]
     */

    private int code;
    /**
     * title : 国漫振兴 青春助力
     * value : http://www.bilibili.com/topic/v2/phone1543.html
     * image : http://i0.hdslb.com/bfs/archive/fa881e70ab6faada14a6ada5a088e62a97c11660.jpg
     * type : 2
     * weight : 1
     * remark :
     * hash : efaf2eb4d8ab3fa70a908dc951a1b31d
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String value;
        private String image;
        private int type;
        private int weight;
        private String remark;
        private String hash;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
