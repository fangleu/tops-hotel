<#import "/common/mainContainer.ftl" as mc/>
<#import "priceDetail.ftl" as priceDetail />
<@mc.container jsFiles=["page/order/hotel/internationalOrderView.js","common/formValidator.js","common/selectPanel.js"]
				cssFiles=["/css/store_hotel.css"]
				localCssFiles=[]
				menuItem="酒店">

<form action="${basepath}/pur/order/hotel/international/normal/create" method="post" id="createGTANormalOrder">

<input type="hidden" name="hotelOrderGTABookingItemBos[0].itemCity" value="${(hotel.cityIsoCode)!}" />
<input type="hidden" name="hotelOrderGTABookingItemBos[0].itemCityName" value="${(hotel.cityName)!}" />
<input type="hidden" name="hotelOrderGTABookingItemBos[0].itemCode" value="${(hotel.id?split('_')[1])!}" />
<input type="hidden" name="hotelOrderGTABookingItemBos[0].itemName" value="${(hotel.name)!}" />
<input type="hidden" name="hotelOrderGTABookingItemBos[0].bookingItemType" value="hotel" />
<input type="hidden" name="hote.id" value="${(hotel.id)!}" />


<div class="flow clearfix">
	<h3><a href ="" onClick="parent.history.back(); return false;" >返回</a></h3>
	<div class="instr pull-right">
		<span class="highlight-fl">创建订单</span>
		<span>支付</span>
		<span style="margin-right:0px;">确认</span>
	</div>
</div>

