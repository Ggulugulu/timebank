package com.example.timebank.bean;

import java.io.Serializable;

public class VolBean implements Serializable {
    private String id;// 项目的唯一编号id
    private String name;// 项目的名称
    private String description;
    private String needPeople;
    private String nowPeople;
    private String category;// 项目的分类
    private String value;// 项目的时间货币
    private String time;// 项目所需要的时间
    private String address;
    private String ownerName;// 项目发起者的用户名
    private String gender;
    private String createTime; // 项目的发布时间
    private String startTime;// 项目的开始时间
    private String endTime;// 项目的结束时间
    private String workTime;
    private String phone;
    private String contactPersonName;
    private String ownerId;
    private String state;// 项目的完成情况 可承接 已满员 已完成（已完成的需要公示两天）

    private int Visibility;//查看详情 是否可见
    private String ButtonText;//按钮 文字
    private int Recource;//按钮 颜色
    private int TextRecource;//按钮 颜色
    private boolean clickable = true;

    public int getTextRecource() {
        return TextRecource;
    }

    public void setTextRecource(int textRecource) {
        TextRecource = textRecource;
    }

    public void setClickable(boolean status){
        this.clickable = status;
    }
    public boolean getClickable() {
        return clickable;
    }

    public int getRecource() {
        return Recource;
    }
    public void setRecource(int recource) {
        Recource = recource;
    }
    public String getButtonText() {
        return ButtonText;
    }

    public void setButtonText(String ButtonText) {
        this.ButtonText =ButtonText;
    }

    public int getVisibility() {
        return Visibility;
    }

    public void setVisibility(int status) {
        this.Visibility =status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
