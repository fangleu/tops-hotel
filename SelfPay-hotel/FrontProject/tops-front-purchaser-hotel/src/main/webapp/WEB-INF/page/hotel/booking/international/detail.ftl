<#import "/common/mainContainer.ftl" as mc/>
<#import "components/roomListView.ftl" as roomListView/>
<@mc.container jsFiles=["common/commonUtils.js",
			"common/baiduditu.js",
			"common/formValidator.js",
			"js/jcarousellite_1.0.1.pack.js",
			"page/hotel/booking/initDate.js",
			"page/hotel/booking/international/detail.js",
			"page/hotel/booking/international/roomListViewTip.js"
			]
		cssFiles=["css/store_hotel.css"]
		localCssFiles=["page/hotel/zcitytip.css", "page/hotel/search.css"]
		menuItem="酒店"
>

<#--<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=13736a10c548d2e533c23af49e72de44"></script>-->
<script type="text/javascript" src="${host}/js/jquery.cycle.all.js"></script>
<section class="main-body clearfix">
	<input id="cityName" type="hidden" value="${(hotel.cityName)!}" />
	<input id="hotelName" type="hidden" value="${(hotel.name)!}" />
	<div class="breadbar">
		<span><i class="icon_seat"></i><a href="${basepath}/hotel/booking/search">国际预付酒店</a></span>&gt;<span>${(hotel.name)!}</span>
	</div>
	<div class="hotelSummary_block clearfix">
		<div class="summary_left" style="min-width:80%;width:80%;">
			<ul class="hotelInfo">
				<li class="hotelName">${(hotel.name)!}</li>
				<li>地址：${(hotel.regionName)!} ${(hotel.cityName)!} <br/>${(hotel.districtName)!} ${(hotel.address)!}</li>
			</ul>
			<ul class="hotelInfo_tips">
				<#if (hotel.hotelRating)??>
				<li>
					<div class="hotelStarLevel hotelStar<#switch hotel.hotelRating><#case '6'>2_5<#break><#case '7'>3_5<#break><#case '8'>4_5<#break><#default>${hotel.hotelRating}</#switch>">
						<span></span>
					</div>
				</li>
				<li><#switch hotel.hotelRating>
						<#case '2'>二星级及以下/经济酒店<#break>
						<#case '3'>三星级/舒适酒店<#break>
						<#case '4'>四星级/高档酒店<#break>
						<#case '5'>五星级/豪华酒店<#break>
						<#case '6'>准三星级酒店<#break>
						<#case '7'>准四星级酒店<#break>
						<#case '8'>准五星级酒店<#break>
					</#switch>
				</li>
				</#if>
				<#--
				<li class="icon-list">
					<#if (hotel.facilities)??>
						<#list hotel.facilities as f>
							<#if f.facilityTypeName=="hotelFacility">
								${f.facilityName!},
							</#if>
						</#list>
					</#if>
				</li>
				-->
			</ul>
		</div>
		<div class="summary_right">
			<span><em class="pricermb">&yen;</em><em class="hotel_Price">${(hotel.lowestPrice)!0}</em> 起</span>
			<div style="margin-top:10px">
				<a class="adaptiveButton brightRed_btn" href="#house_type">
					<span class="left"></span>
					<span class="center center_1">立即预订</span>
					<span class="right"></span>
				</a>
			</div>
		</div>
	</div>
	<!--images start-->
	<ul class="hotelImgs clearfix">
		<li class="hotelLeftBigImg more-pic">
			<a href="javascript:void(0);" target="_blank" name="hotel_img">
			<#if (hotel.defaultPhoto)??>
				<img src="${(hotel.defaultPhoto)!}" >
			<#else>
				<img src="${host}/images/store/hotel_noPic.jpg" />
			</#if>
			<label class="ImgHover"><span>共<b><#if (hotel)?? && (hotel.photos)??>${hotel.photos?size}<#else>0</#if></b>张图片</span></label>
			</a>
		</li>
		<li>
			<#--酒店图片-->
			<#if (hotel)?? && (hotel.photos)??>
				<#assign photoLimit=4 />
				<#list hotel.photos?keys as photo>
				<#if photo_index &lt; photoLimit>
					<#if photo="mapLink">
						<#assign photoLimit=5 />
					<#else>
						<#if ((photo_index+photoLimit) %2 == 0)>
						<ul class="hotelMoSmImgTop clearfix">
						</#if>
							<li>
								<a href="javascript:void(0)" >
								<img data-big-src="hotelPic-big.jpg" src="${(photo)!}" />
								<label class="ImgHover"><span>客户图片</span></label></a>
							</li>
						<#if ((photo_index+photoLimit) %2 != 0) || (photo_index+1 == hotel.photos?keys?size)>
						</ul>
						</#if>
					</#if>
				</#if>
				</#list>
			</#if>
		</li>
		<li class="hotelRightMap">
		<#if hotel?? && (hotel.photos)??>
			<#--<div class="map" id="dituContent" longitude="${(hotel.longitude)!}" latitude="${(hotel.latitude)!}"></div>-->
			<#list hotel.photos?keys as photo>
			<#if photo=="mapLink">
			<#--<iframe src="${(hotel.photos[photo])!}" height=238px width=320px></iframe>-->
			<iframe frameborder="0" width="325px" height="240px" scrolling="no" src="${(hotel.photos[photo])!}" ></iframe>
			</#if>
			</#list>
		</#if>
		</li>
	</ul>
	<!--images end-->

	<!--houseType booking-->
	<div class="hotel-info-item" style="border-bottom:none">
		<ul class="changeList clearfix">
			<li><a href="#hotel_img"><i></i>酒店图片</a></li>
			<li class="liLine"></li>
			<li><a href="#house_type" class="currentLi" ><i></i>房型预定</a></li>
			<li class="liLine"></li>
			<li><a href="#introduce" ><i></i>酒店介绍</a></li>
			<li class="liLine"></li>
			<li><a href="#facility" ><i></i>位置交通</a></li>
		</ul>
		<div class="house_search" >
			<div class="condition" style="padding:0 10px 20px 10px;">
				<form action="${(basepath)!}/hotel/booking/international/detail" method="post" id="detailForm" >
					<input type="hidden" name="hotelId" value="${(hotel.id)!}" />
					<input type="hidden" name="cityIsoCode" value="${(criteria.cityIsoCode)!}" />
					<b style="margin-right:10px;">重新查询价格</b>
					<label><strong>入住日期</strong><input type="text" id="checkinDate" name="checkInDate" value="${(criteria.checkInDate)!}" placeholder="" style="width:118px" class="gray-border"/></label>
					<label><strong>离店日期</strong><input type="text" id="checkoutDate" name="checkOutDate" value="${(criteria.checkOutDate)!}" placeholder="" style="width:118px" class="gray-border"/></label>
					<label style="padding: 3px 0;"><strong>预订房间</strong></label>
						<select data-role="dropdownlist" id="internationalRoomType" name="roomType" style="width:108px;vertical-align:middle;margin-right:5px;margin-top:-3px;" >
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
						<select data-role="dropdownlist" id="internationalRoomNo" name="roomNo" style="width:40px;vertical-align:middle;margin-right:5px;margin-top:-3px;">
							<#list 1..5 as i>
							<option value="${i}" <#if (criteria.roomNo)?? &&(i?string==criteria.roomNo)>selected</#if>>${i}</option>
							</#list>
						</select>

					<label>
						<strong>客人国籍</strong>
						<input type="text" name="nationalityName" value="${(criteria.nationalityName)!}" placeholder="中国" style="width:100px" class="gray-border"/>
						<input type="hidden" id="nationality" name="nationality" value="${(criteria.nationality)!}"/>
					</label>
					<a class="adaptiveButton brightRed_btn medium" href="javascript:$('#detailForm').submit();" style="float:right;margin-top: -4px;" name="house_type">
						<span class="left"></span>
						<span class="center center_1">查询</span>
						<span class="right"></span>
					</a>
				</form>
			</div>
		</div>
		<#if hotel??>
		<@roomListView.roomListView hotel=hotel criteria=criteria currentPage="detail" />
		</#if>
	</div>
	<!--house List End-->

	<!--house introduce-->
	<div class="hotel-info-item">
		<a name="introduce"></a>
		<div class="hotelItemTitle">酒店介绍</div>

		<div class="abroadhotelIntroduce">
			<#if (hotel.reports)??>
			<#list (hotel.reports)?keys as key>
			<#if key="restaurant">
			<h5>餐厅</h5>
			<p>${(hotel.reports[key])!}</p>
			</#if>
			</#list>
			</#if>

			<h5>酒店介绍</h5>
			<p>${(hotel.summary)!'-'}</p>

			<#if (hotel.facilities)??>
			<h5>设施服务</h5>
			<table>
				<#list hotel.facilities as f>
					<#if f_index%2==0>
					<tr>
					</#if>
				<#--    <#if f.facilityTypeName=="hotelFacility">-->
						<td>${f.facilityName!}</td>
				<#--    </#if>-->
					<#if f_index%2==1 || (f_index+1)==hotel.facilities?size>
					</tr>
					</#if>
				</#list>
			</table>
			</#if>
		</div>
	</div>
	<!--house introduce End-->

	<!--house equipments-->
	<div class="hotel-info-item">
		<a name="facility"></a>
		<div class="hotelItemTitle">位置交通</div>

		<div class="abroadhotelEquipment">
		<#if hotel??>
			<div class="hotelMap" style="position: relative;">
				<#list hotel.photos?keys as photo>
				<#if photo=="mapLink">
				<iframe src="${(hotel.photos[photo])!}" height=430 width=450 style="border:none;"></iframe>
				<#--<img src="${(hotel.photos[photo])!}" alt="地图" />-->
				</#if>
				</#list>

				<div class="hotelMap_tips">
					<i></i>
					<div class="innerTit">
						<span>${(hotel.name)!}</span>
					</div>
				</div>
			</div>
			<div class="abroadMapText">
				<#if (hotel.areaInfos)?? && hotel.areaInfos?size gt 0>
				<h5>地区</h5>
				<p>
				<#list hotel.areaInfos as areaInfo>
					${areaInfo!}<br />
				</#list>
				</p>
				</#if>

				<#if (hotel.reports)??>
				<#list (hotel.reports)?keys as key>
				<#if key="location">
				<h5>位置</h5>
				<p>${(hotel.reports[key])!}</p>
				</#if>
				</#list>
				</#if>
			</div>
		</div>
		</#if>
	</div>
	<!--house equipments End-->
