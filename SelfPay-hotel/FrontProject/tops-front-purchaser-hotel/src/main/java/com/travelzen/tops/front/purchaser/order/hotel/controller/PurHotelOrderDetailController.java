package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.BizException;
import com.travelzen.framework.logger.core.ri.RequestIdentityLogger;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderState;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.front.purchaser.order.hotel.util.DownloadHotelConfirmOrderJudgement;
import com.travelzen.tops.hotel.order.engine.client.proxy.HotelOrderEngineProxy;
import com.travelzen.tops.hotel.order.engine.model.component.THotelOrder;
import com.travelzen.tops.hotel.order.engine.model.detail.request.TOrderDetailRequest;
import com.travelzen.tops.hotel.order.engine.model.detail.response.TOrderDetailResponse;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.core.hotel.bo.gta.GTAHotelOrderBo;
import com.travelzen.tops.order.hotel.common.log.service.IHotelOrderLogService;
import com.travelzen.tops.order.hotel.common.service.HotelOrderAuthCenterFactory;
import com.travelzen.tops.order.hotel.common.service.HotelOrderStageCenterFactory;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderStageCenter;
import com.travelzen.tops.order.hotel.common.vo.HotelOrderVo;
import com.travelzen.tops.order.hotel.common.vo.Handler.HotelOrderVoConverter;
import com.travelzen.tops.order.hotel.creme.dto.CreateOrderCriteria;
import com.travelzen.tops.order.hotel.creme.service.query.ICremeHotelOrderQueryService;
import com.travelzen.tops.order.hotel.creme.vo.CremeHotelOrderExternalData;
import com.travelzen.tops.order.hotel.gta.proccessor.IGTAOrderChargeConditionProccessorServiceLayer;
import com.travelzen.tops.order.hotel.gta.service.IGTAHotelOrderQueryService;
import com.travelzen.tops.order.hotel.gta.util.GTAOrderChargeConditionUtil;
import com.travelzen.tops.order.hotel.gta.vo.GTAHotelOrderExternalData;
import com.travelzen.tops.order.hotel.gta.vo.GTAHotelOrderVoConverterHandler;

/**
 * 
 * @author wangmeng
 * 
 */
@Controller
@RequestMapping("/pur/hotel/ordermanage")
public class PurHotelOrderDetailController extends HotelBaseController {

	private static Logger logger = RequestIdentityLogger.getLogger(PurHotelOrderDetailController.class);

	@Resource(name = "hotel_order_cremeQueryService")
	private ICremeHotelOrderQueryService hotelOrderQueryService;

	@Resource(name = "hotel_order_gtaQueryService")
	private IGTAHotelOrderQueryService gtaHotelOrderQueryService;

	@Resource(name = "hotelOrder_authCenterFactory")
	private HotelOrderAuthCenterFactory hotelOrderAuthCenterFactory;

	@Resource(name = "gtaOrderChargeConditionProccessorServiceLayer")
	private IGTAOrderChargeConditionProccessorServiceLayer chargeConditionProccesor;

	@Resource(name = "hotelOrder_stageCenterFactory")
	private HotelOrderStageCenterFactory hotelOrderStageCenterFactory;

	@Resource(name = "downloadHotelConfirmOrderJudgement")
	private DownloadHotelConfirmOrderJudgement downloadHotelConfirmOrderJudgement;

	@Resource(name="hotel_order_LogService")
	private IHotelOrderLogService hotelOrderLogService;
	
