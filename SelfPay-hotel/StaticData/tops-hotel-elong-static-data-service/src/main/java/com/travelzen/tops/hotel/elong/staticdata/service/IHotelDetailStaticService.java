package com.travelzen.tops.hotel.elong.staticdata.service;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;



public interface IHotelDetailStaticService {

    public Hotel getElongHotelByHotelId(String hotelId);
}
