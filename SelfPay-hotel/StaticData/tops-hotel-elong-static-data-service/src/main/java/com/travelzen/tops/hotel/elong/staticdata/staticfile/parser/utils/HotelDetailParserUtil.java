package com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.utils;

import org.apache.commons.lang3.StringUtils;
/**
 * 解析酒店详情XML文件需要用到的相关工具
 * @author muyuansun
 * @date 2014-1-11 下午3:19:41
 */
public class HotelDetailParserUtil {
	
    /**
     * 根据bedType组装页面显示的label信息
     * 例如
     * 		1 bedType = 大床,双床
     * 		  返回字符串将为 大/双床
     * 
     * 		2 bedType = 大床（200cm*200cm）
     * 		  返回字符串将为 大床
     * 
     * 		3 bedType = 双床
     * 		  返回字符串将为 双床
     * 
     * @author muyuansun
     * @date 2014-1-11 下午3:10:51
     * @param bedType
     * @return
     */
    /*public static String gatherRoomBedTypeLabel(String bedType){
    	String result = null;
    	if(bedType == null || bedType.length() <= 0){
    		return result;
    	}
    	StringBuffer sb = new StringBuffer();
    	// 2,3 情况处理
    	if(!StringUtils.contains(bedType, ",")){
    		if(bedType.contains("（")){//2
    			sb.append(StringUtils.substringBefore(bedType, "（").trim().charAt(0));
    			sb.append("床");
    			return sb.toString();
    		}else{//3
    			sb.append(String.valueOf(bedType.trim().charAt(0)));
    			sb.append("床");
    			return sb.toString();
    		}
    	}
    	// 1 情况处理
		String[] subStrArray = StringUtils.split(bedType, ",");
		if(subStrArray == null || subStrArray.length <= 0){
			return result;
		}
		LinkedHashSet<String> itemDuplicate = new LinkedHashSet<>();
		for(int i = 0;i < subStrArray.length;i++){
			String item = subStrArray[i];
			if(item.contains("（")){
				item = StringUtils.substringBefore(item, "（").trim();
			}
			itemDuplicate.add(String.valueOf(item.trim().charAt(0)));
			//检查是否是最后一个元素
		}
		if(itemDuplicate.size() <= 0){
			return result;
		}
		Object[] itemDuplicateArray = itemDuplicate.toArray();
		for(int i = 0;i < itemDuplicateArray.length;i++){
			if((i + 1) == itemDuplicateArray.length){
				sb.append(itemDuplicateArray[i]);
				sb.append("床");
			}else{
				sb.append(itemDuplicateArray[i]);
				sb.append("/");
			}
		}
    	return result = sb.toString().trim();
    }*/
	
	/**
	 * 根据bedType组装页面显示的label信息
	 * 此处不转换
	 * @param bedType
	 * @return
	 */
	public static String gatherRoomBedTypeLabel(String bedType){
		return bedType;
	}
    
    public static String[] gatherRoomIds(String roomIdStr){
    	String[] result = null;
    	if(roomIdStr == null || roomIdStr.length() <= 0){
    		return result;
    	}
    	if(!StringUtils.contains(roomIdStr, ",")){
    		result = new String[1];
    		result[0] = roomIdStr.trim(); 
    		return result;
    	}
    	String[] roomIdArray = StringUtils.split(roomIdStr,",");
    	if(roomIdArray == null || roomIdArray.length <= 0){
    		return result;
    	}
    	result = new String [roomIdArray.length];
    	for(int i = 0;i < roomIdArray.length;i++){
    		result[i] = roomIdArray[i].trim();
    	}
    	return result;
    }

}
