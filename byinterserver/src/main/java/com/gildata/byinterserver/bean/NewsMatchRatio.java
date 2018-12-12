package com.gildata.byinterserver.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chenchen on 2018/11/16.
 */
public class NewsMatchRatio implements Cloneable{
    private String success = "0";
    private String fail = "0";
    private String total = "0";
    private String successRatio = "0.00";

    @JSONField(serialize = false)
    private int successOffSet = 0;

    @JSONField(serialize = false)
    private int failOffSet = 0;

    public NewsMatchRatio() {
    }

    public NewsMatchRatio(String success, String fail, String total) {
        this.success = success;
        this.fail = fail;
        this.total = total;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSuccessRatio() {
        return successRatio;
    }

    public void setSuccessRatio(String successRatio) {
        this.successRatio = successRatio;
    }

    public int getSuccessOffSet() {
        return successOffSet;
    }

    public void setSuccessOffSet(int successOffSet) {
        this.successOffSet = successOffSet;
    }

    public int getFailOffSet() {
        return failOffSet;
    }

    public void setFailOffSet(int failOffSet) {
        this.failOffSet = failOffSet;
    }

    @Override
    public NewsMatchRatio clone() {
        try {
            return (NewsMatchRatio) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
