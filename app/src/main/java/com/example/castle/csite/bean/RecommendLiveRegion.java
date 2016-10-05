package com.example.castle.csite.bean;

import java.util.List;

/**
 * Created by castle on 16-10-5.
 */

public class RecommendLiveRegion {

    /**
     * code : 0
     * data : [{"title":"B站第一上单奶妈三分钟拿一血","cover":"http://i0.hdslb.com/bfs/live/c64a1d98796885ad27bcaa36a0ea8f43bedf2de0.jpg","param":"395443","name":"星妈克","face":"http://i0.hdslb.com/bfs/face/2159751e32fc196afa47f564a00c41cf4806da7d.jpg","online":3290,"area":"电子竞技","area_id":4,"finish":0},{"title":"优酱洗澡陪你们看《七日重生》致敬林正英","cover":"http://i0.hdslb.com/bfs/live/9da7be68993c8c174566bb6e695334eb638e9dd7.jpg","param":"330355","name":"优酱是咸鱼","face":"http://i0.hdslb.com/bfs/face/ccc601508ae54a3497500adcef7b3ca52309d42f.jpg","online":15675,"area":"生活娱乐","area_id":6,"finish":0},{"title":"广州CICF Expo 2016 生放送","cover":"http://i0.hdslb.com/bfs/live/f5edc5e1930d34fc4707b91e4e0323a118e9dc07.jpg","param":"20006","name":"哔哩哔哩直播基","face":"http://i0.hdslb.com/bfs/face/e7601ac93f61fd953d38bf38a158d0e2f9e7434b.jpg","online":13141,"area":"御宅文化","area_id":2,"finish":0},{"title":"【枫叔】黎明傻鸡","cover":"http://i0.hdslb.com/bfs/live/a889f6d7c9dbe00207d43532905f3ffaa0bc2586.jpg","param":"101526","name":"狼枫子","face":"http://i0.hdslb.com/bfs/face/10fb15e2efe7ae272779db76a72d9e2bec663637.jpg","online":3525,"area":"单机联机","area_id":1,"finish":0}]
     */

    private int code;
    /**
     * title : B站第一上单奶妈三分钟拿一血
     * cover : http://i0.hdslb.com/bfs/live/c64a1d98796885ad27bcaa36a0ea8f43bedf2de0.jpg
     * param : 395443
     * name : 星妈克
     * face : http://i0.hdslb.com/bfs/face/2159751e32fc196afa47f564a00c41cf4806da7d.jpg
     * online : 3290
     * area : 电子竞技
     * area_id : 4
     * finish : 0
     */

    private List<RecommendContent.ResultBean.BodyBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RecommendContent.ResultBean.BodyBean> getData() {
        return data;
    }

    public void setData(List<RecommendContent.ResultBean.BodyBean> data) {
        this.data = data;
    }


}
