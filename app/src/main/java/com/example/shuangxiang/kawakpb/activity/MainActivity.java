package com.example.shuangxiang.kawakpb.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.util.Log;
import android.widget.RadioGroup;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.Utils;

public class MainActivity extends FragmentActivity {
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();


    }

    /**
     * 初始化控件
     */
    private void initView() {


        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //设置radioGroup改变的监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int buttonId = radioGroup.getCheckedRadioButtonId();
                // int position = getIntent().getIntExtra(Constants.ActivityExtra.CHECK_POSITION, 0);
                switch (checkedId) {
                    case R.id.rb_home:
                        showFragment(HomeFragment.class);
                        break;
                    case R.id.rb_login:
                        showFragment(LoginFragment.class);
                        break;
                    case R.id.rb_parameter:
                        showFragment(ParameterFragment.class);
                        break;
                    case R.id.rb_setting:
                        showFragment(SettingFragment.class);
                        break;
                }

            }
        });

    }

    public Fragment showFragment(Class<? extends Fragment> fragmentClass) {
        return Utils.replace(getSupportFragmentManager(), R.id.frameLayout, fragmentClass);
    }

}
