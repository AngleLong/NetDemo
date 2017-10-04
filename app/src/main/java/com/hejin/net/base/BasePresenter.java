package com.hejin.net.base;

import com.hejin.net.net.BaseBean;
import com.hejin.net.net.BaseObservable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: *贺金龙
 * 创建时间: *  2017/9/29 17:27
 * 类描述: * 所有请求Model的基类
 * 修改人: *
 * 修改内容: *
 * 修改时间: *
 * 类说明: *
 *  
 */
public class BasePresenter<V> {

    public V mView;

    public void attachView(V mView) {
        this.mView = mView;
    }

    public void detachView() {
        mView = null;
    }

    /**
     * 这个方法写在基类中，确保每个基类都包含这个方法
     * 这个方法的作用就是关联上游数据和下游回调用的
     *
     * @param observable 上游的Observable，也就是Retrofit的Api直接返回的一个数据，请求数据
     * @param subscriber 下游的数据，也就是回调中的数据
     */
    public <T> void createSubscription(Observable<BaseBean<T>> observable, BaseObservable<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())/*这个就是取消订阅的相应的方法*/
                .subscribe(subscriber);
    }
}
