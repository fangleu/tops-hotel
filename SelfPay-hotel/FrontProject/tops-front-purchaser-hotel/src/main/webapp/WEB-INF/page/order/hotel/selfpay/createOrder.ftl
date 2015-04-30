<#import "/common/mainContainer.ftl" as mc/>
<#import "createTemplate.ftl" as createTemplate /><#-- roomlist tip -->
<#import "guaranteeTemplate.ftl" as guaranteeTemplate /><#-- guarantee tip -->
<@mc.container jsFiles=["common/commonUtils.js",
			"common/formValidator.js",
			"page/order/hotel/selfpay/createOrder.js",
			"page/hotel/booking/selfpay/initDate.js",
			"common/autoComplete.js",
			"js/kendo.web.js",
			"js/cultures/kendo.culture.zh-CN.min.js",
			"js/coin-slider.js"]
		cssFiles=["/css/store.css" "/css/store_hotel.css" "/css/store_reset.css"  "/css/kendo.store.css" "/css/kendo.common.min.css"]
		menuItem="酒店">

<div class="flow clearfix">
	<h3><a href ="" onClick="parent.history.back(); return false;" >返回</a></h3>
	<div class="instr pull-right">
		<span class="highlight-hotel">创建</span>
		<span style="margin-right:0px;">提交</span>
		<span style="margin-right:0px;">确认</span>
	</div>
</div>

