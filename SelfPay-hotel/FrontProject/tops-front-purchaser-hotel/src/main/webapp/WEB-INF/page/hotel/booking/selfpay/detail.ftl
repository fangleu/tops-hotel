<#import "/common/mainContainer.ftl" as mc/>
<#import "roomListView.ftl" as roomlist/>
<@mc.container jsFiles=["common/commonUtils.js" "common/baiduditu.js","common/formValidator.js",
			"page/hotel/booking/selfpay/detail.js","js/jcarousellite_1.0.1.pack.js",
			"page/hotel/booking/selfpay/roomListViewTip.js",
			"page/hotel/booking/selfpay/initDate.js"] 
		cssFiles=["css/store_hotel.css"] 
		localCssFiles=["page/hotel/zcitytip.css", "page/hotel/search.css"] menuItem="酒店">

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=13736a10c548d2e533c23af49e72de44"></script>
<script type="text/javascript" src="${host}/js/jquery.cycle.all.js"></script>
<#if hotel??>
 <section class="main-body clearfix">
  <input id="cityName" type="hidden" value="${(hotel.hotelCity)!}" />  
  <input id="hotelName" type="hidden" value="${(hotel.hotelName)!}" />
	  <div class="breadbar">
	    <span>
	    	<i class="icon_seat"></i>
	    	<a href="${basepath}/hotel/booking/search">国内现付酒店</a>
	    </span>&gt;<span>${(hotel.hotelName)!}</span>
	  </div>
	  <div class="hotelSummary_block clearfix">
	    <div class="summary_left">
		  <ul class="hotelInfo">
		    <li class="hotelName">${(hotel.hotelName)!}</li>
			<li>  ${(hotel.regionName)!} ${(hotel.cityName)!} ${(hotel.districtName)!} ${(hotel.address)!}  </li>
		  </ul>
		  <ul class="hotelInfo_tips">
		  	
			
			<li><div style="margin-left: 0px" class="<#if (hotel.starRate>0) && (hotel.starRate<=5)>hotelStarLevel hotelStar${hotel.starRate}</#if>"><span></span></div></li>
			<li><div>${(hotel.starDetail)!}</div></li>
								
			 <li class="icon-list">
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
			 </li>
		  </ul>
		
		</div>
		<div class="summary_right">
		  <span><em class="pricermb">&yen;</em><em class="hotel_Price">${(hotel.lowRate)!0}</em> 起</span>

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
	  <div>
	      <ul class="hotelImgs clearfix">
                  <li class="hotelLeftBigImg more-pic">
                  <a href="javascript:void(0);" target="_blank">
                  	<#if (hotel.thumbNailUrl)??>
	        		<img src="${hotel.thumbNailUrl}" >
	        	<#else>
	        		<img src="${(basepath)!}/images/store/hotel_noPic.jpg" >
	        	</#if>
	        	<label class="ImgHover"><span>共<b><#if (hotel)?? && (hotel.thumbNailUrlList)??>${(hotel.thumbNailUrlList?size+1)!}<#else><#if (hotel.thumbNailUrl)??>1<#else>0</#if></#if></b>张图片</span></label>
                   </a>
                   
        <#if (hotel)?? && (hotel.thumbNailUrlList)??>
      		<script type="text/x-kendo-template" id="imageTemplate">
			<div class="allImg clearfix">
			<div class="bigImg">
			<a href="javascript:void(0);" class="leftArrow" id="prev">
				<div></div>
			</a>
			<div id="slideshow" class="pics">
				<#assign default="">
				<#if (hotel.thumbNailUrl)??>
					<#assign default=hotel.thumbNailUrl>
					<img class="pic" src="${(hotel.thumbNailUrl)!}"/>
				</#if>
				<#list hotel.thumbNailUrlList as photo>
					<#if photo!="mapLink" && photo!=default>
						<img src="${(photo)!}"/>
					</#if>
				</#list>
		</div>
		<div class="font_copyright"></div>
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
                  </li>
                  <li>
                  	<#--酒店图片-->
                	<#if (hotel)?? && (hotel.thumbNailUrlList)??>
                		<#list hotel.thumbNailUrlList as photo>
                		<#if photo_index <4>
                			<#if (photo_index %2 == 0)>
                			<ul class="hotelMoSmImgTop clearfix">
                			</#if>
	            				 <li>
	            				 <a href="javascript:void(0)"><img src="${photo}"/>
		                                 <label class="ImgHover"><span>客户图片</span></label></a>
		                          </li>
		                    <#if (photo_index %2 != 0) || (photo_index+1 == hotel.thumbNailUrlList?size)>
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
              </div>
	  <!--images end-->
      
		<!--houseType booking-->
