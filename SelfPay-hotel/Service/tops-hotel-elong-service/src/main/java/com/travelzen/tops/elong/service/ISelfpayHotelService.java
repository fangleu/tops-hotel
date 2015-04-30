package com.travelzen.tops.elong.service;

import org.springframework.beans.factory.InitializingBean;

import com.travelzen.tops.elong.request.model.SelfPaySearchCriteria;
import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelDetailDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelListDTO;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;

public interface ISelfpayHotelService extends InitializingBean {
	/**
	 * TODO 查询酒店列表DTO
	 * @param request
	 * @param pageSize 页面条数
	 * @param pageIndex 第几页
	 * @return
	 */
	public HotelListDTO queryHotels(SelfPaySearchCriteria criteria, String pageSize, String pageIndex) throws CommonException;
	/**
	 * TODO 查询酒店地图数据
	 * @param hotelId 		酒店id
	 * @param checkInDate 	入住日期
	 * @param checkOutDate 	退房日期
	 * @return
	 */
	public HotelDetailDTO queryHotelDetail(String hotelId,String checkInDate ,String checkOutDate) throws Exception;
	/**
	 * TODO 查询城市交通信息
	 * @param cityId 		城市id
	 * @return
	 */
	public HotelGeo queryTrafficInfo(String cityId);
}
