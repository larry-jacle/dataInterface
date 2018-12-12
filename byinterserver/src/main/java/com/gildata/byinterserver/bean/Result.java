package com.gildata.byinterserver.bean;

/**
 * 前端返回对象
 * @Auther: shizh26250
 * @Date: 2018/11/27 13:10
 * @Description:
 */
public class Result<T> {

    // 状态
    private int status;

    // 成功
    public static final int STATUS_SUCCESSED = 1;
    // 失败
    public static final int STATUS_FIALED = 0;

    // 消息
    private String msg;

    // 数据
    private T data;

    public Result(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
