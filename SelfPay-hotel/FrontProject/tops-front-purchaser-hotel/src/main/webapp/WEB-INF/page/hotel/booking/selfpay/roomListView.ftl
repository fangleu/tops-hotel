<#macro roomlist hotel>
<#import "tooltipTemplate.ftl" as tooltipTemplate /><#-- roomlist tip -->
	<div class="roomTypeListContainer clearfix">
	<table>
		<thead>
			<tr>
				<td class="roomTypeGroupFirstField" width="250">房型</td>
	            <td width="90">床型</td>
	            <td width="90">早餐</td>
	            <td width="60">宽带</td>
	            <td width="85">规定</td>
	            <td width="85">日均价</td>
	            <td width="130">日均返佣</td>
	            <td width="48">&nbsp;</td>
	            <td width="90">&nbsp;</td>
			</tr>
		</thead>
	</table>
	 <#if hotel?? && (hotel.roomList)??>
	 <#list hotel.roomList as roomtype>	
		<div class="roomTypeDetailContainer" style="overflow: hidden; height: 45px;">
		<table>
        	<tbody>
                <#if (roomtype.ratePlanList)??>
                <#list roomtype.ratePlanList as ratePlan>
        			<#if ratePlan_index ==0>
	                    <tr>
	                       <td width="254" class="tdTitle"><span style="font-weight:bold;">${roomtype.roomName!}</span></td>
	                        <td width="90">
	                        	<span class="expand_hide" >
	                           	<#if ratePlan.bedType??&&ratePlan.bedType=="其床">其它床型<#else>${ratePlan.bedType!'--'}</#if>
                        		</span>
                        	</td>
	                        <td width="90"><span class="expand_hide" >${ratePlan.breakfastType!}</span></td>
	                        <td width="60"><span class="expand_hide" >${ratePlan.netType!'--'}</span></td>
	                        <td width="85"><em class="<#if ratePlan.limitDesc=="免费取消">canNotcancelled<#else>cancelled</#if> regulations expand_hide" 
	                        	bkid=${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!} >${ratePlan.limitDesc!}</em>
	                        	<@tooltipTemplate.stipulateTemplate Conditions=ratePlan.cancelMsg bkid=hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId/>
	                        </td>
	                        <td width="84"><span class="hotelRoomPrice expand_hide" bki=_${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!}>
	                        	<em class="changeFont">￥</em>${ratePlan.roomPrice!}</span>
		                        <#if ratePlan.dayPriceList??>
	                        	<@tooltipTemplate.stipulate  dayPriceList = ratePlan.dayPriceList  weekList = weekList bki="_"+hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId totalPrice = ratePlan.totalPrice/> 
	                        	</#if>
	                        </td>
	                      
	                        <td width="131"><span class="hotel_return expand_hide" ><em><#if commissionRate??> ${(((commissionRate/100) * ratePlan.roomPrice!0)?int)!} </#if>元</em></span></td>
	                        <#if (ratePlan.guaranteeRule)?? && ratePlan.guaranteeRule != "0">
	                        <td width="40"><span bki=${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!} class="hotel_ensure expand_hide" >担保</span>
	                        	<#if ratePlan.guaranteeDesc??>
		                        		<@tooltipTemplate.guaranteeDesc Conditions=ratePlan.guaranteeDesc bkid=hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId/> 
		                        </#if>
	                        </td>
	                        <#else>
	                        <td width="40"><span class=" expand_hide" ></span></td>
	                        </#if>
	                        <td width="90">
	                            <div class="expand_hide" >
	                                <a href="javascript:submitOrderForm('${(hotel.hotelId)!}','${(roomtype.roomId)!}','${(ratePlan.roomTypeId)!}','${(ratePlan.ratePlanId)!}',
									'${(ratePlan.guarantee?string)!}','${(ratePlan.amount)!}','${(ratePlan.startTime)!}','${(ratePlan.endTime)!}')"
									 class="adaptiveButton mediumOrange_btn">
	                                    <span class="left"></span>
	                                    <span class="center center_1">预订</span>
	                                    <span class="right"></span>
	                                </a>
	                            </div>
	                            <#if ratePlan.roomNum != "0">
	                             <div class="opp_remainRoom ratePlan_roomNum" style="display: none;">
	                               <span class="remain_room">
	                               仅剩${ratePlan.roomNum}间
	                               </span>
	                            </div>
	                            <#else>
	                             <div class="opp_remainRoom ratePlan_roomNum" style="display: none;">
	                              
	                            </div>
	                            </#if>
	                            
	                        </td>
	                    </tr>
                    </#if>
       
                    <tr>
                        <td class="roomTypeGroupDetailFirstField" > <#if ratePlan.giftList??><i class="ico_gift warmTipsLayer" bki=${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!}>
                         <#if ratePlan.giftList??>
	                        <@tooltipTemplate.giftsTemplate  giftsList =ratePlan.giftList   bki=hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId/> 
	                        </#if>
                        </i></#if> ${ratePlan.ratePlanName!} </td>
                        <td>
                           <#if ratePlan.bedType??&&ratePlan.bedType=="其床">其它床型 
	                        <#else>
                           ${ratePlan.bedType!'--'}
                           </#if>
                        </td>
                        <td>${ratePlan.breakfastType!}</td>
                        <td>${ratePlan.netType!'--'}</td>
                        <td><em class="<#if ratePlan.limitDesc=="免费取消">canNotcancelled<#else>cancelled</#if> regulations" bkid=${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!}>${ratePlan.limitDesc!}</em>
                        	<@tooltipTemplate.stipulateTemplate Conditions=ratePlan.cancelMsg bkid=hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId/>
                        </td>
                        <td><span class="hotelRoomPrice floatLayer" bki=${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!}>
                        	<em class="changeFont">￥</em>${ratePlan.roomPrice!}
	                        <#if ratePlan.dayPriceList??>
	                        <@tooltipTemplate.stipulate  dayPriceList = ratePlan.dayPriceList  weekList = weekList bki=hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId totalPrice = ratePlan.totalPrice/> 
	                        </#if>
	                        </span>
	                    </td>
                        <td><span class="hotel_return"><em><#if commissionRate??>${(((commissionRate/100) * ratePlan.roomPrice!0)?int)!} </#if>元</em></span></td>
                        <#if (ratePlan.guaranteeRule)?? && ratePlan.guaranteeRule != "0">
                        <td><span  bki=rule_${(hotel.hotelId)!}${(ratePlan.roomTypeId)!}${(ratePlan.ratePlanId)!} class="hotel_ensure">担保</span>
                        	<#if ratePlan.guaranteeDesc??>
	                        		<@tooltipTemplate.guaranteeDesc Conditions=ratePlan.guaranteeDesc bkid="rule_" + hotel.hotelId+ratePlan.roomTypeId+ratePlan.ratePlanId/> 
	                     	</#if>
                        </td>
                        <#else>
                        <td><span></span></td>
                        </#if>
                        <td>
                            <a href="javascript:submitOrderForm('${(hotel.hotelId)!}','${(roomtype.roomId)!}','${(ratePlan.roomTypeId)!}','${(ratePlan.ratePlanId)!}',
								'${(ratePlan.guarantee?string)!}','${(ratePlan.amount)!}','${(ratePlan.startTime)!}','${(ratePlan.endTime)!}');"
								 class="adaptiveButton mediumOrange_btn">
                                <span class="left"></span>
                                <span class="center center_1">预订</span>
                                <span class="right"></span>
                            </a>
                        </td>
                    </tr>
                </#list>
                </#if>
                </tbody>
       		</table>
      	</div>
		</#list>
	    </#if>
	   
	<div class="roomTypeListBottomButtonContainer">
		<a href="javascript:void(0);" class="expand" style="display:none;">收起全部房型<i class="ico_up"></i></a>
		<a href="javascript:void(0);" class="expand" >展开全部房型<i class="ico_down"></i></a>
	</div>
	<form action="${(basepath)!}/pur/order/hotel/selfpay/orderView" method="post" id="orderForm" target="_blank">
		<input type="hidden" name="hotelId">
		<input type="hidden" name="roomId">
		<input type="hidden" name="roomCatId">
		<input type="hidden" name="bookingClassId">
		<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}">
		<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}">
	</form>
</div>
	  
</#macro>