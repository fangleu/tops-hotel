package com.car.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 企业号给关注的用户主动发送推送消息
*/

public class SendWeChatMessage {
	
	Logger log = Logger.getLogger(SendWeChatMessage.class);
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	/**
	* 发送任意类型消息
	*
	* @param msgType
	* text|image|voice|video|file|news
	* @param touser
	* 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，
	* 则向关注该企业应用的全部成员发送
	* @param toparty
	* 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	* @param totag
	* 标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数
	* @param content
	* msgType=text时 ,文本消息内容
	* @param mediaId
	* msgType=image|voice|video时 ,对应消息信息ID（--------）
	* @param title
	* msgType=news|video时，消息标题
	* @param description
	* msgType=news|video时，消息描述
	* @param url
	* msgType=news时，消息链接
	* @param picurl
	* msgType=news时，图片路径
	* @param safe
	* 表示是否是保密消息，0表示否，1表示是，默认0
	*/
	public void sendWeChatMsg(String msgType, String touser, String toparty, String totag, String content, 
			String mediaId, String title, String description, String url, String picurl, String safe) {
	
		URL uRl;
		String ACCESS_TOKEN = refreshAccessToken.getAccessToken().getAccess_token();
		String action = Constants.CREATE_SESSION_URL + ACCESS_TOKEN;
		String json = buildJson(msgType , touser , toparty , totag , 
			 content , mediaId , title , description , url , picurl , safe);
		System.out.println("\njson " + json);
		try {
			uRl = new URL(action);
			HttpsURLConnection http = (HttpsURLConnection) uRl.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
			"application/json;charset=UTF-8");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//
			// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //
			// 读取超时30秒
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(json.getBytes("UTF-8"));// 传入参数
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			System.out.println("请求返回结果:" + result);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param 封装发送消息请求json
	 * @return
	 */
	private String buildJson(String msgType , String touser , String toparty , String totag , 
			String content , String mediaId , String title , String description , String url , String picurl , String safe){
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"touser\":" + "\"" + touser + "\",");
		sb.append("\"toparty\":" + "\"" + toparty + "\",");
		sb.append("\"totag\":" + "\"" + totag + "\",");
		if (msgType.equals("text")) {
			sb.append("\"msgtype\":" + "\"" + msgType + "\",");
			sb.append("\"text\":" + "{");
			sb.append("\"content\":" + "\"" + content + "\"");
			sb.append("}");
		} else if (msgType.equals("image")) {
			sb.append("\"msgtype\":" + "\"" + msgType + "\",");
			sb.append("\"image\":" + "{");
			sb.append("\"media_id\":" + "\"" + mediaId + "\"");
			sb.append("}");
		} else if (msgType.equals("voice")) {
			sb.append("\"msgtype\":" + "\"" + msgType + "\",");
			sb.append("\"voice\":" + "{");
			sb.append("\"media_id\":" + "\"" + mediaId + "\"");
			sb.append("}");
		} else if (msgType.equals("video")) {
			sb.append("\"msgtype\":" + "\"" + msgType + "\",");
			sb.append("\"video\":" + "{");
			sb.append("\"media_id\":" + "\"" + mediaId + "\",");
			sb.append("\"title\":" + "\"" + title + "\",");
			sb.append("\"description\":" + "\"" + description + "\"");
			sb.append("}");
		} else if (msgType.equals("file")) {
			sb.append("\"msgtype\":" + "\"" + msgType + "\",");
			sb.append("\"file\":" + "{");
			sb.append("\"media_id\":" + "\"" + mediaId + "\"");
			sb.append("}");
		} else if (msgType.equals("news")) {
			sb.append("\"msgtype\":" + "\"" + msgType + "\",");
			sb.append("\"news\":" + "{");
			sb.append("\"articles\":" + "[");
			sb.append("{");
			sb.append("\"title\":" + "\"" + title + "\",");
			sb.append("\"description\":" + "\"" + description + "\",");
			sb.append("\"url\":" + "\"" + url + "\",");
			sb.append("\"picurl\":" + "\"" + picurl + "\"");
			sb.append("}");
			sb.append("]");
			sb.append("}");
		}
		sb.append(",\"safe\":" + "\"" + safe + "\",");
		sb.append("\"agentid\":" + "\"" + toparty + "\"");
		sb.append("}");
		
		return sb.toString();
		
	}


}	
