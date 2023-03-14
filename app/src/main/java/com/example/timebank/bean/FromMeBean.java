package com.example.timebank.bean;

public class FromMeBean {
    private String id;// 项目的唯一编号id
    private String name;// 项目的名称
    private int value;// 项目的时间货币
    private int time;// 项目所需要的时间
    private String category;// 项目的分类
    private String state;// 项目的完成情况 可承接 已满员 已完成（已完成的需要公示两天）
    private String createTime; // 项目的发布时间
    private String startTime;// 项目的开始时间
    private String endTime;// 项目的结束时间
    private String Text;// 文字
    private int Recource;//按钮 颜色
    private int shenbaoRecource;
    private int visibility;//申报
    private String shenbaoText;

    public String getShenbaoText() {
        return shenbaoText;
    }



    public void setShenbaoText(String shenbaoText) {
        this.shenbaoText = shenbaoText;
    }



    public int isVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }



    public void setShenbaoRecource(int status){
        this.shenbaoRecource = status;
    }
    public int getShenbaoRecource() {
        return shenbaoRecource;
    }

    public int getRecource() {
        return Recource;
    }
    public void setRecource(int recource) {
        Recource = recource;
    }
    public String getText() {
        return Text;
    }

    public void setText(String ButtonText) {
        this.Text =ButtonText;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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
