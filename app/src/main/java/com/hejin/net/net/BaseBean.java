package com.hejin.net.net;

/**
 *   作者：*  贺金龙
 *   创建时间：*  2017/9/29 15:41
 *   类描述：*
 *   修改人：*
 *   修改内容:*
 *   修改时间：*
 *  
 */
public class BaseBean<T> {
    /**
     * status : SUCCESS/FAIL
     * message : 请求成功或失败
     * code : null(只有在失败的情况下才有用)
     * data : 一个泛型的类型
     */

    private String status;
    private String message;
    private String code;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
