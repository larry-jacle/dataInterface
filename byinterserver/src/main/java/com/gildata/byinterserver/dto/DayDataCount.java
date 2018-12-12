package com.gildata.byinterserver.dto;

/**
 * 每天数据量
 * @Auther: shizh26250
 * @Date: 2018/11/27 19:28
 * @Description:
 */
public class DayDataCount {

    // 日期
    private String date;

    // 域名
    private String host;

    // 域名量
    private int hostCnt;

    // 网页量
    private int urlCnt;

    // 数据量
    private int dataCnt;

    public String getDate() {
        return date;
    }

    public int getHostCnt() {
        return hostCnt;
    }

    public String getHost() {
        return host;
    }

    public int getUrlCnt() {
        return urlCnt;
    }

    public int getDataCnt() {
        return dataCnt;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setHostCnt(int hostCnt) {
        this.hostCnt = hostCnt;
    }

    public void setUrlCnt(int urlCnt) {
        this.urlCnt = urlCnt;
    }

    public void setDataCnt(int dataCnt) {
        this.dataCnt = dataCnt;
    }
}
