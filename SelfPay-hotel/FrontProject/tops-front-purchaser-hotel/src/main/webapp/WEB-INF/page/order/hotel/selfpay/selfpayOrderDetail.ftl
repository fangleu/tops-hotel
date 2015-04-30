<#import "/common/mainContainer.ftl" as mc/>
<#import "/page/order/hotel/selfpay/bookingProduct.ftl" as bookingProduct>
<#import "/page/order/hotel/selfpay/map.ftl" as map>
<#import "/page/order/hotel/components/orderOperations.ftl" as orderOperations>
<@mc.container jsFiles=["page/order/hotel/hotelOrderDetail.js",
			"page/order/hotel/orderOperations.js",
			"common/baidudituII.js"]
		cssFiles=["css/store_flight.css"] menuItem="酒店" >

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
                    <th style="width:320px"><span class="orderNO">订单号：<strong>${hotelOrder.id!}</strong></span></th>
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
                        <label><em class="lightGray888">下单时间：</em><i class="lightGray4040">${(hotelOrder.createDate)?date}</i></label>
                       <#if (hotelOrder.purchaseRemark)??>
                    	<#if (hotelOrder.purchaseRemark?length > 18) >
                    	${hotelOrder.purchaseRemark?substring(0,18)}...
                    	  <#else>
                    	     ${(hotelOrder.purchaseRemark)!}
                    	</#if>
                     </#if>
                        </i></label>
                    </td>
                    <td><em class="lightGray888">状态：</em><b class="endorsing">${(orderStateDesc)!}</b>
                        <!--未支付订单时间提醒-->
	                   <b id="time_rest"></b>
	                   	<#if hotelOrder.pageState?? && (hotelOrder.pageState.desc== "未支付")>
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
			<h3 class="chiefTit">现付酒店</h3><b class="tipSpan">${(hotelOrder.refundType)!}</b>
			<span class="hotel_affirmNO"><b>酒店确认号</b>${(hotelOrder.hotelDetail.hotelConfirmCode)!}</span>
		</div>
		<@bookingProduct.bookingProduct hotelOrder=hotelOrder type=""/>
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
            <td>${(hotelOrder.confirmTypeDetail.contactName)!}</td>
            <td> <#if (hotelOrder.confirmTypeDetail.isSms)?? && hotelOrder.confirmTypeDetail.isSms>短信  </#if>
                 <#if (hotelOrder.confirmTypeDetail.isFax)?? && hotelOrder.confirmTypeDetail.isFax>传真  </#if>
                 <#if (hotelOrder.confirmTypeDetail.isEmail)?? && hotelOrder.confirmTypeDetail.isEmail>Email </#if>
            </td>
            <td>${(hotelOrder.confirmTypeDetail.mobileno)!}</td>
            <td>${(hotelOrder.confirmTypeDetail.telephone)!}</td>
            <td>${(hotelOrder.confirmTypeDetail.email)!}</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="departure" style="margin-bottom: 0;">
    <div class="chief"> <h3>订单条款</h3> </div>
    <div style="padding: 15px 10px;">
        <table class="hotel_terms_tips">
    	    <tr>
                <td>
                <#if hotelOrder.hotelSelfpayBookingInfo.earliestArrivalTime?? && hotelOrder.hotelSelfpayBookingInfo.latestArrivalTime??>
			<#assign earliestArrivalTime = hotelOrder.hotelSelfpayBookingInfo.earliestArrivalTime />
	        	<#assign latestArrivalTime = hotelOrder.hotelSelfpayBookingInfo.latestArrivalTime />
			预计到店时间 ${earliestArrivalTime} 
			到
			<#if earliestArrivalTime?substring(0,10) = latestArrivalTime?substring(0,10) >
	        		${latestArrivalTime?substring(10,16)}
	        	<#else>
	        		${latestArrivalTime}
	        	</#if>
	        	；
		</#if>
		</td>
            </tr>
            <tr>
                <td>床型由酒店依情况而定；</td>
            </tr>
            <tr>
                <td>请到酒店前台报客人姓名办理入住手续，入住时请携带有效证件；</td>
            </tr>
            <tr>
                <td>酒店房价已包含服务费，如无特别注明，均未包含当地政府的收费项目；</td>
            </tr>
            <tr>
                <td>所发生的费用请在离店前直接与酒店结清，并由酒店开具发票给您；</td>
            </tr>
            <tr>
                <td>预订的酒店房间在得到确认后，如需更改请及时通知我们（变更内容包括：酒店名称、客人姓名、入住日期、离店日期、支付方式、取消等）；</td>
            </tr>
            <tr>
                <td>如需要延住，请及时与我们联系，房型、 房数、否则酒店有可能会按照门市价格向您收取；</td>
            </tr>
            <tr>
                <td>按惯例，通常最早入住时间为 14:00，离店时间为正午12:00；如提前入住或推迟离店，酒店会酌情加收一定的费用，请谅解；</td>
            </tr>
            <tr>
                <td>如需要协助变更或取消预订，请将详细需求邮件至 hotel.op@travelzen.com，我们将第一时间为您处理。</td>
            </tr>
        </table>
    </div>
</div>

<div id="footFix">
	<div class="accountInfoBlock" id="ac">
	    <div class="inner_accountInfoBlock">
	        <span class="accountCon">应付合计<strong class="totlePrice"><em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${(hotelOrder.totalOrderFee/100)!}</b></strong>
	        </span>
	        <span>
	        <#if (operations)??>
	        	<@orderOperations.orderDetailPage orderOperations=operations hotelOrderId=hotelOrder.id />
		</#if>
	        </span>
		<#if canDownloadConfirmOrder?? && canDownloadConfirmOrder = true>
			<a target="_blank" href="${(basepath)!}/pur/order/hotel/download/hotelcomformdownload?hotelOrderId=${(hotelOrderId)!}"><span class="relevanceOr associatedOrderNo">下载客户确认单</span></a>
	    	</#if>
	    </div>
	</div>
</div>

<form action="${(basepath)!}/hotel/booking/selfpay/detail" method="post" id="detailForm" target="_blank">
	<input type="hidden" name="hotelId">
	<input type="hidden" name="checkInDate">
	<input type="hidden" name="checkOutDate">
	<input type="hidden" name="roomNo" value="1">
</form>

<@map.map hotelOrder/>
<#include "/page/order/hotel/components/orderCancelWindow.ftl">
</@mc.container>
