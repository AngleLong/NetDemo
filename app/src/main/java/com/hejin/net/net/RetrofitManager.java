package com.hejin.net.net;

import com.hejin.net.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：*  贺金龙
 * 创建时间：*  2017/9/28 21:12
 * 类描述：* 这个类是进行网络请求的管理类，用来管理所有网络请求的
 * 修改人：*
 * 修改内容:*
 * 修改时间：*
 * 类说明：这里面不会存在单例了，都是静态的方法，所以直接调用就可以了
 * 这里提供的只有Retrofit的API接口方法，用这个调用网络请求，
 * 如果有多个API的情况只要增加相应的方法就可以了
 */
public class RetrofitManager {
    private static int mReadTime = 10;/*读取超时*/
    private static int mWriteTime = 10;/*写入超时*/
    private static int mConnectTime = 10;/*连接超时*/

    private static Retrofit createRetrofit() {
         /*构建Retrofit对象*/
        /*支持以实体类返回*/
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.UrlPath.BaseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())/*支持以实体类返回*/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit;
    }

    /*创建OkHttpClient的实例*/
    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .readTimeout(mReadTime, TimeUnit.SECONDS)
                .writeTimeout(mWriteTime, TimeUnit.SECONDS)
//                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new PublicInterceptor())/*添加公共请求参数*/
                .connectTimeout(mConnectTime, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)/*失败重连*/
                .connectionPool(new ConnectionPool())
//                .cache()/*增加缓存对的操作*/
                .build();
    }

    /*这里就很好的把SystemApiService的耦合和OkHttp3解决了,这里可以提供多个ApiService*/
    public static ApiServer provideApiService() {
         /*这里如果要是这么封装的话，必须要把所有的接口都卸载这个ApiServer里面了*/
        return createRetrofit().create(ApiServer.class);
    }

}
