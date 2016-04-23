package com.example.shuangxiang.kawakpb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuangxiang.kawakpb.Constants.Constants;
import com.example.shuangxiang.kawakpb.R;
import com.example.shuangxiang.kawakpb.Utils.CacheUtils;
import com.example.shuangxiang.kawakpb.Utils.Utils;

/**
 * Created by zuheng.lv on 2016/4/21.
 * 忘记密码界面
 */
public class ForgetFragment extends Fragment{

    private View view;
    private Button forget_btn_back;
    private EditText forget_et_username;
    private EditText forget_et_code;
    private Button forget_btn_getcode;
    private EditText forget_et_passwoed;
    private EditText forget_et_passwoed_second;
    private Button forget_btn_confirm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgetpassword,container,false);
        initForgetData();
        return view;
    }
//初始化忘记密码界面信息
    private void initForgetData() {
        forget_btn_back = (Button) view.findViewById(R.id.forget_btn_back);
        forget_btn_getcode = (Button) view.findViewById(R.id.forget_btn_getcode);
        forget_btn_confirm = (Button) view.findViewById(R.id.forget_btn_confirm);
        forget_et_username = (EditText) view.findViewById(R.id.forget_et_username);
        forget_et_code = (EditText) view.findViewById(R.id.forget_et_code);
        forget_et_passwoed = (EditText) view.findViewById(R.id.forget_et_passwoed);
        forget_et_passwoed_second = (EditText) view.findViewById(R.id.forget_et_passwoed_second);
        forget_btn_back.setOnClickListener(new forgetOnclickListener());
        forget_btn_getcode.setOnClickListener(new forgetOnclickListener());
        forget_btn_confirm.setOnClickListener(new forgetOnclickListener());
    }

//监听忘记密码界面的按键
    private class forgetOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.forget_btn_back:
                    Utils.replace(getFragmentManager(),R.id.frameLayout,LoginFragment.class);
                    break;
                case R.id.forget_btn_getcode:

                    break;
                case R.id.forget_btn_confirm:
                    if(forget_et_username.getText().equals("")||forget_et_username.getText() ==null){
                        Toast.makeText(getActivity(),"用户名不能为空,请输入用户名并重新点击确定",Toast.LENGTH_SHORT).show();
                        break;
                    }else if(forget_et_passwoed.getText().equals("")||forget_et_passwoed_second.getText() ==null) {
                        Toast.makeText(getActivity(),"密码不能为空,请输入密码并重新点击确定",Toast.LENGTH_SHORT).show();
                        break;
                    }
                   else if(!forget_et_passwoed_second.getText().toString().equals(forget_et_passwoed.getText().toString())){
                        Toast.makeText(getActivity(),"密码不一致,请检查密码并提交",Toast.LENGTH_SHORT).show();
                }else{
                        CacheUtils.putString(getActivity(), Constants.ActivityExtra.USERNAME,forget_et_username.getText().toString());
                        CacheUtils.putString(getActivity(),Constants.ActivityExtra.PASSWORD,forget_et_passwoed_second.getText().toString());
                        Toast.makeText(getActivity(),"密码修改成功",Toast.LENGTH_SHORT).show();
                        Utils.replace(getFragmentManager(), R.id.frameLayout, LoginFragment.class);
                    }

            }
        }
    }
}
