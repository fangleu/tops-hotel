package com.travelzen.tops.hotel.order.schedule.selfpay.status.updater;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.formbean.PageBean;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderState;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.order.core.hotel.bo.common.HotelOrderQueryCriteria;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.hotel.common.service.query.IHotelOrderQueryIdService;
import com.travelzen.tops.order.hotel.creme.service.query.ICremeHotelOrderQueryService;
import com.travelzen.tops.order.hotel.selfpay.service.SelfpayHotelOrderStateSynchronizeService;
@Component("hotelSelfpayStatusScheduler")
public class HotelSelfpayStatusScheduler implements InitializingBean {
	
	private static Logger LOG = LoggerFactory.getLogger(HotelSelfpayStatusScheduler.class);
	
	private static String CONFIG_FILE = "properties/tops-hotel-order-schedule-G.properties";

	@Resource(name="hotel_order_hotelOrderQueryIdService")
	private IHotelOrderQueryIdService hotelOrderQueryIdService;
	@Resource(name="hotel_order_cremeQueryService")
	private ICremeHotelOrderQueryService  cremeHotelOrderQueryService;
	@Resource(name="selfpayHotelOrderStateSynchronizeService")
	private SelfpayHotelOrderStateSynchronizeService orderStateSynchronizeService;

	//是否为测试环境标记
	private String envTestFlag = null;
	//订单同步线程数
	private String orderSynchronizationThreadNumber = null;
	//线程池
	private static ExecutorService executorService = Executors.newFixedThreadPool(25);
	//查询elong订单的线程睡眠时间，单位是毫秒
	private long threadSleepMillisecond;
	
