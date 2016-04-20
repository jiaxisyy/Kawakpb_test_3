package com.example.shuangxiang.kawakpb.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shuang.xiang on 2016/4/19.
 */
public abstract class BaseFragment extends Fragment{

    public Context context;
    public View rootView;//片段界面的根节点视图对象

    //初始化片段
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取得该片段宿主的activity
        context=getActivity();

    }

    /**
     * 实例化片段界面
     *
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView=initView();

        return rootView;


    }
    //当前片段宿主activity又重新初始化完成,则调用该方法,一般在该方法中加载片段的数据,和监听片段的控件


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        initData();

    }

    //初始化片段的数据
    public abstract void initData();

    //初始化片段界面
    public abstract View initView();

    //
}
