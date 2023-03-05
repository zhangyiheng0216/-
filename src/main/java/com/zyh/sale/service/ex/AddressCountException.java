package com.zyh.sale.service.ex;

/**
 * @author yujiangsheng
 * @create 2022-04-06-16:59
 */
// 收获地址总数超出限制（20条）
public class AddressCountException extends ServiceException{
    public AddressCountException() {
        super();
    }

    public AddressCountException(String message) {
        super(message);
    }

    public AddressCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountException(Throwable cause) {
        super(cause);
    }

    protected AddressCountException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
