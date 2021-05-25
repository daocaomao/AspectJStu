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
public class SensorsDataApi {
    private static final String TAG = SensorsDataApi.class.getSimpleName();
    private static final Object mLock = new Object();
    private static SensorsDataApi instance;
    public static String SDK_VERSION = "1.0.0";
    private static String mDeviceId;
    private static Map<String, Object> mDeviceInfo;

    private SensorsDataApi(Application application) {
        mDeviceId = SensorsDataPrivate.getAndroidID(application.getApplicationContext());
        mDeviceInfo = SensorsDataPrivate.getDeviceInfo(application.getApplicationContext());
    }

    public static SensorsDataApi init(Application application) {
        synchronized (mLock) {
            if (instance == null) {
                instance = new SensorsDataApi(application);
            }
        }
        return instance;
    }

    public static SensorsDataApi getInstance() {
        return instance;
    }


    public void track(String eventName, JSONObject properties) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("event", eventName);
            jsonObject.put("device_id", mDeviceId);

            JSONObject sendProperties = new JSONObject(mDeviceInfo);

            if (properties != null) {
                SensorsDataPrivate.mergeJSONObject(properties, sendProperties);
            }

            jsonObject.put("properties", sendProperties);
            jsonObject.put("time", System.currentTimeMillis());

            Log.i(TAG, SensorsDataPrivate.formatJson(jsonObject.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
