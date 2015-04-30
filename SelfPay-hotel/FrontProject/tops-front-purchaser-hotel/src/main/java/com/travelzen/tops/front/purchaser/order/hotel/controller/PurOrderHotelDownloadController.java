package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.exception.BizException;
import com.travelzen.framework.util.LogFormatter;
import com.travelzen.framework.util.LogFormatter.Project;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.front.purchaser.order.hotel.util.DownloadHotelConfirmOrderJudgement;
import com.travelzen.tops.order.core.hotel.bo.common.HotelOrderCommonBo;
import com.travelzen.tops.order.hotel.common.msg.bean.HotelOrderFaxBean;
import com.travelzen.tops.order.hotel.common.msg.bean.HotelOrderMessageConst.HotelOrderMessageType;
import com.travelzen.tops.order.hotel.common.msg.service.HotelOrderMessageEntityFactory;
import com.travelzen.tops.order.hotel.common.msg.service.impl.HotelOrderMessageServiceHandle;
import com.travelzen.tops.order.hotel.common.service.query.IHotelOrderQueryCommonService;

/**
 * 
 * @author yanxin.wang@travelzen.com
 * @since 2014年5月29日
 * @description 客户确认单下载
 * @version
 */
@Controller
@RequestMapping("/pur/order/hotel/download")
public class PurOrderHotelDownloadController {
	private final Logger logger = LoggerFactory.getLogger(PurOrderHotelDownloadController.class);

	@Resource(name="hotel_order_hotelOrderQueryCommonService")
	private IHotelOrderQueryCommonService<HotelOrderCommonBo> orderService;

	@Resource(name = "downloadHotelConfirmOrderJudgement")
	private DownloadHotelConfirmOrderJudgement downloadHotelConfirmOrderJudgement;

	/**
	 * 
	 * @Description 客户确认单下载
	 * @param @return
	 * @param @throws Exception
	 * @return ResponseEntity<byte[]>
	 * @throws
	 */
	@RequestMapping(value = "/hotelcomformdownload", method = RequestMethod.GET)  
	public ResponseEntity<byte[]> download(String hotelOrderId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> resp = null;
		HotelOrderFaxBean docBean = null;
		HotelOrderMessageType hotelOrderMessageType = null;
		try {
			HotelOrderCommonBo order = orderService.getById(hotelOrderId);
			if(order == null){
				throw BizException.instance("订单不存在:"+hotelOrderId);
			}
			if(!downloadHotelConfirmOrderJudgement.canDownloadConfirmOrder(order)) {
				throw BizException.instance("当前订单不可以下载客户确认单:["+hotelOrderId+"]");
			}
			String docName = "客户确认单_"+hotelOrderId+".doc";
			docName = new String(docName.getBytes("UTF-8"),"iso-8859-1");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", docName);
			if(OrderType.GTA_NORMAL_HOTEL.name().equals(order.getOrderType())){
				hotelOrderMessageType = HotelOrderMessageType.GTA_JD1003_FAX;
			}else{
				hotelOrderMessageType = HotelOrderMessageType.CREME_JD1003_FAX;
			}
			HotelOrderMessageEntityFactory factory = HotelOrderMessageServiceHandle
					.getHotelOrderMessageEntityFactory(hotelOrderMessageType);
			docBean = (HotelOrderFaxBean) factory.getInstence(hotelOrderId, hotelOrderMessageType);
			docBean.writeFaxFile();
			resp = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(docBean.getFaxPath())),  
					headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(LogFormatter.errorFormat(Project.tops_order_hotel_service,
					"PurOrderHotelDownloadController.download", hotelOrderId,
					"SEND_ORDER_DOWNLOAD_FAILED",
					"download order confirm failed", e));
		}finally{
			if(docBean != null){
				docBean.deleteFaxFile();
			}
		}
		
	    return resp;
	}
}
