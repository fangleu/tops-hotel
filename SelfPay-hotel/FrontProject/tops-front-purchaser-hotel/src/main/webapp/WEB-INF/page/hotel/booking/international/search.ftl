<#import "/common/mainContainer.ftl" as mc/>
<@mc.container jsFiles=["common/commonUtils.js",
			"common/baiduditu.js",
			"page/hotel/booking/international/hotelValidator.js",
			"common/formValidator.js",
			"common/autoComplete.js",
			"page/hotel/booking/international/search.js",
			"page/hotel/booking/initDate.js",
			"js/coin-slider.js",
			"page/hotel/booking/international/common.js",
			"page/hotel/booking/international/roomListViewTip.js"
		]
		cssFiles=["css/kendo.reset.css" "/css/store_hotel.css"  "/css/kendo.store.css"]
		localCssFiles=["page/hotel/search.css"]
		menuItem="酒店">
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=13736a10c548d2e533c23af49e72de44"></script>
	<section id="main_body" class="main-body clearfix">
		<div id="searchPanel" class="hotel_search abroadHotel_search">
			<div class="gray-border" style="position:relative;">
				<h2 class="searchTitle"><em class="abroadhotelIcon"></em>国际预付酒店查询</h2>
				<div class="abroadCondition">
					<label>
						<strong><em>*</em>城市</strong>
						<input id="internationalCity" type="text" name="cityName" value="${(criteria.cityName)!}" style="width:197px;font-size:12px;" placeholder="城市名称" class="gray-border city-ac-hotel"/>
						<input id="internationalCityCode" name="cityIsoCode" type="hidden" value="${(criteria.cityIsoCode)!}"/>
					</label>
					<label>
						<strong><em>*</em>入住日期</strong>
						<input type="text" id="checkinDate" name="checkinDate" value="${(criteria.checkInDate)!}" style="width:136px;" readonly class="gray-border" />
					</label>
					<label>
						<strong><em>*</em>离店日期</strong>
						<input type="text" id="checkoutDate" name="checkOutDate" value="${(criteria.checkOutDate)!}" style="width:136px;" readonly class="gray-border" k-msg-position="bottom" k-msg-css="{width:170, top:0, left:-5}" />
					</label>
					<label>
					<strong><em>*</em>预订房间</strong>
					<select data-role="dropdownlist" id="internationalRoomType" name="roomType" style="width:115px;vertical-align:middle;margin-right:5px;">
						<#assign roomType="">
						<#if (criteria.roomType)??>
							<#assign roomType=criteria.roomType>
						</#if>
							<option value="TS" <#if roomType=="TS">selected</#if>>单人用双人房</option>
							<option value="DB" <#if roomType=="DB">selected</#if>>双人大床房</option>
							<option value="TB" <#if roomType=="TB">selected</#if>>双人两床房</option>
							<option value="SB" <#if roomType=="SB">selected</#if>>单人房</option>
							<option value="TR" <#if roomType=="TR">selected</#if>>三人房</option>
							<option value="Q" <#if roomType=="Q">selected</#if>>四人房</option>
					</select>
					<select data-role="dropdownlist" name="roomNo" id="internationalRoomNo" style="width:45px;vertical-align:middle;margin-right:3px">
							<#list 1..5 as i>
								<#if i?string == (criteria.roomNo)>
									<option value="${(criteria.roomNo)!}" selected>${(criteria.roomNo)!}</option>
								<#else>
									<option value="${i}">${i}</option>
								</#if>
						</#list>
					</select>
					</label>
					<label>
						<strong>客人国藉</strong>
						<input type="text" id="nationalityName" name="nationalityName" value="${(criteria.nationalityName)!}" style="width:197px" class="gray-border"/>
						<input type="hidden" id="nationality" name="nationality" value="${(criteria.nationality)!}"/>
					</label>
					<label>
						<strong>关键字</strong>
						<input type="text" id="hotelName" name="hotelName" value="${(criteria.hotelName)!}"  style="width:346px;font-size:12px;" class="gray-border"/>
					</label>
					<a id="btn_search_second" class="adaptiveButton brightRed_btn" style="position:absolute;right:26px;bottom:8px;">
						<span class="left"></span>
						<span class="center">搜索</span>
						<span class="right"></span>
					</a>
				</div>
			</div>
		</div>
		<div class="searching gray-border" id='fruitlessDiv'>  <!--fruitless start-->
			<div class="searching_tip">
				<div class="tip_bg"></div>
				<div class="tip_text">正在为您查询，请稍候...</div>
			</div>
		</div>
	</section> <!--bottom end-->

	<style>
	.map {
		float: right;
		height: 240px;
		width: 480px;
	}
</style>
<#include "/kendoTemplate/kendoCityPop.ftl" />
</@mc.container>