</section>

<#include "components/orderForm.ftl" />
<#include "components/tooltipTemplate.ftl" />

<#if (hotel)?? && (hotel.photos)??>
<script type="text/x-kendo-template" id="imageTemplate">
<div class="allImg clearfix">
	<div class="bigImg">
		<a href="javascript:void(0);" class="leftArrow" id="prev">
				<div></div>
		</a>
		<div id="slideshow" class="pics">
				<#assign default="">
				<#if (hotel.defaultPhoto)??>
					<#assign default=hotel.defaultPhoto>
					<img class="pic" src="${(hotel.defaultPhoto)!}"/>
				</#if>
				<#list hotel.photos?keys as photo>
					<#if photo!="mapLink" && photo!=default>
						<img src="${(photo)!}"/>
					</#if>
				</#list>
		</div>
		<div class="font_copyright">Image from VFM Leonardo, Inc.</div>
		<a href="javascript:void(0);"  class="rightArrow" id="next">
			<div> </div>
		</a>
		<span id="scrollText"></span>
		</div>
		<a href="javascript:void(0);"  class="arrowUpblock" id="up">
		<div><i class="arrowUp"></i>
		</div></a>
		<a href="javascript:void(0);" class="arrowDownblock" id="down">
		<div><i class="arrowDown"></i>
		</div></a>
	</div>
</script>
</#if>

<style>
	.map {
		float: right;
		height: 240px;
		width: 325px;
	}
</style>

</@mc.container>
