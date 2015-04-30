package com.travelzen.tops.elong.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.RatePlanItem;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumGuaranteeChangeRule;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumGuaranteeMoneyType;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.GuaranteeRule;

public class ElongHotelGuranteeRule {
	private static final String LIMIT_CANCEL_RULE_MSG = "如未按时入住，酒店将扣除您的担保金额作为违约金。";

	public static void elongGuranteeRule(String checkInDate  , GuaranteeRule rule , RatePlanItem ratePlanItem){
		ratePlanItem.setGuaranteeRule("担保");
		ratePlanItem.setLimitType("not");
		ratePlanItem.setLimitDesc("不可取消");
		ratePlanItem.setGuaranteeDesc(rule.getDescription());
		String cancelMsg = "订单一经预订成功，不可变更/取消。" + LIMIT_CANCEL_RULE_MSG;
		if(rule.getChangeRule().equals(EnumGuaranteeChangeRule.NeedSomeDay)){
			DateTime guaranteeRuleDate = new DateTime(rule.getDay());
			if(StringUtils.isNotBlank(rule.getTime())){
				String time[] =  rule.getTime().split(":");
				int hours = Integer.parseInt(time[0]);
				int minutes = Integer.parseInt(time[1]);
				DateTime lastestCancelTime = guaranteeRuleDate.plusHours(hours).plusMinutes(minutes);
				if(lastestCancelTime.isAfterNow()){
					ratePlanItem.setLimitType("rule");
					ratePlanItem.setLimitDesc("限时取消");
					cancelMsg = DateTimeUtil.format(lastestCancelTime, DateTimeUtil.DATE_PATTERN)
							+ " " + rule.getTime()
							+ " 前可以免费变更/取消订单，之后无法变更取消。" + LIMIT_CANCEL_RULE_MSG;
				}
			}
		}else if(rule.getChangeRule().equals(EnumGuaranteeChangeRule.NeedCheckin24hour)){
			DateTime lastestCancelTime = new DateTime(checkInDate).plusDays(1).minusHours(rule.getHour());
			if(lastestCancelTime.isAfterNow()){
				ratePlanItem.setLimitType("rule");
				ratePlanItem.setLimitDesc("限时取消");
				cancelMsg = DateTimeUtil.format(lastestCancelTime, DateTimeUtil.DATE_PATTERN + " HH")
					+ ":00 前可以免费变更/取消订单，之后无法变更取消。" + LIMIT_CANCEL_RULE_MSG;
			}
		}else if (rule.getChangeRule().equals(EnumGuaranteeChangeRule.NeedCheckinTime)){
			DateTime lastestCancelTime = new DateTime(checkInDate).plusHours(14).minusHours(rule.getHour());
			if(lastestCancelTime.isAfterNow()){
				ratePlanItem.setLimitType("rule");
				ratePlanItem.setLimitDesc("限时取消");
				cancelMsg = DateTimeUtil.format(lastestCancelTime, DateTimeUtil.DATE_PATTERN + " HH")
						+ ":00 前可以免费变更/取消订单，之后无法变更取消。" + LIMIT_CANCEL_RULE_MSG;
			}
		}
		ratePlanItem.setCancelMsg(cancelMsg);

		if(EnumGuaranteeMoneyType.FirstNightCost.name().equals(rule.getGuaranteeType().name())){
			ratePlanItem.setGuaranteeType("首晚");
		}else{
			ratePlanItem.setGuaranteeType("全额");
		}
	}
	
}
