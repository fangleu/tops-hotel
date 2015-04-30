<#import "/common/userContainer.ftl" as uc/>
<#import "/common/pagination.ftl" as page/>
<#import "/page/order/hotel/components/orderOperations.ftl" as orderOperations>
<@uc.container jsFiles=["page/order/hotel/listOrder.js",
			"page/order/hotel/orderOperations.js",
			"common/autoComplete.js",
			"page/hotel/booking/search/cityList.js",
			"page/hotel/booking/search/zcitytip.js"]
		cssFiles=["css/store_ucenter.css","css/store_flight.css"] localCssFiles=["page/hotel/zcitytip.css"]
		menuItem="酒店" userItem="预付酒店订单">
<#include "/kendoTemplate/kendoCityPop.ftl" />
<main class="us-content">
    <h1 class="us-title">预付酒店订单</h1>
    <div class="us-body">
        <aside>
            <ul class="tab_change clearfix">
            	<#list customerQuickQueryTabs as queryTab>
	                <li <#if (criteria.queryTab)?? &&  criteria.queryTab == queryTab>class="currentState"</#if>>
	                	<em>
		                	<a href="${basepath}/pur/hotel/ordermanage/orders?queryTab=${queryTab}">${queryTab.tabName}</a>
	                	</em>
	                </li>
                </#list>
            </ul>
        </aside>
        <div class="tab_item">
            <section class="us-query clearfix">
            	<form action="${basepath}/pur/hotel/ordermanage/searchOrders" id="orderForm" method="post">
            		<input type="hidden" name="queryTab" value="${(criteria.queryTab)!}" />
            		<input type="hidden" name="pageNo" id="page" value="${(pageBean!).page!1}" />
	                <table class="us-query-table">
	                    <tr>
	                        <th>订单编号：</th>
	                        <td><input id="id" type="text" name="id" value="${(criteria!).id!}" style="width:100px;"></td>
	                        <th>
	                        	<#assign type="下单">
	                        	<#if  (criteria.queryTab)??>
	                        		<#switch criteria.queryTab><#case "endorse"><#case "refund"><#assign type="变更"></#switch>
	                        	</#if>
	                        	${type}日期：
	                        </th>
	                   <#-- <td><input id="createDate" class="datepicker dpUlt" type="text" name="createDate" <#if (criteria!).createDate??>value="${criteria.createDate?date}"</#if>></td>
	                   -->
	                   		<td><input id="createDate" class="datepicker dpShort" style="width:75px;" type="text" readonly name="createDate" <#if (criteria.createDate)??>value="${criteria.createDate?date}"</#if>>
	                   		 - 
	                   		<input id="endDate" class="datepicker dpShort" style="width:75px;" type="text" readonly name="endDate" <#if (criteria.endDate)??>value="${criteria.endDate?date}"</#if>></td>
	                   		<#if (criteria.queryTab)?? &&criteria.queryTab="queryTab">
		                        <th>订单状态：</th>
		                        <td>
			                        <#assign show=true>
			                        <#if  (criteria.queryTab)??>
			                        	<#switch criteria.queryTab><#case "confirmTab"><#assign show=false></#switch>
			                        </#if>
			                        <#if show>
			                        	<select data-role="dropdownlist" style="width:55px" id="customerHotelOrderType" name="customerHotelOrderType">
			                        			<option value="">全部</option>
				                                <#list customerHotelOrderTypes as custHotelOrderType>
				                                	<option value="${custHotelOrderType}" <#if criteria??><#if criteria.customerHotelOrderType??><#if custHotelOrderType?string==(criteria!).customerHotelOrderType>selected</#if></#if></#if>>${custHotelOrderType.getName()}</option>
				                                </#list>
				                        </select>
				                    </#if>
			                    	 <select data-role="dropdownlist" <#if !show>style="width:135px"<#else>style="width:79px"</#if> id="hotelOrderPageState" name="hotelOrderPageState">
			                        	<option value="">订单状态</option>
			                        	<#list customerHotelOrderStates as custHotelOrderState>
			                           		<option value="${custHotelOrderState}" <#if criteria??><#if criteria.hotelOrderPageState??> <#if custHotelOrderState==(criteria!).hotelOrderPageState>selected</#if></#if></#if>>${custHotelOrderState.desc}</option>
			                            </#list>
			                        </select>
								</td>
								 </#if>
	                    </tr>
	                    <tr>
	                        <th>城市：</th>
	                        <td><input id="city" type="text" name="cityName" value="${(criteria.cityName)!}" style="width:105px;padding:0 0;">
	                        <input id="cityCode" name="cityCode" type="hidden" value="${(criteria.cityCode)!}"/>
	                        </td>
		                        <input type="hidden" id="queryTab" value="${(criteria!).queryTab!}">
		                        <input type="hidden" id="customerHotelOrderType" value="${(criteria!).customerHotelOrderType!}">
		                        <th>入住日期：</th>
		                        <td>
		                            <input id="checkinDate" type="text" name="checkinDate" class="datepicker dpUlt" value="<#if (criteria.checkinDate)??>${criteria.checkinDate?date}</#if>" readonly style="width:150px;" >
		                        </td>
		                    <th>旅客姓名：</th>
		                    <td>
		                    	<input id="guestName" type="text" name="guestName" value="${(criteria!).guestName!}" style="width:130px;">
		                    </td>
	                    </tr>
	                </table>
	                <a class="adaptiveButton brightRed_btn">
	                    <span class="left"></span>
	                    <span class="center" onclick="$('#orderForm').submit()">搜&nbsp;索</span>
	                    <span class="right"></span>
	                </a>
	            </form>
            </section>
            <section class="us-result">
                <table>
                    <thead>
                        <tr>
                            <th>订单编号</th>
                            <th style="width:65px;">${type}日期</th>
                            <th nowrap>城市</th>
                            <th>酒店名称</th>
                            <th style="width:65px;">入住日期</th>
                            <th style="width:65px;">离店日期</th>
                            <th nowrap>订单金额</th>
                            <th nowrap>订单状态</th>
                            <th nowrap style="width:40px;">操作</th>
                        </tr>
                    </thead>
                    <#if hotelOrderBos??>
	                    <#list hotelOrderBos as hotelOrder>
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
		                        <td>${hotelOrder.createDate?date}</td>
		                        <td>${(hotelOrder.cityName)!}</td>
		                        <td>${(hotelOrder.hotelName)!}</td>
		                        <td><#if (hotelOrder.checkinDate)??>${(hotelOrder.checkinDate)?string("yyyy-MM-dd")}</#if></td>
		                        <td><#if (hotelOrder.checkoutDate)??>${(hotelOrder.checkoutDate)?string("yyyy-MM-dd")}</#if></td>
		                        <td>${(hotelOrder.totalOrderFeeYuan)!}</td>
		                        <td>${(hotelOrder.pageState.desc)!}</td>
		                        <td>
		                        <#if (batchOrderOperations[hotelOrder.id])??>
			                        <@orderOperations.orderSearchPage orderOperations = batchOrderOperations[hotelOrder.id] hotelOrderId=hotelOrder.id />
		                        </#if>
					</td>
		                    </tr>
	                    </#list>
	                </#if>
                </table>
                <@page.container pageBean.page pageBean.startPage pageBean.endPage pageBean.totalPage pageBean.totalCount />
            </section>
        </div>
    </div>

</main>
<#include "/page/order/hotel/components/orderCancelWindow.ftl">
</@uc.container>