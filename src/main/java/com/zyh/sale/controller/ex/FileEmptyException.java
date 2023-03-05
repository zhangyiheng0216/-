package com.zyh.sale.controller.ex;

/**
 * @author yujiangsheng
 * @create 2022-04-03-11:05
 */
//文件上传相关异常的基类
public class FileEmptyException extends FileUploadException {
    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    protected FileEmptyException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
