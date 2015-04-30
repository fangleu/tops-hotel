<#import "/common/HomeMainContainer.ftl" as mc/>
<@mc.container jsFiles=["page/hotel/hotel_index.js", "common/autoComplete.js","js/coin-slider.js",
						"common/formValidator.js",
						"page/hotel/booking/search/cityList.js", "page/hotel/booking/search/hotHotel.js", "page/hotel/booking/search/zcitytip.js",
						"js/jquery.cycle.all.js",
						"page/hotel/booking/international/common.js",
						"page/hotel/booking/selfpay/initDate.js"]
				cssFiles=["css/coin-slider-styles.css"] localCssFiles=["page/hotel/zcitytip.css"]
				menuItem="酒店">

	<div class="hotelHomePageBG clearfix">
	</div>
		<div class="annPrompt prompt-top-pass" businessType ="酒店"></div>

	<div class="main-wrapper main-top-pass">
		<section class="querytab querytab-hotel">
			<div class="querytab-wrapper col clearfix querytab-top-pass">
			<aside class="querytab-controller">
			<ul>
				<li><b data-role="ctype" data-index="1" data-group="qtab" class="current">国内预付</b></li>
				<li><b data-role="ctype" data-index="3" data-group="qtab">国内现付</b></li>
				<li><b data-role="ctype" data-index="2" data-group="qtab">国际预付</b></li>
			</ul>
			</aside>
			<div class="querytab-content col">
			<form id="hotelSearchForm" name="hotelSearchForm"
				action="${basepath}/hotel/booking/search/search" val="${(basepath)!}/hotel/booking/search/detail" method="post">
				<input type="hidden" name="hotelId" />
				<div class="querytab-hotel-content" data-role="ctype-item" data-index="1" data-group="qtab">
					<h3>国内 预付酒店</h3>
					<span class="text1" style="" data-role="ctype-item" data-group="trip_type" data-index="c2"> 团队单请咨询：<b class="tel">400-720-6666</b></span>
						<ul>
							<li>
								<table class="default_search">
									<colgroup>
										<col class="col1">
										<col class="col2">
										<col class="col3">
										<col class="col4">
									</colgroup>
									<tr>
										<th>城市</th>
										<td>
											<input class="city" type="text"  id="city" name="cityName" value="${(cityName)!}" codeInputId="cityCode" placeholder="拼音/城市码/中文" style="width:150px" />
											<input type="hidden" id="cityIsoCode" name="cityIsoCode" value="${(cityIsoCode)!}"/>
										</td>
									</tr>
									<tr>
										<th>入住日期</th>
										<td id="confirm_date">
										<input class="startDate" type="text" id="checkinDate" name="checkInDate" value="${(checkinDate)!}" readonly>
										</td>
										<th>离店日期</th>
										<td>
										<input class="endDate" type="text" id="checkoutDate" name="checkOutDate" value="${(checkoutDate)!}" class="menology gray-border second" readonly>
										</td>
									</tr>
									<tr>
										<th>预定间数</th>
										<td>
											<select data-role="dropdownlist" name="roomNo" id="roomNo" style="width:152px;">
												<#list 1..5 as i>
													<option value="${i}">${i}</option>
												</#list>
											</select>
										</td>
									</tr>
									</table>
							</li>
							<li>
							<h4 class="underline-title"><span><i class="icon icon-arrows"></i>高级搜索</span></h4>
							<table class="hotel_advanced_search">
								<colgroup>
									<col class="col1">
									<col class="col2">
									<col class="col3">
									<col class="col4">
									</colgroup>
								<tr>
									<th>关键字</th>
									<td>
										<input type="text" id="hotelName" name="hotelName" value="" placeholder="酒店名称、商圈、地标">
									</td>
									<th>酒店星级</th>
									<td>
										<select id="hotelRatings" name="hotelRatings" style="width:152px;" data-role="dropdownlist">
											<option value="all">全部</option>
											<#list hotelRatings as Rating>
												<option value =${(Rating.rating)!}>${(Rating.nameCn)!}</option>
											</#list>
										</select>
									</td>
								</tr>
							</table>
							</li>
						</ul>

							<div class="btn_area">
								<button type="button" id="btn_search_first" class="btn btn-c1 btn-s1">搜  索</button>
							</div>
				</div>
				</form>
				<div class="querytab-hotel-content" style="display: none;height: 332px;" data-role="ctype-item" data-index="3" data-group="qtab" >
				<form id="selfpayhotelSearchForm" name="selfpayhotelSearchForm"
				action="${basepath}/hotel/booking/selfpay/selfPaySearch" method="post">
                    <h3>国内 现付酒店</h3>
                    <span class="text1" style="" data-role="ctype-item" data-group="trip_type" data-index="c2"> 团队单请咨询：<s class="tel">400-720-6666</s></span>
                    <ul>
                        <li>
                            <table class="default_search">
                                <colgroup>
                                    <col class="col1">
                                    <col class="col2">
                                    <col class="col3">
                                    <col class="col4">
                                </colgroup>
                                <tr>
                                    <th>出发城市</th>
                                   <td>
											<input class="city_1" type="text"  id="city_1" name="cityName" value="北京" codeInputId="cityCode" placeholder="拼音/城市码/中文" style="width:150px" />
											<input type="hidden" id="cityIsoCode1" name="cityIsoCode" value="CN010"/>
										
										</td>
                                    
                                </tr>
                                <tr>
                                    <th>入住日期</th>
                                    <td>
										<input class="startDate" type="text" id="checkinDate_selfpay" name="checkInDate" value="${(checkinDate)!}">
										</td>
                                    <th>离店日期</th>
                                   <td><input class="endDate" type="text" id="checkoutDate_selfpay" name="checkOutDate" value="${checkoutDate!}" class="menology gray-border second" readonly>
                                   </td>
                                </tr>
                            </table>
                        </li>
                        <li>
                            <h4 class="underline-title"><span><i class="icon icon-arrows"></i>高级搜索</span></h4>
                            <table class="hotel_advanced_search">
                                <colgroup>
                                    <col class="col1">
                                    <col class="col2">
                                    <col class="col3">
                                    <col class="col4">
                                </colgroup>
                                <tr>
                                    <th>关键字</th>
                                    <td>
                                        <input type="text" name="keywords" placeholder="酒店名称，商圈，地标">
                                    </td>
                                    <th></th>
                                    <td style="padding-left: 30px;">
                                        <button  id="btn_selfpay_search_first" type="button" class="btn btn-c1 btn-s1">搜  索</button> 
                                    </td>
                                </tr>
                            </table>
                        </li>
                    </ul>
                    </form>
                </div>
				<div class="querytab-hotel-content" style="display:none;" data-role="ctype-item" data-index="2" data-group="qtab">
					<form 	id="internationalHotelSearchForm" name="internationalHotelSearchForm"
							action="${basepath}/hotel/booking/international/search"
							val="${(basepath)!}/hotel/booking/international/detail" method="post">
					<h3>港澳台 · 国际 预付酒店</h3>
					<span class="text1" style="" data-role="ctype-item" data-group="trip_type" data-index="c2"> 团队单请咨询：<b class="tel">400-720-6666</b></span>
					<ul>
						<li>
							<table class="default_search">
								<colgroup>
									<col class="col1">
									<col class="col2">
									<col class="col3">
									<col class="col4">
								</colgroup>
								<tr>
									<th>城市</th>
									<td>
										<input class="city_ac" type="text" id="internationalCity" placeholder="城市名称" name="cityName" value="香港" style="width:150px" />
										<input type="hidden" id="internationalCityCode" name="cityIsoCode" value="HKG_C" />
									</td>
								</tr>
								<tr>
									<th>入住日期</th>
									<td><input class="startDate" type="text" id="checkinDate2" name="checkInDate" value="${checkinDate!}"/></td>
									<th>离店日期</th>
									<td><input class="endDate" type="text" id="checkoutDate2" name="checkOutDate" value="${checkoutDate!}"/></td>
								</tr>
								<tr>
									<th>预定房间</th>
									<td>
										<select id="internationalRoomType" name="roomType" data-role="dropdownlist" style="width:152px;">
											<option value="TS">单人用双人房</option>
											<option value="DB" selected>双人大床房</option>
											<option value="TB">双人两床房</option>
											<option value="SB">单人房</option>
											<option value="TR">三人房</option>
											<option value="Q">四人房</option>
										</select>
									</td>
									<th>间数</th>
									<td>
										<select name="roomNo" id="internationalRoomNo" data-role="dropdownlist" style="width:152px;">
											<#list 1..4 as i>
											<option value="${i}">${i}</option>
											</#list>
										</select>
									</td>
								</tr>
								</table>
						</li>
						<li>
						<h4 class="underline-title"><span><i class="icon icon-arrows"></i>高级搜索</span></h4>
						<table class="hotel_advanced_search">
							<colgroup>
								<col class="col1">
								<col class="col2">
								<col class="col3">
								<col class="col4">
								</colgroup>
							<tr>
								<th>客人国籍</th>
								<td>
									<input type="text" id="nationalityName" name="nationalityName" value="China 中国" style="width:150px"/>
									<input type="hidden" id="nationality" name="nationality" value="CN"/>
								</td>
								<th>关键字</th>
								<td>
									<input type="text" id="hotelName" name="hotelName" >
								</td>
							</tr>
						</table>
						</li>
					</ul>

					<div class="btn_area">
						<button id="btn_international_search_first" type="button" class="btn btn-c1 btn-s1">搜  索</button>
					</div>
					</form>
				</div>
				
				 
				
				
			</div>
			</div>
		</section>