	@Resource(name="selfpayHotelOrderStageCenter")
	private IHotelOrderStageCenter selfpayHotelOrderStageCenter;//现付酒店订单状态流程

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String viewDetail(String hotelOrderId, Model model) throws Exception {
		HotelOrderBo hotelOrderBo = null;
		try {
			hotelOrderBo = hotelOrderQueryService.getHotelOrderBo(hotelOrderId, HotelOrderTable.hotel_order);
		} catch (Exception e) {
			throw BizException.instance(ReturnCode.E_DATA_VALIDATION_ERROR, "未找到订单：" + hotelOrderId);
		}
		if (!TopsSecurityUtils.getUserFromSession().getCustomerKey().equals(hotelOrderBo.getCustomerKey())) {
			throw BizException.instance(ReturnCode.E_DATA_VALIDATION_ERROR, "未授权");
		}
		model.addAttribute("hotelOrderLogs", hotelOrderLogService.getHotelOrderLogs(hotelOrderId));
		model.addAttribute("operations", hotelOrderAuthCenterFactory.getAuthIntercace(hotelOrderBo).getOperations(hotelOrderBo, OperationSource.purchaser));
		if (OrderType.GTA_NORMAL_HOTEL.name().equals(hotelOrderBo.getOrderType())
				|| OrderType.GTA_REFUND_HOTEL.name().equals(hotelOrderBo.getOrderType())) {
			return gtaDetail(hotelOrderId, model);
		} else if (OrderType.SELFPAY_NORMAL_HOTEL.name().equals(hotelOrderBo.getOrderType())){
			return selfpayDetail(hotelOrderId, model);
		} else {
			return cremeDetail(hotelOrderId, model);
		}

	}

