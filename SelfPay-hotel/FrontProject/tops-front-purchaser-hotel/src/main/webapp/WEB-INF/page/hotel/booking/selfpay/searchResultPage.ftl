<#import "sort.ftl" as sort/>
<#import "pagination.ftl" as pg/>
<#import "roomListView.ftl" as roomlist/>
<div id="sortResultPage" class="hotel_result gray-border">
<div>
<@sort.sort/>
</div>
<#if result??>
	<#if !isSalesPolicyNull>
		<#if (result.hotelItemList)?? && (result.hotelItemList?size > 0)>
			<#list result.hotelItemList as hotel>
			<#if hotel_index < ((page.pageSize)*(page.pageNo))>			
				<div class="hotel_resultDetail">
					<div class="hotelPanel">
					
						<div class="hotelBasicInfo clearfix">
							<div class="hotelImage imgSize">
							<#if (hotel.thumbNailUrl)??>  
									<a href="javascript:submitDetailForm('${(hotel.hotelId)!}');">
										<img 
											src="${hotel.thumbNailUrl}"  
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
											<div class="hotelName"><a href="javascript:submitDetailForm('${(hotel.hotelId)!}');" style="color: #E94749;font: bold 16px '宋体';">${(hotel.hotelName)!}</a></div>
											<div class="hotelAddress">
												<span class="hotelPlace">${(hotel.address)!}</span>
											</div>
									</div>
									
								<div class="starLevelPrice pull-right">
								<ul class="starLevel">
								<#if (hotel.starRate)??>
									<li><div style="margin-left: 0px" class="<#if (hotel.starRate>0) && (hotel.starRate<=5)>hotelStarLevel hotelStar${hotel.starRate}</#if>"><span></span></div></li>
									<li><div>${(hotel.starDetail)!}</div></li>
								</#if>
								</ul>
									<label class="markPrice">
										<em style="color:#ff6500;">&yen;</em>
										<b style="font-size:26px; color:#ff6500;">${(hotel.lowRate)!0}</b>
										<em style="color:#404040;">起</em>
									</label>
									</div>
								</div>
								<p class="seeDetails">
									<#if (hotel.hotelDesc)?? && (hotel.hotelDesc?length gt 100)>
										${(hotel.hotelDesc[0..100])}...
										<#else>
										${(hotel.hotelDesc)!'-'}
									</#if>
									<a href="javascript:submitDetailForm('${(hotel.hotelId)!}');" >[查看详情]</a>
								</p>
								
							 <div class="icon-list">
	                             <#if (hotel.facilityType)??>
		                             <#list hotel.facilityType as f>
		                             <#if f??>
		                             	<#if (f== "免费wifi")>
		                                   <span class="ico-wifi" title="免费wifi"></span>
		                                    <#elseif (f == "收费wifi")>
		                                   <span class="ico-wifi" title="收费wifi"></span>
		                                   
		                                   <#elseif (f == "免费宽带")>
		                                   <span class="ico-broadband" title="收费宽带"></span>
		                                   <#elseif (f == "收费宽带")>
		                                   <span class="ico-broadband" title="收费宽带"></span>
		                                   
		                                    <#elseif (f == "免费停车场")>
		                                   <span class="ico-park" title="免费停车场"></span>
		                                   <#elseif (f == "收费停车场")>
		                                   <span class="ico-park" title="收费停车场"></span>
		                                   
		                                   <#elseif (f == "免费接机服务")>
		                       				<span class="ico-pickup" title="免费接机服务"></span>
		                       				<#elseif (f == "收费接机服务")>
		                       				<span class="ico-pickup" title="收费接机服务"></span>
		                       				
		                                	<#elseif (f=="酒店餐厅")>
		                                	<span class="ico-breakfast" title="酒店餐厅"></span>
		                                	<#elseif (f=="健身房")>
		                                	<span class="ico-gym" title="健身房"></span>
		                                	<#elseif (f=="商务中心")>
		                                	<span class="ico-business" title="商务中心"></span>
		                                	<#elseif (f=="会议室")>
		                                	<span class="ico-meeting" title="会议室"></span>
		                                	
		                                	<#elseif (f== "室内游泳池")>
		                                   <span class="ico-swimming" title="室内游泳池"></span>
		                                   <#elseif (f== "室外游泳池")>
		                                   <span class="ico-swimming" title="室外游泳池"></span>
		                                </#if>
		                             </#if>
		                             </#list>
	                             </#if>
	                          </div>  
	                          
							</div>
							
						</div>
				<@roomlist.roomlist hotel=hotel/>	
				</div>
				
			</div>
			</#if>  
			</#list>
		<#else>
			<center>
				<div class="exclamation" ><p>没有查询到符合条件的酒店，请检查查询条件，重新查询！</p></div>
			</center>
		</#if>
	<#else>
		<center>
			<div class="exclamation" ><p>无匹配政策！</p></div>
		</center>
	</#if>
<#else>
	<center>
		<div class="exclamation" ><p>无返回结果，请稍后再查！</p></div>
	</center>
</#if>

	<#if page??>
		<@pg.pagination curPage=page.pageNo allCount=page.totalItemCount pageSize=page.pageSize  />  
	</#if>
	<form action="${(basepath)!}/hotel/booking/selfpay/detail" method="post" id="detailForm" target="_blank">
		<input type="hidden" name="hotelId">
		<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}">
		<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}">
	</form>
	
</div>
