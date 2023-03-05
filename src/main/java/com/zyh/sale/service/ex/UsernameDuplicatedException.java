package com.zyh.sale.service.ex;

/**
 * @author zhangyiheng
 * @create 2023-01-27-22:06
 */
//用户名被占用异常
public class UsernameDuplicatedException extends ServiceException{
    //alt+ insert 重写异常

    public UsernameDuplicatedException() {
        super();
    }

    public UsernameDuplicatedException(String message) {
        super(message);
    }

    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
