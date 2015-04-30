<#import "/common/mainContainer.ftl" as mc/>
<#import "components/bookingProduct.ftl" as bookingProduct>
<#import "components/map.ftl" as map>
<#include "components/commonParameter.ftl">
<#import "/page/order/hotel/components/orderOperations.ftl" as orderOperations>
<@mc.container jsFiles=["page/order/hotel/hotelOrderDetail.js",
			"page/order/hotel/countdownTime.js",
			"page/order/hotel/countdownTimeInit.js",
			"page/order/hotel/orderOperations.js",
			"common/baidudituII.js"]
		cssFiles=["css/store_flight.css"] menuItem="酒店" >
<#include "components/originalOrderInfo.ftl"/>

<#switch hotelOrderVo.hotelOrder.orderType>
	<#case 'ENDORSE_HOTEL'>
	<#case 'PURCHASER_ENDORSE_HOTEL'>
	<#case 'OFFLINE_ENDORSE_HOTEL'>
		<#assign orderTypeName='变更费用'>
	<#break>
	<#case 'REFUND_HOTEL'>
	<#case 'PURCHASER_REFUND_HOTEL'>
	<#case 'OFFLINE_REFUND_HOTEL'>
		<#assign orderTypeName='退订费用'>
	 <#break>
	 <#default>
		 <#assign orderTypeName='其他费用'>
</#switch>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=13736a10c548d2e533c23af49e72de44"></script>
<#include "/page/order/hotel/components/hotelOrderStage.ftl">

<div class="order newOrder">  <!--order start-->
     <div class="orderStatus">
            <table class="endorseOrder newEndorseOrder">
                <colgroup>
                    <col width="300"/>
                    <col width="220"/>
                    <col width="300"/>
                    <col width="200"/>
                </colgroup>
                <tr>
                    <th style="width:320px"><span class="orderNO">订单号：<strong>${hotelOrderId}</strong></span></th>
                    <th colspan="2">
		            	<#if (relatedOrderVos)?? &&(relatedOrderVos?size>1)>
							<#assign endorseSize=relatedOrderVos?size-1>
						<#else>
							<#assign endorseSize=0>
						</#if>
                        <span id="child_order_trigger" class="relevanceOr associatedOrderNo">关联订单<i id="endorseSize">(${(endorseSize)!})</i> <em>&nbsp;</em></span>
                    </th>
                    <th></th>
                </tr>
                <tr>
                    <td colspan="3" class="minWidthLabel">
                        <label><em class="lightGray888">下单时间：</em><i class="lightGray4040">${(hotelOrderVo.hotelOrder.createDate)?date}</i></label>
                        <label><em class="lightGray888">支付方式：</em><i class="lightGray4040">${(hotelOrderVo.externalObject.pagePaymentType)!}</i></label>
                         <label><em class="lightGray888">备注信息：</em><i class="lightGray4040">
                       <#if (hotelOrderVo.hotelOrder.purchaseRemark)??>
                    	<#if (hotelOrderVo.hotelOrder.purchaseRemark?length > 18) >
                    	${hotelOrderVo.hotelOrder.purchaseRemark?substring(0,18)}...
                    	  <#else>
                    	     ${(hotelOrderVo.hotelOrder.purchaseRemark)!}
                    	</#if>
                     </#if>
                        </i></label>
                    </td>
                    <td><em class="lightGray888">状态：</em><b class="endorsing">${(hotelOrderVo.pageState.desc)!}</b>
                        <!--未支付订单时间提醒-->
	                   <b id="time_rest"></b>
	                   	<#if hotelOrderVo.pageState?? && (hotelOrderVo.pageState.desc== "未支付")>
	               			 <input type="hidden"  id="restSecond" value="${(restSecond)!}" />
	                    <#else>
	                         <input type="hidden"  id="restSecond" value="0" />
	                    </#if>
                    </td>
                </tr>
            </table>
     </div>
     <div class="order_trace newOrderTrace clearfix">
             <div class="trace">订单跟踪：</div>
             <ul class="traceList">
            	<#list hotelOrderLogs as hotelOrderLog>
            		<#if hotelOrderLog.purchaserLog?? && (hotelOrderLog.logType != "operator_log")>
            			<#if (hotelOrderLog.purchaserLog != "")>
				         <li <#if hotelOrderLog_index gt 1>class="flexiBleContent"</#if>>
				             <i class="orderIcon"></i>
				             <i class="orderLine" style="top: 50%;"></i>
				             <label>${(hotelOrderLog.purchaserLog)!}</label>
				             <label class="opUser">${(hotelOrderLog.createDate?string("yyyy-MM-dd HH:mm:ss"))!}  &nbsp;&nbsp;&nbsp; 操作人：${(hotelOrderLog.purchaserName)!}</label>
				         </li>
	                     </#if>
            		</#if>
                 </#list>
                 <li class="orderNoLine">
                     <i class="orderLine"></i>
                     <div class="orderFlexibleType">
                         查看全部
                     </div>
                 </li>
             </ul>
             <div class="orderArrowUp"></div>
     </div>
