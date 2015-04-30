<#import "/common/mainContainer.ftl" as mc/>
<#import "pagination.ftl" as page/>
<#import "sort.ftl" as sort/>
<@mc.container jsFiles=["common/commonUtils.js",
						"page/hotel/booking/selfpay/hotelValidator.js",
						"common/formValidator.js",
						"page/hotel/hotel_index.js",
						"page/hotel/booking/selfpay/search.js",
						"page/hotel/booking/selfpay/roomListViewTip.js",
						"page/hotel/booking/selfpay/initDate.js",
						"common/autoComplete.js",
						"js/coin-slider.js"
				]
				cssFiles=["/css/store.css" "/css/store_hotel.css" "/css/store_reset.css"  "/css/kendo.store.css" "/css/kendo.common.min.css"]
				menuItem="酒店">
				
	<section id="main_body" class="main-body clearfix">
		<div id="searchPanel" class="hotel_search abroadHotel_search">
			<div class="gray-border" style="position:relative;">
				<h2 class="searchTitle"><em class="abroadhotelIcon"></em>国内现付酒店查询</h2>
				<div class="abroadCondition">
					<label>
						<strong><em>*</em>城市</strong>
						<input id="city_1" type="text" name="cityName" value="${(criteria.cityName)!}" style="width:120px;font-size:12px;" placeholder="城市名称" class="gray-border city-ac-hotel"/>
						<input id="cityId" name="cityIsoCode" type="hidden" value="${(criteria.cityIsoCode)!}"/>
					</label>
					<label>
						<strong><em>*</em>入住日期</strong>
						<input type="text" id="checkinDate_selfpay" name="checkInDate" value="${(criteria.checkInDate)!}" style="width:140px;" readonly class="gray-border" />
					</label>
					<label>
						<strong><em>*</em>离店日期</strong>
						<input type="text" id="checkoutDate_selfpay" name="checkOutDate" value="${(criteria.checkOutDate)!}" style="width:140px;" readonly class="gray-border" k-msg-position="bottom" k-msg-css="{width:170, top:0, left:-5}" />
					</label>
					<label>
					<label>
						<strong>关键字</strong>
						<input type="text" id="keywords" name="keywords" value="${(criteria.keywords)!}"  style="width:130px;font-size:12px;" class="gray-border" />
					</label>
					<a id="btn_search_second" class="adaptiveButton brightRed_btn" style="position:absolute;right:26px;bottom:8px;">
						<span class="left"></span>
						<span  class="center">搜索</span>
						<span class="right"></span>
					</a>
				</div>
			</div>
		</div>
		
		<div id="filterPanel"></div>

		<div class="searching gray-border" id='fruitlessDiv'> <!--fruitless start-->
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
