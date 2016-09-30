package com.example.castle.csite.bean;

import java.util.List;

/**
 * Created by castle on 16-9-25.
 * 推荐页面banner
 */
public class RecommendBanner {
    private int code;
    private String message;
    private List<Banner> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Banner> getResult() {
        return result;
    }

    public void setResult(List<Banner> result) {
        this.result = result;
    }

    public static class Banner {
        /**
         * cover : http://i0.hdslb.com/bfs/bangumi/c36b9023905f661c7632eb66be28df7a74c6bd60.jpg
         * cursor : 1474768920987
         * desc : 一位是不可一世的英雄王，一位是摒弃理想的守护者。他们都是Archer，真伪无关乎胜败。本期Fate/IT已张弦引箭。
         * id : 1775
         * is_new : 1
         * link : http://www.bilibili.com/html/activity-20160923fgo.html
         * title : Fate/Infinite Tales EP.3 伪物真传
         */

        private String cover;
        private long cursor;
        private String desc;
        private int id;
        private int is_new;
        private String link;
        private String title;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public long getCursor() {
            return cursor;
        }

        public void setCursor(long cursor) {
            this.cursor = cursor;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
