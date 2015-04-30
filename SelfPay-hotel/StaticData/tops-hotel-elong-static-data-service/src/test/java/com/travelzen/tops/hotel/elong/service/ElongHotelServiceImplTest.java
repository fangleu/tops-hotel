package com.travelzen.tops.hotel.elong.service;
//package com.travelzen.elong.newest.inter.service;
//
//import com.travelzen.elong.newest.inter.service.impl.ElongHotelServiceImpl;
//import com.travelzen.elong.newest.thrift.common.*;
//import com.travelzen.elong.newest.thrift.realtime.HotelDetailRequest;
//import com.travelzen.elong.newest.thrift.realtime.HotelDetailResponse;
//import com.travelzen.elong.newest.thrift.realtime.OrderCreateRequest;
//import com.travelzen.elong.newest.thrift.realtime.OrderCreateResponse;
//import org.apache.thrift.TException;
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.LocalDate;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
//import static junit.framework.Assert.assertNotNull;
//
///**
// * User: sutao
// * Date: 3/4/14
// * Time: 5:50 PM
// */
//
//
//@ContextConfiguration(locations = {
//        "classpath:spring/applicationContext-eni-service.xml",
//        "classpath:spring/applicationContext-eni-mongo.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class ElongHotelServiceImplTest {
//
//    @Resource
//    ElongHotelServiceImpl elongRealtimeService;
//
//    @Test
//    public void test() {
//        assertNotNull(elongRealtimeService);
//
//    }
//    private static final DateTimeZone SHANGHAI = DateTimeZone.forID("Asia/Shanghai");
//
//    @Test
//    public void test1() {
//        LocalDate newArrivalDate = LocalDate.now().plusDays(5);
//        DateTime datetime = newArrivalDate.toDateTimeAtMidnight(SHANGHAI);
//
//        System.out.println(datetime.plusHours(13).toString());
//    }
//
//    private static final String hotelId = "40101570";
//    private static final String roomTypeId = "1017";
//    private static final int ratePlanId = 17123;
//    private static final float totalPrice = 368.0f;
//
//    @Test
//    public void testOrderCreate() throws TException {
//        assertNotNull(elongRealtimeService);
//
//        LocalDate newArrivalDate = LocalDate.now().plusDays(5);
//        DateTime datetime = newArrivalDate.toDateTimeAtMidnight(SHANGHAI);
//
//        System.out.println(datetime.plusHours(13).toString());
//
//        OrderCreateRequest req = new OrderCreateRequest();
//        req.setAffiliateConfirmationId("acId" + System.currentTimeMillis()).setArrivalDate(newArrivalDate.toString())
//                .setDepartureDate(newArrivalDate.plusDays(1).toString())
//                .setLatestArrivalTime(datetime.plusHours(13).toString())
//                .setEarliestArrivalTime(datetime.plusHours(12).toString())
//                .setConfirmationType("NotAllowedConfirm")
//                .setCurrencyCode(CurrencyCode.RMB.name())
//                .setCustomerIpAddress("127.0.0.1")
//                .setCustomerType(CustomerType.All.name())
//                .setIsForceGuarantee(false)
//                .setIsGuaranteeOrCharged(false)
//                .setIsNeedInvoice(false)
//                .setNumberOfCustomers(1)
//                .setNumberOfRooms(1)
//                .setContact(
//                        new Contact().setGender(Gender.Female.name()).setMobile("8001517517").setName("McDonald")
//                ).setOrderRooms(
//                Arrays.asList(new OrderRoom().setCustomers(
//                        Arrays.asList(
//                                new Customer().setGender(Gender.Female.name()).setName("KFC").setNationality("正定")))
//                )
//        ).setPaymentType(PaymentType.SelfPay.name())
//                .setTotalPrice(totalPrice)
//                .setHotelId(hotelId).setRoomTypeId
//                (roomTypeId).setRatePlanId(ratePlanId);
//
//
//        OrderCreateResponse result = elongRealtimeService.createOrder(req);
//        System.out.println("[result]:" + result);
//        Assert.assertNotNull(result);
//        Assert.assertNotNull(result.getResult());
//    }
//
//    @Test
//    public void testHotelDetail() throws TException {
//        assertNotNull(elongRealtimeService);
//        HotelDetailRequest hotelDetailRequest = new HotelDetailRequest()
//                .setArrivalDate(LocalDate.now().plusDays(7).toString())
//                .setDepartureDate(LocalDate.now().plusDays(7).toString())
//                .setHotelIds("40101570")
//                .setRatePlanId(17123)
//                .setRoomTypeId("1020")
//                .setPaymentType(PaymentType.SelfPay.name());
//        HotelDetailResponse ret = elongRealtimeService.hotelDetail(hotelDetailRequest);
//
//        assertNotNull(ret);
//        System.out.println(ret);
//
//    }
//}
