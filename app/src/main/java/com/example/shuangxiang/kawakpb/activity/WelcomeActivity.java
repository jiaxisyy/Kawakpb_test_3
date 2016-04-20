package com.example.shuangxiang.kawakpb.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.shuangxiang.kawakpb.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shuang.xiang on 2016/4/19.
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //休息两秒进入主界面

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //进入主界面
                enterMain();
            }
        };
        timer.schedule(task, 2000);
    }


    /**
     *
     * 进入主界面
     */
    private void enterMain() {

        Intent intent = new Intent(this, MainActivity.class);


        //跳转到主界面
        startActivity(intent);
        //关闭界面
        finish();
    }

}
