package com.gildata.byinterserver.domain;

import javax.persistence.*;

/**
 * Created by chenchen on 2018/11/27.
 */
@Entity
@Table(name = "logrecord")
public class Logrecord {

    @Id
    @GeneratedValue
    private Integer ID;

    private Integer type; // 0:fail, 1:success

    private Integer indexloc;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndexloc() {
        return indexloc;
    }

    public void setIndexloc(Integer indexloc) {
        this.indexloc = indexloc;
    }
}
