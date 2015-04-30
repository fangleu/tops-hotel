package com.travelzen.tops.hotel.elong.service;
//package com.travelzen.elong.newest.inter.service;
//
//import com.travelzen.elong.newest.inter.pojo.request.HotelDetailRequest;
//import com.travelzen.elong.newest.inter.pojo.response.HotelDetailResponse;
//import com.travelzen.elong.newest.inter.service.impl.HotelDetailDynamicService;
//import org.joda.time.LocalDate;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotNull;
//import static junit.framework.Assert.assertTrue;
//
///**
// * Created with IntelliJ IDEA.
// * User: sutao
// * Date: 2/25/14
// * Time: 2:02 PM
// * To change this template use File | Settings | File Templates.
// */
//@ContextConfiguration(locations = {
//        "classpath:spring/applicationContext-eni-service.xml",
//        "classpath:spring/applicationContext-eni-mongo.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class HotelDetailDynamicServiceTest {
//
//    @Autowired
//    HotelDetailDynamicService hotelDetailDynamicService;
//
//    @Test
//    public void test() {
//        HotelDetailRequest req = new HotelDetailRequest();
//        req.setArrivalDate(LocalDate.now().plusDays(7).toString());
//        req.setDepartureDate(LocalDate.now().plusDays(7).toString());
//        req.setHotelIds("40101570");
//        req.setRatePlanId(17123);
//        req.setRoomTypeId("1020");
//
//        HotelDetailResponse ret = hotelDetailDynamicService.hotelDetail(req);
//
//        assertNotNull(ret);
//        assertTrue(ret.isSuccess());
//        assertNotNull(ret.getResult());
//        assertEquals(1, ret.getResult().getCount());
//        assertNotNull(ret.getResult().getHotels());
//        assertEquals(1, ret.getResult().getHotels().size());
//    }
//}
