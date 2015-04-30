<#import "components/pagination.ftl" as pg/>
<#import "components/roomListView.ftl" as roomListView/>
<div id="sortResultPage" class="hotel_result gray-border">
	<#include "components/sortBar.ftl"/>
	<#if (result.propertySearchResult)??>
		<#list result.propertySearchResult as hotel>
			<div class="hotel_resultDetail">
				<div class="hotelPanel">
					<div class="hotelBasicInfo clearfix">
						<div class="hotelImage imgSize">
							<#if (hotel.defaultPhoto)??>
								<a href="javascript:submitDetailForm('${(hotel.id)!}');">
									<img
										src="${hotel.defaultPhoto}"
										onerror="javascript:this.src='${host}/images/hotel_noPic.jpg'"
										style="width:240px;height:180px;"
										alt=""
									/>
								</a>
							<#else>
								<img src="${host}/images/store/hotel_noPic.jpg" style="width:240px;height:180px;" alt="">
							</#if>
						</div>
						<div class="hotelDesc">
							<div class="clearfix gray-dashBorder-bottom">
								<div class="addressName pull-left">
										<div class="hotelName"><a href="javascript:submitDetailForm('${(hotel.id)!}');" style="color: #E94749;font: bold 16px '宋体';">${(hotel.name)!}</a></div>
										<#-- <div style="display:none;" hint="${(hotel.publicRemark)!}"></div>-->
										<div class="hotelAddress">
											<span class="hotelPlace">${(hotel.address)!}</span>
											<#--<span class="hotel-map">
												<a target="_blank" href="http://ditu.google.cn/maps?q=${(hotel.longitude)!}%2C${(hotel.latitude)!}">
													<i></i>
												</a>
											</span>-->
										</div>
								</div>
								<div class="starLevelPrice pull-right">
										<ul class="starLevel">
											<#if (hotel.hotelRating)??>
											<li>
											<div style="margin-left: 0px" class="hotelStarLevel hotelStar<#switch hotel.hotelRating><#case '6'>2_5<#break><#case '7'>3_5<#break><#case '8'>4_5<#break><#default>${hotel.hotelRating}</#switch>">
												<span></span>
											</div>
									<!--      <i class="starLevelPic"></i></li>-->
											<li><div>
												<#switch hotel.hotelRating>
												<#case '2'>二星级及以下/经济酒店<#break>
												<#case '3'>三星级/舒适酒店<#break>
												<#case '4'>四星级/高档酒店<#break>
												<#case '5'>五星级/豪华酒店<#break>
												<#case '6'>准三星级酒店<#break>
												<#case '7'>准四星级酒店<#break>
												<#case '8'>准五星级酒店<#break>
												</#switch>
												<div></li>
												</#if>
										</ul>
										<label class="markPrice">
											<em style="color:#ff6500;">&yen;</em>
											<b style="font-size:26px; color:#ff6500;">${(hotel.lowestPrice)!0}</b>
											<em style="color:#404040;">起</em>
										</label>
								</div>
							</div>
							<p class="seeDetails">
								<#if (hotel.summary)?? && (hotel.summary?length>100)>
									${hotel.summary[1..100]}...
								<#else>
									${(hotel.summary)!'-'}
								</#if>
								<a href="javascript:submitDetailForm('${(hotel.id)!}');" >[查看详情]</a>
							</p>
							<#--
							<div class="icon-list">
							<#if (hotel.facilities)??>
								<#list hotel.facilities as f>
									<#if f.facilityTypeName=="hotelFacility">
										${f.facilityName!},
									</#if>
								</#list>
							</#if>
							</div>
							-->
						</div>
					</div>
			<@roomListView.roomListView hotel=hotel criteria=criteria currentPage="search" />
			</div>
		</div>
	</#list>
	<#else>
		<center>
		<div class="exclamation" >
			<p>没有查询到符合条件的酒店，请检查查询条件，重新查询！</p>
		</div>
	</center>
	</#if>
	<@pg.pagination curPage=page.pageNo allCount=page.totalItemCount pageSize=page.pageSize  />
	<form action="${(basepath)!}/hotel/booking/international/detail" method="post" id="detailForm" target="_blank">
		<input type="hidden" name="hotelId"/>
		<input type="hidden" name="cityIsoCode" value="${(criteria.cityIsoCode)!}"/>
		<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}"/>
		<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}"/>
		<input type="hidden" name="roomNo" value="${(criteria.roomNo)!}"/>
		<input type="hidden" name="roomType" value="${(criteria.roomType)!}"/>
		<input type="hidden" name="nationality" value="${(criteria.nationality)!}" />
		<input type="hidden" name="nationalityName" value="${(criteria.nationalityName)!}" />
	</form>

	<#include "components/orderForm.ftl" />
	<#include "components/tooltipTemplate.ftl" />
</div>