</div>  <!--order end-->

	<div class="departure">
		<div class="chief clearfix">
			<h3 class="chiefTit">预付酒店</h3><b class="tipSpan">${(hotelOrderVo.hotelOrder.refundType)!}</b>
		    <#if (origOrderVo)??>
		    	<#if (hotelOrderVo.hotelOrder.orderType)?? &&( hotelOrderVo.hotelOrder.orderType=="ENDORSE_HOTEL" || hotelOrderVo.hotelOrder.orderType=="PURCHASER_ENDORSE_HOTEL" || hotelOrderVo.hotelOrder.orderType=="OFFLINE_ENDORSE_HOTEL")>
		    		<a href="javascript:void(0);" class="info_view toggle_trigger" toggle='{"target":"#orignal_Order","filter":"","toggleText":["查看原订单","收起原订单"]}'>收起原订单</a>
		    	</#if>
		    </#if>
		</div>
		<#if (origOrderVo)??>
			<#if (hotelOrderVo.hotelOrder.orderType)?? &&( hotelOrderVo.hotelOrder.orderType=="ENDORSE_HOTEL" || hotelOrderVo.hotelOrder.orderType=="PURCHASER_ENDORSE_HOTEL" || hotelOrderVo.hotelOrder.orderType=="OFFLINE_ENDORSE_HOTEL")>
				<div class="advanced_hotel" id="orignal_Order">
				<@bookingProduct.bookingProduct hotelOrderVo=origOrderVo type="orig"/>
				<div class="book_former"></div>
				</div>
			</#if>
			<@bookingProduct.bookingProduct hotelOrderVo=hotelOrderVo type=""/>
		<#else>
			<@bookingProduct.bookingProduct hotelOrderVo=hotelOrderVo type=""/>
		</#if>
	</div>


<div class="departure">
    <div class="chief">
        <h3>联系信息</h3>
    </div>
    <table cellpadding="0" cellspacing="0" class="route traveller noMarginTop">
        <colgroup>
            <col width="248"/>
            <col width="115"/>
            <col width="130"/>
            <col width="125"/>
            <col width="340"/>
        </colgroup>
        <thead>
        <tr>
            <th>联系人姓名</th>
            <th>确认方式</th>
            <th>手机</th>
            <th>固定电话</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${(hotelOrderVo.confirmTypeDetailBo.contactName)!}</td>
            <td> <#if (hotelOrderVo.confirmTypeDetailBo.isSms)?? && hotelOrderVo.confirmTypeDetailBo.isSms>短信  </#if>
                 <#if (hotelOrderVo.confirmTypeDetailBo.isFax)?? && hotelOrderVo.confirmTypeDetailBo.isFax>传真  </#if>
                 <#if (hotelOrderVo.confirmTypeDetailBo.isEmail)?? && hotelOrderVo.confirmTypeDetailBo.isEmail>Email </#if>
            </td>
            <td>${(hotelOrderVo.confirmTypeDetailBo.mobileno)!}</td>
            <td>${(hotelOrderVo.confirmTypeDetailBo.telephone)!}</td>
            <td>${(hotelOrderVo.confirmTypeDetailBo.email)!}</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="departure" style="margin-bottom: 0;">
    <div class="chief"> <h3>订单条款</h3> </div>
    <div style="padding: 15px 10px;">
        <table class="hotel_terms_tips">
	    	<#if (hotelOrderVo.hotelOrder.orderType == 'PURCHASER_REFUND_HOTEL') || (hotelOrderVo.hotelOrder.orderType == 'REFUND_HOTEL') || (hotelOrderVo.hotelOrder.orderType == 'OFFLINE_REFUND_HOTEL')>
	    		 <tr>
	           	 	<td>订单退订成功，原订单与退订单不能作为入住凭证。如有预订酒店需求，请重新查询预订。</td>
	        	</tr>
	    	<#else>
	    		<tr>
	                <td class="talignR">床型：</td>
	                <td>床型由饭店依情况而定。</td>
	            </tr>
	            <tr>
	                <td class="talignR">宠物：</td>
	                <td>不可以携带宠物</td>
	            </tr>
	            <tr>
	                <td class="talignR">入住证件：</td>
	                <td><#if (hotelOrderVo.externalObject.pageGuestTypes)??>
	                        <#list hotelOrderVo.externalObject.pageGuestTypes as ls >
	                        ${(ls.identity)!}
	                        </#list>
	                    </#if>
	                </td>
	            </tr>
	            <tr>
	                <td class="talignR">入住时间：</td>
	                <td><#if (hotelOrderVo.hotelDetailBo.checkinProvision)??>${(hotelOrderVo.hotelDetailBo.checkinProvision)!}:00之后<#else>-</#if>，提前到店可能需要等待；</td>
	            </tr>
	            <tr>
	                <td class="talignR">退房时间：</td>
	                <td><#if (hotelOrderVo.hotelDetailBo.checkinProvision)??>${(hotelOrderVo.hotelDetailBo.checkoutProvision)!}:00之前<#else>-</#if>，延迟离店将会产生其他自理费用。</td>
	            </tr>
	            <tr>
	                <td class="talignR">变更取消：</td>
	                <td>${(hotelOrderVo.hotelDetailBo.cancleProvision)!}</td>
	            </tr>
	            <tr>
	                <td class="talignR">未入住：</td>
	                <td>未入住且切事前未申请取消，将不会获得退款；</td>
	            </tr>
	            <tr>
	                <td class="talignR">提前离店：</td>
	                <td>已入住提前退房，请电话或邮件与客服联系，客服将尽快与酒店确认并为您办理。</td>
	            </tr>
	            <tr>
	                <td class="talignR">其他：</td>
	                <td>如需要变更或取消订房，请将详细需求邮件至 hotel.op@travelzen.com，我们将第一时间为您处理。</td>
	            </tr>
	    	</#if>
        </table>
    </div>
