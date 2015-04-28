package com.travelzen.tops.hotel.chinaonline.bean.base;

import org.joda.time.DateTime;

public class Rates {

	private DateTime rateDate;
	
	private Double rate;


	public DateTime getRateDate() {
		return rateDate;
	}

	public void setRateDate(DateTime rateDate) {
		this.rateDate = rateDate;
	}
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	
}
