package com.example.timebank.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.timebank.bean.ProvinceBean;
import com.example.timebank.utils.GetJsonDataUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class BaseActivity extends Activity {
    /**
     * @param context
     * @param view
     * 加载地址选择
     */
    public static void loadLocation(Context context, TextView view) {
        //  省
        List<ProvinceBean> options1Items = new ArrayList<>();
        //  市
        ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
        //  区
        ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
        // 解析数据
        parseData(context, options1Items, options2Items, options3Items,view);
    }
    private static void parseData(Context context, List<ProvinceBean> options1Items, ArrayList<ArrayList<String>> options2Items, ArrayList<ArrayList<ArrayList<String>>> options3Items,TextView view) {
        String jsonStr = new GetJsonDataUtil().getJson(context, "province.json");
        // 数据解析
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<ProvinceBean>>() {
        }.getType();
        List<ProvinceBean> shengList = gson.fromJson(jsonStr, type);
        //     把解析后的数据组装成想要的list
        options1Items = shengList;
        //     遍历省
        for (int i = 0; i < shengList.size(); i++) {
            //         存放城市
            ArrayList<String> cityList = new ArrayList<>();
            //         存放区
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();
            //         遍历市
            for (int c = 0; c < shengList.get(i).city.size(); c++) {
                //        拿到城市名称
                String cityName = shengList.get(i).city.get(c).name;
                cityList.add(cityName);

                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                if (shengList.get(i).city.get(c).area == null || shengList.get(i).city.get(c).area.size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(shengList.get(i).city.get(c).area);
                }
                province_AreaList.add(city_AreaList);
            }
            //添加城市
            options2Items.add(cityList);
            //添加地区
            options3Items.add(province_AreaList);
        }
        //  展示省市区选择器
        showPickerView(context, options1Items, options2Items, options3Items,view);
    }
    private static void showPickerView(final Context context, final List<ProvinceBean> options1Items, final ArrayList<ArrayList<String>> options2Items, final ArrayList<ArrayList<ArrayList<String>>> options3Items, final TextView view)
    {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).name +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                Toast.makeText(context, tx, Toast.LENGTH_SHORT).show();
                view.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * @param textView 时间选择后显示的地方
     * timepickerview选择时间
     */
    public void showTime(final TextView textView) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        //private SimpleDateFormat sdf; 时间显示样式 BaseActivity
        //sdf = new SimpleDateFormat("yyyy-MM");

        startDate.set(2021, 7, 23);
        endDate.set(2021, 12, 30);
        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //textView.setText(new SimpleDateFormat().format(date));

                textView.setText( ( date.getYear() + 1900 ) + "年" + (date.getMonth()+1) +"月"+date.getDate()+"日"+date.getHours()+"时"+date.getMinutes()+"秒");
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                //.isDialog(true)//是否显示为对话框样式
                .build();

        pvTime.show();
    }



}

