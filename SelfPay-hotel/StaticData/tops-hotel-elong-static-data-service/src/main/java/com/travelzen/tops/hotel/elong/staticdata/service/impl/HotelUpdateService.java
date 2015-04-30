package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.CriteriaContainerImpl;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants;
import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.common.utils.DateUtil;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelIndexDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IUpdateHotelDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;
import com.travelzen.tops.hotel.elong.mongo.entity.statistic.UpdateHotel;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelUpdateService;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.HotelDetailParser;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.HotelListParser;

@Service(value="hotelUpdateService")
public class HotelUpdateService implements IHotelUpdateService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private Logger UPDATE_HOTEL_LOG = LoggerFactory.getLogger("UPDATE_HOTEL_LOG"); 
	
	private ExecutorService pool = Executors.newFixedThreadPool(100);
	
	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	@Resource
	private IHotelIndexDao hotelIndexDao = null;

	@Resource
	private IHotelDao hotelDao = null;
	
	@Resource
	private IUpdateHotelDao updateHotelDao = null;
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	/**
	 * 每日更新，无效的酒店ID的数量（status != 0）
	 */
	private static AtomicInteger INVAILD_STATUS_COUNT = new AtomicInteger();
	private static AtomicInteger VAILD_STATUS_COUNT = new AtomicInteger();
	private static AtomicInteger TOTAL_DOWNLOAD_COUNT_FINISHED = new AtomicInteger();
	private static AtomicInteger TOTAL_DOWNLOAD_COUNT_FAILURE = new AtomicInteger();
	private static AtomicInteger INPUTSTREAM_IS_NULL_COUNT = new AtomicInteger();
	
	
	/**
	 * 计算每天需要更新的酒店信息
	 * 		-- 计算需要更新的酒店信息，并保存到mongo中
	 * 		-- 将老数据删除，将新下载的hotellist文件解析后重新入库
	 * @author muyuansun
	 * @throws FileDownloadException 
	 * @throws CommonException 
	 * @throws InvaildParameterException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @date 2014-1-6 下午8:25:30
	 */
	@Override
	public void hotelDetailStaticFileUpdate() throws CommonException, FileDownloadException, IOException, InterruptedException{
		long startTime = System.currentTimeMillis();
		try {
			/**
			 * 从ELONG，获得最近酒店基本信息列表，并将本地记录全部更新（先删除后更新的方式）
			 */
			List<HotelIndex> hotelIndexs = findHotelListFromElong();
			if(hotelIndexDao.deleteAll()){
				hotelIndexDao.saveEntity(hotelIndexs);
			}
			/**
			 * 如果mongo中没有全部可用酒店列表及酒店更新列表信息，需要全量下载
			 */
			if(hotelDao.getTotalCount() <= 0){
				downLoadAndPersistentHotelStaticInfo(hotelIndexs,"全量下载酒店信息");
				LOG.info("[INVAILD_STATUS_COUNT = {}]",INVAILD_STATUS_COUNT);
				LOG.info("[VAILD_STATUS_COUNT = {}]",VAILD_STATUS_COUNT);
				LOG.info("[TOTAL_DOWNLOAD_COUNT_FINISHED = {}]",TOTAL_DOWNLOAD_COUNT_FINISHED.get());
				LOG.info("[TOTAL_DOWNLOAD_COUNT_FAILURE = {}]",TOTAL_DOWNLOAD_COUNT_FAILURE.get());
				LOG.info("[INPUTSTREAM_IS_NULL_COUNT = {}]",INPUTSTREAM_IS_NULL_COUNT.get());
			}else{
				if(hotelIndexs == null || hotelIndexs.size() <= 0){
					return;
				}
				List<UpdateHotel> updateHotels = new ArrayList<>();
				
				List<HotelIndex> needDelete = new ArrayList<>();
				List<HotelIndex> needAdd = new ArrayList<>();
				List<HotelIndex> needUpdate = new ArrayList<>();
				/**
				 * 获得酒店详细信息，根据此进行是否更新对比
				 */
				Map<String,Hotel> hotelLocal = hotelDao.findHotelFromLocal(null);
				LOG.info("[HOTEL_LOCAL_SIZE = {}]",hotelLocal.size());
				//获得最新的酒店接口列表
				Iterator<HotelIndex> iterator = hotelIndexs.iterator();
				/**
				 * 提取需要更新的酒店信息
				 */
				while(iterator.hasNext()){
					HotelIndex hotelIndex = iterator.next();
						/**
						 * 检查是否存在本地数据库中，没有则添加，有则比较更新时间判断是否需要更新，并添加到对应的新增和更新列表中
						 */
						if(hotelLocal.get(hotelIndex.getHotelId()) == null){
							needAdd.add(hotelIndex);
							UpdateHotel updateHotel = new UpdateHotel();
							updateHotel.setHotelId(hotelIndex.getHotelId());
							updateHotel.setUpdateType(CommonConstants.HotelUpdateType.ADD.type());
							updateHotels.add(updateHotel);
						}else{
							//检查时间戳是否需要更新,格式如 yyyy-MM-dd HH:mm:ss 例如 2014-01-08 01:24:56
							String localDatabaseUpdatetime = hotelLocal.get(hotelIndex.getHotelId()).getUpdatedTime();
							String updatetime = hotelIndex.getUpdatedTime();
							long localDatabaseUpdatetimeMs = DateUtil.getDate(localDatabaseUpdatetime, "yyyy-MM-dd HH:mm:ss").getTime();
							long updatetimeMs = DateUtil.getDate(updatetime, "yyyy-MM-dd HH:mm:ss").getTime();
							
							//如果更新时间大于本地数据库更新时间，则需要更新
							if(updatetimeMs > localDatabaseUpdatetimeMs){
								needUpdate.add(hotelIndex);
								UpdateHotel updateHotel = new UpdateHotel();
								updateHotel.setHotelId(hotelIndex.getHotelId());
								updateHotel.setUpdateType(CommonConstants.HotelUpdateType.UPDATE.type());
								updateHotels.add(updateHotel);
								if(UPDATE_HOTEL_LOG.isDebugEnabled()){
									Object [] param = new Object[]{
											hotelIndex.getHotelId(),
											localDatabaseUpdatetime,
											hotelIndex.getUpdatedTime(),
											localDatabaseUpdatetimeMs,
											updatetimeMs
									};
									UPDATE_HOTEL_LOG.debug("[HOTEL_ID = {}][LOCAL_DATABASE_UPDATETIME = {}][UPDATETIME = {}][LOCAL_DATABASE_UPDATETIME_MS = {}][UPDATETIME_MS = {}]",
											param);
								}
							}
							
						}
						VAILD_STATUS_COUNT.addAndGet(1);
				}//while end
				
				UPDATE_HOTEL_LOG.info("[INVAILD_STATUS_COUNT = {}]",INVAILD_STATUS_COUNT);
				UPDATE_HOTEL_LOG.info("[VAILD_STATUS_COUNT = {}]",VAILD_STATUS_COUNT);
				UPDATE_HOTEL_LOG.info("[NEED_DELETE_SIZE = {}]",needDelete.size());
				UPDATE_HOTEL_LOG.info("[NEED_ADD_SIZE = {}]",needAdd.size());
				UPDATE_HOTEL_LOG.info("[NEED_UPDATE_SIZE = {}]",needUpdate.size());
				/**
				 * 数据库更新阶段，包括删除，新增，修改
				 */
				if(needDelete != null && needDelete.size() > 0){
					//删除 Hotel 表的记录
					deleteHotelByHotelId(needDelete);
				}
				/**
				 * 新增
				 */
				if(needAdd != null && needAdd.size() > 0){
					downLoadAndPersistentHotelStaticInfo(needAdd,"需要新增的酒店信息");
				}
				/**
				 * 更新-采用先删除再添加的方式
				 */
				if(needUpdate != null && needUpdate.size() > 0){
					//删除 Hotel 表的记录
					deleteHotelByHotelId(needUpdate);
					//新增
					downLoadAndPersistentHotelStaticInfo(needUpdate,"需要更新的酒店信息");
				}
				/**
				 * 存储更新酒店信息
				 */
				if(updateHotels != null && updateHotels.size() > 0){
					//先删除后添加
					if(updateHotelDao.deleteAll()){
						updateHotelDao.saveEntity(updateHotels);
					}
				}
				LOG.info("[TASK_FINISHED]");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		} 
		
		long endTIme = System.currentTimeMillis();
		LOG.info("[COST TIME = {}]",(endTIme - startTime) / 1000);
	}
	
	/**
	 * 停止线程池
	 * @author muyuansun
	 * @date 2014-1-17 上午10:40:52
	 * @return
	 * @throws CommonException 
	 */
	public boolean shutdown() throws CommonException{
		boolean result = false;
		try {
			pool.shutdownNow();
			result = true;
		} catch (Exception e) {
			throw CommonException.instance("线程池关闭异常");
		}
		return result;
	}
	
	/**
	 * 下载并存储酒店信息，包括基本信息和详细信息
	 * @author muyuansun
	 * @date 2014-1-11 下午8:11:01
	 * @param hotelIndexs
	 * @throws InterruptedException 
	 */
	public void downLoadAndPersistentHotelStaticInfo(List<HotelIndex> hotelIndexs,String logTip) throws InterruptedException{
		List<List<HotelIndex>> blockInfo = divideSegmentByThreadNumber(hotelIndexs);
		if(blockInfo == null || blockInfo.size() <= 0){
			return;
		}
		LOG.info("[{}][HOTEL_INDEXS_SIZE = {}]",logTip,hotelIndexs.size());
		LOG.info("[{}][BLOCK_SIZE = {}]",logTip,blockInfo.size());
		CountDownLatch countDownLatch = new CountDownLatch(blockInfo.size());
		for(List<HotelIndex> block:blockInfo){
			pool.execute(new HotelIndexDownloadAndStore(block,countDownLatch));
		}
		countDownLatch.await();
	}
	
	/**
	 * 按HotelId字段删除酒店基本信息
	 * @author muyuansun
	 * @date 2014-1-11 下午8:00:54
	 * @param needDelete
	 */
	public void deleteHotelIndexByHotelId(List<HotelIndex> hotelIndexs){
		LOG.info("[BEFORE_DELETED_HOTEL_INDEX_SIZE = {}]",hotelIndexDao.getTotalCount());
		if(hotelIndexs == null || hotelIndexs.size() <= 0){
			return;
		}
		//删除 HotelIndex 表的记录
		Query<HotelIndex> hotelIndexQuery = hotelIndexDao.createQuery();
		CriteriaContainerImpl[] hotelIndexDeleteCriterias = new CriteriaContainerImpl[hotelIndexs.size()];
		for(int i = 0;i < hotelIndexs.size();i++){
			hotelIndexDeleteCriterias[i] = hotelIndexQuery.criteria("hotelId").equal(hotelIndexs.get(i).getHotelId());
		}
		hotelIndexQuery.or(hotelIndexDeleteCriterias);
		//后期需要改成批量删除的形式
		for(HotelIndex hotelIndex:hotelIndexQuery.asList()){
			hotelIndexDao.deleteById(hotelIndex.getId());
		}
		LOG.info("[AFTER_DELETED_HOTEL_INDEX_SIZE = {}]",hotelIndexDao.getTotalCount());
	}
	/**
	 * 按HotelId字段删除酒店详细信息
	 * @author muyuansun
	 * @date 2014-1-11 下午8:03:00
	 * @param needDelete
	 */
	public void deleteHotelByHotelId(List<HotelIndex> hotelIndexs){
		if(hotelIndexs == null || hotelIndexs.size() <= 0){
			return;
		}
		long start = System.currentTimeMillis();
		LOG.info("[删除之前的酒店数量 = {}]",hotelDao.getTotalCount());
		Datastore datastore = hotelDao.createDatastore();
		int flag = 0;
		long startTime = System.currentTimeMillis();
		//批量删除,每批二百
		Query<Hotel> hotelQuery = hotelDao.createQuery();
		CriteriaContainerImpl[] criteriaContainerImpls = new CriteriaContainerImpl[200];
		for(int i = 0;i < hotelIndexs.size();i++){
			try {
				criteriaContainerImpls[flag++] = hotelQuery.criteria("hotelId").equal(hotelIndexs.get(i).getHotelId());
				if(flag == 200){
					hotelQuery.or(  
							criteriaContainerImpls
					);
					datastore.delete(hotelQuery);
					long endTime = System.currentTimeMillis();
					hotelQuery = hotelDao.createQuery();
					LOG.info("[删除的酒店数量 = {}][耗时 = {} 毫秒][酒店剩余数量 = {}]",flag,endTime - startTime,hotelDao.getTotalCount());
					//变量初始化为原始状态，为下一次批量逻辑做准备
					flag = 0;
					criteriaContainerImpls = new CriteriaContainerImpl[200];
					startTime = System.currentTimeMillis();
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(),e);
				//如有异常重新初始化，下次循环的依赖的参数
				hotelQuery = hotelDao.createQuery();
				flag = 0;
				criteriaContainerImpls = new CriteriaContainerImpl[200];
				startTime = System.currentTimeMillis();
			}
		}
		try {
			//如果最后一批小于200，将此批也要删除
			if(flag > 0 && criteriaContainerImpls != null && criteriaContainerImpls[0] != null){
				if(hotelQuery == null){
					hotelQuery = hotelDao.createQuery();
				}
				hotelQuery.or(  
						criteriaContainerImpls
						);
				datastore.delete(hotelQuery);
				LOG.info("[删除的酒店数量 = {}][耗时 = {} 毫秒][酒店剩余数量 = {}]",flag,System.currentTimeMillis() - startTime,hotelDao.getTotalCount());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		long end = System.currentTimeMillis();
		LOG.info("[总删除的酒店数量 = {}][耗时 = {} 毫秒]",hotelIndexs.size(),end - start);
		LOG.info("[删除之后的酒店数量 = {}]",hotelDao.getTotalCount());
	}
	
	/**
	 * 获得艺龙酒店静态文件列表信息
	 * @author muyuansun
	 * @date 2014-1-8 下午10:44:12
	 * @return
	 * @throws CommonException
	 * @throws FileDownloadException
	 * @throws IOException 
	 */
	public List<HotelIndex> findHotelListFromElong() throws CommonException, FileDownloadException, IOException{
		InputStream inputStream = null;
		List<HotelIndex> hotelIndexs = null;
		LOG.info("开始下载酒店基本信息");
		long startTime = System.currentTimeMillis();
		try {
			HotelListParser hotelListParser = (HotelListParser) hotelStaticFileDownloadService.downloadHotelList();
			hotelIndexs = hotelListParser.getHotelIndex();
			if(hotelIndexs == null || hotelIndexs.size() <= 0){
				throw CommonException.instance("没有获得酒店列表基础数据");
			}
		} catch (Exception e) {
			throw CommonException.instance("没有获得酒店列表基础数据",e);
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		long endTime = System.currentTimeMillis();
		if(LOG.isDebugEnabled()){
			LOG.debug("[下载酒店基本信息,用时:{} .seconds]",(endTime - startTime) / 1000);
		}
		return hotelIndexs;
	}
	
	/**
	 * 将需要更新的数据按线程数分块，用于每个线程分批执行下载
	 * @author muyuansun
	 * @date 2014-1-7 下午8:52:00
	 */
	public List<List<HotelIndex>> divideSegmentByThreadNumber(List<HotelIndex> hotelIndexs){
		List<List<HotelIndex>> result = null;
		if(hotelIndexs == null || hotelIndexs.size() <= 0){
			return result;
		}
		result = new ArrayList<>();
		
		/**
		 * 下载线程数，最大100，如果大于100，则设置成一百，如果小于10，则设置成10
		 */
		int threadNumber = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_THREAD_NUM.keyName(), 50);
		if(threadNumber < 10){
			threadNumber = 10;
		}
		if(threadNumber > 100){
			threadNumber = 100;
		}
		LOG.info("[THREAD_NUMBER = {}]",threadNumber);
		//如果小于线程数，直接返回一个block
		if(hotelIndexs.size() < threadNumber){
			result.add(hotelIndexs);
			return result;
		}
		//
		int blockSize;
		//被除数
		BigDecimal dividend = new BigDecimal(hotelIndexs.size());
		//除数
		BigDecimal divisor = new BigDecimal(threadNumber);
		//块大小
		blockSize = dividend.divide(divisor,10,BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP).intValue();
		//块信息
		List<HotelIndex> blockInfo = new ArrayList<>();
		//设值
		for(HotelIndex hotelIndex:hotelIndexs){
			//如果状态不等于0，则不更新
			if(hotelIndex.getStatus() != 0){
				if(LOG.isDebugEnabled()){
					INVAILD_STATUS_COUNT.addAndGet(1);
					LOG.debug("[INVAILD_STATUS_HOTEL_ID = {}][STATUS_CODE = {}]",hotelIndex.getHotelId(),hotelIndex.getStatus());
				}
				continue;
			}
			VAILD_STATUS_COUNT.addAndGet(1);
			blockInfo.add(hotelIndex);
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
	
	/**
	 * - 根据提供的酒店基本信息（HotelIndex）列表集合下载酒店详情静态文件，并解析成 {@link com.travelzen.tops.hotel.elong.mongo.staticfile.Hotel} 对象 存储到本地数据库（mongo）
	 * - 存储酒店基本信息到本地数据库（mongo）
	 * @author muyuansun
	 * @date 2014-1-9 下午7:58:20
	 */
	private class HotelIndexDownloadAndStore implements Runnable{
		
		private Logger LOG = LoggerFactory.getLogger(this.getClass());
		
		private List<HotelIndex>  hotelIndexs = null;
		
		private CountDownLatch countDownLatch = null;
		
		private int batchSize = 20;
		
		public HotelIndexDownloadAndStore(List<HotelIndex>  hotelIndexs,CountDownLatch countDownLatch){
			this.hotelIndexs = hotelIndexs;
			this.countDownLatch = countDownLatch;
			batchSize  = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTEL_BATCH_THRESHOLD.keyName(), 20);
		}
		
		@Override
		public void run() {
				if(hotelIndexs == null || hotelIndexs.size() <= 0){
					return;
				}
				List<Hotel> batchHotelList = new ArrayList<>();
				for(HotelIndex hotelIndex:hotelIndexs){
					try {
						//根据酒店ID获得酒店静态文件输入
						HotelDetailParser hotelDetailParser = (HotelDetailParser) hotelStaticFileDownloadService.downloadHotelDetail(hotelIndex.getHotelId(),null);
						Hotel hotel = hotelDetailParser.getHotel();
						if(hotel != null){
							TOTAL_DOWNLOAD_COUNT_FINISHED.addAndGet(1);
							hotel.setUpdatedTime(hotelIndex.getUpdatedTime());
							batchHotelList.add(hotel);
							if(batchHotelList.size() >= batchSize){
								hotelDao.saveEntity(batchHotelList);
								LOG.info("[BATCH_SAVE_FINISHED,SIZE = {}]",batchHotelList.size());
								batchHotelList.clear();
							}
						}else{
							TOTAL_DOWNLOAD_COUNT_FAILURE.addAndGet(1);
							if(LOG.isDebugEnabled()){
								LOG.debug("[HOTEL_INFORMATION_IS_NULL.HOTEL_ID = {}]",hotelIndex.getHotelId());
							}
						}
					} catch (FileDownloadException e) {
						TOTAL_DOWNLOAD_COUNT_FAILURE.addAndGet(1);
						LOG.error(e.getMessage(),e);
					} catch (InvaildParameterException e) {
						TOTAL_DOWNLOAD_COUNT_FAILURE.addAndGet(1);
						LOG.error(e.getMessage(),e);
					}
				}// for end
				//如果还有剩余小于阀值残留，也要持久化
				if(batchHotelList.size() > 0){
					hotelDao.saveEntity(batchHotelList);
					LOG.info("[BATCH_SAVE_FINISHED,SIZE = {}]",batchHotelList.size());
				}
				countDownLatch.countDown();
			}
		}

}
