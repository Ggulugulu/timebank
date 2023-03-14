package com.example.timebank.bean;

import android.widget.Button;

public class ShenHeBean {
    private String volname;
    private String username;
    private String money;
    private String time;

    public Button getNot_pass() {
        return not_pass;
    }

    public Button getCan_pass() {
        return can_pass;
    }

    private Button not_pass;
    private Button can_pass;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getVolname() {
        return volname;
    }

    public void setVolname(String volname) {
        this.volname = volname;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
