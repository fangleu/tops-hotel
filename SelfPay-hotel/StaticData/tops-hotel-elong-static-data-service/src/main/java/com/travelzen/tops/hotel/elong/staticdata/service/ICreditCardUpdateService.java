package com.travelzen.tops.hotel.elong.staticdata.service;

import java.util.List;
import java.util.Map;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;

public interface ICreditCardUpdateService {
	
	public void creditCardUpdate() throws CommonException;
	
	public Map<String, String> findCreditCardAndName();
	
	public List<String> findCreditCardList(String lang);
}
