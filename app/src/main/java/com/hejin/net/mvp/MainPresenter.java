package com.hejin.net.mvp;

import android.util.Log;

import com.hejin.net.Constants;
import com.hejin.net.base.BasePresenter;
import com.hejin.net.net.BaseObservable;
import com.hejin.net.net.RetrofitManager;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者: *贺金龙
 * 创建时间: *  2017/9/29 18:16
 * 类描述: *
 * 修改人: *
 * 修改内容: *
 * 修改时间: *
 * 类说明: *
 *  
 */
public class MainPresenter extends BasePresenter
        implements MainContract.IMainPresenter {

    private String TAG = "done";

    @Override
    public void requestNet() {

        Map<String, String> map = new HashMap<>();
        map.put(Constants.PublicParameter.PHONE, "18701147727");
//        String sign = MapUtils.createEncryption(map);
//        map.put(Constants.PublicParameter.APPSIGN, sign);


        createSubscription(RetrofitManager.provideApiService().getLoginSms("18701147727"), new BaseObservable<String>() {
            @Override
            public void onDataSuccess(String s) {
                Log.e(TAG, "onDataSuccess: " + s);
            }

            @Override
            public void onDataError(String s) {
                Log.e(TAG, "onDataError: " + s);
            }
        });
    }
}
