package com.hejin.net;

/**
 *   作者：*  贺金龙
 *   创建时间：*  2017/9/29 9:27
 *   类描述：*  存放所有静态字段的类
 *   修改人：*
 *   修改内容:*
 *   修改时间：*
 *  
 */
public class Constants {
    /*存放所有URL字段的接口*/
    public interface UrlPath {
        /**
         * 所有请求的根接口
         */
        String BaseUrl = "http://zhibonotify.youbozb.com:8080/youbo-interfaces/";
        /**
         * 获取验证码接口
         */
        String GETLOGINSMS = "app/getLoginSms";
    }

    /*存放公共请求参数的接口*/
    public interface PublicParameter {
        String APPVERSIONNAME = "appVersionName";
        String APPTYPE = "appType";
        String APPDEVICEID = "appDeviceId";
        String APPSIGN = "appSign";
        String SUCCESS = "SUCCESS";
        String FAIL = "FAIL";
        String TIMESTAMP = "timestamp";


        String PHONE = "phone";
    }
}
