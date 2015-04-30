package com.travelzen.tops.elong.service.impl;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.travelzen.tops.elong.service.ISelfpayHotelConnectorService;
import com.travelzen.tops.elong.utils.ElongConnectorUtils;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.ElongConfigurationKey;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.BaseRequst;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CancelOrderCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CancelOrderResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreditCardInfo;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.DetailHotel;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumLocal;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelDetailCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelList;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelListCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.OrderDetailResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.OrderIdsCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.ValidateCreditCardCondition;

@Service(value="selfpayHotelConnectorService")
public class SelfpayHotelConnectorService implements ISelfpayHotelConnectorService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	//ELONG连接器URL
	private String elongConnectorServerUrl = null;
	//appKey
	private String appKey = null;
	//accessElongEnableGzip
	private String accessElongEnableGzip = null;
	
	@Override
	public CancelOrderResult elongHotelOrderCancel(CancelOrderCondition cancelOrderCondition) {
		CancelOrderResult result = null;
		if(cancelOrderCondition == null){
			throw new RuntimeException("取消订单详情请求参数 cancelOrderCondition 为 NULL，请检查。");
		}
		try {
			BaseRequst<CancelOrderCondition> baseRequst = new BaseRequst<CancelOrderCondition>();
			baseRequst.Request = cancelOrderCondition;
			baseRequst.Local = EnumLocal.zh_CN;
			baseRequst.Version = 1.1;
			String hotelOrderCancelData = ElongConnectorUtils.convertJsonObjectToJsonString(baseRequst);
			LOG.info("[取消订单ELONG请求参数 = {}]",hotelOrderCancelData);
			String format = "json";
			String method = "hotel.order.cancel";
			String hotelOrderCancelDataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(hotelOrderCancelData);
			String hotelOrderCancelDataRequestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, hotelOrderCancelDataUTF8String,elongConnectorServerUrl);
			LOG.info("[取消订单ELONG请求参数 = {}]",hotelOrderCancelDataRequestUrl);
			String jsonResult = null;
			if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelOrderCancelDataRequestUrl,true);
			}else{
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelOrderCancelDataRequestUrl,false);
			}
			if(jsonResult == null || jsonResult.length() <= 0){
				return result;
			}
			LOG.info("[取消订单ELONG想应结果 = {}]",jsonResult);
			result =  (CancelOrderResult) getResult(new CancelOrderResult(), jsonResult);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return result;
	}

	@Override
	public OrderDetailResult elongHotelOrderDetail(OrderIdsCondition orderIdsCondition) {
		OrderDetailResult result = null;
		if(orderIdsCondition == null){
			throw new RuntimeException("查看订单详情请求参数 orderIdsCondition 为 NULL，请检查。");
		}
		try {
			BaseRequst<OrderIdsCondition> baseRequst = new BaseRequst<OrderIdsCondition>();
			baseRequst.Request = orderIdsCondition;
			baseRequst.Local = EnumLocal.zh_CN;
			baseRequst.Version = 1.1;
			String hotelOrderDetailData = ElongConnectorUtils.convertJsonObjectToJsonString(baseRequst);
			LOG.info("[订单详情ELONG请求参数 = {}]",hotelOrderDetailData);
			String format = "json";
			String method = "hotel.order.detail";
			String hotelOrderDetailDataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(hotelOrderDetailData);
			String hotelOrderDetailRequestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, hotelOrderDetailDataUTF8String,elongConnectorServerUrl);
			LOG.info("[订单详情ELONG请求参数 = {}]",hotelOrderDetailRequestUrl);
			String jsonResult = null;
			if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelOrderDetailRequestUrl,true);
			}else{
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelOrderDetailRequestUrl,false);
			}
			if(jsonResult == null || jsonResult.length() <= 0){
				return result;
			}
			LOG.info("[订单详情ELONG想应结果 = {}]",jsonResult);
			result =  (OrderDetailResult) getResult(new OrderDetailResult(), jsonResult);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	@Override
	public HotelList elongHotelList(HotelListCondition hotelListCondition) {
		HotelList HotelList = null;
		
		return HotelList;
	}

	@Override
	public CreateOrderResult elongHotelOrderCreate(CreateOrderCondition createOrderCondition) {
		CreateOrderResult result = null;
		if(createOrderCondition == null){
			throw new RuntimeException("创建订单详情请求参数 createOrderCondition 为 NULL，请检查。");
		}
		try {
			if(createOrderCondition.isIsForceGuarantee()){
				String meString = ElongConnectorUtils.getTimestamp() + "#" + createOrderCondition.getCreditCard().getNumber();
				String cardNoDES =  ElongConnectorUtils.encryptDES(meString, appKey.substring(appKey.length() - 8, appKey.length()));
				createOrderCondition.getCreditCard().setNumber(cardNoDES);
			}
			BaseRequst<CreateOrderCondition> baseRequst = new BaseRequst<CreateOrderCondition>();
			baseRequst.Request = createOrderCondition;
			baseRequst.Local = EnumLocal.zh_CN;
			baseRequst.Version = 1.1;
			String hotelOrderCreateData = ElongConnectorUtils.convertJsonObjectToJsonString(baseRequst);
			LOG.info("[创建订单ELONG请求参数 = {}]",hotelOrderCreateData);
			String format = "json";
			String method = "hotel.order.create";
			String hotelOrderCreateDataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(hotelOrderCreateData);
			String hotelOrderCreateRequestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, hotelOrderCreateDataUTF8String,elongConnectorServerUrl);
			String jsonResult = null;
			if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelOrderCreateRequestUrl,true);
			}else{
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelOrderCreateRequestUrl,false);
			}
			if(jsonResult == null || jsonResult.length() <= 0){
				return result;
			}
			LOG.info("[创建订单ELONG想应结果 = {}]",jsonResult);
			result =  (CreateOrderResult) getResult(new CreateOrderResult(), jsonResult);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			if(e instanceof com.alibaba.fastjson.JSONException){
				return result;
			}
		}
		return result;
	}
	
	@Override
	public DetailHotel elongHotelDetail(HotelDetailCondition hotelDetailCondition) {
		
		DetailHotel result = null;
		if(hotelDetailCondition == null){
			throw new RuntimeException("酒店详情请求参数 hotelDetailCondition 为 NULL，请检查。");
		}
		try {
			BaseRequst<HotelDetailCondition> baseRequst = new BaseRequst<HotelDetailCondition>();
			baseRequst.Request = hotelDetailCondition;
			baseRequst.Local = EnumLocal.zh_CN;
			baseRequst.Version = 1.1;
			String hotelDetailData = ElongConnectorUtils.convertJsonObjectToJsonString(baseRequst);
			LOG.info("[酒店详情ELONG请求参数 = {}]",hotelDetailData);
			String format = "json";
			String method = "hotel.detail";
			String hotelDetailDataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(hotelDetailData);
			String hotelDetailRequestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, hotelDetailDataUTF8String,elongConnectorServerUrl);
			String jsonResult = null;
			if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelDetailRequestUrl,true);
			}else{
				jsonResult = ElongConnectorUtils.requestElongConnector(hotelDetailRequestUrl,false);
			}
			if(jsonResult == null || jsonResult.length() <= 0){
				return result;
			}
			LOG.info("[酒店详情ELONG相应结果 = {}]",jsonResult);
			result =  (DetailHotel) getResult(new DetailHotel(), jsonResult);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return result;
	}

	@Override
	public CreditCardInfo creditcardValidate(String number) {
		CreditCardInfo result = null;
		if(number == null){
			throw new RuntimeException("酒店详情请求参数 hotelDetailCondition 为 NULL，请检查。");
		}
		ValidateCreditCardCondition validateCreditCardCondition = new ValidateCreditCardCondition();
		validateCreditCardCondition.setCreditCardNo(number);
		try {
			String meString = ElongConnectorUtils.getTimestamp() + "#" + validateCreditCardCondition.getCreditCardNo();
			String cardNo = ElongConnectorUtils.encryptDES(meString, appKey.substring(appKey.length() - 8, appKey.length()));
			validateCreditCardCondition.setCreditCardNo(cardNo);
			BaseRequst<ValidateCreditCardCondition> baseRequst = new BaseRequst<ValidateCreditCardCondition>();
			baseRequst.Request = validateCreditCardCondition;
			baseRequst.Local = EnumLocal.zh_CN;
			baseRequst.Version = 1.1;
			String validateCreditCardData = ElongConnectorUtils.convertJsonObjectToJsonString(baseRequst);
			LOG.info("[酒店信用卡验证ELONG请求参数 = {}]",validateCreditCardData);
			String format = "json";
			String method = "common.creditcard.validate";
			String validateCreditCardDataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(validateCreditCardData);
			String validateCreditCardRequestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, validateCreditCardDataUTF8String,elongConnectorServerUrl);
			String jsonResult = null;
			if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
				jsonResult = ElongConnectorUtils.requestElongConnector(validateCreditCardRequestUrl,true);
			}else{
				jsonResult = ElongConnectorUtils.requestElongConnector(validateCreditCardRequestUrl,false);
			}
			if(jsonResult == null || jsonResult.length() <= 0){
				return result;}
			LOG.info("[酒店信用卡验证ELONG相应结果 = {}]",jsonResult);
			result = (CreditCardInfo) getResult(new CreditCardInfo(), jsonResult);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * @deprecated 
	 * 	use : protected Object getResult(Object obj, String jsonResult)
	 * @param jsonResult
	 * @return
	 */
	protected String getResultJsonString(String jsonResult){
		String result = null;
		if(jsonResult == null || jsonResult.length() <= 0){
			return result;
		}
		return StringUtils.substringAfter(StringUtils.substringBeforeLast(jsonResult, "}"), "\"Result\":");
	}
	
	/**
	 * 获取CreateOrderResult等Object对象
	 * @param obj
	 * @param jsonResult
	 * @return
	 */
	protected Object getResult(Object obj, String jsonResult){
		try{
			JSONObject jsonResultObj = new JSONObject(jsonResult);
			if(!jsonResultObj.isNull("Result")){
				String dataResult = jsonResultObj.getString("Result");
				obj = JSON.parseObject(dataResult, obj.getClass());
			}else if(!jsonResultObj.isNull("Code")){
				String errorCode = jsonResultObj.getString("Code");
				Method mothed = obj.getClass().getMethod("setText", String.class);
				mothed.invoke(obj, errorCode);
			}
		}catch(Exception e){
			LOG.error(e.getMessage(),e);
		}
		return obj;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		elongConnectorServerUrl =  elongConfiguration.get(ElongConfigurationKey.ENCAPSULATION_ELONG_SERVER_URL.keyName());
		appKey  =  elongConfiguration.get(ElongConfigurationKey.APP_KEY.keyName());
		accessElongEnableGzip = elongConfiguration.get(ElongConfigurationKey.ACCESS_ELONG_ENABLE_GZIP.keyName());
	}

}
