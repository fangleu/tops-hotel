package com.travelzen.tops.hotel.elong.staticfile.parser.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.utils.HotelDetailParserUtil;

/**
 * 测试解析酒店详情XML文件需要用到的相关工具
 * @author muyuansun
 * @date 2014-1-11 下午3:25:26
 */
public class HotelDetailParserUtilTest {
	
	@Test
	public void testGatherRoomBedTypeLabel(){
		Assert.assertEquals("大床",HotelDetailParserUtil.gatherRoomBedTypeLabel("大床（200cm*200cm）"));
		Assert.assertEquals("大/双床",HotelDetailParserUtil.gatherRoomBedTypeLabel("大床,双床"));
		Assert.assertEquals("双床",HotelDetailParserUtil.gatherRoomBedTypeLabel("双床"));
	}
	
	@Test
	public void testStringUtils(){
		Assert.assertEquals(true, StringUtils.contains("大床,双床", ","));
		Assert.assertEquals(false, StringUtils.contains("大床", ","));
	}
	
}
