package com.gildata.byinterserver.dto;

/**
 * 实时监控DTO
 * @Auther: shizh26250
 * @Date: 2018/11/27 17:13
 * @Description:
 */
public class ImportDetailDto {

    // 时间
    private String time;

    // 总条数
    private int total;

    // 成功条数
    private int successCnt;

    // 新闻条数
    private int newsCnt;

    // 微博条数
    private int weiboCnt;

    // 微信条数
    private int weixinCnt;

    // 知乎条数
    private int zhihuCnt;

    public String getTime() {
        return time;
    }

    public int getTotal() {
        return total;
    }

    public int getSuccessCnt() {
        return successCnt;
    }

    public int getNewsCnt() {
        return newsCnt;
    }

    public int getWeiboCnt() {
        return weiboCnt;
    }

    public int getWeixinCnt() {
        return weixinCnt;
    }

    public int getZhihuCnt() {
        return zhihuCnt;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSuccessCnt(int successCnt) {
        this.successCnt = successCnt;
    }

    public void setNewsCnt(int newsCnt) {
        this.newsCnt = newsCnt;
    }

    public void setWeiboCnt(int weiboCnt) {
        this.weiboCnt = weiboCnt;
    }

    public void setWeixinCnt(int weixinCnt) {
        this.weixinCnt = weixinCnt;
    }

    public void setZhihuCnt(int zhihuCnt) {
        this.zhihuCnt = zhihuCnt;
    }
}