<div class="create_order" >
    <div class="create_outerblock">
    <aside class="pull-left  side_info orange-border-top create_orderLeft" style="width:180px;">
        <figure>
            <img src="${(hotel.thumbNailUrl)!}" width="160" height="120" alt="${(hotel.hotelName)!}" title="${(hotel.hotelName)!}" />
            <figcaption style="text-align:left;">${(hotel.hotelName)!}</figcaption>
        </figure>

        <table style="margin:0 auto;" class="build_hotel_overview">
            <colgroup>
                <col width="25%"/>
                <col width=""/>
            </colgroup>
            <tbody>
                <tr>
                    <th>地址：</th>
                    <td>${(hotel.address)!}</td>
                </tr>
                 <tr>
                    <th>电话：</th>
                    <td>${(hotel.phone)!}</td>
                </tr>
                <tr>
                    <th>房型：</th>
                    <td>${(roomInfo.roomName)!}</td>
                </tr>
                <tr>
                    <th>床型：</th>
                    <td>${(ratePlan.bedType)!}</td>
                </tr>
                <tr>
                    <th>面积：</th>
                    <td>${(roomInfo.area)!}平方米</td>
                </tr>
                <tr>
                    <th>楼层：</th>
                    <td>${(roomInfo.floor)!}层</td>
                </tr>
                <tr>
                <th>备注：</th>
                <td>${(roomInfo.comments)!}</td>
                </tr>
            </tbody>
        </table>
    
    </aside>
   <form action="${(basepath)!}/pur/order/hotel/selfpay/createOrder" method="post" id="createOrder" >
    <input type="hidden" name="hotelId"  value="${(criteria.hotelId)!}">
	<input type="hidden" name="checkinDate"  value="${(criteria.checkInDate)!}">
	<input type="hidden" name="checkoutDate"  value="${(criteria.checkOutDate)!}">
	<input type="hidden" name="roomId"  value="${(criteria.roomId)!}">
	<input type="hidden" name="roomCatId"  value="${(criteria.roomCatId)!}">
	<input type="hidden" name="bookingClassId"  value="${(criteria.bookingClassId)!}">
	<input type="hidden" name="customerKey"  value="${(criteria.customerKey)!}">
	<input type="hidden" name="totalPrice" value="${totalPrice!0}">
	<input type="hidden" name="purchaseRemark">
	<input type="hidden" name="remarkToHotel">
	
	<input type="hidden" name="selfPay.guaranteeAmount" value="${guaranteeAmount!}">
	<input type="hidden" name="selfPay.guaranteeRulesContent" value="${(ratePlan.cancelMsg)!}">
	<input type="hidden" name="selfPay.currencyCode" value="${(ratePlan.currencyCode)!}">
	<input type="hidden" name="selfPay.latestArrivalTime" value="${(criteria.checkInDate)!} ${(ratePlan.startTime)!}">
	<input type="hidden" name="selfPay.isGuarantee" value="0">
	<input type="hidden" name="selfPay.confirmationType" value="0">
	<input type="hidden" name="isTimeGuarantee" 
		value="<#if ratePlan.guaranteeRule == "01" || ratePlan.guaranteeRule == "11">1<#else>0</#if>">
	<input type="hidden" name="isChooseTimeGuarantee">
    
    <div class="pull-left" id="h_order_info" style="width:783px;">
        <div class="row create_orderRight h_order_infoDiv" style="margin-bottom: 0;">
            <h3 class="h_order_title"><strong>入住信息</strong></h3>
            <section class="row checkin_info font_Color">
            	<#--
                <div id="frequentFlyerContainer">
                    <div id="ffc_title"><label for="ffc_input">选择常旅客</label></div>
                    <div><input type="text" placeholder="请输入姓名搜索" id="ffc_input" name=""></div>
                </div>
                -->
                <table class="checkin_info_table">
                    <colgroup>
                        <col width="115"/>
                    </colgroup>
                    
                    <tbody>
                        <tr>
                            <th>预订房型：</th>
                            <td>${(roomInfo.roomName)!}</td>
                        </tr>
                        
                        <tr>
                            <th>入住时间：</th>
                            <td>${(criteria.checkInDate)!} 入住；${(criteria.checkOutDate)!} 离店 <a href="javascript:modifyDateContainerToggle();" class="button5 modifyDate">修改日期</a>
                              <@createTemplate.modifyDateContainerTemplate checkInDate=criteria.checkInDate checkOutDate=criteria.checkOutDate/>
                            </td>
                        </tr>
                        <tr>
                            <th>预订间数：</th>
                            <td>
                            	<#if (criteria.roomNo)??><#assign roomNumber = criteria.roomNo?number /><#else><#assign roomNumber = 1></#if>
                                <input readonly type="text" id="modifyQuantity" style="width:50px" class="modifyQuantity" name="roomNumber" value="${roomNumber}"/>间 
                            </td>
                        </tr>
                        <tr>
                            <th>房费总价：</th>
                            <td>
                                <span class="trRow_price" ><em>¥</em><b>${totalPrice}</b></span>
                                <span class="trRow_room">房费信息</span>
                                <#if (ratePlan.dayPriceList)??>
                                <@createTemplate.stipulate dayPriceList=ratePlan.dayPriceList weekList=weekList />
                                </#if>
                                <span class="hotel_return">${commissionAmount!0}元</span>
                                <span style="vertical-align: super;">预订免费，入住后酒店前台付款。</span>
                            </td>
                        </tr>
                        <tr>
                            <th style="vertical-align:top;">住客姓名：</th>
                            <td>旅客姓名应该和所持证件上的拼写一致。（中文：韩美美；英文：HAN/MEIMEI）。
                                <div class="passenger">
					<div class="passengerBlock passenger">
					<p class="passengerRowTitle"><span class="spanLeft">房间</span><span class="spanCenter">中文：韩美美；英文：HAN/MEIMEI</span>
					<#list 1..roomNumber as i>
					<p class="passengerRow">
						<span class="spanLeft roomNumber"><em>*</em>房间${i}</span>
						<span id="hotelGuests" class="span">
						<input id="name${i}" type="text" name="hotelGuests[${i-1}].name"  placeholder="住客${i}(中文/英文) (必填)" style="height:23px;width:140px;" class="gray-border"/>
						&nbsp&nbsp&nbsp&nbsp
						<input id="name${roomNumber+i}" type="text" name="hotelGuests[${roomNumber+i-1}].name"  placeholder="住客${i}(中文/英文)" style="height:23px;width:110px;" class="gray-border"/>
						</span>
					</p>
					</#list>
                                </div>

                            </td>
                        </tr>
                        <tr>
									<th style="vertical-align: top;">酒店备注：</th>
									<td>
									<p style="color:#999;margin-bottom:10px;">所有备注要求尽量满足，不能保证</p>
									<div id="RemarkCheckbox" class="hotel-remark-list">
										<label><input id="purchaseRemark1" name="noteRadio" type="radio">尽量大床</label>
										<label><input id="purchaseRemark2" name="noteRadio" type="radio">尽量双床</label>
										<label><input name="noteCheckbox" type="checkbox" value="尽量无烟">尽量无烟</label>
										<label><input name="noteCheckbox" type="checkbox" value="尽量高楼层">尽量高楼层</label>
										<label><input name="noteCheckbox" type="checkbox" value="尽量相邻">尽量相邻</label>
										<label><input name="noteCheckbox" type="checkbox" value="尽量原房续住">尽量原房续住</label>
										</div>
										</td>
								</tr>
                        <#if (ratePlan.startTime)??>
                        <tr>
                            <th style="vertical-align:top;" rowspan="3">最晚到店时间：</th>
                            <#assign lastOver = (.now < (criteria.checkInDate +" "+ ratePlan.startTime)?datetime("yyyy-MM-dd HH:mm"))>
                            <#if lastOver >
	                            <td>
	                                <input id="startName1" name="startName" type="radio" checked/>${(criteria.checkInDate)!}  ${(ratePlan.startTime)!} 前
	                                <span class="hotelCreateRec">推荐</span> 
	                                <span class="hotelCreateTips"><i></i>通常酒店14点办理入住，早到可能需要等待。</span>
	                            </td>
                            </#if>
                        </tr>
                        <tr>
                            <td>
                                <input  id="startName2"  name="startName" type="radio" <#if !lastOver>checked</#if>/>${(criteria.checkInDate)!}  ${(ratePlan.startTime)!} 后 
                            </td>
                        </tr>
                        
                        </#if>
                    </tbody>
                </table>
                
			<@guaranteeTemplate.guaranteeTemplate/>
                
            </section>

            <h3 class="h_order_title"><strong>联系人信息</strong></h3>
            <section>
                <table class="checkin_info_table">
                    <colgroup>
                        <col width="115"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <th><em>*</em>确认方式：</th>
                        <td>
				<select id="confirmType" data-role="dropdownlist" style="width:162px; " name="">
					<option value="短信确认" selected="">短信确认</option>
					<option value="传真确认">传真确认</option>
					<option value="Email确认">Email确认</option>
				</select>
				<input type="hidden" name="hotelConfirmTypeDetail.isSms"  value="0">
				<input type="hidden" name="hotelConfirmTypeDetail.isFax"  value="0">
				<input type="hidden" name="hotelConfirmTypeDetail.isEmail"  value="0">
                        </td>
                    </tr>
                    <tr>
                        <th><em>*</em>联系人姓名：</th>
                        <td><input type="text" id="contactName"  name="hotelConfirmTypeDetail.contactName" class="gray-border" style="width:160px;"  value="${(contact.name)!}"/></td>
                    </tr>
                    <tr>
                        <th><em>*</em>手机号码：</th>
                        <td><input type="text"  id="mobileno"  name="hotelConfirmTypeDetail.mobileno" class="gray-border" style="width:160px;" value="${(contact.phone)!}"/></td>
                    </tr>
                    <tr>
                        <th>固定电话：</th>
                        <td>
                            <input type="text"  id="telephone"  name="hotelConfirmTypeDetail.telephone" class="gray-border" style="width:160px;" value="<#if (contact.zipCode)?? && (contact.zipCode)!="">${(contact.zipCode)!}-</#if>${(contact.tel)!}" class="gray-border" style="width:160px;" placeholder="区号-电话号码-分机号"/>
                        </td>
                    </tr>
                    <tr>
                        <th><em id="emailTip" style="display:none">*</em>Email：</th>
                        <td><input type="text"  id="email"   name="hotelConfirmTypeDetail.email" class="gray-border" style="width:160px;" value="${(contact.emailAddr)!}"/></td>
                    </tr>

                    </tbody>
                </table>
            </section>

        </div>
     </div>
