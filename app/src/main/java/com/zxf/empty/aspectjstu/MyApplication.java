package com.zxf.empty.aspectjstu;

import android.app.Application;

import com.zhangxf.plugin.SensorsDataApi;

/**
 * @Description:
 * @Author: zhangxf
 * @CreateDate: 2021/5/21 16:28
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SensorsDataApi.init(this);
    }
}
