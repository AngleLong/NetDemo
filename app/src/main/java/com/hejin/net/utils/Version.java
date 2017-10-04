package com.hejin.net.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.hejin.net.NetAPP;

/**
 * 获取版本号的方法
 *
 * @author Administrator
 */
public class Version {

    public static String getVersion() {
        PackageManager packageManager = NetAPP.getInstance().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(NetAPP.getInstance().getPackageName(), 0);
            String version = packageInfo.versionName;
            return version;
        } catch (NameNotFoundException e) {
            return "1.0.0";
        }
    }
}
