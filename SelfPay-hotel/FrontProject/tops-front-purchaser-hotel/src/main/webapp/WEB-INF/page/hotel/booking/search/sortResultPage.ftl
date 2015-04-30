<#import "page.ftl" as pg/>
<#import "win/salePrice.ftl" as salePrice/>
<div id="sortResultPage" class="hotel_result" style="border:1px solid #ececec;">
<#include "sort.ftl"/>
	<#if (result.propertySearchResult)??>
		<#list result.propertySearchResult as hotel>
			<div class="hotel_resultDetail">
		        <div class="hotelPanel">
		            <div class="hotelBasicInfo clearfix">
		                <div class="hotelImage imgSize">
			                <#if (hotel.defaultPhoto)??>
			                <a href="javascript:submitDetailForm('${(hotel.id)!}');">
			                	<img src="${(mediaserver)!}/imageservice?mediaImageId=${(hotel.defaultPhoto)!}&mediaType=image"  onerror="javascript:this.src='${host}/images/hotel_noPic.jpg'" style="width:240px;height:180px;" alt="">
			                </a>
			                <#else>
			                	<img src="${host}/images/store/hotel_noPic.jpg" style="width:240px;height:180px;" alt="">
			                </#if>
		                </div>
		                <div class="hotelDesc">
		                	<div class="clearfix gray-dashBorder-bottom">
                                   <div class="addressName pull-left">
                                        <div class="hotelName">
                                        	<a href="javascript:submitDetailForm('${(hotel.id)!}');" style="color: #E94749;font: bold 16px '宋体';">${(hotel.name)!}</a>
                                        </div>
                                        <div class="hotelAddress">
                                        	<span class="hotelPlace">${(hotel.address)!}</span>
                                        	<span class="hotel-map"><i longitude="${(hotel.longitude)!}" latitude="${(hotel.latitude)!}"></i></span>
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
                             <div class="hotelCustomerType">
                                  <label style="font-size:14px; color:#404040;"><b>宾客类型：</b><span>${(hotel.guestInfoCn)!}</span></label>
                                  <#if (hotel.guestRemark)?? &&(hotel.guestRemark != "")><label class="typeHint"><i></i><span>${(hotel.guestRemark)!}</span></label></#if>
                             </div>
                             <p class="seeDetails">
                             	<#if (hotel.summary)?? && (hotel.summary?length>100)>
                             		${hotel.summary[1..100]}...
                             	<#else>
                             		${(hotel.summary)!'-'}
                             	</#if>
	                             <a href="javascript:submitDetailForm('${(hotel.id)!}');" >[查看详情]</a>
                             </p>
                             <div class="icon-list">
                             <#assign showBreakfast = true >
                             <#if (hotel.facilities)??>
                             <#list hotel.facilities as f>
                             	<#if f.facilityTypeName="General Service">
                             		<#if (f.facilityName == "无线宽带")>
                                   <span class="ico-wifi" title="wifi"></span>
                                   <#elseif (f.facilityName == "停车场")>
                                   <span class="ico-park" title="停车场"></span>
                                   <#elseif (f.facilityName == "机场接送")>
                       				<span class="ico-pickup" title="机场接送"></span>
                                   </#if>
                                <#elseif (f.facilityTypeName="Restaurant") && showBreakfast = true>
                                 	<#assign showBreakfast = false > 
                                	<span class="ico-breakfast" title="酒店餐厅"></span>
                                <#elseif (f.facilityTypeName="Entertainment") && (f.facilityName == '室外游泳池')>
                                   <span class="ico-swimming" title="室外游泳池"></span>
                                </#if>
                             </#list>
                             </#if>
                             </div>

		                </div>
		            </div>
		            <div class="roomTypeListContainer clearfix">
		                <table>
		                    <thead>
		                    
		                     	<tr>
                                    <td class="roomTypeGroupFirstField" width="240">房型</td>
                                    <td width="150">床型</td>
                                    <td width="130">早餐</td>
                                    <td width="75">宽带</td>
                                    <td width="80">规定</td>
                                    <td width="100">&nbsp;</td>
                                    <td width="100">销售价</td>
                                    <td width="85">&nbsp;</td>
                                </tr>
		                    </thead>
		                </table>
        		     <#if (hotel.roomCats)??>
	                	<#list (hotel.roomCats) as roomCat>

	                	<#if (roomCat.validRoomCat)?? && (roomCat.validRoomCat)?string == 'true'>
		                 <div class="roomTypeDetailContainer" style="">
		                    <table>
		                        <tbody>
                                <tr>
                                    <td width="240" class="tdTitle"><b>${(roomCat.name)!}</b></td>
                                    <td width="150">				                           	 	
                                        <span class="expand_hide">
		                       <#--    	 		<#if (roomCat.roomTypes)??>
		                           	 			<#if (roomCat.roomTypes[0].addBedService)?string != 'true'><span style="font-weight:bold">不可加床</span></#if>
		                           	 		</#if>-->
		                           	 	${(roomCat.roomTypes[0].name)!}
                           	 			<#if (roomCat.roomTypes[0].bedSize)?? && (roomCat.roomTypes[0].bedSize != "")>
                           	 			(${(roomCat.roomTypes[0].bedSize)!}
                           	 				<#if  (roomCat.roomTypes[0].bedType)?? && (roomCat.roomTypes[0].bedType) == 'BIG_DOUBLE_BED'>
	                           	 				${(roomCat.roomTypes[0].bedSizeOther)!}
	                           	 			</#if>
                           	 			)
                           	 			</#if>
		                           	 	</span>
			                        </td>
                                    <td width="130">
                                    	<span class="expand_hide">
		                                	<#if (roomCat.defaultBookingClass.bookingClassDailyRates[0].breakFastOptionalService)??>
			                                  <#list roomCat.defaultBookingClass.bookingClassDailyRates[0].breakFastOptionalService as breakFastService>
			                                    <#if (breakFastService.serviceDetail)??>
			                                    	<#if (breakFastService.number)??>
			                                    		<#if breakFastService.number == 0>
			                                    			无早
			                                  			<#elseif (breakFastService.number <0)>
			                                  				含早
			                                  			<#else>
			                                  				${(breakFastService.number)!0}份&nbsp;
			                                  			</#if>
			                                  		</#if>
			                                  	<#else>
			                                  	-
			                                  	</#if>
			                                  </#list>
			                            	<#else>
			                              	-
		                                	</#if>
                                    	</span>
                                    </td>
                                    <td width="75">
                                    	<span class="expand_hide">
                            		 	  <#if (roomCat.defaultBookingClass.bookingClassDailyRates[0].internetOptionalService)?? >
			                                  	包含&nbsp;
			                              <#else>
			                              -
		                                  </#if>
                                       	</span>
                                    </td>
                                    <td width="80">
                                    	<span class="expand_hide">
                                    		<em class="stipulate" data-checkin="${(roomCat.defaultBookingClass.checkinTime)!"-"}" data-checkout="${(roomCat.defaultBookingClass.checkoutTime)!"-"}" data-other="${(roomCat.defaultBookingClass.cancelPolicy)!"-"}">规定</em>
                                        </span>
                                    </td>
                                    <td width="100">
                                    	<span class="expand_hide">
                                    		<#if (roomCat.defaultBookingClass.allot.roomAvailDesc)??>
                                    			<#if roomCat.defaultBookingClass.allot.roomAvailDesc == '有房'>
                                    				<span class="greenFont">立即确认</span>
                                    		
                                    			</#if>
                                    		</#if>
                                    	</span>
                                    </td>
                                    <td width="100">
                                		<#if (roomCat.defaultBookingClass.allot.isValid)?? && (roomCat.defaultBookingClass.allot.isValid == true) &&(roomCat.defaultBookingClass.isValid)?? && (roomCat.defaultBookingClass.isValid == true)>
	                                    	<span class="expand_hide hotelRoomPrice" rateid="${(roomCat.defaultBookingClass.id)!}">
                                			<em class="changeFont">&yen;</em>${(roomCat.defaultBookingClass.bookingClassDailyRates[0].sellingRate)!}
                               			<#else>
                                			<span class="expand_hide hotelRoomPrice sold" rateid="${(roomCat.defaultBookingClass.id)!}">
                                			<em class="changeFont">&yen;</em>${(roomCat.defaultBookingClass.bookingClassDailyRates[0].sellingRate)!}
                              			</#if>
                              			<#if (criteria.validDates)??>
			                            	<@salePrice.salePrice bookingClassDailyRates=roomCat.defaultBookingClass.bookingClassDailyRates id=roomCat.defaultBookingClass.id validDates=criteria.validDates/>
			                            </#if>
                                  	  	</span>
                                    </td>
                                    <td width="85">
                                    	<div class="expand_hide">
										<#if (roomCat.defaultBookingClass.allot.isValid)?? && (roomCat.defaultBookingClass.allot.isValid == true) &&(roomCat.defaultBookingClass.isValid)?? && (roomCat.defaultBookingClass.isValid == true)>
                                        <a id="${(hotel.id)!}_${(roomCat.defaultBookingClass.roomCatId)!}_${(roomCat.defaultBookingClass.id)!}" href="javascript:submitOrderForm('${(hotel.id)!}', '${(roomCat.defaultBookingClass.roomCatId)!}','${(roomCat.defaultBookingClass.id)!}');" data-checkin="${(hotel.checkinTime)!}" data-checkout="${(hotel.checkoutTime)!}" bq="${(roomCat.defaultBookingClass.bookingRequirement)!}" ad="${(roomCat.defaultBookingClass.advanceDays)!}" at="${(roomCat.defaultBookingClass.advanceTime)!}" cd="${(roomCat.defaultBookingClass.checkInDays)!}" class="adaptiveButton mediumOrange_btn">
                                            <span class="left"></span>
                                            <span class="center center_1">预订</span>
                                            <span class="right"></span>                                        
                                        </a>
                            			<#else>
                            			<a href="javascript:;"  class="adaptiveButton mediumDisabled_btn">
                                            <span class="left"></span>
                                            <span class="center center_1">预订</span>
                                            <span class="right"></span>
                                        </a>
                                        </#if>
                                        </div>
                                    </td>
                                </tr>
                            <#list roomCat.bookingClass as bookingClass>
                            
                            	<tr>
                                    <td class="roomTypeGroupDetailFirstField">
                                    <#if (bookingClass.giftPkg)?? && (bookingClass.giftPkg) !="">
                                    <i class="ico_gift" title="${(bookingClass.giftPkg)!}"></i>
                                    </#if>
                                    ${(bookingClass.name)!}
	                                    <#if (bookingClass.advanceTime)?? && bookingClass.advanceTime?length gt 3>
		                                    <label class="timeContent">
		                                   		<i class="timePic"/></i>
			                                   		<#if (bookingClass.advanceTime?substring(0,bookingClass.advanceTime?length-3)?eval lte 12)>
			                                   			上午${(bookingClass.advanceTime)!}前
			                                   		<#else>
			                                   			下午${(bookingClass.advanceTime)!}前
			                                   		</#if>
		                                    </label>
	                                    </#if>
                                    </td>
                                    <td>
                                    	<span>
		            <#--               	 		<#if (roomCat.roomTypes)??>
		                           	 			<#if (roomCat.roomTypes[0].addBedService)?string != 'true'><span style="font-weight:bold">不可加床</span></#if>
		                           	 		</#if>-->
		                           	 	${(roomCat.roomTypes[0].name)!}
                           	 			<#if (roomCat.roomTypes[0].bedSize)?? && (roomCat.roomTypes[0].bedSize != "")>
                           	 			(${(roomCat.roomTypes[0].bedSize)!}
                           	 				<#if  (roomCat.roomTypes[0].bedType)?? && (roomCat.roomTypes[0].bedType) == 'BIG_DOUBLE_BED'>
	                           	 				${(roomCat.roomTypes[0].bedSizeOther)!}
	                           	 			</#if>
                           	 			)
                           	 			</#if>
		                           	 	</span>
                                    </td>
                                    <td>
                                    	<#if (bookingClass.bookingClassDailyRates[0].breakFastOptionalService)??>
		                                  <#list bookingClass.bookingClassDailyRates[0].breakFastOptionalService as breakFastService>
		                                    <#if (breakFastService.serviceDetail)??>
		                                    	<#if (breakFastService.number)??>
		                                    		<#if breakFastService.number == 0>
		                                    			无早
		                                  			<#elseif (breakFastService.number lt 0)>
		                                  				含早
		                                  			<#else>
		                                  				${(breakFastService.number)!}份&nbsp;
		                                  			</#if>
		                                  		</#if>
		                                  	<#else>
		                                  	-
		                                  	</#if>
		                                  </#list>
		                            	<#else>
		                              	-
	                                	</#if>
                                    </td>
                                    <td>
                                    	 <#if (bookingClass.bookingClassDailyRates[0].internetOptionalService)?? >
			                                  	包含&nbsp;
			                              <#else>
			                              -
		                                  </#if>
                                    </td>
                                    <td><em class="stipulate" data-checkin="${(bookingClass.checkinTime)!"-"}" data-checkout="${(bookingClass.checkoutTime)!"-"}" data-other="${(bookingClass.cancelPolicy)!"-"}">规定</em></td>
                                    <td>
                                    <#if (bookingClass.allot.roomAvailDesc)??>
                            			<#if bookingClass.allot.roomAvailDesc == '有房'>
                            				<span class="greenFont">立即确认</span>
                            		
                            			</#if>
                            		</#if>
                                    </td>
                                    <td>
                                    
                                  		<#assign bookingId=("H"+bookingClass.id)>
	                                	<span <#if (bookingClass.allot.isValid)?? && (bookingClass.allot.isValid == true) &&(bookingClass.isValid)?? && (bookingClass.isValid == true)>
	                                    		class="hotelRoomPrice"<#else>class="hotelRoomPrice sold" </#if>   rateid="${(bookingId)!}">
		                                			 <em class="changeFont">&yen;</em>${(bookingClass.bookingClassDailyRates[0].sellingRate)!}
	                              			 <#if (criteria.validDates)??>
					                        <@salePrice.salePrice bookingClassDailyRates=bookingClass.bookingClassDailyRates id=bookingId validDates=criteria.validDates/>
					                        </#if>
	                                    </span>
                                    </td>
                                    <td>
                                    	<#if (bookingClass.allot.isValid)?? && (bookingClass.allot.isValid == true) &&(bookingClass.isValid)?? && (bookingClass.isValid == true)>
                                        <a id="${(hotel.id)!}_${(bookingClass.roomCatId)!}_${(bookingClass.id)!}" href="javascript:submitOrderForm('${(hotel.id)!}','${(bookingClass.roomCatId)!}','${(bookingClass.id)!}');" data-checkin="${(hotel.checkinTime)!}" data-checkout="${(hotel.checkoutTime)!}" bq="${(bookingClass.bookingRequirement)!}" ad="${(bookingClass.advanceDays)!}" at="${(bookingClass.advanceTime)!}" cd="${(bookingClass.checkInDays)!}" class="adaptiveButton mediumOrange_btn">
                                            <span class="left"></span>
                                            <span class="center center_1">预订</span>
                                            <span class="right"></span>
                                        </a>
                            			 <#else>
                            			<a href="javascript:;"  class="adaptiveButton mediumDisabled_btn">
                                            <span class="left"></span>
                                            <span class="center center_1">预订</span>
                                            <span class="right"></span>
                                        </a>
                                        </#if>
                                    </td>
                                </tr>
							</#list>
		                        </tbody>
		                     </table>
		                  </div>
          		           </#if>
           				</#list>
       				</#if>
			    <div class="roomTypeListBottomButtonContainer">
		            <a href="javascript:void(0);" class="expand">收起全部房型<i class="ico_up"></i></a>
		            <a href="javascript:void(0);" class="expand" style="display:none;" >展开全部房型<i class="ico_down"></i></a>
		        </div>
              </div>
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
     <form action="${(basepath)!}/hotel/booking/search/detail" method="post" id="detailForm" target="_blank">
		<input type="hidden" name="hotelId">
		<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}">
		<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}">
		<input type="hidden" name="roomNo" value="${(criteria.roomNo)!}">
	</form>
	<form action="${(basepath)!}/pur/order/hotel/orderView" method="post" id="orderForm">
		<input type="hidden" name="hotelId">
		<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}">
		<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}">
		<input type="hidden" name="roomCatId">
		<input type="hidden" name="bookingClassId">
		<input type="hidden" name="roomNo" value="${(criteria.roomNo)!}">
	</form>
	
	<script type="text/x-kendo-template" id="ruleTemplate">
    <div class="stipulateCont">
	    <p><b>入住时间：</b>#= target.data('checkin') #:00之后</p>
		<p><b>退房时间：</b>#= target.data('checkout') #:00之前</p>
		<p><b>变更/取消：</b>#= target.data('other') #</p>
	</div>
	</script>

</div>