	/**
	 * 更新elong酒店订单的状态，此接口负责将本地系统的订单状态与elong系统的订单状态进行同步
	 * @author muyuansun
	 * @date 2014-10-28 下午2:08:45
	 */
	@Scheduled(cron = "${elong.selfpay.hotel.order.status.updater.cron.expression}")
	public void hotelSelfpayStatusUpdater() {
		try {
			long startTime = System.currentTimeMillis();
			HotelOrderQueryCriteria queryCriteria = new HotelOrderQueryCriteria();
			//订单类型
			List<String> orderTypes = new ArrayList<>();
			orderTypes.add(OrderType.SELFPAY_NORMAL_HOTEL.name());
			queryCriteria.setOrderTypes(orderTypes);
			//订单状态
			List<String> orderStates = new ArrayList<>();
			orderStates.add(HotelOrderState.created.name()); //已创建
			orderStates.add(HotelOrderState.waiting_confirm.name()); //确认中
			orderStates.add(HotelOrderState.confirm_complete.name()); //已确认
			orderStates.add(HotelOrderState.not_checkIn.name());//未入住
			orderStates.add(HotelOrderState.checkIn_complete.name());//已入住
			queryCriteria.setOrderStates(orderStates);
			//页码。从1开始
			queryCriteria.pageIndex = 1;
			//每页的行数
			queryCriteria.pageNum = 10000;
			PageBean<String> pageBeanResult = hotelOrderQueryIdService.searchOrders(queryCriteria);
			if(pageBeanResult == null){
				return;
			}
			List<String> hotelOrderIds = pageBeanResult.getData();
			if(hotelOrderIds == null || hotelOrderIds.size() <= 0){
				LOG.info("[没有获得需要进行和Elong系统同步的订单信息，无需同步]");
				return;
			}
			List<HotelOrderBo> hotelOrderBos = cremeHotelOrderQueryService.getByHotelOrderIds(hotelOrderIds, HotelOrderTable.all);
			LOG.info("[获得需要和ELONG同步状态的订单ID数量 = {}][获得需要和ELONG同步状态的订单详情数量 = {}]",hotelOrderIds.size(),hotelOrderBos.size());
			if(LOG.isDebugEnabled()){
				for(HotelOrderBo hotelOrderBo:hotelOrderBos){
					if(hotelOrderBo.getOrderInterfaceNo() == null || hotelOrderBo.getOrderInterfaceNo().length() <= 0){
						continue;
					}
					LOG.info("[本地系统订单号 = {}][elong订单号 = {}][订单类型 = {}][订单状态 = {}][elong订单状态 = {}]",hotelOrderBo.getId(),hotelOrderBo.getOrderInterfaceNo(),
							hotelOrderBo.getOrderType(),hotelOrderBo.getState(), hotelOrderBo.getOrderInterfaceState());
				}
			}
			/*
			 * 订单状态同步
			 */
			elongHotelOrdersynchronizer(hotelOrderBos);
			long endTime = System.currentTimeMillis();
			LOG.info("[同步订单状态完成][耗时 = {} 毫秒]",endTime - startTime);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 平台Elong订单状态同步器
	 * @author muyuansun
	 * @date 2014-11-18 上午11:48:48
	 * @param hotelOrderBos
	 */
	public void elongHotelOrdersynchronizer(List<HotelOrderBo> hotelOrderBos){
		List<List<HotelOrderBo>> blockInfo = null;
		try {
			if(orderSynchronizationThreadNumber != null && orderSynchronizationThreadNumber.trim().length() > 0){
				blockInfo = divideSegmentByThreadNumber(hotelOrderBos,Integer.valueOf(orderSynchronizationThreadNumber));
			}else{
				blockInfo = divideSegmentByThreadNumber(hotelOrderBos,20);
			}
			if(blockInfo == null || blockInfo.size() <= 0){
				return;
			}
			LOG.info("[批量更新拆分的块大小 = {}]",blockInfo.size());
			CountDownLatch countDownLatch = new CountDownLatch(blockInfo.size());
			for(List<HotelOrderBo> block:blockInfo){
				executorService.execute(new ElongHotelOrdersynchronizerThread(block,countDownLatch,orderStateSynchronizeService,threadSleepMillisecond));
			}
			countDownLatch.await();
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}
	
	/**
	 * 按线程数将订单信息按块拆分，为每个线程执行与Elong订单同步的
	 * @author muyuansun
	 * @date 2014-10-31 上午11:27:11
	 * @param hotelOrderBos
	 * @param threadNumber
	 * @return
	 */
	private  List<List<HotelOrderBo>> divideSegmentByThreadNumber(List<HotelOrderBo> hotelOrderBos,int threadNumber){
		List<List<HotelOrderBo>> result = null;
		if(hotelOrderBos == null || hotelOrderBos.size() <= 0){
			return result;
		}
		result =  new ArrayList<>();
		
		if(threadNumber < 1){
			threadNumber = 1;
		}
		if(threadNumber > 20){
			threadNumber = 20;
		}
		if(envTestFlag != null && envTestFlag.trim().length() > 0 && envTestFlag.equals("true")){
			//测试环境Elong接口访问不能太频繁，订单同步状态线程强制设置为1
			threadNumber = 1;
		}
		LOG.info("[指定执行线程数参数值 = {}]",threadNumber);
		//如果小于等于线程数，线程数修改为和要处理的订单数量一致，每个订单一个线程
		if(hotelOrderBos.size() <= threadNumber){
			LOG.info("[当前订单数小于线程数，将订单个数设置为线程个数，每个线程一个订单][订单数 = {}][线程数 = {}]",hotelOrderBos.size(),threadNumber);
			threadNumber = hotelOrderBos.size();
		}
		int blockSize;
		//被除数
		BigDecimal dividend = new BigDecimal(hotelOrderBos.size());
		//除数
		BigDecimal divisor = new BigDecimal(threadNumber);
		//块大小
		blockSize = dividend.divide(divisor,10,BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP).intValue();
		//块信息
		List<HotelOrderBo> blockInfo = new ArrayList<>();
		//设值
		for(HotelOrderBo hotelOrderBo:hotelOrderBos){
			blockInfo.add(hotelOrderBo);
			if(blockInfo.size() >= blockSize){
				result.add(blockInfo);
				blockInfo = new ArrayList<>();
			}
		}
		if(blockInfo != null && blockInfo.size() > 0 ){
			result.add(blockInfo);
		}
		return result;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		envTestFlag = TopsConfReader.getConfContent(CONFIG_FILE, "env.test.flag", ConfScope.G);
		threadSleepMillisecond = (long) TopsConfReader.getConfContentForLong(CONFIG_FILE, "elong.selfpay.hotel.order.synchronization.thread.sleepMillisecond", ConfScope.G);
		if (threadSleepMillisecond < 0) {
			threadSleepMillisecond = 10000;
		}
		orderSynchronizationThreadNumber = TopsConfReader.getConfContent(CONFIG_FILE, "elong.selfpay.hotel.order.synchronization.thread.number", ConfScope.G);
		LOG.info("[测试环境标识 = {}][Elong 订单状态同步线程数 = {}][threadSleepMillisecond = {}]",envTestFlag,orderSynchronizationThreadNumber,threadSleepMillisecond);
	}
	
}
