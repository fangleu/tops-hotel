<#macro bookingProduct hotelOrderVo type>
<#import "bookingDetail.ftl" as bookingDetail>
<#include "commonParameter.ftl">
<#assign weekMaps1={"0":"星期日","1":"星期一","2":"星期二","3":"星期三","4":"星期四","5":"星期五","6":"星期六"} > 
<table cellpadding="0" cellspacing="0" class="route traveller noMarginTop">
    <colgroup>
        <col width="258"/>
        <col width="115"/>
        <col width="130"/>
        <col width="115"/>
        <col width="105"/>
        <col width="85"/>
        <col width="150"/>
    </colgroup>
    <thead>
    <tr>
        <th>酒店名称</th>
        <th>房型</th>
        <th>床型</th>
        <th>入住日期</th>
        <th>离店日期</th>
        <th>间数</th>
        <th>旅客姓名</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><span class="font14_name advanceHotelName">
        	<a class="linkA" href="javascript:<#if (hotelOrderVo.hotelDetailBo.hotelId)??>hotelDetail('${hotelOrderVo.hotelDetailBo.hotelId}')</#if>;">${(hotelOrderVo.hotelDetailBo.hotelName)!}</a>
        	</span>
        	<p><#if type!="orig"><i class="mapLocationIcon"></i></#if>${(hotelOrderVo.hotelDetailBo.cityName)!} ${(hotelOrderVo.hotelDetailBo.hotelAddress)!}</p>
        	<p>${(hotelOrderVo.hotelDetailBo.mainPhone)!}</p>
        </td>
        <td>${(hotelOrderVo.hotelDetailBo.roomCat)!}</td>
        <td>${(hotelOrderVo.hotelDetailBo.bedType)!}</td>
        <td>${(hotelOrderVo.hotelDetailBo.checkinDate?date)!}</td>
        <td>${(hotelOrderVo.hotelDetailBo.checkoutDate?string('yyyy-MM-dd'))!}</td>
        <td>${(hotelOrderVo.hotelDetailBo.roomNo)!} 间</td>
        <td>
        <#list hotelOrderVo.hotelGuestBo as hotelGuestBo>
        	 <p>房间${hotelGuestBo_index+1}：${(hotelGuestBo.name)!}</p>
        </#list>
        </td>
    </tr>
    <#if hotelOrderVo.hotelDetailBo.giftPkg?? && hotelOrderVo.hotelDetailBo.giftPkg != "">
    <tr class="dotted_top">
        <td colspan="7"><span class="trRow_tit">礼包信息：</span><span>${(hotelOrderVo.hotelDetailBo.giftPkg)!}</span></td>
    </tr>
    </#if>
    <tr class="dotted_top">
        <td colspan="7">
        	<span class="trRow_tit">房费合计：</span><span class="trRow_price"><em>¥</em>${(hotelOrderVo.externalObject.totalOrderSelfFeeYuan)!}</span>
            <span id="<#if type="orig">orig</#if>priceDetail" class="trRow_room">房费信息</span>
            <@bookingDetail.bookingDetail hotelOrderVo=hotelOrderVo type=type/>
        </td>
    </tr>
    </tbody>
</table>

</#macro>