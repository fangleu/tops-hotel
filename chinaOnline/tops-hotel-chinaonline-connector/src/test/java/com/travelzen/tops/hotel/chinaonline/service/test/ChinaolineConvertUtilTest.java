package com.travelzen.tops.hotel.chinaonline.service.test;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;

import com.travelzen.search.util.Constants;

public class ChinaolineConvertUtilTest {

	@Test
	public void test() throws BadHanyuPinyinOutputFormatCombination{
		
		String ss = "真旅网测试"; 
		
		String pinyinString = Constants.getPinyinAndSim(ss)[0].toUpperCase();
		
		System.out.println(pinyinString);
		
	}
	
}
