package com.example.timebank.bean;

import android.graphics.Bitmap;

public class ShenBaoBean {
    String name;
    String text;
    String star;
    String time;
    String last;
    Bitmap touxiang;

    public Bitmap getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(Bitmap touxiang) {
        this.touxiang = touxiang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }



}
