package com.travelzen.tops.hotel.elong.common.constants;

public class CommonConstants {
	
	/**
	 * 艺龙接口相关配置参数的key
	 * @author muyuansun
	 * @date 2013-12-31 下午5:38:56
	 */
	public enum ElongConfigurationKey{
		ELONG_API_URI("elong.api.uri"),
		ELONG_API_SSL_URI("elong.api.ssl.uri"),
		APP_KEY("app.key"),
		SECRET_KEY("secret.key"),
		USER("user"),
		ACCESS_ELONG_ENABLE_GZIP("access.elong.enable.gzip"),
		ELONG_DATA_LOCAL_CACHE("elong.data.local.cache"),
		HOTELLIST_ELONG_DOWNLOAD_URL("hotellist.elong.download.url"),
		HOTELDETAIL_ELONG_DOWNLOAD_URL("hoteldetail.elong.download.url"),
		HOTELDETAIL_DOWNLOAD_THREAD_NUM("hoteldetail.download.thread.num"),
		HOTELDETAIL_DOWNLOAD_TIMEOUT("hoteldetail.download.timeout"),
		HOTEL_GEO_CN_DOWNLOAD_URL("hotel.geo.cn.download.url"),
		HOTEL_AMENITY_DOWNLOAD_URL("hotel.amenity.download.url"),
		CREDIT_CARD_DOWNLOAD_URL("credit.card.download.url"),
		DOWNLOAD_HOTEL_DETAIL_TRIGGER("download.hotel.detail.trigger"),
		DOWNLOAD_HOTEL_GEO_CN_TRIGGER("download.hotel.geo.cn.trigger"),
		CANCEL_ELONG_HOTEL_ORDER_TRIGGER("cancel.elong.hotel.order.trigger"),
		HOTEL_BATCH_THRESHOLD("hotel.batch.threshold"),
		ENCAPSULATION_ELONG_SERVER_URL("encapsulation.elong.server.url"),
		HOTCITY_ELONG_URL("hotcity.elong.url"),
		CITYSUGGEST_ELONG_KEYWORDS_URL("citysuggest.elong.keywords.url"),
		CITYSUGGEST_ELONG_JSONRESULT_URL("citysuggest.elong.jsonresult.url");

		private String name;
		private ElongConfigurationKey(String name){
			this.name = name;
		}
		public String keyName(){
			return name;
		}
	}
	
	/**
	 * 配置文件的路径
	 * @author muyuansun
	 * @date 2013-12-31 下午6:43:40
	 */
	public enum ConfigurationPath{
		ELONG_CONFIGURATION_PATH("properties/elong_connector_config.xml"),
		ELONG_TASK_TRIGGER_PATH("config/task-trigger.xml");
		private String path;
		private ConfigurationPath(String path){
			this.path = path;
		}
		public String path(){
			return path;
		}
	}
	
	/**
	 * 艺龙接口请求参数名称
	 * @author muyuansun
	 * @date 2013-12-31 下午7:31:52
	 */
	public enum ElongRequestParameter{
		USER("user"),
		METHOD("method"),
		TIMESTAMP("timestamp"),
		FORMAT("format"),
		DATA("data"),
		SIGNATURE("signature");
		private String paramName;
		private ElongRequestParameter(String paramName){
			this.paramName = paramName;
		}
		public String paramName(){
			return paramName;
		}
	}
	
	/**
	 * elong 酒店更新类型
	 * @author muyuansun
	 * @date 2014-1-11 下午9:27:10
	 */
	public enum HotelUpdateType{
		ADD("A"),
		DELETE("D"),
		UPDATE("U");
		private String updateType;
		private HotelUpdateType(String updateType){
			this.updateType = updateType;
		}
		public String type(){
			return updateType;
		}
	}
	
	/**
	 * 国际酒店静态文件解析
	 * @author muyuansun
	 * @date 2014-1-21 上午11:14:35
	 */
	public enum InternationalHotelField{
		EAN_HOTEL_ID("EAN_HOTEL_ID"),
		NAME("NAME"),
		COUNTRY("COUNTRY"),
		STAR_RATING("STAR_RATING");
		private String filed;
		private InternationalHotelField(String filed){
			this.filed = filed;
		}
		public String filed(){
			return filed;
		}
	}
	

}
