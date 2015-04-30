<#import "/common/mainContainer.ftl" as mc/>
<@mc.container jsFiles=["common/commonUtils.js" "common/baiduditu.js" "page/hotel/booking/search/hotelValidator.js",
			"common/formValidator.js", "common/autoComplete.js","page/hotel/booking/search/search.js",
			"page/hotel/booking/search/cityList.js","page/hotel/booking/search/zcitytip.js","js/coin-slider.js",
			"page/hotel/booking/initDate.js"]
		cssFiles=["css/kendo.reset.css" "/css/store_hotel.css"  "/css/kendo.store.css"]
		localCssFiles=["page/hotel/zcitytip.css", "page/hotel/search.css"]
		menuItem="酒店">

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=13736a10c548d2e533c23af49e72de44"></script>
    <section id="main_body" class="main-body clearfix">
        <div id="searchPanel" class="hotel_search">
        	<div style="border-bottom:1px solid #ececec;">
        		<h2 class="searchTitle" style="background:#fff;"><em class="hotelIcon"></em>国内预付酒店查询</h2>
	            <div class="condition" style="background:#fff;">
	            <input type="hidden" id="hotelRating" value="${(criteria.hotelRatings[0])!}"/>
	                <label>
	               	<strong><em>*</em>城市</strong><input id="city" type="text" name="cityName"  style="width:125px;font-size:12px;" placeholder="拼音/城市码/中文" class="gray-border city-ac-hotel" value="${(criteria.cityName)!}"/>
	
	                	<input id="cityCode" name="cityIsoCode" type="hidden" value="${(criteria.cityIsoCode)!}"/>
	                </label>
	                <label>
	                	<strong><em>*</em>入住日期</strong><input type="text" id="checkinDate" name="" placeholder="" style="width:95px;" readonly class="gray-border" value="${(criteria.checkInDate)!}" />
	                </label>
	                <label>
	                	<strong><em>*</em>离店日期</strong><input type="text" id="checkoutDate" name="" placeholder="" style="width:95px;" readonly class="gray-border" value="${(criteria.checkOutDate)!}" k-msg-position="bottom" k-msg-css="{width:170, top:0, left:-5}" />
	                </label>
	                 <label>
	                   <strong><em>*</em>预订间数</strong><select data-role="dropdownlist" name="roomNo" id="roomNo" style="width:40px;vertical-align:middle;">
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
	                	<span>关键字</span>&nbsp;<input type="text" id="hotelName" name="hotelName" placeholder="酒店名称、商圈、地标" style="width:150px;font-size:12px;" class="gray-border" value="${(criteria.hotelName)!}"/>
	                </label>
	                <a id="btn_search_second" class="adaptiveButton brightRed_btn">
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

    <#include "/kendoTemplate/kendoCityPop.ftl" />
    
<script type="text/x-kendo-template" id="toshow_rule">
	<div class="toshow_rule">
		<div>
			<span class="join_left">入住时间：</span><span>#=checkinTime#</span>
			<span class="join_left">离店时间：</span><span>#=checkoutTime#</span>
		</div>
		<div>
			其他政策：<span>#=cancelPolicy#</span>
		</div>
	</div>
</script>
    
<script type="text/x-kendo-template" id="toshow_other">
	<div class="toshow_other">
		<span>#=other#</span>
	</div>
</script>

<script type="text/x-kendo-template" id="mapTemplate">
	<div class="map" id="dituContent" longitude="" latitude=""></div>   	
</script>

<div id="confirmWindow" style="padding:15px 30px;display:none;">
	<span id="window_tip">您的预订日期不满足选择产品的条件，请修改预定日期或选择其他产品</span>
	<div style="text-align:right;padding-top:10px">
	    <a href="javascript:;"  class="adaptiveButton mediumOrange_btn updateDate">
	        <span class="left"></span>
			<span class="center center_1">修改日期</span>
			<span class="right"></span>
		</a>
	   	<a href="javascript:void(0);" onclick="javascript:w.close();" class="adaptiveButton mediumOrange_btn" >
	        <span class="left"></span>
			<span class="center center_1">选择其他产品</span>
			<span class="right"></span>
		</a>
	</div>
</div>
<style>
  	.map {
	    float: right;
	    height: 240px;
	    width: 480px;
	}
  </style>

</@mc.container>