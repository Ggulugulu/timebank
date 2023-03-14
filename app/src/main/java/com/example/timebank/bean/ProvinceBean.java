package com.example.timebank.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by wwbb04 on 2021/7/18.
 */

public class ProvinceBean implements IPickerViewData {
    public String name;
    public List<Shi> city;

    public static class Shi {
        public String name;
        public List<String> area;

    }

    //  这个要返回省的名字
    @Override
    public String getPickerViewText() {
        return this.name;
    }
}

