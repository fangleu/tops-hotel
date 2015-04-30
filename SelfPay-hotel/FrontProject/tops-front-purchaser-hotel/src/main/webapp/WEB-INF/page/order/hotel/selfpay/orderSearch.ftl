<#import "/common/userContainer.ftl" as uc/>
<#import "/common/pagination.ftl" as page/>
<@uc.container jsFiles=["page/order/hotel/listOrder.js",
			"page/order/hotel/selfpay/orderSearch.js",
			"page/order/hotel/orderOperations.js",
			"common/autoComplete.js",
			"page/hotel/booking/search/cityList.js",
			"page/hotel/booking/search/zcitytip.js"]
		cssFiles=["css/store_ucenter.css","css/store_flight.css"] localCssFiles=["page/hotel/zcitytip.css"]
		menuItem="酒店" userItem="现付酒店订单">
<#include "/kendoTemplate/kendoCityPop.ftl" />
<main class="us-content">
    <h1 class="us-title">现付酒店订单</h1>
    <div class="us-body">
        <aside>
            <ul class="tab_change clearfix">
                <li class="currentState">
                	<em>
	                	<a href="">订单查询</a>
                	</em>
                </li>
            </ul>
        </aside>
        <div class="tab_item">
            <section class="us-query clearfix">
            	<form id="selfpayOrderForm" method="post">
            		<input type="hidden" name="pageIndex" id="pageIndex" value="1"/>
	                <table class="us-query-table">
	                    <tr>
	                        <th>订单编号：</th>
	                        <td><input id="id" type="text" name="orderNo" value="" style="width:100px;"></td>
	                        <th>下单日期：
	                        </th>
	                   		<td><input id="createDate" class="datepicker dpShort" style="width:75px;" type="text" readonly name="bookingBeginDate">
	                   		 - 
	                   		<input id="endDate" class="datepicker dpShort" style="width:75px;" type="text" readonly name="bookingEndDate"></td>
	                        <th>订单状态：</th>
	                        <td>
	                        	<select data-role="dropdownlist" style="width:55px" name="orderTypes">
                        			<option value="">全部</option>
                        			<option value="">正常</option>
                        			<#--
	                                <#list customerHotelOrderTypes as custHotelOrderType>
	                                	<option value="${custHotelOrderType}" >${custHotelOrderType.getName()}</option>
	                                </#list>
	                                -->
		                        </select>
		                    	 <select data-role="dropdownlist" style="width:79px" name="orderStates">
		                        	<option value="">订单状态</option>
		                        	<#list hotelOrderStates as hotelOrderState>
		                           		<option value="${hotelOrderState}">${hotelOrderState.desc}</option>
		                            </#list>
		                        </select>
							</td>
	                    </tr>
	                    <tr>
	                        <th>城市：</th>
	                        <td><input id="city" type="text" name="cityName" value="" style="width:105px;padding:0 0;">
	                        <input id="cityCode" name="cityCode" type="hidden" value=""/>
	                        </td>
		                        <th>入住日期：</th>
		                        <td>
		                            <input id="checkinDate" type="text" name="checkInDate" class="datepicker dpUlt" value="" readonly style="width:150px;" >
		                        </td>
		                    <th>旅客姓名：</th>
		                    <td>
		                    	<input id="guestName" type="text" name="guestName" value="" style="width:130px;">
		                    </td>
	                    </tr>
	                </table>
	                <a id="selfpaySearchBtn" class="adaptiveButton brightRed_btn">
	                    <span class="left"></span>
	                    <span class="center">搜&nbsp;索</span>
	                    <span class="right"></span>
	                </a>
	            </form>
            </section>
            
            <section id="orderResult" class="us-result"></section>
        </div>
    </div>

</main>
<#include "/page/order/hotel/components/orderCancelWindow.ftl">
</@uc.container>