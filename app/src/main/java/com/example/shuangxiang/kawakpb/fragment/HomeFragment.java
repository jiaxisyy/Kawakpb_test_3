package com.example.shuangxiang.kawakpb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by zuheng.lv on 2016/4/20.
 * 主界面
 */
public class HomeFragment extends Fragment {

    private View view;
    private TextView home_tv_pressure;
    private TextView home_tv_concentration;
    private TextView home_tv_flow;
    private TextView home_tv_total;
    private Button btn_home_machine_a;
    private Button btn_home_machine_b;
    private Button btn_home_machine_switch;
    private TextView home_tv_time;
    private TextView home_tv_date;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                updaData(msg.getData().getString("random_pressure"), msg.getData().getString("random_concentration"), msg.getData().getString("random_flow"), msg.getData().getString("random_total"));
                break;
                case 2:

                    home_tv_date.setText(msg.getData().getString("formatterDate"));
                   home_tv_time.setText(msg.getData().getString("formatterTime"));
                    break;

            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initHomeData();
        TimeAndDate();
        Ramdom();
        return view;
    }

    //主界面数据初始化-----------------------------------------------------------
    private void initHomeData() {
        //初始化主界面的压力、浓度、流量、累计流量的值
        home_tv_pressure = (TextView) view.findViewById(R.id.home_tv_pressure);
        home_tv_concentration = (TextView) view.findViewById(R.id.home_tv_concentration);
        home_tv_flow = (TextView) view.findViewById(R.id.home_tv_flow);
        home_tv_total = (TextView) view.findViewById(R.id.home_tv_total);
        home_tv_date = (TextView)view.findViewById(R.id.home_tv_date);
        home_tv_time = (TextView)view.findViewById(R.id.home_tv_time);
        //初始化按键
        btn_home_machine_a = (Button) view.findViewById(R.id.btn_home_machine_a);
        btn_home_machine_b = (Button) view.findViewById(R.id.btn_home_machine_b);
        btn_home_machine_switch = (Button) view.findViewById(R.id.btn_home_machine_switch);

        btn_home_machine_a.setSelected(CacheUtils.getBoolean(getActivity(), Constants.ActivityExtra.HOME_MACHINE_A));

        btn_home_machine_b.setSelected(CacheUtils.getBoolean(getActivity(), Constants.ActivityExtra.HOME_MACHINE_B));
        btn_home_machine_switch.setSelected(CacheUtils.getBoolean(getActivity(), Constants.ActivityExtra.HOME_SWITCH));
        btn_home_machine_a.setOnClickListener(new HomeOnclickListener());
        btn_home_machine_b.setOnClickListener(new HomeOnclickListener());
        btn_home_machine_switch.setOnClickListener(new HomeOnclickListener());
    }

    //主界面数据存储--------------------------------------------------------------------
    public void saveHomeData() {
        CacheUtils.putBoolean(getActivity(), Constants.ActivityExtra.HOME_MACHINE_A, btn_home_machine_a.isSelected());
        CacheUtils.putBoolean(getActivity(), Constants.ActivityExtra.HOME_MACHINE_B, btn_home_machine_b.isSelected());
        CacheUtils.putBoolean(getActivity(), Constants.ActivityExtra.HOME_SWITCH, btn_home_machine_switch.isSelected());
    }

    public void updaData(String pressure, String concentration, String flow, String total) {
        home_tv_pressure.setText(pressure);
        home_tv_concentration.setText(concentration);
        home_tv_flow.setText(flow);
        home_tv_total.setText(total);
    }

    //监听主界面按键
    private class HomeOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_home_machine_a:

                    saveHomeData();
                    break;
                case R.id.btn_home_machine_b:

                    saveHomeData();
                    break;
                case R.id.btn_home_machine_switch:

                    saveHomeData();
                    break;
            }
        }
    }

    public void TimeAndDate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long sysTime = System.currentTimeMillis();
                    long sysDate = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);
                    CharSequence sysDateStr = DateFormat.format("yyyy-MM-dd",sysDate);
                    Bundle bundle = new Bundle();
                    bundle.putString("formatterTime", String.valueOf(sysTimeStr));
                    bundle.putString("formatterDate", String.valueOf(sysDateStr));
                    Message msg = new Message();
                    msg.setData(bundle);
                    msg.what =2;
                    handler.sendMessage(msg);
                }
            }
        }).start();

    }
    public void Ramdom() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int random_flow = 1;
                int random_total = 0;
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String random_pressure = String.valueOf((int) (Math.random() * 10 +90));
                    String random_concentration = String.valueOf((int) (Math.random() * 10 + 90));
                    random_total = random_flow + random_total;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("random_pressure", random_pressure);
                    bundle.putString("random_concentration", random_concentration);
                    bundle.putString("random_flow", String.valueOf(random_flow));
                    bundle.putString("random_total",String.valueOf(random_total));
                    msg.setData(bundle);
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
}
