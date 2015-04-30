<#macro modifyDateContainerTemplate checkInDate checkOutDate>
<div class="modifyDateContainer" style="position: absolute;width:352px;display:none;">
	<div class="rowNumber">
		入住时间
		<input type="text" id="checkinDate_selfpay" name=""  value="${(criteria.checkInDate)!}" placeholder="" style="height: 23px;width:80px;" class="gray-border"/> 至 
		<input type="text"  id="checkoutDate_selfpay"  name="" value="${(criteria.checkOutDate)!}" placeholder="" style="height: 23px;width:80px;" class="gray-border"/>
		<a class="adaptiveButton brightOrange_btn" id="adaptiveButtonDate">
			<span class="left"></span>
			<span class="center center_1">确定</span>
			<span class="right"></span>
		</a>
	</div>
	<div class="rowBg">
		<span class="promptTxt">入住20天以上</span>请联系客服请联系客服
	</div>
</div>
</#macro>

<#macro stipulate dayPriceList weekList>
<div class="k-animation-container" style="display: none; position: absolute; top: 151.5px; left: 197px; width: 1000px;">
	<div class="k-widget k-tooltip lineWeight k-popup k-group k-reset k-state-border-up k-tooltip-dir-n">
		<div class="k-tooltip-content">
			<div class="layerGrayContainer_noArrow">
				<p class="abroadhotelPriceBlock">
					<span>  ${(criteria.checkInDate?replace("-","/"))!}
						周${(criteria.checkInDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))} 至
						${(criteria.checkOutDate?replace("-","/"))!}
						周${(criteria.checkOutDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))}</span><span
						style="float: right;"> <i>${dayPriceList.dayPriceList?size}</i>晚<i id="roomNoInPriceDetailBlock">${roomNumber}</i>间
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
							<th style="width:60px;color: #e94749;text-align:left;">
						<#else>
							<th style="width:60px;text-align:left;">
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
					<span style="font-weight: bold;">总价：
						<span class="totlePrice"><em>${totalPrice}</em>元</span>
					</span>
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

<#macro bankList>
    <div class="layerGrayContainer_noArrow floatLayer" style="top: 417.5px; left: -3px; z-index: 10; position: absolute;  display: none;">
        <div class="bankChartBox">
            <div class="bankChart"><span>境内卡</span><i class="close">&nbsp;</i></div>
            <div class="inland_bank">
                <ul>
                    <li class="checked bankClass"><span class="business bankClass1" val="招商银行" code="1"></span><i></i></li>
                    <li class="bankClass"><span class="commerce bankClass1" val="中国工商银行" code="3"></span><i></i></li>
                    <li class="bankClass"><span class="construct  bankClass1" val="中国建设银行" code="4"></span><i></i></li>
                    <li class="bankClass"><span class="citic bankClass1" val="中信银行" code="5"></span><i></i></li>
                    <li class="bankClass"><span class="postal bankClass1" val="中国邮政储蓄银行" code="27"></span><i></i></li>
                    <li class="bankClass"><span class="china bankClass1" val="中国银行" code="2"></span><i></i></li>
                    <li class="bankClass"><span class="agriculture bankClass1" val="中国农业银行" code="28"></span><i></i></li>
                    <li class="bankClass"><span class="shenzhen bankClass1" val="深圳发展银行" code="7"></span><i></i></li>
                    <li class="bankClass"><span class="tsation bankClass1" val="交通银行" code="10"></span><i></i></li>
                    <li class="bankClass"><span class="spdb bankClass1" val="上海浦东发展银行" code="22"></span><i></i></li>
                    <li class="bankClass"><span class="brighten bankClass1" val="中国光大银行" code="9"></span><i></i></li>
                    <li class="bankClass"><span class="shanghai bankClass1" val="上海银行" code="26"></span><i></i></li>
                </ul>
            </div>

            <div class="bankChart"><span>境外卡</span></div>
            <div class="abroad_bank">
                <ul>
                    <li class="bankClass"><span class="card_visa bankClass1" val="VISA" code="11"></span><i></i></li>
                    <li class="bankClass"><span class="card_master bankClass1" val="MASTERCARD" code="12"></span><i></i></li>
                    <li class="bankClass"><span class="card_amex bankClass1" val="AMEX" code="13"></span><i></i></li>
                    <li  class="bankClass"><span class="card_jcb bankClass1" val="JCB" code="15"></span><i></i></li>
                </ul>
            </div>
        </div>
    </div>
</#macro>