</form>
    </div>
    <input type="hidden" id="amount" value="${(ratePlan.amount)!0}">
    <input type="hidden" id="guaranteeRule" value="${(ratePlan.guaranteeRule)!}">
</div>

    </section>

<div class="accountInfoBlock" id="ac">
    <div class="inner_accountInfoBlock">
        <span class="accountConTips">注：如需发票，请从酒店前台索取</span>
        <span class="accountCon">房费合计<strong class="totlePrice"><em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${totalPrice}</b></strong>
        </span>
        <span>
              <a id="createButton" class="adaptiveButton brightRed_btn" style="margin-right: 10px;">
                  <span class="left"></span>
                  <span class="center center_1">提交订单</span>
                  <span class="right"></span>
              </a>
        </span>
    </div>
</div>
<form action="${(basepath)!}/pur/order/hotel/selfpay/orderView" method="post" id="orderViewForm" >
	<input type="hidden" name="hotelId"  value="${(criteria.hotelId)!}">
	<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}">
	<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}">
	<input type="hidden" name="roomId"  value="${(criteria.roomId)!}">
	<input type="hidden" name="roomCatId"  value="${(criteria.roomCatId)!}">
	<input type="hidden" name="bookingClassId"  value="${(criteria.bookingClassId)!}">
	<input type="hidden" name="roomNo" value="${roomNumber}">
</form>

<form action="${(basepath)!}/pur/order/hotel/selfpay/orderResult" method="post" id="resultForm">
	<input type="hidden" name="isSuccess" value="false">
	<input type="hidden" name="errorInfo">
</form>

<script type="text/x-kendo-template" id="cardCVVTemplate">
    <div class="layerGrayContainer_noArrow" style="background: #fff;">
       <div class="cardChart"><span>信用卡图示</span></div>
       <div class="cardChartCon"></div>
    </div>
</script>

<script type="text/html" id="modifyQuantityTemplate">
    <div class="modifyQuantityContainer">
        <div class="rowNumber">
            <span>预订间数</span>
            <#list 1..5 as i>
            <span class="mdt_roombookingNum <#if roomNumber == i>current</#if>">${i}</span>
            </#list>
        </div>
        <div class="rowBg">
            <span class="promptTxt">大于5间</span>请联系客服请联系客服
        </div>
    </div>
</script>
</@mc.container>
