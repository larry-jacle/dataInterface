package com.gildata.byinterserver.bean;

/**
 * Created by chenchen on 2018/11/16.
 *
 * 博约数据消息包实体
 */
public class NewsItem {
    private String newsDate;
    private Integer successNewsNum;
    private Integer totalNewsNum;
    private Integer xinwen;
    private Integer weibo;
    private Integer weixin;
    private Integer zhihu;

    public NewsItem() {
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public Integer getSuccessNewsNum() {
        return successNewsNum;
    }

    public void setSuccessNewsNum(Integer successNewsNum) {
        this.successNewsNum = successNewsNum;
    }

    public Integer getTotalNewsNum() {
        return totalNewsNum;
    }

    public void setTotalNewsNum(Integer totalNewsNum) {
        this.totalNewsNum = totalNewsNum;
    }

    public Integer getXinwen() {
        return xinwen;
    }

    public void setXinwen(Integer xinwen) {
        this.xinwen = xinwen;
    }

    public Integer getWeibo() {
        return weibo;
    }

    public void setWeibo(Integer weibo) {
        this.weibo = weibo;
    }

    public Integer getWeixin() {
        return weixin;
    }

    public void setWeixin(Integer weixin) {
        this.weixin = weixin;
    }

    public Integer getZhihu() {
        return zhihu;
    }

    public void setZhihu(Integer zhihu) {
        this.zhihu = zhihu;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "newsDate='" + newsDate + '\'' +
                ", successNewsNum=" + successNewsNum +
                ", totalNewsNum=" + totalNewsNum +
                ", xinwen=" + xinwen +
                ", weibo=" + weibo +
                ", weixin=" + weixin +
                ", zhihu=" + zhihu +
                '}';
    }
}
