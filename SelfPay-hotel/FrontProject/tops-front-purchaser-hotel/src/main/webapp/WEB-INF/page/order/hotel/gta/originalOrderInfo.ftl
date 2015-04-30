<#--关联订单,开始-->
<#if (relatedOrderVos)?? && (relatedOrderVos?size > 1)>
<div style="display:none;position: absolute;z-index:100" class="newEndorseOrder" id="child_order">
    <div class="hideBorderBot"></div>
    <div class="newReOrder">
        <div class="rawOrd clearfix">
            <span class="rawNum" style="width:75%">原订单 
            	<a href="${basepath}/pur/hotel/ordermanage/detail?hotelOrderId=${(relatedOrderVos[0].hotelOrder.originalOrderId)!}" target="_blank">${(relatedOrderVos[0].hotelOrder.originalOrderId)!}</a>
        	</span>
            <em class="ordNum" style="float:left"><em>¥ </em>${(relatedOrderVos[0].externalObject.totalOrderFeeRmbYuan)!}</em>
        </div>
        <ul class="orderSta">
    	<#list relatedOrderVos as endorseOrder>
    		<#if (endorseOrder_index > 0)>
            <li>
                <i class="signOrd"> <em></em> </i>
                <div class="dateSta">
                    <span>${(endorseOrder.hotelOrder.createDate?string("yyyy-MM-dd HH:mm"))!} </span> <em class="sta">${(endorseOrder.externalObject.pageOrderState)!}</em>
                </div>
                <div class="staList <#if !endorseOrder_has_next>currentSta</#if>">
                    <table>
                        <colgroup>
                            <col width="260px"/>
                            <col width="85px"/>
                        </colgroup>
                        <tr>
                            <td style="width:75%;">${(endorseOrder.externalObject.pageOrderTypeDesc)!}
	                            <a href="${basepath}/pur/hotel/ordermanage/detail?hotelOrderId=${(endorseOrder.hotelOrder.id)!}" target="_blank">${(endorseOrder.hotelOrder.id)!}</a> &nbsp; 
	                            <br/>操作人：${(endorseOrder.hotelOrder.creatorName)!}
                            </td>
                            <td class="weight"><em>¥ </em>${(endorseOrder.externalObject.totalOrderFeeRmbYuan)!}</td>
                        </tr>
                        <tr>
                            <td colspan="2">${(endorseOrder.hotelDetailBo.cityName)!} | ${(endorseOrder.hotelDetailBo.hotelName)!}</td>
                        </tr>
                        <tr>
                            <td colspan="2">${(endorseOrder.hotelDetailBo.roomCat)!} | ${(endorseOrder.hotelDetailBo.bookingclassName)!}</td>
                        </tr>
                        <tr>
                            <td colspan="2">${(endorseOrder.hotelDetailBo.checkinDate?date)!}~${(endorseOrder.hotelDetailBo.checkoutDate?date)!} ${(endorseOrder.hotelDetailBo.roomNo)!}间</td>
                        </tr>
                    </table>
                </div>
            </li>
            </#if>
        </#list>
        </ul>
    </div>
</div>
</#if>
