package com.example.timebank.bean;

import android.graphics.Bitmap;

public class GongShiBean {
    private String user;
    private String baozheng;
    private String renwu1;
    private String renwu2;
    private String renwu3;
    private String beginTime;
    private String endTime;
    private Bitmap touxaing;

    public Bitmap getTouxaing() {
        return touxaing;
    }

    public void setTouxaing(Bitmap touxaing) {
        this.touxaing = touxaing;
    }

    public String getRenwu2() {
        return renwu2;
    }

    public void setRenwu2(String renwu2) {
        this.renwu2 = renwu2;
    }

    public String getRenwu3() {
        return renwu3;
    }

    public void setRenwu3(String renwu3) {
        this.renwu3 = renwu3;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBaozheng() {
        return baozheng;
    }

    public void setBaozheng(String baozheng) {
        this.baozheng = baozheng;
    }

    public String getRenwu1() {
        return renwu1;
    }

    public void setRenwu1(String renwu1) {
        this.renwu1 = renwu1;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
}
