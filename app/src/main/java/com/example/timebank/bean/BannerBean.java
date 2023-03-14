package com.example.timebank.bean;

import android.widget.ImageView;

public class BannerBean {
    private String name;
    private ImageView img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img.getId();
    }

    public void setImg(ImageView img) {
        this.img =img;
    }


}
