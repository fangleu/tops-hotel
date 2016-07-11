package com.car.check.test;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

public class DateTest {
	
	@org.junit.Test
	public void Test(){
		
		DateTime startDate = new DateTime().minusDays(1).withTimeAtStartOfDay();
		DateTime endtDate = new DateTime();
		System.out.println(startDate + "\n" + endtDate);
		
	}

}
