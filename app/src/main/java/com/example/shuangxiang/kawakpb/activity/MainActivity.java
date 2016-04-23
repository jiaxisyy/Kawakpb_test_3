package com.example.shuangxiang.kawakpb.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;

import com.example.shuangxiang.kawakpb.Utils.Utils;
import com.example.shuangxiang.kawakpb.fragment.HomeFragment;
import com.example.shuangxiang.kawakpb.fragment.LoginFragment;
import com.example.shuangxiang.kawakpb.fragment.SystemModeFragment;
import com.example.shuangxiang.kawakpb.fragment.SystemSettingFragment;
import com.example.shuangxiang.kawakpb.fragment.TimeParaFragment;
import com.example.shuangxiang.kawakpb.fragment.ValveSettingFragment;
import com.example.shuangxiang.kawakpb.interf.LoginSucceed;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private RadioGroup radioGroup;
    private EditText et__home_pressure;
    private EditText et__home_concentration;
    private EditText et__home_flow;
    private EditText et__home_Total;
    private DrawerLayout drawerLayout;
    private TextView left_login, logout, login_succeed_state, login_succeed_introduce, login_succeed_setting, login_succeed_history, login_succeed_time_parameter;
    private LinearLayout ll_left_menu;
    private RadioButton rb_setting,rb_parameter;//  主界面的设置按钮
    private View include_login_menu, include_login_menu_succeed, fragment_login_menu_succeed, fragment_login_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showFragment(HomeFragment.class);
        initData();
        registerReceiver(new LoginSuccessReciver(),new IntentFilter("login"));
    }

    /**
     * 初始化控件
     */
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
        radioGroup.check(R.id.rb_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        fragment_login_menu_succeed = getLayoutInflater().inflate(R.layout.fragment_login_menu_succeed, null);
        fragment_login_menu = getLayoutInflater().inflate(R.layout.fragment_login_menu, null);
        ll_left_menu = (LinearLayout) findViewById(R.id.ll_left_menu);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rb_parameter = (RadioButton) findViewById(R.id.rb_parameter);
        left_login = (TextView) fragment_login_menu.findViewById(R.id.left_login);
        login_succeed_state = (TextView) fragment_login_menu_succeed.findViewById(R.id.login_succeed_state);
        login_succeed_introduce = (TextView) fragment_login_menu_succeed.findViewById(R.id.login_succeed_introduce);
        login_succeed_setting = (TextView) fragment_login_menu_succeed.findViewById(R.id.login_succeed_setting);
        login_succeed_history = (TextView) fragment_login_menu_succeed.findViewById(R.id.login_succeed_history);
        login_succeed_time_parameter = (TextView) fragment_login_menu_succeed.findViewById(R.id.login_succeed_time_parameter);
        left_login.setOnClickListener(this);
        login_succeed_state.setOnClickListener(this);
        login_succeed_introduce.setOnClickListener(this);
        login_succeed_setting.setOnClickListener(this);
        login_succeed_history.setOnClickListener(this);
        login_succeed_time_parameter.setOnClickListener(this);
        rb_parameter.setOnClickListener(this);
       // new LoginFragment().setLoginSucceed(this);
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
                        //判断用户是否登录
                        if (CacheUtils.getBoolean(getApplicationContext(), Constants.ActivityExtra.ISLOGIN)) {
                            ll_left_menu.removeAllViews();
                            ll_left_menu.addView(fragment_login_menu_succeed);
                        } else {//未登录
                            ll_left_menu.removeAllViews();
                            ll_left_menu.addView(fragment_login_menu);
                        }
                        drawerLayout.openDrawer(Gravity.LEFT);
//                        showLeftMenu();
                        break;
                    case R.id.rb_setting:
                        showFragment(ValveSettingFragment.class);
                        break;
                }
            }
        });

    }


    public Fragment showFragment(Class<? extends Fragment> fragmentClass) {
        return Utils.replace(getSupportFragmentManager(), R.id.frameLayout, fragmentClass);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //登陆

            case R.id.left_login:

                //底部选择到登录模块
                radioGroup.check(R.id.rb_login);
                //关闭侧滑
                drawerLayout.closeDrawers();
                //跳转登录界面
                showFragment(LoginFragment.class);
                break;
            case R.id.login_succeed_setting:
                drawerLayout.closeDrawers();
                showFragment(SystemSettingFragment.class);
                break;
            case R.id.login_succeed_state:
                drawerLayout.closeDrawers();
                showFragment(SystemModeFragment.class);
                break;
            case R.id.login_succeed_time_parameter:
                drawerLayout.closeDrawers();
                showFragment(TimeParaFragment.class);
                break;
            case R.id.rb_parameter:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }

    }


    public class LoginSuccessReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String flag = intent.getExtras().getString("FLAG");
            System.out.println(flag);
            if(flag.equals("1")){
                rb_setting.setVisibility(View.VISIBLE);
                radioGroup.check(R.id.rb_parameter);
                showFragment(HomeFragment.class);
                drawerLayout.openDrawer(Gravity.LEFT);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(new LoginSuccessReciver());
        CacheUtils.putBoolean(this,Constants.ActivityExtra.ISLOGIN,false);
    }
}

