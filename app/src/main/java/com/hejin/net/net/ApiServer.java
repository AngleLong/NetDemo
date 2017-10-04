package com.hejin.net.net;

import com.hejin.net.Constants;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 *   作者：*  贺金龙
 *   创建时间：*  2017/9/29 10:01
 *   类描述：* 所有网络请求的接口层
 *   修改人：*
 *   修改内容:*
 *   修改时间：*
 *  
 */
public interface ApiServer {
    /**
     * 获取验证码接口
     */
    @FormUrlEncoded
    @POST(Constants.UrlPath.GETLOGINSMS)
    Observable<BaseBean<String>> getLoginSms(@Field(Constants.PublicParameter.PHONE) String phone);
}
