package com.example.shuangxiang.kawakpb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.Utils;

/**
 * Created by zuheng.lv on 2016/4/21.
 * 电阀校准界面
 */
public class ValveSettingFragment extends Fragment{

    private View view;
    private Button valvesetting_btn_back;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_valve_setting,container,false);
        initValveSettingDate();
        return view;
    }

    public void initValveSettingDate(){
        valvesetting_btn_back = (Button)view.findViewById(R.id.valvesetting_btn_back);
        valvesetting_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.replace(getFragmentManager(), R.id.frameLayout, HomeFragment.class);
                Intent intent = new Intent("login");
                intent.putExtra("FLAG","1");
                getActivity().sendBroadcast(intent);
            }
        });
    }
}
