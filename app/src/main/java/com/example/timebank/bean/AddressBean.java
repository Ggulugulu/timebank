package com.example.timebank.bean;

public class AddressBean {
    private String name;
    private String phone;
    private String guanxi;
    private int moren;

    public void setMoren(int moren) {
        this.moren = moren;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGuanxi() {
        return guanxi;
    }

    public void setGuanxi(String guanxi) {
        this.guanxi = guanxi;
    }

    public int getMoren() {
        return moren;
    }
}
