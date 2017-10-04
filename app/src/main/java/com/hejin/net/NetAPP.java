package com.hejin.net;

import android.app.Application;

/**
 * 作者: *贺金龙
 * 创建时间: *  2017/9/29 18:25
 * 类描述: *
 * 修改人: *
 * 修改内容: *
 * 修改时间: *
 * 类说明: *
 *  
 */
public class NetAPP extends Application {

    // 提供一个单件
    private static NetAPP mApplication;

    // 单例
    public static NetAPP getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
