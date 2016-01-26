package com.jeecg.p3.shaketicket.exception;

import org.jeecgframework.p3.core.common.exception.ActBizException;

/**
 * 保险异常类, 所有错误都封装为此错误, 以错误区分.
 *
 * @version $ v1.0 Sep 3, 2014 $
 */
public class ShaketicketHomeException extends ActBizException {
    private static final long serialVersionUID = 7787204828254391226L;

    /** Chinese Description */
    private String chineseMsg;

    public ShaketicketHomeException(Throwable cause, String defineCode, String errorMessage) {
        super(defineCode, errorMessage, cause);
    }

    public ShaketicketHomeException(Throwable cause, ShaketicketHomeExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg(), cause);
        this.chineseMsg = exp.getErrChineseMsg();
    }

    /**
     * Constructor.
     * @param defineCode error code
     */
    public ShaketicketHomeException(String defineCode) {
        super(defineCode);
    }

    public ShaketicketHomeException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ShaketicketHomeException(ShaketicketHomeExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg());
        this.chineseMsg = exp.getErrChineseMsg();
    }

    public ShaketicketHomeException(ShaketicketHomeExceptionEnum exp, String errMsg) {
        super(exp.getErrCode(), errMsg);
    }

    public ShaketicketHomeException(ShaketicketHomeExceptionEnum exp, String errMsg, String chineseMsg) {
        super(exp.getErrCode(), errMsg);
        this.chineseMsg = chineseMsg;
    }

    public ShaketicketHomeException newInstance(String message, Object... args) {
        ShaketicketHomeException ex = new ShaketicketHomeException(this.defineCode);
        ex.setMessage(message, args);
        return ex;
    }

    public String getChineseMsg() {
        return getMessage();
    }

    public String getMessage() {
        if (null == chineseMsg) {
            return message;
        }
        return chineseMsg;
    }
}
