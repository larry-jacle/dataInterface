package com.gildata.byinterserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LiChao on 2018/11/20
 */

@Entity
@Table(name = "usrBYZQSJYB")
public class UsrBYZQSJYB {


    @Id
    @GeneratedValue
    private Integer ID;
    private Integer XWLYCL;  //对应XWLY、MTCCCL
    private String XWLY;    //对应MTCC
    private String SJYBM;   //对应SJYBM
    private Integer LMLB;   //对应LMFL
    private String ZYMC;    //对应MTLM
    private String ZYLJ;    //对应sector


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getXWLYCL() {
        return XWLYCL;
    }

    public void setXWLYCL(Integer XWLYCL) {
        this.XWLYCL = XWLYCL;
    }

    public String getXWLY() {
        return XWLY;
    }

    public void setXWLY(String XWLY) {
        this.XWLY = XWLY;
    }

    public String getSJYBM() {
        return SJYBM;
    }

    public void setSJYBM(String SJYBM) {
        this.SJYBM = SJYBM;
    }

    public Integer getLMLB() {
        return LMLB;
    }

    public void setLMLB(Integer LMLB) {
        this.LMLB = LMLB;
    }

    public String getZYMC() {
        return ZYMC;
    }

    public void setZYMC(String ZYMC) {
        this.ZYMC = ZYMC;
    }

    public String getZYLJ() {
        return ZYLJ;
    }

    public void setZYLJ(String ZYLJ) {
        this.ZYLJ = ZYLJ;
    }

    @Override
    public String toString() {
        return "UsrBYZQSJYB{" +
                "ID=" + ID +
                ", XWLYCL=" + XWLYCL +
                ", XWLY='" + XWLY + '\'' +
                ", SJYBM='" + SJYBM + '\'' +
                ", LMLB=" + LMLB +
                ", ZYMC='" + ZYMC + '\'' +
                ", ZYLJ='" + ZYLJ + '\'' +
                '}';
    }
}
