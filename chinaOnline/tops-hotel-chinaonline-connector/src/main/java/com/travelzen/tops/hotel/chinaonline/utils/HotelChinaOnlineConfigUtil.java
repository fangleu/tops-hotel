package com.travelzen.tops.hotel.chinaonline.utils;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;

public class HotelChinaOnlineConfigUtil {
	
	public static final String PROPERTIES_FILE_PATH = "tops-hotel/hotel-chinaonline-connector.properties";
	public static final String QUERY_URL = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "QueryUrl", ConfScope.R);
	public static final String ORDER_URL = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "OrderUrl", ConfScope.R);
	public static final String GUARANTEE_TYPE = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "GuaranteeType", ConfScope.R);
	public static final String ORIGIN_ENTITY_ID = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "Origin.EntityID", ConfScope.R);
	public static final String ORIGIN_SYSTEM_TYPE  = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "Origin.SystemType", ConfScope.R);
	public static final String DESTINATION_ENTITY_ID = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "Destination.EntityID", ConfScope.R);
	public static final String DESTINATION_SYSTEM_TYPE = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "Destination.SystemType", ConfScope.R);
	public static final String QUALIFYING_ID_TYPE = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "QualifyingIdType", ConfScope.R);
	public static final String QUALIFYING_ID_VALUE = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "QualifyingIdValue", ConfScope.R);
	public static final String CHINCODE = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "ChinCode", ConfScope.R);
	public static final String COMMENT_NAME_SPACE = TopsConfReader.getConfContent(PROPERTIES_FILE_PATH, "Commentnamespace", ConfScope.R);
	
}
