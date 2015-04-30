<#import "/common/mainContainer.ftl" as mc/>
<#import "win/salePrice.ftl" as salePrice/>
<@mc.container jsFiles=["common/commonUtils.js" "common/baiduditu.js","common/formValidator.js",
			"page/hotel/booking/search/detail.js","js/jcarousellite_1.0.1.pack.js",
			"page/hotel/booking/initDate.js"] 
		cssFiles=["css/store_hotel.css"] 
		localCssFiles=["page/hotel/zcitytip.css", "page/hotel/search.css"] menuItem="酒店">

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=13736a10c548d2e533c23af49e72de44"></script>
<script type="text/javascript" src="${host}/js/jquery.cycle.all.js"></script>

<#if hotel??>
 <section class="main-body clearfix">
  <input id="cityName" type="hidden" value="${(hotel.cityName)!}" />
  <input id="hotelName" type="hidden" value="${(hotel.name)!}" />
	  <div class="breadbar">
	    <span>
	    	<i class="icon_seat"></i>
	    	<a href="${basepath}/hotel/booking/search">国内及港澳预付酒店</a>
	    </span>&gt;<span>${(hotel.name)!}</span>
	  </div>
	  <div class="hotelSummary_block clearfix">
	    <div class="summary_left">
		  <ul class="hotelInfo">
		    <li class="hotelName">${(hotel.name)!}</li>
			<li>${(hotel.regionName)!} ${(hotel.cityName)!} ${(hotel.districtName)!} ${(hotel.address)!}</li>
			<li><b>宾客类型：</b>${(hotel.guestInfoCn)!}
			<#if (hotel.guestRemark)?? &&(hotel.guestRemark != "")>
			<i class="icon_remind"></i><span style="font-size:12px">${(hotel.guestRemark)!}</span>
			</#if>
			</li>
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
			 <li class="icon-list">
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
			 </li>
		  </ul>
		
		</div>
		<div class="summary_right">
		  <span><em class="pricermb">&yen;</em><em class="hotel_Price">${(hotel.lowestPrice)!0}</em> 起</span>

		   <div style="margin-top:10px">
             <a class="adaptiveButton brightRed_btn" href="javascript:changeTab('tab_booking')">
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
                  <a href="javascript:void(0);" target="_blank">
                  	<#if (hotel.defaultPhoto)??>
		        		<img src="${mediaserver}/imageservice?mediaImageId=${(hotel.defaultPhoto)!}&mediaType=image" >
		        	<#else>
		        		<img src="${host}/images/store/hotel_noPic.jpg" >
		        	</#if>
		        	<label class="ImgHover"><span>共<b><#if (hotel)?? && (hotel.photos)??>${hotel.photos?size+1}<#else><#if (hotel.defaultPhoto)??>1<#else>0</#if></#if></b>张图片</span></label>
                   </a>
                   
                   
                  </li>
                  <li>
                  	<#--酒店图片-->
                	<#if (hotel)?? && (hotel.photos)??>
                		<#list hotel.photos?keys as photo>
                		<#if photo_index <4>
                			<#if (photo_index %2 == 0)>
                			<ul class="hotelMoSmImgTop clearfix">
                			</#if>
	            				 <li>
	            				 <a href="javascript:void(0)"><img data-big-src="hotelPic-big.jpg" src="${mediaserver}/imageservice?mediaImageId=${(photo)!}&mediaType=image"/>
		                                 <label class="ImgHover"><span>客户图片</span></label></a>
		                          </li>
		                    <#if (photo_index %2 != 0) || (photo_index+1 == hotel.photos?keys?size)>
                			</ul>
                			</#if>
                		</#if>
                		</#list>
                	</#if>
                  </li>
                  <li class="hotelRightMap">
                 
                  <div class="map" id="dituContent" longitude="${(hotel.longitude)!}" latitude="${(hotel.latitude)!}"></div>
	<!--			  <a href="javascript:void(0)" target="_blank">
				  <img src="../../static/images/store/hotelSmallMap.jpg" alt=""/>
				  </a>-->
				  </li>
              </ul>
	  <!--images end-->
      
		<!--houseType booking-->
	<#--	 <div id="tab_booking" tabindex="0">-->
		 <div class="hotel-info-item" style="border-bottom:none">
			  <ul class="changeList clearfix">
			  <#--
			    <li><a href="#"><i></i>酒店图片</a></li>
				<li class="liLine"></li>
				<li><a href="javascript:" class="currentLi" onclick="changeTab('tab_booking',this)"><i></i>房型预定</a></li>
				<li class="liLine"></li>
				<li><a href="javascript:" onclick="changeTab('tab_introduce',this)"><i></i>酒店介绍</a></li>
				<li class="liLine"></li>
				<li><a href="javascript:" onclick="changeTab('tab_facility',this)"><i></i>服务设施</a></li>
				-->
				<li><a href="#hotel_img"><i></i>酒店图片</a></li>
                <li class="liLine"></li>
                <li><a href="#house_type" class="currentLi"><i></i>房型预定</a></li>
                <li class="liLine"></li>
                <li><a href="#introduce"><i></i>酒店介绍</a></li>
                <li class="liLine"></li>
                <li><a href="#facility"><i></i>服务设施</a></li>
			  </ul>
			  <div id="tab_booking">
			  <div class="house_search" >
		            <div class="condition">
		            <form action="${(basepath)!}/hotel/booking/search/detail" method="post" id="detailForm" />
		            <input type="hidden" name="hotelId" value="${(criteria.hotelId)!}" />
		            <b>重新查询价格</b>
		            <label><strong><em class="important">*</em>入住日期</strong><input type="text" id="checkinDate" name="checkInDate" value="${(criteria.checkInDate)!}" placeholder="" style="width:140px" class="gray-border"/></label>
		            <label><strong><em class="important">*</em>离店日期</strong><input type="text" id="checkoutDate" name="checkOutDate" value="${(criteria.checkOutDate)!}" placeholder="" style="width:140px" class="gray-border"/></label>
		            <label>
		                <strong><em class="important">*</em>预订间数</strong>
		                <select data-role="dropdownlist" name="roomNo" style="width:45px;vertical-align:middle;">
		                    <#list 1..5 as i>
		                    <option value="${i}" <#if (criteria.roomNo)?? &&(i?string==criteria.roomNo)>selected</#if>>${i}</option>
		                    </#list>
		                </select>
		            </label>
		            <a class="adaptiveButton brightRed_btn medium" href="javascript:$('#detailForm').submit();" name="house_type" style="float:right">
		                <span class="left"></span>
		                <span class="center">查询</span>
		                <span class="right"></span>
		            </a>
		            </form>
		        </div>
		    </div>
			
			<!--house List-->
			
            <div class="roomTypeListContainer clearfix">
                <table>
                    <thead>
                    
                     	<tr>
                            <td class="roomTypeGroupFirstField" width="200">房型</td>
                            <td width="140">床型</td>
                            <td width="80">早餐</td>
                            <td width="50">宽带</td>
                            <td width="70">规定</td>
    						<td width="40"></td>
                            <td width="100">销售价</td>
            				<td width="35"></td>
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
                            <td width="150"></td>
                            <td width="130"></td>
                            <td width="75"> </td>
                            <td width="80"></td>
                            <td width="100"></td>
                            <td width="100"></td>
                            <td width="90"></td>
                        </tr>
                    <#list roomCat.bookingClass as bookingClass>
                    
                    	<tr>
                            <td class="roomTypeGroupDetailFirstField" width="320">
                            <#if (bookingClass.giftPkg)?? && (bookingClass.giftPkg) !="">
                            <i class="ico_gift" title="${(bookingClass.giftPkg)!}"></i>
                            </#if>
                            ${(bookingClass.name)!}
	                            <#if (bookingClass.advanceTime)??>
	                                <label class="timeContent">
	                               		<i class="timePic"/></i>
	                                   		<#if (bookingClass.advanceTime?substring(0,bookingClass.advanceTime?length-3)?eval <=12)>
	                                   			上午${(bookingClass.advanceTime)!}前
	                                   		<#else>
	                                   			下午${(bookingClass.advanceTime)!}前
	                                   		</#if>
	                                </label>
	                            </#if>
                            </td>
                            <td width="200">
                           
  <#--               	 		<#if (roomCat.roomTypes)??>
		                           	 			<#if (roomCat.roomTypes[0].addBedService)?string != 'true'><span style="font-weight:bold">不可加床</span></#if>
		                           	 		</#if>-->
                           	 	${(roomCat.roomTypes[0].name)!}
                   	 			<#if (roomCat.roomTypes[0].bedSize)?? && (roomCat.roomTypes[0].bedSize != "")>
                   	 			(${(roomCat.roomTypes[0].bedSize)!})
                   	 			</#if>
                            </td>
                            <td>
                            	<#if (bookingClass.bookingClassDailyRates[0].breakFastOptionalService)??>
                                  <#list bookingClass.bookingClassDailyRates[0].breakFastOptionalService as breakFastService>
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
                            </td>
                            <td>
                            	 <#if (bookingClass.bookingClassDailyRates[0].internetOptionalService)?? >
	                                  	包含
	                              <#else>
	                              -
                                  </#if>
                            </td>
                            <td><em class="stipulate" data-checkin="${(bookingClass.checkinTime)!"-"}" data-checkout="${(bookingClass.checkoutTime)!"-"}" data-other="${(bookingClass.cancelPolicy)!"-"}">规定</em></td>
                            <td>
                            <#if (bookingClass.allot.roomAvailDesc)??>
                    			<#if bookingClass.allot.roomAvailDesc == '有房'>
                    				<span class="greenFont">立即确认</span>
                    			<#--<#elseif roomCat.allot.roomAvailDesc == '待确认'>
                    				<span class="greenFont">待确认</span>
                    			-->
                    			</#if>
                    		</#if>
                            </td>
                            <td>
                            
                          		<#assign bookingId=("H"+bookingClass.id)>
                            	<span <#if (bookingClass.allot.isValid)?? && (bookingClass.allot.isValid == true) &&(bookingClass.isValid)?? && (bookingClass.isValid == true)>
                                		class="hotelRoomPrice"<#else>class="hotelRoomPrice sold" style="color:black;"</#if>   rateid="${(bookingId)!}">
                                			 <em class="changeFont">&yen;</em>${(bookingClass.bookingClassDailyRates[0].sellingRate)!}
                          			 <#if (criteria.validDates)??>
			                        <@salePrice.salePrice bookingClassDailyRates=bookingClass.bookingClassDailyRates id=bookingId validDates=criteria.validDates/>
			                        </#if>
                                </span>
                            </td>
                            <td width="90">
                            	<#if (bookingClass.allot.isValid)?? && (bookingClass.allot.isValid == true) &&(bookingClass.isValid)?? && (bookingClass.isValid == true)>
                                <!-- <a href="javascript:submitOrderForm('${(hotel.id)!}','${(roomCat.id)!}','${(bookingClass.id)!}');" data-checkin="${(hotel.checkinTime)!}" data-checkout="${(hotel.checkoutTime)!}"  class="adaptiveButton mediumOrange_btn"> -->
                                <a id="${(hotel.id)!}_${(roomCat.id)!}_${(bookingClass.id)!}" href="javascript:submitOrderForm('${(hotel.id)!}','${(bookingClass.roomCatId)!}','${(bookingClass.id)!}');" data-checkin="${(hotel.checkinTime)!}" data-checkout="${(hotel.checkoutTime)!}" bq="${(bookingClass.bookingRequirement)!}" ad="${(bookingClass.advanceDays)!}" at="${(bookingClass.advanceTime)!}" cd="${(bookingClass.checkInDays)!}" class="adaptiveButton mediumOrange_btn">
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
<!--		    <div class="roomTypeListBottomButtonContainer">
	            <a href="javascript:void(0);" class="expand">收起全部房型<i class="ico_up"></i></a>
	            <a href="javascript:void(0);" class="expand" style="display:none;" >展开全部房型<i class="ico_down"></i></a>
	        </div>-->
	      </div>
	     </div>
		<!--house List End-->	
		</div>
	<#--	</div>-->
	  
	 <#--  <div id="tab_introduce"> -->
	   <div class="hotel-info-item">
			  <a name="introduce"></a>
			 <div class="info-itemTitle clearfix">
				酒店介绍
			 </div>
			<!--house introduce-->
			<div id="tab_introduce" class="hotelIntroduce">
			    <div class="clearfix">    
				  <p><b>开业时间</b><#if (hotel.openDate)??>${hotel.openDate}年<#else>-</#if></p>
				  <p><b>装修时间</b><#if (hotel.renovationDate)??>${hotel.renovationDate}年<#else>-</#if></p>
				  <p><b>房&nbsp;&nbsp;间&nbsp;&nbsp;数</b>${(hotel.noOfRoom)!}间</p>
				  </div>
				  <div class="hotelText clearfix">
				   <div class="hotelTextTit">酒店介绍</div>
				   <div class="hotelTextCon"><p>${(hotel.summary)!'-'}</p></div>
				</div>
			</div>
			<!--house introduce End-->
		</div>
		<#--</div>-->
		
		<#-- <div id="tab_facility">-->
		 <div class="hotel-info-item" >
			  <a name="facility"></a>
			   <div class="info-itemTitle clearfix">
				服务设施
			 </div>
			<!--house equipments-->
		<div id="tab_facility" class="hotelEquipment">
			   <div class="equipmentItem clearfix">
			   <div class="hotelTextTit">酒店服务</div>
			   <div class="hotelTextCon">
			    <ul>
			    <#if (hotel.facilities)??>
		        	<#list hotel.facilities as f>
		        		<#if f.facilityTypeName="General Service">
		        			<li>${(f.facilityName)!}</li>
		        		</#if>
		            </#list>
			    </#if>
				</ul>
			   </div>
            </div>
			<div class="equipmentItem clearfix">
			   <div class="hotelTextTit">酒店餐饮</div>
			   <div class="hotelTextCon">
			    <ul>
                <#if (hotel.facilities)??>
		        	<#list hotel.facilities as f>
		        		<#if f.facilityTypeName="Restaurant">
		        			<li>${(f.facilityName)!}</li>
		        		</#if>
		            </#list>
		            </#if>
				</ul>
			   </div>
            </div>
			<div class="equipmentItem clearfix">
			   <div class="hotelTextTit">商务设施</div>
			   <div class="hotelTextCon">
			    <ul>
                <#if (hotel.facilities)??>
		        	<#list hotel.facilities as f>
		        		<#if f.facilityTypeName="Business">
		        			<li>${(f.facilityName)!}</li>
		        		</#if>
		            </#list>
		            </#if>
				</ul>
			   </div>
            </div>
			<div class="equipmentItem clearfix">
			   <div class="hotelTextTit">娱乐设施</div>
			   <div class="hotelTextCon">
			    <ul>
                <#if (hotel.facilities)??>
		        	<#list hotel.facilities as f>
		        		<#if f.facilityTypeName="Entertainment">
		        			<li>${(f.facilityName)!}</li>
		        		</#if>
		            </#list>
		            </#if>
				</ul>
			   </div>
            </div>
		</div>
		<!--house equipments End-->
		</div>
	<#--	</div>-->
	   
	   
        <div class="itemBorder itemPoint">
        <div class="titleline">
            <h3>温馨提示</h3>
        </div>
        <div class="itemContent">
            <p>${(hotel.publicRemark)!'-'}</p>
        </div>
        </div>

    </section>

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

	<#if (hotel)?? && (hotel.photos)?? || (hotel.defaultPhoto)??>
	<script type="text/x-kendo-template" id="imageTemplate">
		<div class="allImg clearfix">
			<div class="bigImg">
				<a href="javascript:void(0);" class="leftArrow" id="prev">
					<div></div>
				</a>
				<div id="slideshow" class="pics">
	              			<#if (hotel.defaultPhoto)??>
			        		<img src="${mediaserver}/imageservice?mediaImageId=${(hotel.defaultPhoto)!}&mediaType=image"/>
			        	</#if>
			        	<#if (hotel.photos)??>
			        		<#list hotel.photos?keys as photo>
		                   		<img src="${mediaserver}/imageservice?mediaImageId=${(photo)!}&mediaType=image"/>
		                    		</#list>
			        	</#if>
				</div>
				<a href="javascript:void(0);"  class="rightArrow" id="next"> 
					<div> </div>
				</a> 
				<span id="scrollText"></span>
			</div>
			<a href="javascript:void(0);"  class="arrowUpblock" id="up">
				<div><i class="arrowUp"></i></div>
			</a>
			<a href="javascript:void(0);" class="arrowDownblock" id="down">
				<div><i class="arrowDown"></i></div>
			</a>     
		</div>
	</script>
	</#if>
	
	<div id="confirmWindow" style="padding:15px 30px;display:none;">
    <span id="window_tip">您的预订日期不满足选择产品的条件，请修改预定日期或选择其他产品</span>
    <div style="text-align:right;padding-top:10px">
        <a href="javascript:;" class="button_1_2 updateDate" style="min-width:70px; padding:7px 0; background:#ff3300; display:inline-block; color:#fff; text-align:center; border-radius:4px; -moz-border-radius:4px; -webkit-border-radius:4px;">修改日期</a>
       	<a href="javascript:;" class="button_1_2 close" style="min-width:70px; padding:7px 0; background:#ff3300; display:inline-block; color:#fff; text-align:center; border-radius:4px; -moz-border-radius:4px; -webkit-border-radius:4px;">选择其他产品</a>
    </div>
	</div>
  <style>
  	.map {
	    float: right;
	    height: 240px;
	    width: 325px;
	}
  </style>
<#else>
<div class="error-page gray-border">
	<div class="error-page_con">
        <p>没有查到相应查询条件的搜索结果，请返回重新选择</p>
        <p class="p_back"><a href="javascript:history.go(-1)">返回</a></p>
	</div>
</div>
</#if> 
</@mc.container>