<script>
	$(function(){
		$.role_ctype({
			changeClass:'current'
		});
		kendo.init('body');
	})
</script>


<section class="main-body clearfix"> <!--body start-->

<!--[if lte IE 7]>
<style type="text/css">
.hoverIcon{position:relative;overflow:hidden;}
.hoverIcon a{position:absolute;left:50%;top:50%;}
.hoverIcon a img{position:relative;left:-50%;top:-50%;}
</style>
<![endif]-->
<section class="hotHotelBlock clearfix">
<!--hotHotel start-->
	<div class="hotHotelContent" id="hotHotelContent" style="height: 350px;">
		<div class="blockTopTitle clearfix" id="hotCityBanner">
			<b>热门酒店推荐</b>
			<ul class="hotelPlace">
			</ul>
		</div>
	</div>
<!--hotHotel end-->

</section>

<section class="hotCityBrand clearfix">
<!--hotCityBrand start-->
<div class="hotCityBrandContent clearfix">
	<div class="hotCityContent">
	<div class="blockTopTitle clearfix">
			<b>热门城市</b>
	</div>

	<ul class="clearfix" style="margin-bottom:8px">
		<li><a href="javascript:interCitySearch('香港','HKG_C');"><img src="${host}/images/store/homeStore/xianggang.jpg" title="香港 购物天堂"/>
			<label class="hotCityImgHover"><span class="searchIcon"></span></label>
		</a></li>
		<li><a href="javascript:void(0);" style="cursor:default;"><img src="${host}/images/store/homeStore/homeHotCityImg02.jpg" title=""/>
		</a></li>
	</ul>
	<ul class="clearfix">
		<li><a href="javascript:citySearch('昆明','CN0871');"><img src="${host}/images/store/homeStore/homeHotCityImg03.jpg" title="昆明"/>
			<label class="hotCityImgHover"><span class="searchIcon"></span></label>
		</a></li>
		<li><a href="javascript:citySearch('深圳','CN0755');"><img src="${host}/images/store/homeStore/shenzhen.jpg" title="深圳"/>
			<label class="hotCityImgHover"><span class="searchIcon"></span></label>
		</a></li>
		<li><a href="javascript:citySearch('大连','CN0411');"><img src="${host}/images/store/homeStore/homeHotCityImg05.jpg" title="大连"/>
			<label class="hotCityImgHover"><span class="searchIcon"></span></label>
		</a></li>
	</ul>

	</div>

	<div class="hotelBrandContent">
	<div class="blockTopTitle clearfix" style="padding-left:0;">
			<b>酒店品牌推荐</b>
	</div>
	<ul class="clearfix">
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand01.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand02.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand03.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand04.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand05.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand06.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand07.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand08.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand09.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand10.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand11.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand12.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand13.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand14.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand15.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand16.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand17.png" title=""/></a></li>
		<li class="hoverIcon"><a href="javascript:void(0);"><img src="${host}/images/store/homeStore/hotelBrand18.png" title=""/></a></li>
	</ul>
	</div>
