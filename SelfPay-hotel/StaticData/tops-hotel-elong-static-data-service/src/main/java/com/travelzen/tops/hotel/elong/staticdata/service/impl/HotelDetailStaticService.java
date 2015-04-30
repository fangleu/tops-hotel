package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelDetailStaticService;

@Service("elong_hotelDetial_static_service")
public class HotelDetailStaticService implements IHotelDetailStaticService {

    @Resource(name = "hotelDao")
    private IHotelDao hotelDao;

    @Override
    public Hotel getElongHotelByHotelId(String hotelId) {
        if (ValidationUtil.isNotNull(hotelId)) {
            List<Hotel> hotels = hotelDao.findHotelById(hotelId);
            if (ValidationUtil.isNotNull(hotels)) {
                return hotels.get(0);
            }
        }
        return null;
    }

}
