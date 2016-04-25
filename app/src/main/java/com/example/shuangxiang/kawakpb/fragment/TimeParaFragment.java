package com.example.shuangxiang.kawakpb.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;
import com.example.shuangxiang.kawakpb.Utils.Utils;
import com.example.shuangxiang.kawakpb.wheel.StrericWheelAdapter;
import com.example.shuangxiang.kawakpb.wheel.WheelView;


import java.util.Calendar;

/**
 * Created by zuheng.lv on 2016/4/21.
 * 时间参数界面
 */
public class TimeParaFragment extends Fragment{

    private View view;
    private EditText timepara_et_cold_a;
    private EditText timepara_et_cold_b;
    private EditText timepara_et_oxy_a;
    private EditText timepara_et_oxy_b;
    private Button timepara_btn_back;
//时间选择器
    private WheelView yearWheel,monthWheel,dayWheel,hourWheel,minuteWheel,secondWheel;
    public static String[] yearContent=null;
    public static String[] monthContent=null;
    public static String[] dayContent=null;
    public static String[] hourContent = null;
    public static String[] minuteContent=null;
    public static String[] secondContent=null;
    private TextView timesetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_parameter,container,false);
        initTimeParaData();
        initContent();
        return view;
    }

        /**
         *  展示时间选择
         */
        private void showDialog(){

    }


    private void initTimeParaData() {
        timepara_et_cold_a = (EditText)view.findViewById(R.id.timepara_et_cold_a);
        timepara_et_cold_b = (EditText)view.findViewById(R.id.timepara_et_cold_b);
        timepara_et_oxy_a = (EditText)view.findViewById(R.id.timepara_et_oxy_a);
        timepara_et_oxy_b = (EditText)view.findViewById(R.id.timepara_et_oxy_b);
        //PLC时间点击事件
        timesetting = (TextView) view.findViewById(R.id.timesetting);
        timesetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.time_picker, null);
                Calendar calendar = Calendar.getInstance();
                    int curYear = calendar.get(Calendar.YEAR);
                    int curMonth= calendar.get(Calendar.MONTH)+1;
                    int curDay = calendar.get(Calendar.DAY_OF_MONTH);
                    int curHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int curMinute = calendar.get(Calendar.MINUTE);
                    int curSecond = calendar.get(Calendar.SECOND);

                    yearWheel = (WheelView)view.findViewById(R.id.yearwheel);
                    monthWheel = (WheelView)view.findViewById(R.id.monthwheel);
                    dayWheel = (WheelView)view.findViewById(R.id.daywheel);
                    hourWheel = (WheelView)view.findViewById(R.id.hourwheel);
                    minuteWheel = (WheelView)view.findViewById(R.id.minutewheel);
                    secondWheel = (WheelView)view.findViewById(R.id.secondwheel);


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(view);

                    yearWheel.setAdapter(new StrericWheelAdapter(yearContent));
                    yearWheel.setCurrentItem(curYear-2013);
                    yearWheel.setCyclic(true);
                    yearWheel.setInterpolator(new AnticipateOvershootInterpolator());


                    monthWheel.setAdapter(new StrericWheelAdapter(monthContent));

                    monthWheel.setCurrentItem(curMonth-1);

                    monthWheel.setCyclic(true);
                    monthWheel.setInterpolator(new AnticipateOvershootInterpolator());

                    dayWheel.setAdapter(new StrericWheelAdapter(dayContent));
                    dayWheel.setCurrentItem(curDay-1);
                    dayWheel.setCyclic(true);
                    dayWheel.setInterpolator(new AnticipateOvershootInterpolator());

                    hourWheel.setAdapter(new StrericWheelAdapter(hourContent));
                    hourWheel.setCurrentItem(curHour);
                    hourWheel.setCyclic(true);
                    hourWheel.setInterpolator(new AnticipateOvershootInterpolator());

                    minuteWheel.setAdapter(new StrericWheelAdapter(minuteContent));
                    minuteWheel.setCurrentItem(curMinute);
                    minuteWheel.setCyclic(true);
                    minuteWheel.setInterpolator(new AnticipateOvershootInterpolator());

                    secondWheel.setAdapter(new StrericWheelAdapter(secondContent));
                    secondWheel.setCurrentItem(curSecond);
                    secondWheel.setCyclic(true);
                    secondWheel.setInterpolator(new AnticipateOvershootInterpolator());

                    builder.setTitle("123");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            StringBuffer sb = new StringBuffer();
                            sb.append(yearWheel.getCurrentItemValue()).append("-")
                                    .append(monthWheel.getCurrentItemValue()).append("-")
                                    .append(dayWheel.getCurrentItemValue());

                            sb.append(" ");
                            sb.append(hourWheel.getCurrentItemValue())
                                    .append(":").append(minuteWheel.getCurrentItemValue())
                                    .append(":").append(secondWheel.getCurrentItemValue());
                            timesetting.setText(sb);
                            dialog.cancel();
                        }
                    });
                    builder.show();
            }
        });
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_COLD_A).isEmpty()){
            timepara_et_cold_a.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_COLD_A));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_COLD_B).isEmpty()){
            timepara_et_cold_b.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_COLD_B));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_OXY_A).isEmpty()){
            timepara_et_oxy_a.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_OXY_A));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_OXY_B).isEmpty()){
            timepara_et_oxy_b.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.TIMEPARA_OXY_B));
        }
        timepara_btn_back = (Button) view.findViewById(R.id.timepara_btn_back);
        timepara_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.replace(getFragmentManager(), R.id.frameLayout, HomeFragment.class);
                Utils.replace(getFragmentManager(),R.id.frameLayout,HomeFragment.class);
                Intent intent = new Intent("login");
                intent.putExtra("FLAG","1");
                getActivity().sendBroadcast(intent);
            }
        });
    }
    private void saveTimeparaData() {
        if((timepara_et_cold_a.getText()!=null) && (!timepara_et_cold_a.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.TIMEPARA_COLD_A,timepara_et_cold_a.getText().toString());
        }
        if((timepara_et_cold_b.getText()!=null) && (!timepara_et_cold_b.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.TIMEPARA_COLD_B,timepara_et_cold_b.getText().toString());
        }
        if((timepara_et_oxy_a.getText()!=null) && (!timepara_et_oxy_a.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.TIMEPARA_OXY_A,timepara_et_oxy_a.getText().toString());
        }
        if((timepara_et_oxy_b.getText()!=null) && (!timepara_et_oxy_b.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.TIMEPARA_OXY_B,timepara_et_oxy_a.getText().toString());
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        saveTimeparaData();
    }
    public void initContent()
    {
        yearContent = new String[10];
        for(int i=0;i<10;i++)
            yearContent[i] = String.valueOf(i+2013);

        monthContent = new String[12];
        for(int i=0;i<12;i++)
        {
            monthContent[i]= String.valueOf(i+1);
            if(monthContent[i].length()<2)
            {
                monthContent[i] = "0"+monthContent[i];
            }
        }

        dayContent = new String[31];
        for(int i=0;i<31;i++)
        {
            dayContent[i]=String.valueOf(i+1);
            if(dayContent[i].length()<2)
            {
                dayContent[i] = "0"+dayContent[i];
            }
        }
        hourContent = new String[24];
        for(int i=0;i<24;i++)
        {
            hourContent[i]= String.valueOf(i);
            if(hourContent[i].length()<2)
            {
                hourContent[i] = "0"+hourContent[i];
            }
        }

        minuteContent = new String[60];
        for(int i=0;i<60;i++)
        {
            minuteContent[i]=String.valueOf(i);
            if(minuteContent[i].length()<2)
            {
                minuteContent[i] = "0"+minuteContent[i];
            }
        }
        secondContent = new String[60];
        for(int i=0;i<60;i++)
        {
            secondContent[i]=String.valueOf(i);
            if(secondContent[i].length()<2)
            {
                secondContent[i] = "0"+secondContent[i];
            }
        }
    }


}
