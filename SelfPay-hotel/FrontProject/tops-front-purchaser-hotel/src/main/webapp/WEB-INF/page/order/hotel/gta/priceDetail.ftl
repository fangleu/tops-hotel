<#--	价格日历		-->
<#macro priceDetail criteria itemPrice bookingClassDailyRates=[]>
<div id="priceCale" class="layerGrayBlock" style="z-index:100;display:none;font-size: 12px; position:absolute;">
	<#if (criteria.validDates)??>
	<#assign validDates=criteria.validDates />
	<div class="layerGrayContainer_noArrow" style="width:560px">
		
		<p class="abroadhotelPriceBlock">
			<span><#-- 示例：2014/04/03 周四 至 2014/04/11 周五 8天 -->
				${(criteria.checkInDate?replace("-","/"))!}
				周${(criteria.checkInDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))}
				至 ${(criteria.checkOutDate?replace("-","/"))!}
				周${(criteria.checkOutDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))}
			</span>
			<span style="float:right;">
				<i>${bookingClassDailyRates?size}</i>晚 <i>${(criteria.roomNo)!}</i>间
			</span>
		</p>
		<#assign weekCount = (validDates?size)/7 />
		<#list 1..weekCount as week>
		<table class="abroadhotelSalePriceTbl">
			<thead>
			<tr>
				<th class="first" style="width:60px;text-align:left"><b>第${week}周</b></th>
				<#list 0..6 as i>
				<#assign day = validDates[i]?date("yyyy-MM-dd")?string("EEE") />
				<th class="<#if day="星期六"|| day="星期日">weekend</#if><#if i=6> last</#if>" style="text-align:left">
					周${day?substring(2,3)}
				</th>
				</#list>
			</tr>
			</thead>
			<tbody>
			<tr height=30>
				<td class="first">
					<div><b>价格</b></div>
					<div><b>餐食</b></div>
				</td>
				<#list 7*(week-1)..(7*week-1) as index>
				<td style="width:60px;">
				<#if (index &lt; bookingClassDailyRates?size)>
					<div class="price" ruleId="${(bookingClassDailyRates[index].policyId)!"-"}">
						&yen;${(bookingClassDailyRates[index].sellingRateRMB)!"-"}
					</div>
					<div>
						<#if (bookingClassDailyRates[index].breakFastOptionalService)??>
							${(bookingClassDailyRates[index].breakFastOptionalService[0].remark)!}
						<#else>
							无
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
			<#if (itemPrice.includedOfferDiscount)?? && itemPrice.includedOfferDiscount &gt; 0>
			<span style="text-decoration:line-through;margin-right:15px;">原价：&yen;${(itemPrice.grossWithoutDiscount)!}</span>
			</#if>
			<span style="font-weight:bold;">总价：<span class="totlePrice"><em> &yen;${(itemPrice.gross)!}</em>元</span></span>
		</div>
	</div>
	</#if><#-- end if validDates -->
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
