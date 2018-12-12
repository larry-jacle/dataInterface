package com.gildata.byinterserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by chenchen on 2018/11/27.
 */
@Entity
@Table(name = "usrBYZXXQ")
public class UsrBYZXXQ {

    @Id
    @GeneratedValue
    private Long ID;

    private Date timestamp;
    private Date inserttime;
    private Integer msgtype;
    private String host;
    private String msgsourceurl;
    private String weburl;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Integer getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Integer msgtype) {
        this.msgtype = msgtype;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMsgsourceurl() {
        return msgsourceurl;
    }

    public void setMsgsourceurl(String msgsourceurl) {
        this.msgsourceurl = msgsourceurl;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    @Override
    public String toString() {
        return "UsrBYZXXQ{" +
                "ID=" + ID +
                ", timestamp=" + timestamp +
                ", msgtype=" + msgtype +
                ", msgsourceurl='" + msgsourceurl + '\'' +
                ", weburl='" + weburl + '\'' +
                '}';
    }
}
