package com.travelzen.tops.hotel.elong.staticdata.service;

import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;

public interface IHotelStaticFileDownloadService {
	
	public Object downloadHotelList() throws FileDownloadException;
	
	public Object downloadHotelDetail(String hotelId,String lang) throws FileDownloadException,InvaildParameterException;
	
	public Object downloadHotelGeo() throws FileDownloadException;
	
	public Object downloadCreditCard() throws FileDownloadException;
	
	public Object downloadAmenity() throws FileDownloadException;

}
