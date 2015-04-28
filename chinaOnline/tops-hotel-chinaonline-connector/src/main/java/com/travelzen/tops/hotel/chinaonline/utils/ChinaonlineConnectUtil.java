package com.travelzen.tops.hotel.chinaonline.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.tempuri.AvailabilityServiceSoap;
import org.tempuri.ReservationServiceSoap;

public class ChinaonlineConnectUtil {

	private static AvailabilityServiceSoap availabilityServiceSoap = null;
	private static ReservationServiceSoap reservationServiceSoap = null;
	
	public static AvailabilityServiceSoap getChinaonlineAvailabilityService() {
		if (availabilityServiceSoap == null) {
			JaxWsProxyFactoryBean soapFactoryBean = new JaxWsProxyFactoryBean();
			soapFactoryBean.setServiceClass(AvailabilityServiceSoap.class);
			soapFactoryBean.setAddress(HotelChinaOnlineConfigUtil.QUERY_URL);
			availabilityServiceSoap = (AvailabilityServiceSoap) soapFactoryBean.create();
			Client clientProxy = ClientProxy.getClient(availabilityServiceSoap);
			clientProxy.getOutInterceptors().add(new LoggingOutInterceptor());
			clientProxy.getInInterceptors().add(new LoggingInInterceptor());
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			/* 设置客户端的超时时间 */
			httpClientPolicy.setConnectionTimeout(60000);
			httpClientPolicy.setReceiveTimeout(60000);
			httpClientPolicy.setAllowChunking(false);
			http.setClient(httpClientPolicy);
		}
		return availabilityServiceSoap;
	}
	
	public static ReservationServiceSoap getChinaonlineReservationService () {
		if (reservationServiceSoap == null) {
			JaxWsProxyFactoryBean soapFactoryBean = new JaxWsProxyFactoryBean();
			soapFactoryBean.setServiceClass(ReservationServiceSoap.class);
			soapFactoryBean.setAddress(HotelChinaOnlineConfigUtil.ORDER_URL);
			reservationServiceSoap = (ReservationServiceSoap) soapFactoryBean.create();
			Client clientProxy = ClientProxy.getClient(reservationServiceSoap);
			clientProxy.getOutInterceptors().add(new LoggingOutInterceptor());
			clientProxy.getInInterceptors().add(new LoggingInInterceptor());
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			/* 设置客户端的超时时间 */
			httpClientPolicy.setConnectionTimeout(60000);
			httpClientPolicy.setReceiveTimeout(60000);
			httpClientPolicy.setAllowChunking(false);
			http.setClient(httpClientPolicy);
		}
		return reservationServiceSoap;
	}
	
}
