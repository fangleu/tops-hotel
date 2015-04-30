package com.travelzen.tops.hotel.elong.staticdata.service;

import java.io.IOException;
import java.util.List;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;

public interface IHotelUpdateService {
	
	public void hotelDetailStaticFileUpdate() throws FileDownloadException, CommonException, InvaildParameterException, InterruptedException, IOException;
	
	public List<HotelIndex> findHotelListFromElong() throws CommonException, FileDownloadException, IOException;
	
	public boolean shutdown() throws CommonException;

}
