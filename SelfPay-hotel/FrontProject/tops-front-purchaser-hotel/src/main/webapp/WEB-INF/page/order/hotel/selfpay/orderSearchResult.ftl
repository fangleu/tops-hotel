<#import "/common/pagination.ftl" as page/>
<#import "/page/order/hotel/components/orderOperations.ftl" as orderOperations>
<table>
    <thead>
        <tr>
            <th>订单编号</th>
            <th>下单日期</th>
            <th nowrap>城市</th>
            <th>酒店名称</th>
            <th>入住日期</th>
            <th>离店日期</th>
            <th nowrap>订单金额</th>
            <th nowrap>订单状态</th>
            <th nowrap style="width:40px;">操作</th>
        </tr>
    </thead>
    <#if hotelOrderBos?? && hotelOrderBos.hotelOrders??>
        <#list hotelOrderBos.hotelOrders as hotelOrder>
            <tr>
            	<#assign orderUrl="${basepath}/pur/hotel/ordermanage/detail?hotelOrderId=${(hotelOrder.id)!}"/>
                <td><a  class="text1" href="${(orderUrl)!}">${(hotelOrder.id)!}</a>
                <div class="oper">操作员：
                <#if (hotelOrder.orderSource)?? && hotelOrder.orderSource != "tops_b2b">
                	${(hotelOrder.creatorName)!}
        	<#else>
                	客服
                </#if>
                </div>
                </td>
                <td>${(hotelOrder.createDate?date?string("yyyy-MM-dd"))!}</td>
                <td>${(hotelOrder.cityName)!}</td>
                <td>${(hotelOrder.hotelName)!}</td>
                <td>${(hotelOrder.checkinDate)!}</td>
                <td>${(hotelOrder.checkoutDate)!}</td>
                <td>${(hotelOrder.totalOrderFee / 100)!}</td>
                <td>
            	<#list hotelOrderStates as hotelOrderState>
            		<#if hotelOrder.state == hotelOrderState>${hotelOrderState.desc}</#if>
            	</#list>
                </td>
                <td>
		<#if (batchOrderOperations[hotelOrder.id])??>
			<@orderOperations.orderSearchPage orderOperations = batchOrderOperations[hotelOrder.id] hotelOrderId=hotelOrder.id />
		</#if>
		</td>
            </tr>
        </#list>
    </#if>
</table>

<#if (hotelOrderBos.pageIndex)??>
	<#if (hotelOrderBos.pageIndex < 3)>
		<#assign startPage = 1>
	<#else>
		<#assign startPage = hotelOrderBos.pageIndex - 2>
	</#if>
	<#if hotelOrderBos.totalPage == 0>
		<#assign endPage = 1>
	<#else>
		<#assign endPage = hotelOrderBos.totalPage>
	</#if>
	<@page.container hotelOrderBos.pageIndex startPage endPage hotelOrderBos.totalPage hotelOrderBos.totalCount />
</#if>
