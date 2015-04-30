package com.travelzen.tops.hotel.elong.common.exception;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.core.common.ReturnCode;

/**
 * service统一转换抛出此异常
 * 非运行时异常，要求控制层必须处理
 * @author zhengzhichao
 *
 */
public class CommonException extends Exception {

    private static final long serialVersionUID = 430305575267731710L;
    private String retMsg = "";
    private ReturnCode retCode;
    private Object[] objects;
    
	public static CommonException instance(String retMsg) {
		return new CommonException(ReturnCode.ERROR,retMsg,null,new Object[0]);
	}
	
	public static CommonException instance(String retMsg,Throwable thr) {
		return new CommonException(ReturnCode.ERROR,retMsg,thr,new Object[0]);
	}
	
    public static CommonException instance(ReturnCode retCode, String retMsg) {
        return new CommonException(retCode,retMsg,null,new Object[0]);
    }

    public static CommonException instance(ReturnCode retCode, String retMsg, Throwable thr) {
        return new CommonException(retCode, retMsg, thr, new Object[0]);
    }

    public CommonException(ReturnCode retCode, String retMsg, Throwable thr, Object... objects) {
        super(String.format("[retCode=%s,retMsg=%s]", retCode.getErrorCode(), retMsg));
        this.retCode = retCode;
        this.objects = objects;
        this.retMsg = retMsg;
        this.initCause(thr);
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public ReturnCode getRetCode() {
        return retCode;
    }

    @Override
    public String toString() {
        return String.format("[retCode=%s,retMsg=%s]", retCode.getErrorCode(), retMsg);
    }

    @Override
    public String getMessage() {
        return String.format("[retCode=%s,retMsg=%s, objects=%s]", this.retCode, this.retMsg, Arrays.deepToString(objects));
    }

    public String getMessage(String format, String separator) {
        return String.format(format, StringUtils.join(objects, separator));
    }

    public String getMessage(String format) {
        return String.format(format, objects);
    }

}
