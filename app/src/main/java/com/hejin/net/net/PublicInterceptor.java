package com.hejin.net.net;

import android.util.Log;

import com.hejin.net.Constants;
import com.hejin.net.NetAPP;
import com.hejin.net.utils.DeviceUuidFactory;
import com.hejin.net.utils.MapUtils;
import com.hejin.net.utils.Version;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *   作者：*  贺金龙
 *   创建时间：*  2017/9/29 10:17
 *   类描述：* 公共请求参数的拦截器
 *   修改人：*
 *   修改内容:*
 *   修改时间：*
 *  
 */
public class PublicInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //--------------------------------------------下面是GET请求添加请求头的方法---------------------------------------//
        //不懂的可以参考这篇文章http://blog.csdn.net/spinchao/article/details/52932145
        if (request.method().equals("GET")) {
            HttpUrl httpUrl = request
                    .url()
                    .newBuilder()
                    .addQueryParameter(Constants.PublicParameter.APPVERSIONNAME, Version.getVersion())
                    .addQueryParameter(Constants.PublicParameter.TIMESTAMP, String.valueOf(System.currentTimeMillis()))
                    .addQueryParameter(Constants.PublicParameter.APPTYPE, "android")
                    .addQueryParameter(Constants.PublicParameter.APPDEVICEID, DeviceUuidFactory.getDeviceId(NetAPP.getInstance()))
                    .build();

            request = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(httpUrl)
                    .build();
        } else if (request.method().equals("POST")) {/*POST请求添加请求参数*/
            if (request.body() instanceof FormBody) {
                Map<String, String> map = new HashMap<>();

                FormBody.Builder builder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();

                    /*把原来的参数添加到新的构建器*/
                for (int i = 0; i < formBody.size(); i++) {
                    builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    Log.e("done", "intercept: " + formBody.encodedName(i) + formBody.encodedValue(i));
                    map.put(formBody.encodedName(i), formBody.encodedValue(i));
                }

                map.put(Constants.PublicParameter.APPVERSIONNAME, Version.getVersion());
                map.put(Constants.PublicParameter.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
                map.put(Constants.PublicParameter.APPTYPE, "android");
                map.put(Constants.PublicParameter.APPDEVICEID, DeviceUuidFactory.getDeviceId(NetAPP.getInstance()));

                String sign = MapUtils.createEncryption(map);

                formBody = builder
                        .addEncoded(Constants.PublicParameter.APPVERSIONNAME, Version.getVersion())
                        .addEncoded(Constants.PublicParameter.TIMESTAMP, String.valueOf(System.currentTimeMillis()))
                        .addEncoded(Constants.PublicParameter.APPTYPE, "android")
                        .addEncoded(Constants.PublicParameter.APPDEVICEID, DeviceUuidFactory.getDeviceId(NetAPP.getInstance()))
                        .addEncoded(Constants.PublicParameter.APPSIGN, sign)
                        .build();

                for (int i = 0; i < formBody.size(); i++) {
                    builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    Log.e("done", "double: " + formBody.encodedName(i) + formBody.encodedValue(i));
                    map.put(formBody.encodedName(i), formBody.encodedValue(i));
                }

                request = request.newBuilder().post(formBody).build();
            }
        }


        return chain.proceed(request);
    }
}
