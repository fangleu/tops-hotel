package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RateAmounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class RateAmounts {
	
	@XmlElement(name = "RateAmount")
	 private List<RateAmount> rateAmountList;
	
	public List<RateAmount> getRateAmountList() {
		return rateAmountList;
	}

	public void setRateAmountList(List<RateAmount> rateAmountList) {
		this.rateAmountList = rateAmountList;
	}



	
}