</div>

<div id="footFix">
	<div class="accountInfoBlock" id="ac">
	    <div class="inner_accountInfoBlock">
	    <#switch orderTypeName>
		<#case "变更费用">
	        <span class="accountCon">房费合计<b>${(hotelOrderVo.externalObject.totalOrderSelfFeeYuan)!}</b>- 已支付房费<b>${(origOrderVo.externalObject.totalOrderSelfFeeYuan)!}</b>+ 变更费<b>${(hotelOrderVo.externalObject.orderProcedureFeeYuan)!}</b>
	        = <em style="font-size: 16px;">应付合计</em>
	        <strong class="totlePrice"><em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${(hotelOrderVo.externalObject.totalOrderFeeYuan)!}</b></strong>
	        </span>
		<#break>
		<#case "退订费用">

	        <span class="accountCon">已支付房费<b>${(hotelOrderVo.externalObject.totalOrderSelfFeeYuan)!}</b>- 退订费<b>${((hotelOrderVo.externalObject.totalOrderSelfFeeYuan)!0)?number + ((hotelOrderVo.externalObject.totalOrderFeeYuan)!0)?number}</b>
	        = <em style="font-size: 16px;">应退合计</em>
	        <strong class="totlePrice"><em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${0-(hotelOrderVo.externalObject.totalOrderFeeYuan?number)!0}</b></strong>
	        </span>
		<#break>
		<#case "其他费用">
	        <span class="accountCon">应付合计<strong class="totlePrice"><em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${(hotelOrderVo.externalObject.totalOrderFeeYuan)!}</b></strong>
	        </span>
	        <span>
	        <#if (operations)??>
	        	<@orderOperations.orderDetailPage orderOperations=operations hotelOrderId=hotelOrderId />
		</#if>
	        </span>
	    <#break>
		</#switch>
		<#if canDownloadConfirmOrder?? && canDownloadConfirmOrder = true>
			<a target="_blank" href="${(basepath)!}/pur/order/hotel/download/hotelcomformdownload?hotelOrderId=${(hotelOrderId)!}"><span class="relevanceOr associatedOrderNo">下载客户确认单</span></a>
	    	</#if>
	    </div>
	</div>
</div>

<form action="${(basepath)!}/hotel/booking/search/detail" method="post" id="detailForm" target="_blank">
	<input type="hidden" name="hotelId">
	<input type="hidden" name="checkInDate">
	<input type="hidden" name="checkOutDate">
	<input type="hidden" name="roomNo" value="1">
</form>

<@map.map hotelOrderVo/>
<#include "/page/order/hotel/components/orderCancelWindow.ftl">

</@mc.container>
