package com.travelzen.tops.hotel.elong.service;
//package com.travelzen.elong.newest.inter.service;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.joda.time.DateTime;
//import org.joda.time.LocalDate;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.travelzen.elong.newest.inter.pojo.request.OrderCancelRequest;
//import com.travelzen.elong.newest.inter.pojo.request.OrderCreateRequest;
//import com.travelzen.elong.newest.inter.pojo.request.OrderDetailRequest;
//import com.travelzen.elong.newest.inter.pojo.request.OrderUpdateRequest;
//import com.travelzen.elong.newest.inter.pojo.response.OrderCancelResponse;
//import com.travelzen.elong.newest.inter.pojo.response.OrderCreateResponse;
//import com.travelzen.elong.newest.inter.pojo.response.OrderDetailResponse;
//import com.travelzen.elong.newest.inter.pojo.response.OrderUpdateResponse;
//import com.travelzen.elong.newest.inter.service.impl.HotelOrderService;
//
//@ContextConfiguration(locations = {
//        "classpath:spring/applicationContext-eni-service.xml",
//        "classpath:spring/applicationContext-eni-mongo.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
//public class HotelOrderServiceTest {
//    @Autowired
//    HotelOrderService hotelOrderService;
//
//    void getSelfPayNoGuarateeHotel() {
//
//    }
//
//    OrderCreateRequest getOrderCreateRequet() {
//        OrderCreateRequest request = loadJsonDataFromFile(
//                "/hotelOrder/hotelOrderCreate.json", OrderCreateRequest.class);
//        request.setAffiliateConfirmationId(
//                String.valueOf(System.currentTimeMillis()));
//        return request;
//    }
//
//    @Before
//    public void before() {
//        assertNotNull(hotelOrderService);
//    }
//
//    @Test
//    public void testOrderCreateInvalideParam() {
//        OrderCreateRequest req = getOrderCreateRequet();
//        req.setHotelId("123456789");
//        OrderCreateResponse result = hotelOrderService.createOrder(req);
//    }
//
//    /**
//     * 到店日期为当前时间之后5天，住1天，到店時間爲12～13
//     *
//     * @param req
//     * @return
//     */
//    private OrderCreateRequest setArrDepDate(OrderCreateRequest req) {
//        LocalDate newArrivalDate = LocalDate.now().plusDays(5);
//        req.setArrivalDate(newArrivalDate.toString());
//        req.setDepartureDate(newArrivalDate.plusDays(1).toString());
//        DateTime datetime = newArrivalDate.toDateTimeAtMidnight();
//        //当天12~13点到店
//        req.setEarliestArrivalTime(datetime.plusHours(12).toString());
//        req.setLatestArrivalTime(datetime.plusHours(13).toString());
//        return req;
//    }
//
//    @Test
//    public void testOrderCreate() {
//        OrderCreateRequest req = setArrDepDate(getOrderCreateRequet());
//        OrderCreateResponse result = hotelOrderService.createOrder(req);
//        System.out.println("[result]:" + result);
//        assertNotNull(result);
//        assertNotNull(result.getResult());
//    }
//
//    public long createOrder() {
//        assertNotNull(hotelOrderService);
//        OrderCreateResponse result = hotelOrderService
//                .createOrder(loadTestData());
//        System.out.println("[result]:" + result);
//        assertNotNull(result);
//        assertNotNull(result.getResult());
//        return result.getResult().getOrderId();
//    }
//
//    @Test
//    public void testOrderDetail() {
//        OrderDetailRequest req = loadJsonDataFromFile(
//                "/hotelOrder/hotelOrderDetail.json", OrderDetailRequest.class);
//        req.setOrderId(67762279);
//        OrderDetailResponse ret = hotelOrderService.orderDetail(req);
//        System.out.println(ret);
//        System.out.println(ret.getResult().getStatus());
//    }
//
//    @Test
//    public void testOrderCancel() {
//        OrderCancelRequest req = loadJsonDataFromFile(
//                "/hotelOrder/hotelOrderCancel.json", OrderCancelRequest.class);
//        OrderCancelResponse ret = hotelOrderService.cancelOrder(req);
//        System.out.println(ret);
//    }
//
//    @Test
//    public void testOrderUpdate() {
//        OrderUpdateRequest req = loadJsonDataFromFile(
//                "/hotelOrder/hotelOrderUpdate.json", OrderUpdateRequest.class);
//        OrderUpdateResponse ret = hotelOrderService.updateOrder(req);
//        System.out.println(ret);
//    }
//
//    <T> T loadJsonDataFromFile(String fileLocInClassPath, Class<T> clazz) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue(new InputStreamReader(getClass()
//                    .getResourceAsStream(fileLocInClassPath)), clazz);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    OrderCreateRequest loadTestData() {
//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//            OrderCreateRequest request = mapper.readValue(
//                    new InputStreamReader(getClass().getResourceAsStream(
//                            "/hotelOrder/hotelOrderCreate.json")),
//                    OrderCreateRequest.class);
//            return request;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
