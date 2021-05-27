package com.zhangxf.plugin;

import android.app.Application;
import android.util.Log;

import org.json.JSONObject;

import java.util.Map;

/**
 * @Description:
 * @Author: zhangxf
 * @CreateDate: 2021/5/21 14:50
 */
public class AnalyticsDataApi {
    private static final String TAG = AnalyticsDataApi.class.getSimpleName();
    private static final Object mLock = new Object();
    private static AnalyticsDataApi instance;
    public static String SDK_VERSION = "1.0.0";
    private static String mDeviceId;
    private static Map<String, Object> mDeviceInfo;

    private AnalyticsDataApi(Application application) {
        mDeviceId = DataPrivate.getAndroidID(application.getApplicationContext());
        mDeviceInfo = DataPrivate.getDeviceInfo(application.getApplicationContext());
    }

    public static AnalyticsDataApi init(Application application) {
        synchronized (mLock) {
            if (instance == null) {
                instance = new AnalyticsDataApi(application);
            }
        }
        return instance;
    }

    public static AnalyticsDataApi getInstance() {
        return instance;
    }


    public void track(String eventName, JSONObject properties) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("event", eventName);
            jsonObject.put("device_id", mDeviceId);

            JSONObject sendProperties = new JSONObject(mDeviceInfo);

            if (properties != null) {
                DataPrivate.mergeJSONObject(properties, sendProperties);
            }

            jsonObject.put("properties", sendProperties);
            jsonObject.put("time", System.currentTimeMillis());

            Log.i(TAG, DataPrivate.formatJson(jsonObject.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