	private String selfpayDetail(String hotelOrderId, Model model) throws Exception {
		TOrderDetailRequest request = new TOrderDetailRequest();
		String customerKey = TopsSecurityUtils.getUserFromSession().getCustomerKey();
		request.setCustomerKey(customerKey);
		request.setOrderId(hotelOrderId);
		try {
			TOrderDetailResponse response = HotelOrderEngineProxy.orderDetail(request);
			THotelOrder hotelOrder = response.getHotelOrder();
			model.addAttribute("hotelOrder", hotelOrder);
			model.addAttribute("orderStateDesc", EnumUtils.isValidEnum(HotelOrderState.class, hotelOrder.getState()) ? HotelOrderState.valueOf(hotelOrder.getState()).getDesc() : "");
			model.addAttribute("customerStage", selfpayHotelOrderStageCenter.getCustomerHotelOrderStages(hotelOrder.getState(),hotelOrder.getConfirmStatus(),hotelOrder.getGatheringState()));
			CreateOrderCriteria criteria = new CreateOrderCriteria();//用于价格日历的显示，可考虑重构之
			criteria.setCheckInDate(response.getHotelOrder().getCheckinDate());
			criteria.setCheckOutDate(response.getHotelOrder().getCheckoutDate());
			model.addAttribute("criteria",criteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return "page/order/hotel/selfpay/selfpayOrderDetail";
	}

	private String cremeDetail(String hotelOrderId, Model model) throws Exception {
		HotelOrderVo<CremeHotelOrderExternalData> hotelOrderVo = hotelOrderQueryService.getHotelOrderVo(hotelOrderId, HotelOrderTable.all);
		List<HotelOrderVo<CremeHotelOrderExternalData>> relatedOrdersVo = hotelOrderQueryService.getHotelOrderVoByOriginalOrderId(hotelOrderVo.getHotelOrder().getOriginalOrderId());
		// 只有变更单需查看历史记录
		HotelOrderVo<CremeHotelOrderExternalData> origOrderVo = null;
		if(StringUtils.isNotBlank(hotelOrderVo.getHotelOrder().getOrigOrderId()) && !hotelOrderVo.getHotelOrder().getOrigOrderId().equals(hotelOrderVo.getHotelOrder().getId())){
			origOrderVo = hotelOrderQueryService.getHotelOrderVo(hotelOrderVo.getHotelOrder().getOrigOrderId(), HotelOrderTable.all);
		}

		model.addAttribute("hotelOrderId", hotelOrderId);
		model.addAttribute("hotelOrderVo", hotelOrderVo);
		model.addAttribute("origOrderVo", origOrderVo);
		//调账单金额为0的在采购商中不能显示出来，由于原来的方法公用，现在查询出来所有的然后去掉这部分
		if(relatedOrdersVo != null && relatedOrdersVo.size() > 0){
			for(int i = relatedOrdersVo.size() - 1; i >=0; i --){
				HotelOrderVo<CremeHotelOrderExternalData> removeVo = relatedOrdersVo.get(i);
				if(removeVo != null && removeVo.getHotelOrder() != null &&
						OrderType.ADJUST_HOTEL.name().equals(removeVo.getHotelOrder().getOrderType()) && removeVo.getHotelOrder().getTotalOrderFee() ==0){
					relatedOrdersVo.remove(removeVo);
				}
			}
		}
		model.addAttribute("relatedOrderVos", relatedOrdersVo);
		model.addAttribute("canDownloadConfirmOrder", downloadHotelConfirmOrderJudgement.canDownloadConfirmOrder(hotelOrderVo.getHotelOrder()));

		// 订单详情页面倒计时参数设置
		model = setOrderExpireTime(hotelOrderVo.getHotelOrder().getCreateDate(), model, 60);
		CreateOrderCriteria criteria = new CreateOrderCriteria();
		criteria.setCheckInDate(new SimpleDateFormat("yyyy-MM-dd").format(hotelOrderVo.getHotelOrder().getCheckinDate()));
		criteria.setCheckOutDate(new SimpleDateFormat("yyyy-MM-dd").format(hotelOrderVo.getHotelOrder().getCheckoutDate()));
		model.addAttribute("criteria",criteria);
		model.addAttribute("customerStage", hotelOrderStageCenterFactory.getStageInterface(
				hotelOrderVo.getHotelOrder()).getCustomerHotelOrderStages(hotelOrderVo.getHotelOrder().getState(),hotelOrderVo.getHotelOrder().getConfirmStatus(),hotelOrderVo.getHotelOrder().getGatheringState()));
		return "page/order/hotel/hotelOrderDetail";
	}

	private String gtaDetail(String hotelOrderId, Model model) {
		//获取订单
		GTAHotelOrderBo gtaBo = gtaHotelOrderQueryService.getById(hotelOrderId);
		chargeConditionProccesor.chargeConditionProccess(gtaBo);
		HotelOrderVo<GTAHotelOrderExternalData> gtaVo = HotelOrderVoConverter.convertBo2Vo(GTAHotelOrderVoConverterHandler.getInstance(), gtaBo);
		List<HotelOrderVo<GTAHotelOrderExternalData>> relatedOrdersVo = gtaHotelOrderQueryService.getHotelOrderByOriginalOrderId(gtaBo.getOriginalOrderId());

		model.addAttribute("hotelOrderId", hotelOrderId);
		model.addAttribute("hotelOrderVo", gtaVo);

		model.addAttribute("relatedOrderVos", relatedOrdersVo);

		// 订单详情页面倒计时参数设置
		model = setOrderExpireTime(gtaVo.getHotelOrder().getCreateDate(), model, 60);
		CreateOrderCriteria criteria = new CreateOrderCriteria();
		criteria.setCheckInDate(new SimpleDateFormat("yyyy-MM-dd").format(gtaVo.getHotelOrder().getCheckinDate()));
		criteria.setCheckOutDate(new SimpleDateFormat("yyyy-MM-dd").format(gtaVo.getHotelOrder().getCheckoutDate()));
		model.addAttribute("criteria",criteria);
		model.addAttribute("customerStage", hotelOrderStageCenterFactory.getStageInterface(gtaBo).getCustomerHotelOrderStages(gtaBo.getState(),gtaBo.getConfirmStatus(),gtaBo.getGatheringState()));
		model.addAttribute("gtaCancelOrderProvisionRMB", GTAOrderChargeConditionUtil.genCancelProvision(gtaBo, false, "<br/>"));
		return "page/order/hotel/gta/hotelOrderDetail";
	}

	/******
	 * 
	 * @param createDate
	 *            订单创建时间
	 * @param model
	 * @param addMinutes
	 *            过期时间（分钟）
	 * @return
	 */
	private Model setOrderExpireTime(java.util.Date createDate, Model model, int addMinutes) {
		if (createDate == null) {
			return model;
		}

		// 订单过期时间
		java.util.Date expireTime = null;

		Calendar cal = Calendar.getInstance();
		cal.setTime(createDate);
		// 加上固定分钟时间，也就是最后为过期时间
		cal.add(Calendar.MINUTE, addMinutes);
		expireTime = cal.getTime();

		java.util.Date currentTime = new java.util.Date();
		// 剩余秒数
		long restTime = (expireTime.getTime() - currentTime.getTime()) / 1000;
		if (restTime < 0) {
			restTime = 0;
		}
		model.addAttribute("expireDate", expireTime);
		model.addAttribute("restSecond", restTime);

		return model;
	}

}