</div>

<!--hotCityBrand end-->

</section>

<!--homePage Banner start-->
<div class="homePageBanner clearfix"><img src="${host}/images/store/homeStore/homeBannerBottom.jpg" title="天地行 钻石服务" /></div>
<!--homePage Banner end-->


<!--<script type="text/javascript">
$(function(){
	$(".pnrImport").click(function () {
	$(".pnrImportInfor,.enquiryInfor-gray").show();
	$(".pnrImport,.enquiryInfor,.enquiryInfor").hide();
		});
	$(".enquiryInfor-gray").click(function () {
	$(".enquiryInfor,.pnrImport").show();
	$(".pnrImportInfor,.enquiryInfor-gray").hide();
		})
})
</script>-->

</section> <!--body end-->
	<#include "/kendoTemplate/kendoCityPop.ftl" />

<script type="text/x-kendo-template" id="cityNameTemplate">
	<li #if(data.index == '1'){# class="li_current" #}#><a href="javascript:void(0);">#=data.cityName#</a></li>
</script>

<script type="text/x-kendo-template" id="hotelImageTemplate">
	<div class="hotHotel_show clearfix" #if(data.index != 1){# style="display:none;" #}#>
		#if(data["hotelInfoList"].length > 0){#
			#for(var i=0; i<data["hotelInfoList"].length; i++){#
				<dl #if(i == data["hotelInfoList"].length - 1){# style="margin-right:0px;" #}#>
					<dt>
						<a href=#if(data["hotelInfoList"][i]["isSuspend"]){#"javascript:void(0);"#}else{#"javascript:detailSubmit('#=data["hotelInfoList"][i]["key"]#');"#}#>
							<img src="${mediaserver}/imageservice?mediaImageId=#=data["hotelInfoList"][i]["defaultPhoto"]#&amp;mediaType=image" onerror="javascript:this.src='${host}/images/hotel_noPic.jpg'">
							<i class="bgShade"></i>
						</a>
					</dt>
					<dd>
						<div class="hotelName">
							<a href=#if(data["hotelInfoList"][i]["isSuspend"]){#"javascript:void(0);"#}else{#"javascript:detailSubmit('#=data["hotelInfoList"][i]["key"]#');"#}# title="#=data["hotelInfoList"][i].hotelName#">#=data["hotelInfoList"][i].hotelName#</a>
						</div>
						<div>
							#if(isNotBlank(data["hotelInfoList"][i].hotelStar)){#
								<span class="hotelStar"><a href=#if(data["hotelInfoList"][i]["isSuspend"]){#"javascript:void(0);"#}else{#"javascript:detailSubmit('#=data["hotelInfoList"][i]["key"]#');"#}#><em class="#=getStarClass(data["hotelInfoList"][i])#"></em></a></span>
							#}#
							<span class="price"><a href=#if(data["hotelInfoList"][i]["isSuspend"]){#"javascript:void(0);"#}else{#"javascript:detailSubmit('#=data["hotelInfoList"][i]["key"]#');"#}#><em>￥</em>#=data["hotelInfoList"][i].hotelPrice#</a></span>
						</div>
					</dd>
				</dl>
			#}#
		#}else{#
			没有热门酒店推荐
		#}#
	</div>
</script>
</@mc.container>
