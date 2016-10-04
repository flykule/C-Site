package com.example.castle.csite.bean;

import java.util.List;

/**
 * Created by castle on 16-10-3.
 */

public class RecommendRegion {

    /**
     * code : 0
     * result : [{"title":"【瞎看什么】戒色吧！逛小黄网会死","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/95cbd6b190b96f98049cbfb97c08aab9986e25b2.jpg_320x200.jpg","param":"6429040","goto":"av","width":580,"height":364,"play":"62.5万","danmaku":"5562"},{"title":"主播炸了121：昔日第一上单今日被秀成猪皮怪","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/db5613977bdce4b6d73368548bff6d7a955024ea.jpg_320x200.jpg","param":"6429326","goto":"av","width":580,"height":364,"play":"41.3万","danmaku":"1944"},{"title":"【史图馆】中国历代疆域变化 第九版","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/10c71f8770986ed006c636ca9bc5d61cc7fe4172.jpg_320x200.jpg","param":"6487711","goto":"av","width":580,"height":364,"play":"10.5万","danmaku":"3.3万"},{"title":"《你的名字》百亿票房背后：改变而非进化","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/9e3a3d8fdfc8e02722cfba0e8666f71c68c3a9b6.jpg_320x200.jpg","param":"6471530","goto":"av","width":580,"height":364,"play":"5.9万","danmaku":"531"}]
     */

    private int code;
    /**
     * title : 【瞎看什么】戒色吧！逛小黄网会死
     * style : gm_av
     * cover : http://i0.hdslb.com/bfs/archive/95cbd6b190b96f98049cbfb97c08aab9986e25b2.jpg_320x200.jpg
     * param : 6429040
     * goto : av
     * width : 580
     * height : 364
     * play : 62.5万
     * danmaku : 5562
     */

    private List<RecommendContent.ResultBean.BodyBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<RecommendContent.ResultBean.BodyBean> getResult() {
        return result;
    }

    public void setResult(List<RecommendContent.ResultBean.BodyBean> result) {
        this.result = result;
    }


}
