package com.travelzen.tops.hotel.elong.common.exception;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.core.common.ReturnCode;


/**
 * 无效参数异常
 * @author muyuansun
 * @date 2014-1-2 上午11:26:22
 */
public class InvaildParameterException extends Exception {
	
	private static final long serialVersionUID = -1334737693579177979L;
	
	private String retMsg = "";
    private ReturnCode retCode;
    private Object[] objects;
    
	public static InvaildParameterException instance(String retMsg) {
		return new InvaildParameterException(ReturnCode.ERROR,retMsg,null,new Object[0]);
	}
	
	public static InvaildParameterException instance(String retMsg,Throwable thr) {
		return new InvaildParameterException(ReturnCode.ERROR,retMsg,thr,new Object[0]);
	}
	
    public static InvaildParameterException instance(ReturnCode retCode, String retMsg) {
        return new InvaildParameterException(retCode,retMsg,null,new Object[0]);
    }

    public static InvaildParameterException instance(ReturnCode retCode, String retMsg, Throwable thr) {
        return new InvaildParameterException(retCode, retMsg, thr, new Object[0]);
    }

    public InvaildParameterException(ReturnCode retCode, String retMsg, Throwable thr, Object... objects) {
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
