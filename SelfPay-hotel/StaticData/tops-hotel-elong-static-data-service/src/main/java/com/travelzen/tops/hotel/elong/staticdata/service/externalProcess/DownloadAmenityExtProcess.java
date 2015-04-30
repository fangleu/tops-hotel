package com.travelzen.tops.hotel.elong.staticdata.service.externalProcess;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.hotel.elong.common.utils.IHttpUtilExternalProcess;
import com.travelzen.tops.hotel.elong.staticdata.service.utils.ExcelReader;

public class DownloadAmenityExtProcess implements IHttpUtilExternalProcess {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object process(InputStream inputStream) {
		try {
			return ExcelReader.getSheet(ExcelReader.createWb(inputStream, ExcelReader.EXCEL_XLSX),0);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

}
