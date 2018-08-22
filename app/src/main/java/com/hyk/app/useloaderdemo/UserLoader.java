package com.hyk.app.useloaderdemo;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class UserLoader extends AsyncTaskLoader<List<UserBean>> {

    public UserLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //如果loader启动 强制执行loadInBackground()方法
        if (isStarted()) {
            forceLoad();
        }
    }

    //在子线程加载数据
    @Override
    public List<UserBean> loadInBackground() {
        //模拟从网络返回数据
        List<UserBean> list = new ArrayList<>();
        list.add(new UserBean("zhangsan", "123"));
        list.add(new UserBean("lisi", "123"));
        list.add(new UserBean("wangwu", "123"));
        return list;
    }
}
