package com.example.shuangxiang.kawakpb.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;
import com.example.shuangxiang.kawakpb.Utils.Utils;

/**
 * Created by zuheng.lv on 2016/4/20.
 * 登录界面
 */
public class LoginFragment extends Fragment{

    private View view;
    private EditText login_input_username;
    private EditText login_input_password;
    private CheckBox login_checkbox_saveuesrname;
    private Button login_btn_signin;
    private TextView login_tv_forget;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_login,container,false);
        initLoginData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    //初始化登录界面控件
    private void initLoginData(){
        login_input_username = (EditText) view.findViewById(R.id.login_input_username);
        login_input_password = (EditText) view.findViewById(R.id.login_input_password);
        login_checkbox_saveuesrname = (CheckBox) view.findViewById(R.id.login_checkbox_saveuesrname);
        login_btn_signin = (Button) view.findViewById(R.id.login_btn_signin);
        login_tv_forget = (TextView) view.findViewById(R.id.login_tv_forget);
        if(!CacheUtils.getString(getActivity(), Constants.ActivityExtra.LOGIN_USERNAME).isEmpty() && CacheUtils.getBoolean(getActivity(), Constants.ActivityExtra.LOGIN_SAVEUSERNAME)){
            login_input_username.setText(CacheUtils.getString(getActivity(), Constants.ActivityExtra.LOGIN_USERNAME));
        }
        login_btn_signin.setOnClickListener(new LoginOnClickListener());
        login_tv_forget.setOnClickListener(new LoginOnClickListener());
        login_checkbox_saveuesrname.setChecked(CacheUtils.getBoolean(getActivity(),Constants.ActivityExtra.LOGIN_SAVEUSERNAME));
    }
    //存储登录界面数据
    private void saveLoginData(){
        //存储用户登录成功状态
        CacheUtils.putBoolean(getActivity(),Constants.ActivityExtra.ISLOGIN,true);


        if(login_checkbox_saveuesrname.isChecked()){
            if(login_input_username.getText()!=null && !login_input_username.equals("")){
                CacheUtils.putString(getActivity(),Constants.ActivityExtra.LOGIN_USERNAME,login_input_username.getText().toString());
                CacheUtils.putBoolean(getActivity(),Constants.ActivityExtra.LOGIN_SAVEUSERNAME,login_checkbox_saveuesrname.isChecked());
            }
        }
    }
    //监听登录界面按键
    class LoginOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_btn_signin:
                    if(login_input_password.getText().toString().equals(CacheUtils.getString(getActivity(),Constants.ActivityExtra.PASSWORD)) && login_input_username.getText().toString().equals(CacheUtils.getString(getActivity(),Constants.ActivityExtra.USERNAME))){
                        Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
//                        loginSucceed.succeed(1);
//                        Utils.replace(getFragmentManager(),R.id.frameLayout,HomeFragment.class);
                        saveLoginData();

                        Intent intent = new Intent("login");
                        intent.putExtra("FLAG","1");
                        getActivity().sendBroadcast(intent);

                        return;
                    }else{
                        Toast.makeText(getActivity(), "用户名或者密码错误,请检查用户名与密码并重新登录", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.login_tv_forget:
                    Utils.replace(getFragmentManager(), R.id.frameLayout, ForgetFragment.class);
                    break;
            }
        }
    }
}
