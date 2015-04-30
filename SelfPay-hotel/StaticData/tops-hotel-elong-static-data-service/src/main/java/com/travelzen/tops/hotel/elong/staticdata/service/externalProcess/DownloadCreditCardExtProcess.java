package com.travelzen.tops.hotel.elong.staticdata.service.externalProcess;

import java.io.InputStream;

import com.travelzen.tops.hotel.elong.common.utils.IHttpUtilExternalProcess;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.CreditCardParser;

public class DownloadCreditCardExtProcess implements IHttpUtilExternalProcess {

	@Override
	public Object process(InputStream inputStream) {
		return new CreditCardParser(inputStream);
	}

}
