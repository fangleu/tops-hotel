/**
 *
 */
package com.travelzen.tops.front.purchaser.hotel.vo;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.tops.order.core.hotel.bo.gta.GTAHotelOrderBo;

/**
 * GTA订单所有信息
 * @author wangmeng
 * @deprecated replaced by HotelOrderVo
 */
public class GTAHotelOrderVo extends GTAHotelOrderBo {
	
	private String totalAddServiceFeeYuan;    //增值服务总额
	

	
	public String getTotalAddServiceFeeYuan() {
		return MoneyUtil.cent2Yuan(getTotalAddServiceFee() == null ? 0 : getTotalAddServiceFee());
	}
	
	public void setTotalAddServiceFeeYuan(String totalAddServiceFeeYuan) {
		if(StringUtils.isNotBlank(totalAddServiceFeeYuan)){
			this.totalAddServiceFeeYuan = totalAddServiceFeeYuan;
			setTotalAddServiceFee(MoneyUtil.yuan2Cent(new BigDecimal(totalAddServiceFeeYuan)));
		}
	}
}
