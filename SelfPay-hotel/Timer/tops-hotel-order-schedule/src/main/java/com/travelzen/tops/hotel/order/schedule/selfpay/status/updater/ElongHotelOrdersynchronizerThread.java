package com.travelzen.tops.hotel.order.schedule.selfpay.status.updater;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.hotel.selfpay.service.SelfpayHotelOrderStateSynchronizeService;

public class ElongHotelOrdersynchronizerThread implements Runnable {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private List<HotelOrderBo>  hotelOrderBos = null;
	
	private SelfpayHotelOrderStateSynchronizeService orderStateSynchronizeService = null;
	
	//控制主线程再所有订单没有同布完成后，阻塞
	private CountDownLatch countDownLatch = null;

	private long threadSleepMillisecond;
	
	public ElongHotelOrdersynchronizerThread(List<HotelOrderBo>  hotelOrderBos,
				CountDownLatch countDownLatch,
				SelfpayHotelOrderStateSynchronizeService orderStateSynchronizeService,
				long threadSleepMillisecond){
		this.hotelOrderBos = hotelOrderBos;
		this.countDownLatch = countDownLatch;
		this.orderStateSynchronizeService = orderStateSynchronizeService;
		this.threadSleepMillisecond = threadSleepMillisecond;
	}
	
	@Override
	public void run() {
		if(hotelOrderBos == null || hotelOrderBos.size() <= 0){
			return;
		}
		for(HotelOrderBo hotelOrderBo:hotelOrderBos){
			try {
				orderStateSynchronizeService.synchronizeOrderState(hotelOrderBo);
				Thread.sleep(threadSleepMillisecond);
			} catch (Exception e) {
				LOG.error(e.getMessage(),e);
			}
		}
		countDownLatch.countDown();
	}

}
