package com.zxf.empty.aspectjstu;

import android.app.Application;

import com.zhangxf.plugin.AnalyticsDataApi;

/**
 * @Description:
 * @Author: zhangxf
 * @CreateDate: 2021/5/21 16:28
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsDataApi.init(this);
    }
}
