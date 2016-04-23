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
 * 特殊控制界面
 */
public class SpecialContralFragment extends Fragment{

    private View view;
    private Button specialcontrol_btn_back;
    private Button specialcontrol_btn_stop_left;
    private Button specialcontrol_btn_stop_right;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_system_mode,container,false);
        initSpicalControlData();
        return view;
    }
    private void initSpicalControlData() {
        specialcontrol_btn_back = (Button) view.findViewById(R.id.specialcontrol_btn_back);
        specialcontrol_btn_stop_left = (Button) view.findViewById(R.id.specialcontrol_btn_stop_left);
        specialcontrol_btn_stop_right = (Button) view.findViewById(R.id.specialcontrol_btn_stop_right);

        specialcontrol_btn_back.setOnClickListener(new specialControlOnClickListener());
    }

    private class specialControlOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.forget_btn_back:
                    Utils.replace(getFragmentManager(), R.id.frameLayout, ForgetFragment.class);
                    Intent intent = new Intent("login");
                    intent.putExtra("FLAG","1");
                    getActivity().sendBroadcast(intent);
                    break;
            }
        }
    }
}
