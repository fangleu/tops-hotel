<#--	订单详情页价格日历		-->
<#macro bookingDetail hotelOrderVo type>
<div id="<#if type="orig">orig</#if>priceCale" class="layerGrayBlock" style="z-index:100;display:none;font-size: 12px; position:absolute;">
	<div class="layerGrayContainer_noArrow">
	<#if (hotelOrderVo.hotelPriceDetailBos)??>
	<#assign Maps={"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9}>
	<#assign weekMaps={"0":"周日","1":"周一","2":"周二","3":"周三","4":"周四","5":"周五","6":"周六"} >
	<#assign hotelPriceDetailBos = hotelOrderVo.hotelPriceDetailBos />
	<#assign days=hotelPriceDetailBos?size>
		
		<p class="abroadhotelPriceBlock">
			<span><#-- 示例：2014/04/03 周四 至 2014/04/11 周五 8天 -->
				${(hotelOrderVo.hotelOrder.checkinDate?string("yyyy/MM/dd"))!}
				周${(hotelOrderVo.hotelOrder.checkinDate?string("EEE")?substring(2,3))}
				至 ${(hotelOrderVo.hotelOrder.checkoutDate?string("yyyy/MM/dd"))!}
				周${(hotelOrderVo.hotelOrder.checkoutDate?string("EEE")?substring(2,3))}
			</span>
			<span style="float:right;">
				<i>${(hotelOrderVo.hotelDetailBo.checkinDayNo)!0}</i>晚 <i>${(hotelOrderVo.hotelDetailBo.roomNo)!0}</i>间
			</span>
		</p>
		<#assign start = 0 />
		<#assign end = (criteria.validDates?size)/7-1 />
		<#list start..end as i>
		<table class="abroadhotelSalePriceTbl">
			<thead>
			<tr>
				<th class="first" style="width:60px;text-align:left"><b>第${i+1}周</b></th>
	            <#list 7*i..(7*(i+1)-1) as index>
	            	<#if (criteria.validDates[index])??>
	            		 <#assign monthAndDay=(criteria.validDates[index])?substring(0,10)>
			             <#assign c=(Maps[monthAndDay?substring(0,1)]*10+Maps[monthAndDay?substring(1,2)])>
			             <#assign y=(Maps[monthAndDay?substring(2,3)]*10+Maps[monthAndDay?substring(3,4)])>
			             <#assign m=(Maps[monthAndDay?substring(5,6)]*10+Maps[monthAndDay?substring(6,7)])>
			             <#assign d=(Maps[monthAndDay?substring(8,9)]*10+Maps[monthAndDay?substring(9,10)])>
			             <#if m=1>
				              <#assign m=13>
				              <#assign y=y-1>
			             <#elseif m=2>
				              <#assign m=14>
				              <#assign y=y-1>
			              </#if>
			             <#assign week=(y+(y/4)?int+(c/4)?int-2*c+((26*(m+1))/10)?int+d-1)%7>
				         <#if (week<0)>
				         	<#assign week=week+7>
				         </#if>
				         <th  style="width:60px;"  class="<#if (week?c=="0") || (week?c=="6")>weekend</#if> <#if (hotelPriceDetailBos[index])??><#if (hotelPriceDetailBos[index].isRefund)??><#if (hotelPriceDetailBos[index].isRefund)>icon_fade_roomInfo fade_roomInfo</#if></#if></#if>">
			             ${(m)!}/${(d)!}${(weekMaps[week?c])!}
			             </th>
	                 </#if>
	 			</#list>
			</tr>
			</thead>
			<tbody>
			<tr height=30>
				<td class="first">
					<div><b>价格</b></div>
					<div><b>餐食</b></div>
				</td>
				<#list 7*i..(7*(i+1)-1) as index>
					<td style="width:70px;">
						<#if (index &lt; hotelPriceDetailBos?size)>
							<div class="price" ruleId="${(hotelPriceDetailBos[index].policyId)!"-"}">
								&yen;${(hotelPriceDetailBos[index].sellingFeeYuanRmb)!"-"}
							</div>
							<div>
								<#if !(hotelPriceDetailBos[index].breakfastNo)?? || hotelPriceDetailBos[index].breakfastNo==0>
			                         无早
                      			<#elseif (hotelPriceDetailBos[index].breakfastNo <0)>
                      				${(hotelPriceDetailBos[index].breakfastName)!}
                      			<#else>
                      				${(hotelPriceDetailBos[index].breakfastName)!}份&nbsp;
			           			</#if>
							</div>
						<#else>
							<div class="price">-</div>
							<div>-</div>
						</#if>
					</td>
				</#list>
			</tr>
			</tbody>
		</table>
		</#list><#-- end list week -->
		<div class="totlePriceBlock">
			<#if (hotelOrderVo.hotelOrder.hotelOrderGTADetailBo.discountRmb)?? && (hotelOrderVo.hotelOrder.hotelOrderGTADetailBo.discountRmb) gt 0>
			<span style="text-decoration:line-through;margin-right:15px;">原价：&yen;${(hotelOrderVo.hotelOrder.hotelOrderGTADetailBo.totalOrderRoomFeeRmbForPriceDetailYuanWithRoomNo)!}</span>
			</#if>
			
			<#if (hotelOrderVo.externalObject.totalOrderFeeRmbYuan)?? && hotelOrderVo.externalObject.totalOrderFeeRmbYuan?number lt 0>
		       <span style="font-weight:bold;">总价：<span class="totlePrice"><em> &yen;${0-(hotelOrderVo.externalObject.totalOrderFeeRmbYuan?number)!0}</em>元</span></span>
		    <#else>
	        	<span style="font-weight:bold;">总价：<span class="totlePrice"><em> &yen;${(hotelOrderVo.externalObject.totalOrderFeeRmbYuan)!}</em>元</span></span>
		    </#if>
		    
			
		</div>
	</#if><#-- end if validDates -->
	</div>
</div>
<style>
.priceFont{
	font-size:12px;
}
.abroadhotelSalePriceTbl b,div{
	font-size:12px;
}
</style>

</#macro>
