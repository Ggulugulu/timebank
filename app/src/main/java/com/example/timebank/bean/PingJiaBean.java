package com.example.timebank.bean;

import android.graphics.Bitmap;

public class PingJiaBean {
    private Bitmap touxiang;
    private String username;
    private String neirong;
    private String accepttime;
    private String overtime;
    private String state;
    private int starbar;

    public String getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(String accepttime) {
        this.accepttime = accepttime;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Bitmap getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(Bitmap touxiang) {
        this.touxiang = touxiang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public int getStarbar() {
        return starbar;
    }

    public void setStarbar(int starbar) {
        this.starbar = starbar;
    }

}
