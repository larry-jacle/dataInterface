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
@Table(name = "usrBYZXSLTJ")
public class UsrBYZXSLTJ {

    @Id
    @GeneratedValue
    private Long ID;

    private Date timestamp;
    private Integer count;
    private Integer type;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UsrBYZXSLTJ{" +
                "ID=" + ID +
                ", timestamp=" + timestamp +
                ", count=" + count +
                ", type=" + type +
                '}';
    }
}
