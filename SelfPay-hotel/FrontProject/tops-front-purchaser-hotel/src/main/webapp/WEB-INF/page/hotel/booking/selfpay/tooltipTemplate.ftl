<#-- 酒店规定 -->
<#macro stipulateTemplate Conditions bkid>
<#if Conditions??>
	<div id="${bkid}" class="layerGrayBlock" style="display:none;position:absolute;z-index:2;width:250px;border:2px solid #AAAAAA;
	color:#555555;margin-left:-100px;margin-top:25px;background-color:#F5F4F4;">
		<div class="abroadtooltipsCont">
			<div class="abroadtoolclickArrow"></div>
			<p><#noescape>${Conditions}</#noescape></p>
		</div>
	</div>
</#if>
</#macro>


<#macro guaranteeDesc Conditions bkid>
<#if Conditions??>
	<div id="ruleDesc_${bkid}" class="layerGrayBlock" style="display:none;position:absolute;z-index:2;width:250px;border:2px solid #AAAAAA;
	color:#555555;margin-left:-116px;margin-top:3px;background-color:#F5F4F4;">
		<div class="abroadtooltipsCont">
			<div class="abroadtoolclickArrow"></div>
			<p><#noescape>${Conditions}</#noescape></p>
		</div>
	</div>
</#if>
</#macro>

<#macro giftsTemplate giftsList bki>
<#if giftsList??>
	<div id="gitf_${bki}" class="layerGrayBlock" style="display:none;position:absolute;z-index:2;width:250px;border:2px solid #AAAAAA;
	color:#555555;margin-left:-100px;margin-top:25px;background-color:#F5F4F4;">
		<div class="abroadtooltipsCont">
			<div class="abroadtoolclickArrow"></div>
			<#list giftsList as gifts>
			<p><#noescape>${gifts.description}</#noescape></p>
			</#list>
		</div>
	</div>
</#if>
</#macro>


<#macro stipulate dayPriceList weekList bki totalPrice>
<div id="rate_${bki}" class="k-animation-container"
	 style="display: none; position: absolute; margin-top: 3.5px; margin-left: -221px; width: 500px;">
	<div
		class="k-widget k-tooltip lineWeight k-popup k-group k-reset k-state-border-up k-tooltip-dir-n">
		<div class="k-tooltip-content">
			<div class="layerGrayContainer_noArrow">
				<p class="abroadhotelPriceBlock">
					<span>  ${(criteria.checkInDate?replace("-","/"))!}
						周${(criteria.checkInDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))} 至
						${(criteria.checkOutDate?replace("-","/"))!}
						周${(criteria.checkOutDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))}</span><span
						style="float: right;"> <i>${dayPriceList.dayPriceList?size}</i>晚<i id="room12">1</i>间
					</span>
				</p>
				<#assign weekCount = (dayPriceList.dayPriceList?size-1)/7+1 />
			<#list 1..weekCount as week>
				<table class="abroadhotelSalePriceTbl">
					<thead>
					<tr >
						<th style="width: 60px;text-align:left;"><b>第${week}周</b></th> 
						<#list weekList as weekList>
							<#if weekList="周六"|| weekList ="周日">
								<th  style="width:60px;color: #e94749;text-align:left;">
							<#else>
								<th  style="width:60px;text-align:left;">
							</#if>
							${weekList}
							</th>
						</#list>
					</tr>
					</thead>
					<tbody>
						<tr>
							<td class="first" style="text-align:left;">
								<div>
									<b><span style="font-weight:bold;">价格</span></b>
								</div>
								<div>
									<b><span style="font-weight:bold;">早餐</span></b>
								</div>
							</td> 
							<#list 7*(week-1)..(7*week)-1 as index>
								<td style="text-align:left;">
									<#if dayPriceList.dayPriceList[index]??>
										<div class="price" val="${dayPriceList.dayPriceList[index].price}">${dayPriceList.dayPriceList[index].price}</div>
										<div>${dayPriceList.dayPriceList[index].breakfast}</div>
									<#else>
										<div>-</div>
										<div>-</div>
									</#if>
								</td> 
                           </#list>
						</tr>
					</tbody>
				</table>
				</#list>
				<div class="totlePriceBlock">
					<span style="font-weight: bold;">总价：<span class="totlePrice"><em
							id="totalPrice4"> ${(totalPrice)!}</em>元</span></span>
				</div>
				<div class="hotelCreateOrderTips" style='text-align:left;'>
					<table >
						<#noescape>${(dayPriceList.priceRuleDesc)!}</#noescape>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</#macro>

