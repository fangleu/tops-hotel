package com.travelzen.tops.elong.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CancelOrderCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CancelOrderResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Contact;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderRoom;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreditCardInfo;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Customer;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.DetailHotel;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumConfirmationType;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumCurrencyCode;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumGender;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumGuestTypeCode;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumPaymentType;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Hotel;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelDetailCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.NightlyRate;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.OrderDetailResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.OrderIdsCondition;

@ContextConfiguration(locations={"classpath*:spring/applicationContext-hotel-elong-service.xml","classpath*:spring/applicationContext-hotel-elong-common.xml","classpath*:spring/applicationContext-hotel-elong-mongo.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SelfpayHotelConnectorServiceTest {
	
	private Logger LOG = LoggerFactory.getLogger(SelfpayHotelConnectorServiceTest.class);
	
	@Resource(name = "selfpayHotelConnectorService")
	private ISelfpayHotelConnectorService selfpayHotelConnectorService = null;
	
	static{
		LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(loggerContext);
        loggerContext.reset();
        try {
        	File file = new File("src/test/resources/tops-hotel-elong-service-logback.xml");
			if(file.exists()){
				configurator.doConfigure(file);
				StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
				System.out.println("******************* 【开发环境】从本地文件系统加载 logback-hotel-engine-server.xml 配置文件完成********************");
			}
       } catch (JoranException e) {
    	   e.printStackTrace();
       }
	}
	
	
	@Test
	public void testElongHotelOrderDetail(){
		Assert.assertNotNull(selfpayHotelConnectorService);
		OrderIdsCondition orderIdsCondition = new OrderIdsCondition();
		orderIdsCondition.setAffiliateConfirmationId("141113103524547");
		orderIdsCondition.setOrderId(116840175);
		OrderDetailResult orderDetailResult = selfpayHotelConnectorService.elongHotelOrderDetail(orderIdsCondition);
		Assert.assertNotNull(orderDetailResult);
		LOG.info("[艺龙订单号 = {}][艺龙订单状态 = {}]",orderDetailResult.getOrderId(),orderDetailResult.getStatus());
	}
	
	@Test
	public void testElongHotelDetail(){
		Assert.assertNotNull(selfpayHotelConnectorService);
		HotelDetailCondition hotelDetailCondition = new HotelDetailCondition();
		hotelDetailCondition.setArrivalDate("2014-11-11");
		hotelDetailCondition.setDepartureDate("2014-11-15");
		hotelDetailCondition.setHotelIds("50101002");
		hotelDetailCondition.setRoomTypeId("1018");
		hotelDetailCondition.setRatePlanId(64239);
		DetailHotel detailHotel = selfpayHotelConnectorService.elongHotelDetail(hotelDetailCondition);
		Assert.assertNotNull(detailHotel);
		List<Hotel> hotels = detailHotel.getHotels();
		Assert.assertNotNull(hotels);
		Assert.assertEquals(1, hotels.size());
		Assert.assertNotNull(detailHotel.getHotels().get(0).getRooms().get(0).getRatePlans().get(0).getNightlyRates());
		List<NightlyRate> nightlyRates = detailHotel.getHotels().get(0).getRooms().get(0).getRatePlans().get(0).getNightlyRates();
		for(NightlyRate nightlyRate:nightlyRates){
			LOG.info("{} = {}",nightlyRate.getDate().toString(),nightlyRate.getMember().toString());
		}
	}
	
	@Test
	public void testCreditcardValidate(){
		String number = "4033910000000000";
		CreditCardInfo creditCardInfo = selfpayHotelConnectorService.creditcardValidate(number);
		
		LOG.info("----->111{}  ----->222{}", creditCardInfo.isIsValid(), creditCardInfo.isIsNeedVerifyCode());
	}
	
	@Test
	public void testElongHotelOrderCreate(){
		Assert.assertNotNull(selfpayHotelConnectorService);
		CreateOrderCondition createOrderCondition = new CreateOrderCondition();
		
		//合作伙伴订单确认号
		createOrderCondition.setAffiliateConfirmationId("10987777123123");
		//酒店编号
		createOrderCondition.setHotelId("40101627");
		//房型编号
		createOrderCondition.setRoomTypeId("1018");
		//产品编号
		createOrderCondition.setRatePlanId(24957);
		//入住时间
		createOrderCondition.setArrivalDate("2014-11-11");
		//离店时间
		createOrderCondition.setDepartureDate("2014-11-17");
		//客人类型
		createOrderCondition.setCustomerType(EnumGuestTypeCode.Chinese);
		//付款类型
		createOrderCondition.setPaymentType(EnumPaymentType.SelfPay);
		//房间数量
		createOrderCondition.setNumberOfRooms(1);
		//客人数量
		createOrderCondition.setNumberOfCustomers(1);
		//最早到店时间
		createOrderCondition.setEarliestArrivalTime("2014-11-11 14:00");
		//最晚到店时间
		createOrderCondition.setLatestArrivalTime("2014-11-11 23:59");
		//货币类型
		createOrderCondition.setCurrencyCode(EnumCurrencyCode.RMB);
		//总价
		createOrderCondition.setTotalPrice(BigDecimal.valueOf(3228));
		//客人访问IP
		createOrderCondition.setCustomerIPAddress("211.151.230.212");
		//是否已担保或已付款
		createOrderCondition.setIsGuaranteeOrCharged(false);
		//确认类型,不需要elong直接发送短信给客人
		createOrderCondition.setConfirmationType(EnumConfirmationType.NotAllowedConfirm);
		/*
		 * 客人信息
		 */
		List<CreateOrderRoom> createOrderRooms = new ArrayList<>();
		CreateOrderRoom createOrderRoom = new CreateOrderRoom();
		List<Customer> customers = new ArrayList<>();
		Customer customer = new Customer();
		//姓名
		customer.setName("孙牧原");
		//性别
		customer.setGender(EnumGender.Maile);
		customers.add(customer);
		createOrderRoom.setCustomers(customers);
		createOrderRooms.add(createOrderRoom);
		createOrderCondition.setOrderRooms(createOrderRooms);
		/*
		 * 联系人
		 */
		Contact contact = new Contact();
		contact.setName("李大海");
		contact.setMobile("18612566611");
		createOrderCondition.setContact(contact);
		CreateOrderResult createOrderResult = selfpayHotelConnectorService.elongHotelOrderCreate(createOrderCondition);
		Assert.assertNotNull(createOrderResult);
		LOG.info("[Elong OrderId = {}]",createOrderResult.getOrderId());
		//[创建订单ELONG想应结果 = {"Code":"0","Result":{"OrderId":67765136,"CancelTime":"2014-11-12T00:00:00+08:00","GuaranteeAmount":0.0,"CurrencyCode":"RMB"}}] 
		//[Elong OrderId = 67765136] 
	}
	
	@Test
	public void testElongHotelOrderCancel(){
		Assert.assertNotNull(selfpayHotelConnectorService);
		CancelOrderCondition cancelOrderCondition = new CancelOrderCondition();
		cancelOrderCondition.setOrderId(116840175);
		cancelOrderCondition.setCancelCode("行程变更");
		CancelOrderResult cancelOrderResult = selfpayHotelConnectorService.elongHotelOrderCancel(cancelOrderCondition);
		Assert.assertNotNull(cancelOrderResult);
		System.out.println(cancelOrderResult.isSuccesss());
		LOG.info("[取消结果 = {}]",cancelOrderResult.isSuccesss());
	}

}
