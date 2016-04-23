package com.example.shuangxiang.kawakpb.fragment;

import android.content.Intent;
import android.os.Bundle;
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
 * 时间参数界面
 */
public class TimeParaFragment extends Fragment{

    private View view;
    private EditText timepara_et_cold_a;
    private EditText timepara_et_cold_b;
    private EditText timepara_et_oxy_a;
    private EditText timepara_et_oxy_b;
    private Button timepara_btn_back;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_parameter,container,false);
        initTimeParaData();
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


}
