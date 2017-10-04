package com.hejin.net.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者: *贺金龙
 * 创建时间: *  2017/9/29 17:39
 * 类描述: * 添加对话框的回调请求
 * 修改人: *
 * 修改内容: *
 * 修改时间: *
 * 类说明: *这个里面判断的请求的状况，这个还需要完善的，这里面的具体逻辑就是
 * 当你请求成功的时候正常走一个回调，当你不成功的时候这里不成功最好是使用Toast
 * 这样就不需要上下文了但是这个还是要根据具体情况具体去分析这个问题
 */
public abstract class BaseObservable<T> implements Observer<BaseBean<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseBean<T> response) {
        String code = response.getCode();
        /*截取最后两位*/
        if (response.getStatus().equals("")) {   /*成功*/
            /*这里返回正常的值*/
            onDataSuccess(response.getData());
        } else {/*失败,这里和服务器约定好了，如果是>80的情况是跳转页面，具体之后再定，<80的情况就是弹出提示*/
            if (code == null) {
                return;
            }
            String lastTwo = code.substring(code.length() - 2);
            onDataErrorCode(code);
            if (Integer.valueOf(lastTwo) > 80) {

            } else {
                onDataError(response.getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onNetError(e);
    }

    @Override
    public void onComplete() {
    }

    public abstract void onDataSuccess(T t);


    public abstract void onDataError(String s);

    /**
     * 请求错误时候的错误信息
     * 这个可以不去实现的
     *
     * @param e 错误信息
     */
    public void onNetError(Throwable e) {
    }

    /**
     * 这个是请求成功，但是服务器返回错误时候的错误信息
     * 这个可以不去实现的
     *
     * @param code 服务器返回的错误编码
     */
    public void onDataErrorCode(String code) {
    }
}