<div class="create_order">
    <aside class="pull-left lightGray_border side_info orange-border-top" style="width:180px;border-right: none;">
        <figure>
			<img src="${(hotel.defaultPhoto)!}" width="160" height="120" alt="" title="" />
			<figcaption style="text-align:left;">${(hotel.name)!}</figcaption>
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
                    <td>${(hotel.mainPhone)!'-'}</td>
                </tr>
                <tr>
                    <th>房型：</th>
                    <td>${(roomCat.name)!}</td>
                </tr>
                <tr>
                    <th>介绍：</th>
                    <td>${(hotel.summary)!}</td>
                </tr>
            </tbody>
        </table>
    </aside>

    <div class="pull-left" id="h_order_info" style="width:80%">
        <div class="row lightGray_border h_order_infoDiv" >
            <h3 class="h_order_title"><strong>入住信息</strong></h3>
            <section class="row checkin_info font_Color">
                <div id="frequentFlyerContainer">
                    <div id="ffc_title"><label for="ffc_input">选择常旅客</label></div>
                    <div><input type="text" placeholder="请输入姓名搜索" id="ffc_input" name=""></div>
                </div>
                <table class="checkin_info_table">
                    <colgroup>
                        <col width="115"/>
                    </colgroup>

                    <tbody>
                        <tr>
                            <th>预订房型：</th>
                            <td>${(roomCat.name)!}</td>
                        </tr>
                        <tr>
                            <th>入住时间：</th>
                            <td>
								<span><span id="span_CheckinDate">${(criteria.checkInDate)!}</span>入住；</span>
								<span><span id="span_CheckoutDate">${(criteria.checkOutDate)!}</span>离店</span>
								<input type="hidden" name="checkinDate" value="${(criteria.checkInDate)!}"  />
								<input type="hidden" name="checkoutDate" value="${(criteria.checkOutDate)!}" />
								<#--<a  class="t_orange" id="change_date">修改时间</a>-->
                            </td>
                        </tr>
                        <tr>
                            <th>预订间数：</th>
                            <td>${(criteria.roomNo)!}间
                               <select  style="width:80px;display:none" name="checkin_room_count" id="booking_room_count">
                                    <option value="1">1</option>
                                    <option value="2" selected="">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                </select>
                                <span style="position:relative;left:-35px;display:none">间</span>
                               <!-- <label>预订房间&gt;5间可以申请<a class="t_orange" href="javascript:void(0);">团队预订</a>,价格优惠哦</label>-->
                            </td>
                        </tr>
                        <tr>
                            <th>房费合计：</th>
                            <td>
                            	<span class="trRow_price"><em>¥</em>${(roomCat.markupFeeAddedItemPrice.gross)!0}</span>
	                            <span class="trRow_room"">房费信息</span>
	                        	<@priceDetail.priceDetail criteria=criteria itemPrice=roomCat.markupFeeAddedItemPrice bookingClassDailyRates=bookingClass.bookingClassDailyRates />
                            </td>
                        </tr>
						<tr>
                            <th>客人国籍：</th>
							<td>
								<input type="hidden" name="hotelOrderGTADetailBo.nationalityCode" value="${(criteria.nationality)!"CN"}" />
								<input type="text" name="hotelOrderGTADetailBo.nationalityName" value="${(criteria.nationalityName)!"China 中国"}" style="border:none;" readondy />
							</td>
                        </tr>
                        <tr>
                            <th style="vertical-align:top;">住客姓名：</th>
                            <td>旅客姓名应该和所持护照上的拼写一致。（例如：HAN/MEIMEI）。
                                <div class="passenger">
									<div class="passengerBlock">
									<p class="passengerRowTitle"><span class="spanLeft">房间</span><span class="spanCenter">姓/名</span><span class="spanRight">主要旅客</span></p>
									<#switch criteria.roomType>
										<#case "TS"><#assign passengerNo = 1 /><#break>
										<#case "DB"><#assign passengerNo = 2 /><#break>
										<#case "TB"><#assign passengerNo = 2 /><#break>
										<#case "SB"><#assign passengerNo = 1 /><#break>
										<#case "TR"><#assign passengerNo = 3 /><#break>
										<#case "Q"><#assign passengerNo = 4 /><#break>
									</#switch>
									<#list 1..criteria.roomNo as i>
										<input type="hidden" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].roomCode" value="${criteria.roomType}" />
										<input type="hidden" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].roomId" value="${bookingClass.id}" />
										<#list 1..passengerNo as j>
										<p class="passengerRow">
											<span class="spanLeft">
												<#if j=1>
												<em>*</em>
							                	<#switch criteria.roomType>
								                	<#case "TS">单人用双人房<#break>
							            			<#case "DB">双人大床房<#break>
													<#case "TB">双人两床房<#break>
													<#case "SB">单人房<#break>
													<#case "TR">三人房<#break>
													<#case "Q">四人房<#break>
													<#default>-
												</#switch>
												</#if>
											</span>
											<span class="spanCenter">
												<input type="hidden" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].hotelOrderGTAGuestBos[${(j-1)!0}].paxId" value="${(i-1)*passengerNo+j}" />
												<input type="hidden" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].hotelOrderGTAGuestBos[${(j-1)!0}].paxType" value="adult" />
												<input type="hidden" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].hotelOrderGTAGuestBos[${(j-1)!0}].childAge" value="" />
												<input type="text" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].hotelOrderGTAGuestBos[${(j-1)!0}].paxName" value="" placeholder="姓名${(i-1)*passengerNo+j}" style="height:23px;" class="gray-border"/>
											</span>
											<span class="spanRight">
												<input type="radio" name="hotelOrderGTABookingItemBos[0].hotelOrderHotelItemBos[${(i-1)!0}].hotelOrderGTAGuestBos[${(j-1)!0}].isPrimary"
														value="true" onclick="checkPrimary(this);"
														<#if i=1&&j=1>checked</#if>/>
											</span>
										</p>
										</#list>
									</#list>
                                </div>
								</div>
                                <!-- 常旅客 -->
                                <#--<div class="travelBlock"><label><input type="checkbox" style="vertical-align:middle;" name="" />保存到常旅客</label></div>-->

                            </td>
                        </tr>
                        <#--<tr>
                            <th style="vertical-align:top;">预订备注：</th>
                            <td>
                                <textarea  name="purchaseRemark"  cols="60" rows="3" placeholder="请填写发票相关备注信息;" class="gray-border block_gap"></textarea>
                            </td>
                        </tr>-->
                    </tbody>
                </table>
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
							<select data-role="dropdownlist" style="width:160px" name="" id="confirmType">
								<option value="0" selected="">短信确认</option>
							<!--	<option value="1">传真确认</option> -->
								<option value="2">Email确认</option>
							</select>
							<!--确认方式-->
							<input id="isSms" type="hidden" name="confirmTypeDetailBo.isSms" value="true"/>
							<input id="isFax" type="hidden" name="confirmTypeDetailBo.isFax" />
							<input id="isEmail" type="hidden" name="confirmTypeDetailBo.isEmail" />
						</td>
                    </tr>
					<tr>
						<th><em>*</em>联系人姓名：</th>
						<td><input type="text" name="confirmTypeDetailBo.contactName" value="${(contact.name)!}" class="gray-border" style="width:160px;"/></td>
					</tr>
					<tr>
						<th><em>*</em>手机：</th>
						<td><input type="text" name="confirmTypeDetailBo.mobileno" value="${(contact.phone)!}" class="gray-border" style="width:160px;"/></td>
					</tr>
					<tr>
						<th>固定电话：</th>
						<td><input type="text" id="contactTel" name="confirmTypeDetailBo.telephone" value="<#if (contact.zipCode)?? && (contact.zipCode)!="">${(contact.zipCode)!}-</#if>${(contact.tel)!}" class="gray-border" style="width:160px; font-size:12px;" placeholder="区号-电话号码-分机号"/></td>
					</tr>
					<tr>
						<th><em></em>Email：</th>
						<td><input type="text" id="contactEmail" name="confirmTypeDetailBo.email" value="${(contact.emailAddr)!}" class="gray-border" style="width:160px;"/></td>
					</tr>

                    </tbody>
                </table>
            </section>

            <h3 class="h_order_title"><strong>酒店规定</strong></h3>
            <section>
                <table class="checkin_info_table">
                    <colgroup>
                        <col width="115"/>
                        <col />
                    </colgroup>
                    <tbody>
                    <tr>
                        <th>入住证件：</th>
                        <td>中宾在酒店入住时需要携带大陆有效证件。</td>
                    </tr>
                    <tr>
                        <th>入住时间：</th>
                        <td>14:00以后</td>
                    </tr>
                    <tr>
                        <th>退房时间：</th>
                        <td>12:00之前</td>
                    </tr>
                    <#if (bookingClass.gtaConditions)??>
                    <tr>
                        <th style="vertical-align:top;">变更取消：</th>
                        <td style="line-height:20px;">
                        <p>酒店预订成功，不能变更预订，包含客人姓名；</p>
                        <#list (bookingClass.gtaConditions?size-1)..0 as i>
						<#assign gtaCondition=bookingClass.gtaConditions[i]>
						
						<#if (gtaCondition.conditionType)?? && gtaCondition.conditionType=="cancellation">
							<#if gtaCondition.toDate??>
								<#if (gtaCondition.charge)?? && !(gtaCondition.charge)>
									<#if gtaCondition.toDate==gtaCondition.fromDate>
										${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 或之后 取消预订，不收取任何费用；
									<#else>
										${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 至 ${(gtaCondition.fromDate?date?string("yyyy年MM月dd日"))!}
										取消预订，不收取任何费用；
									</#if>
								<#else>
									<#if gtaCondition.toDate==gtaCondition.fromDate>
									${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 或之后 取消预订，需要收取 ¥ ${(gtaCondition.chargeAmountRMB)!} 元;
									<#else>
									${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 至 ${(gtaCondition.fromDate?date?string("yyyy年MM月dd日"))!}
										取消预订，需要收取  ¥  ${(gtaCondition.chargeAmountRMB)!}元;
									</#if>
								</#if>
							<#else>
								<#if (gtaCondition.charge)?? && !(gtaCondition.charge)>
									${(gtaCondition.fromDate?date?string("yyyy年MM月dd日"))!} 或更早 取消预订，不收取任何费用；
								<#else>
									酒店预订一经确认，不能变更和取消预订。
								</#if>
							</#if>
				        <#--		<#if gtaCondition.fromDate?? && !gtaCondition.toDate??>
				        		2014年05月31日 或更早 取消预订，不收取任何费用
				        			酒店预订一经确认，不能变更和取消预订。
				            	<#elseif !gtaCondition.fromDate?? && gtaCondition.toDate?? >
				            		${gtaCondition.toDate?date?string("yyyy年MM月dd日")} 或之后 取消预订，需要收取 ¥ ${(gtaCondition.chargeAmountRMB)!} 元;
				            	<#elseif gtaCondition.fromDate?? && gtaCondition.toDate?? >
				            		${gtaCondition.toDate?date?string("yyyy年MM月dd日")} 至 ${gtaCondition.fromDate?date?string("yyyy年MM月dd日")} 取消预订，需要收取 ¥ ${(gtaCondition.chargeAmountRMB)!} 元；
				            	</#if>-->
				            	<br/>
				        	</#if>
				        	
						
						</#list>
						</td>
					</tr>
                    </#if>
                    <#if (roomCat.essentialInfos)??>
                    <tr>
                        <th style="vertical-align:top;"><span class="fontColor_f1634c">重要信息：</span></th>
                        <td>
	                        <span class="fontColor_f1634c">
								<#list roomCat.essentialInfos as essentialInfo>
									<p>${(essentialInfo.text)!}</p>
									<#if (essentialInfo.fromDate)??>
										从${(essentialInfo.fromDate?date?string("yyyy年MM月dd日"))!}起生效
									</#if>
									<#if (essentialInfo.toDate)??>
										到${(essentialInfo.toDate?date?string("yyyy年MM月dd日"))!}结束
									</#if>
								</#list>
	                        </span>
                        </td>
                    </tr>
                    </#if>
                    </tbody>
                </table>
                <div class="warmTipsDiv">
                    <table class="checkin_info_table">
                        <colgroup>
                            <col width="115"/>
                            <col />
                        </colgroup>
                        <tbody>
                        <tr>
                            <th style="vertical-align:top;"><span class="fontColor_f1634c">温馨提示：</span></th>
                            <td>此酒店预订我们将于支付后2个工作小时内尽快给予确认。如订房无法确认，我们将全额返还相关款项到您的支付账户，非
           工作日，工作时间预订的订单，我们将于工作日尽快确认。</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </section>

        </div>

    </div>
</div>




    </section>

	<div class="accountInfoBlock" id="ac">
	    <div class="inner_accountInfoBlock">
	        <span class="accountCon">应付合计
		        <strong class="totlePrice">
		        <em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${(roomCat.markupFeeAddedItemPrice.gross)!}
		        <input type="hidden" name="hotelOrderGTADetailBo.totalOrderFeeRmbYuan" value="${(roomCat.markupFeeAddedItemPrice.gross)!0}"/></b>
		        </strong>
	        </span>
	        <span>
	              <a class="adaptiveButton brightRed_btn" style="margin-right: 10px;">
	                  <span class="left"></span>
	                  <span class="center center_1">提交订单</span>
	                  <span class="right"></span>
	              </a>

	        </span>
	    </div>
	</div>
</div>
</form>

<form action="/tops-front-purchaser-hotel/hotel/booking/international/detail" method="post" id="changeDateForm" >
<input type="hidden" name="hotelId" value="${(hotel.id)!}" />
<input type="hidden" id="change_checkinDate"  name="checkInDate"/>
<input type="hidden"   id="change_checkoutDate" name="checkOutDate"/>
<input type="hidden" name="roomNo" value="${(criteria.roomNo)!}"/>

<script id="edit-booking-date-template" type="text/x-kendo-template">
    <ul class="change_date" style="width:230px;">

        <li><label>入住日期:<input id="VcheckinDate" type="text" name="" value="${criteria.checkInDate}" class="datepicker gray-border" readonly /></label></li>
        <li><label>离店日期:<input id="VcheckoutDate" type="text" name="" value="${criteria.checkOutDate}" class="datepicker gray-border" readonly /></label></li>
        <li style="padding-left:50px;margin-top:13px;">
            <a id="submitDiv" class="adaptiveButton mediumOrange_btn">
                <span class="left"></span>
                <span class="center center_1">预订</span>
                <span class="right"></span>
            </a>
            <a id="cancelDiv" style="margin-left:20px;" href="javascript:void(0);">取消</a>
        </li>
    </ul>
</script>
</form>

</@mc.container>
