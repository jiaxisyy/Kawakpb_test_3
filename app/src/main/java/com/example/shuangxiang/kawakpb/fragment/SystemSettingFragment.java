package com.example.shuangxiang.kawakpb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;
import com.example.shuangxiang.kawakpb.Utils.Utils;

/**
 * Created by zuheng.lv on 2016/4/21.
 * 系统设置界面
 */
public class SystemSettingFragment extends Fragment{


    private View view;
    private Button systemsetting_btn_back;
    private EditText systemsetting_et_wait;
    private EditText systemsetting_et_start;
    private EditText systemsetting_et_host;
    private EditText systemsetting_et_auxiliary;
    private EditText systemsetting_et_prssure;
    private EditText systemsetting_et_concentration;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_system_setting,container,false);
     initSystemSettingData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveSystemSettingData();
    }

    private void initSystemSettingData() {
        systemsetting_btn_back= (Button)view.findViewById(R.id.systemsetting_btn_back);
        systemsetting_et_wait = (EditText) view.findViewById(R.id.systemsetting_et_wait);
        systemsetting_et_start = (EditText) view.findViewById(R.id.systemsetting_et_start);
        systemsetting_et_host = (EditText) view.findViewById(R.id.systemsetting_et_host);
        systemsetting_et_auxiliary = (EditText) view.findViewById(R.id.systemsetting_et_auxiliary);
        systemsetting_et_prssure = (EditText) view.findViewById(R.id.systemsetting_et_prssure);
        systemsetting_et_concentration = (EditText) view.findViewById(R.id.systemsetting_et_concentration);
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_WAIT).isEmpty()){
            systemsetting_et_wait.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_WAIT));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_START).isEmpty()){
            systemsetting_et_start.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_START));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_HOST).isEmpty()){
            systemsetting_et_host.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_HOST));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_AUXILIARY).isEmpty()){
            systemsetting_et_auxiliary.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_AUXILIARY));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_PRASSURE).isEmpty()){
            systemsetting_et_prssure.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_PRASSURE));
        }
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_CONCENTRATION).isEmpty()){
            systemsetting_et_concentration.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.SYSTEM_SETTING_CONCENTRATION));
        }
        //返回按键监听
        systemsetting_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回主界面
                Utils.replace(getFragmentManager(),R.id.frameLayout,HomeFragment.class);
                Intent intent = new Intent("login");
                intent.putExtra("FLAG","1");
                getActivity().sendBroadcast(intent);
            }
        });
    }

    public void saveSystemSettingData(){
        if((systemsetting_et_wait.getText()!=null) && (!systemsetting_et_wait.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.SYSTEM_SETTING_WAIT,systemsetting_et_wait.getText().toString());
        }
        if((systemsetting_et_start.getText()!=null) && (!systemsetting_et_start.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.SYSTEM_SETTING_START,systemsetting_et_start.getText().toString());
        }
        if((systemsetting_et_host.getText()!=null) && (!systemsetting_et_host.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.SYSTEM_SETTING_HOST,systemsetting_et_host.getText().toString());
        }
        if((systemsetting_et_auxiliary.getText()!=null) && (!systemsetting_et_auxiliary.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.SYSTEM_SETTING_AUXILIARY,systemsetting_et_auxiliary.getText().toString());
        }
        if((systemsetting_et_prssure.getText()!=null) && (!systemsetting_et_prssure.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.SYSTEM_SETTING_PRASSURE,systemsetting_et_prssure.getText().toString());
        }
        if(( systemsetting_et_concentration.getText()!=null) && (! systemsetting_et_concentration.equals(""))){
            CacheUtils.putString(getActivity(),Constants.ActivityExtra.SYSTEM_SETTING_CONCENTRATION, systemsetting_et_concentration.getText().toString());
        }
    }

    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what =1;

            }
        }).start();
    }
}