<#--	 <div id="tab_booking" tabindex="0">-->
		 <div class="hotel-info-item" style="border-bottom:none">
			  <ul class="changeList clearfix">
			 		<li><a href="#hotel_img" ><i></i>酒店图片</a></li>
                <li class="liLine"></li>
                <li><a href="#house_type" class="currentLi" ><i></i>房型预定</a></li>
                <li class="liLine"></li>
                <li><a href="#introduce"><i></i>酒店介绍</a></li>
                <li class="liLine"></li>
                <li><a href="#facility"><i></i>服务设施</a></li> 
			  </ul>
			  <div class="house_search">
			  <div class="condition">
		            <form action="${(basepath)!}/hotel/booking/selfpay/detail" method="post" id="detailForm" />
		            <input type="hidden" name="hotelId" value="${(hotel.hotelId)!}" />
		         <span style搜索="font-weight:bold;"> <B>重新查询价格</B> </span>
		            <label><strong><em class="important">*</em>入住日期</strong><input type="text" id="checkinDate_selfpay" name="checkInDate" value="${(criteria.checkInDate)!}" placeholder="" style="width:140px" class="gray-border"/></label>
		            <label><strong><em class="important">*</em>离店日期</strong><input type="text" id="checkoutDate_selfpay" name="checkOutDate" value="${(criteria.checkOutDate)!}" placeholder="" style="width:140px" class="gray-border"/></label>
		            
		            <a class="adaptiveButton brightRed_btn medium" href="javascript:$('#detailForm').submit();" name="house_type" style="float:right">
		                <span class="left"></span>
		                <span class="center">查询</span>
		                <span class="right"></span>
		            </a>
		            </form>
		        </div>
		    </div>
			
			<!--house List-->
			
     <div class="hotelPanel">  
  	 <@roomlist.roomlist hotel=hotel/>	
  	 </div>
	  
		<!--house List End-->	
	
	   <div class="hotel-info-item">
			  <a name="introduce"></a>
			 <div class="info-itemTitle clearfix">
				酒店介绍
			 </div>
			<!--house introduce-->
			<div id="tab_introduce" class="hotelIntroduce">
			    <div class="clearfix">    
				  <p><b>开业时间</b><#if (hotel.openDate)??>${hotel.openDate}<#else>-</#if></p>
				  <p><b>装修时间</b><#if (hotel.renovationDate)??>${hotel.renovationDate}<#else>-</#if></p>
				  </div>
				  <div class="hotelText clearfix">
				   <div class="hotelTextTit">酒店介绍</div>
				   <div class="hotelTextCon"><p>
				   ${(hotel.introEditor)!}
				   </p></div>
				</div>
			</div>
			<!--house introduce End-->
		</div>
	
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
			   <#if (hotel.generalAmenities)??>
		        	<#list hotel.generalAmenities as f>
		        			<li>${f!}</li>
		            </#list>
			    </#if>
			
				</ul>
			   </div>
            </div>
			<div class="equipmentItem clearfix">
			   <div class="hotelTextTit">酒店餐饮</div>
			   <div class="hotelTextCon">
			    <ul>
                 <#if (hotel.diningAmenities)??>
		        	<#list hotel.diningAmenities as f>
		        			<li>${f!}</li>
		            </#list>
			    </#if>
			
				</ul>
			   </div>
            </div>
			<div class="equipmentItem clearfix">
			   <div class="hotelTextTit">商务设施</div>
			   <div class="hotelTextCon">
			    <ul>
              <#if (hotel.conferenceAmenities)??>
		        	<#list hotel.conferenceAmenities as f>
		        			<li>${f!}</li>
		            </#list>
			    </#if>
				</ul>
			   </div>
            </div>
			<div class="equipmentItem clearfix">
			   <div class="hotelTextTit">娱乐设施</div>
			   <div class="hotelTextCon">
			    <ul>
               <#if (hotel.recreationAmenities)??>
		        	<#list hotel.recreationAmenities as f>
		        			<li>${f!}</li>
		            </#list>
			    </#if>
				</ul>
			   </div>
            </div>
		</div>
		<!--house equipments End-->
		</div>
</div>
</section>
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
    
</@mc.container>