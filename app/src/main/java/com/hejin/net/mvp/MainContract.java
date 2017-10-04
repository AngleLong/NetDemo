package com.hejin.net.mvp;

/**
 * 作者: *贺金龙
 * 创建时间: *  2017/9/29 18:17
 * 类描述: * 主页面的契约类
 * 修改人: *
 * 修改内容: *
 * 修改时间: *
 * 类说明: *
 *  
 */
public class MainContract {
    public interface IMainView {

    }

    public interface IMainPresenter {

        /*这里就是随便写了一个网络请求的方法*/
        void requestNet();
    }
}
