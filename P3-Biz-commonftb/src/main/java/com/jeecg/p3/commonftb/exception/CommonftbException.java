package com.jeecg.p3.commonftb.exception;

import org.jeecgframework.p3.core.common.exception.ActBizException;

/**
 * 保险异常类, 所有错误都封装为此错误, 以错误区分.
 *
 * @version $ v1.0 Sep 3, 2014 $
 */
public class CommonftbException extends ActBizException {
    private static final long serialVersionUID = 7787204828254391226L;

    /** Chinese Description */
    private String chineseMsg;

    public CommonftbException(Throwable cause, String defineCode, String errorMessage) {
        super(defineCode, errorMessage, cause);
    }

    public CommonftbException(Throwable cause, CommonftbExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg(), cause);
        this.chineseMsg = exp.getErrChineseMsg();
    }

    /**
     * Constructor.
     * @param defineCode error code
     */
    public CommonftbException(String defineCode) {
        super(defineCode);
    }

    public CommonftbException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public CommonftbException(CommonftbExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg());
        this.chineseMsg = exp.getErrChineseMsg();
    }

    public CommonftbException(CommonftbExceptionEnum exp, String errMsg) {
        super(exp.getErrCode(), errMsg);
    }

    public CommonftbException(CommonftbExceptionEnum exp, String errMsg, String chineseMsg) {
        super(exp.getErrCode(), errMsg);
        this.chineseMsg = chineseMsg;
    }

    public CommonftbException newInstance(String message, Object... args) {
        CommonftbException ex = new CommonftbException(this.defineCode);
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
