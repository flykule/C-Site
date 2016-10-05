package com.example.castle.csite.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by castle on 16-10-1.
 * 推荐页面内容
 */
public class RecommendContent {


    private int code;


    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String type;
        /**
         * param :
         * goto :
         * style : gm_av
         * title : 热门焦点
         */

        private HeadBean head;
        /**
         * title : 【大秦嘴炮帝国（一）】强秦序曲·商鞅变法（上）
         * style : gm_av
         * cover : http://i0.hdslb.com/bfs/archive/303c5c5ec1c0f6a0d2e2fdd44067478a5d88dce1.jpg_320x200.jpg
         * param : 6477644
         * goto : av
         * width : 580
         * height : 364
         * play : 6841
         * danmaku : 237
         */

        private List<BodyBean> body;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public HeadBean getHead() {
            return head;
        }

        public void setHead(HeadBean head) {
            this.head = head;
        }

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public static class HeadBean {
            private String param;
            @SerializedName("goto")
            private String gotoX;
            private String style;
            private String title;
            private String count;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class BodyBean {
            private String title;
            private String style;
            private String cover;
            private String param;
            @SerializedName("goto")
            private String gotoX;
            private int width;
            private int height;
            private String play;
            private String danmaku;
            private String up;
            private String name;
            private String online;
            private String area;
            private String desc1;

            public String getDesc1() {
                return desc1;
            }

            public void setDesc1(String desc1) {
                this.desc1 = desc1;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getUp() {
                return up;
            }

            public void setUp(String up) {
                this.up = up;
            }
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOnline() {
                return online;
            }

            public void setOnline(String online) {
                this.online = online;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public String getGotoX() {
                return gotoX;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPlay() {
                return play;
            }

            public void setPlay(String play) {
                this.play = play;
            }

            public String getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(String danmaku) {
                this.danmaku = danmaku;
            }
        }
    }
}
