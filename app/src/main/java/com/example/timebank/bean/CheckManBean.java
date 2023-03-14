package com.example.timebank.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class CheckManBean implements Serializable {
    private Bitmap touxiang;
    private String user;
    private String rongyu;

    public Bitmap getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(Bitmap touxiang) {
        this.touxiang = touxiang;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRongyu() {
        return rongyu;
    }

    public void setRongyu(String rongyu) {
        this.rongyu = rongyu;
    }
}
