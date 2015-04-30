<#macro bookingProduct hotelOrder type>
<#import "bookingDetail.ftl" as bookingDetail>
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
        <td>
        	<span class="font14_name advanceHotelName">
        	<a class="linkA" href="javascript:<#if (hotelOrder.hotelDetail.hotelId)??>hotelDetail('${hotelOrder.hotelDetail.hotelId}')</#if>;">${(hotelOrder.hotelDetail.hotelName)!}</a>
        	</span>
        	<p><i class="mapLocationIcon"></i>${(hotelOrder.hotelDetail.cityName)!} ${(hotelOrder.hotelDetail.hotelAddress)!}</p>
        	<p>${(hotelOrder.hotelDetail.mainPhone)!}</p>
        </td>
        <td>${(hotelOrder.hotelDetail.roomCat)!}</td>
        <td>${(hotelOrder.hotelDetail.bedType)!}</td>
        <td>${(hotelOrder.hotelDetail.checkinDate?date)!}</td>
        <td>${(hotelOrder.hotelDetail.checkoutDate)!}</td>
        <td>${(hotelOrder.hotelDetail.roomNo)!} 间</td>
        <td>
        <#list hotelOrder.hotelGuest as hotelGuest>
        	 <p>房间${hotelGuest_index+1}：${(hotelGuest.name)!}</p>
        </#list>
        </td>
    </tr>
    <#if hotelOrder.hotelDetail.giftPkg?? && hotelOrder.hotelDetail.giftPkg != "">
    <tr class="dotted_top">
        <td colspan="7"><span class="trRow_tit">礼包信息：</span><span>${(hotelOrder.hotelDetail.giftPkg)!}</span></td>
    </tr>
    </#if>
    <tr class="dotted_top">
        <td colspan="7">
        	<span class="trRow_tit">房费合计：</span><span class="trRow_price"><em>¥</em>${(hotelOrder.totalOrderFee/100)!}</span>
            <span id="<#if type="orig">orig</#if>priceDetail" class="trRow_room">房费信息</span>
            <@bookingDetail.bookingDetail hotelOrder=hotelOrder type=type/>
            <#if hotelOrder.customerCommissionAmount != 0>
            <span class="hotel_return">${hotelOrder.customerCommissionAmount / 100}元</span>
            </#if>
        </td>
    </tr>
    </tbody>
</table>

</#macro>