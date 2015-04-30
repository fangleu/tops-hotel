package com.travelzen.tops.elong.utils;

public class ParamUtils {
	
	/**
	 * 检测是否存在空的字符串
	 * TODO
	 * @author TangTianJiang
	 * @date 2014年1月4日 下午2:21:09
	 * @param params
	 * @return 存在空的值返回false 反之返回为true
	 */
	public static boolean checkStringArrayNotEmpty(String ... params){
		if (null == params || !(params.length>0)) {
			return false;
		}
		for (String param : params) {
			if (null == param || param.trim().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将字符拼接到StringBuffer
	 * @author TangTianJiang
	 * @date 2014年3月17日 下午4:11:28
	 * @param defaultStr 当参数为null时，默认的字符串，当设为null时，不拼接
	 * @param params
	 * @return
	 */
	public static String stringBufferAppendStrings(String defaultStr, String ... params){
		if( null == params){
			return defaultStr;
		}
		StringBuffer sb = new StringBuffer();
		for (String str : params) {
			if (null != str) {
				sb.append(str);
			}else{
				if (null != defaultStr) {
					sb.append(defaultStr);
				}
			}
		}
		return sb.toString();
	}

}
