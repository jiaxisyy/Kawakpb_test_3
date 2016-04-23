package com.example.shuangxiang.kawakpb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;
import com.example.shuangxiang.kawakpb.Utils.Utils;

/**
 * Created by zuheng.lv on 2016/4/21.
 * 系统模式界面
 */
public class SystemModeFragment extends Fragment{

    private View view;
    private Button systemmode_btn_back;
    private Button systemmode_btn_machine_a;
    private Button systemmode_btn_machine_b;
    private Button systemmode_btn_switch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_system_mode,container,false);
        initSystemModeData();
        return view;
    }

    private void initSystemModeData() {
        systemmode_btn_back =(Button)view.findViewById(R.id.systemmode_btn_back);
        systemmode_btn_machine_a =(Button)view.findViewById(R.id.valvesetting_btn_back);
        systemmode_btn_machine_b =(Button)view.findViewById(R.id.valvesetting_btn_back);
        systemmode_btn_switch =(Button)view.findViewById(R.id.valvesetting_btn_back);
        systemmode_btn_back.setOnClickListener(new SystemModeOnclickListener());

    }

    //按键监听与系统状态保存
    private class SystemModeOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.systemmode_btn_back:
                        Utils.replace(getFragmentManager(), R.id.frameLayout, HomeFragment.class);
                    Intent intent = new Intent("login");
                    intent.putExtra("FLAG","1");
                    getActivity().sendBroadcast(intent);
                    break;
                case R.id.systemmode_btn_machine_a:
                    CacheUtils.putBoolean(getActivity(), Constants.ActivityExtra.SYSTEMMODE_MACHINE_A,systemmode_btn_machine_a.isSelected());
                    break;
                case R.id.systemmode_btn_machine_b:
                    CacheUtils.putBoolean(getActivity(), Constants.ActivityExtra.SYSTEMMODE_MACHINE_B, systemmode_btn_machine_b.isSelected());
                    break;
                case R.id.systemmode_btn_switch:
                    CacheUtils.putBoolean(getActivity(), Constants.ActivityExtra.SYSTEMMODE_SWITCH, systemmode_btn_switch.isSelected());
                    break;
            }
        }
    }
}
