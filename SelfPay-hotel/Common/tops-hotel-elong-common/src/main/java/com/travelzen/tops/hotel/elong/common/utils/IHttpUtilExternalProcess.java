package com.travelzen.tops.hotel.elong.common.utils;

import java.io.InputStream;

/**
 * HttpUtilII外部处理接口
 * @author jianming.zhou
 *
 */
public interface IHttpUtilExternalProcess {

	/**
	 * 调用HttpUtil时的外部实现
	 * @param inputStream 
	 * @return
	 */
	public Object process(InputStream inputStream);
}
