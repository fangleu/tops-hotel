<#import "/common/mainContainer.ftl" as mc/>
<@mc.container jsFiles=["page/order/hotel/createNormalOrder.js", "common/formValidator.js","common/selectPanel.js"] cssFiles=[ "css/kendo.reset.css" "/css/store_hotel.css" "/css/kendo.store.css"] localCssFiles=[] menuItem="酒店">
<form action="${basepath}/pur/order/hotel/createOrder" method="post" id="createNormalOrder">
	<input id="contractId" type="hidden" name="hotelDetailBo.contractId" value="${(hotelOrderVo.hotelDetailBo.contractId)!}"/>
    <input id="hotelId" type="hidden" name="hotelDetailBo.hotelId" value="${(hotelOrderVo.hotelDetailBo.hotelId)!}"/>
	<input id="roomCatId" type="hidden" name="hotelDetailBo.roomcatId" value="${(hotelOrderVo.hotelDetailBo.roomcatId)!}"/>
	<input id="bookingClassId" type="hidden" name="hotelDetailBo.bookingclassId" value="${(hotelOrderVo.hotelDetailBo.bookingclassId)!}"/>
	<input type="hidden" name="hotelInterfaceSupplier" value="${(hotelOrderVo.hotelOrder.hotelInterfaceSupplier)!}"/>

	<input type="hidden" name="hotelDetailBo.paymentType" value="${(hotelOrderVo.hotelDetailBo.paymentType)!}"/>
	<input type="hidden" name="hotelDetailBo.cityName" value="${(hotelOrderVo.hotelDetailBo.cityName)!}"/>
	<input type="hidden" name="hotelDetailBo.hotelName" value="${(hotelOrderVo.hotelDetailBo.hotelName)!}"/>
	<input type="hidden" name="hotelDetailBo.region" value="${(hotelOrderVo.hotelDetailBo.region)!}"/>
	<input type="hidden" name="hotelDetailBo.bookingclassName" value="${(hotelOrderVo.hotelDetailBo.bookingclassName)!}">
	<input type="hidden" name="hotelDetailBo.bookingclassExplain" value="${(hotelOrderVo.hotelDetailBo.bookingclassExplain)!}">
	<input type="hidden" name="hotelDetailBo.guestRemark" value="${(hotelOrderVo.hotelDetailBo.guestRemark)!}">
	<input type="hidden" name="hotelDetailBo.roomerNo" value="${(hotelOrderVo.hotelDetailBo.roomerNo)!} ">
	<input type="hidden" name="hotelDetailBo.mainPhone" value="${(hotelOrderVo.hotelDetailBo.mainPhone)!} ">

	<input type="hidden" name="hotelPurchaseContactBo.contactName" value="${(hotelOrderVo.hotelPurchaseContactBo.contactName)!} ">
	<input type="hidden" name="hotelPurchaseContactBo.contactTel" value="${(hotelOrderVo.hotelPurchaseContactBo.contactTel)!} ">
	<input type="hidden" name="hotelPurchaseContactBo.contactMobile" value="${(hotelOrderVo.hotelPurchaseContactBo.contactMobile)!} ">
	<input type="hidden" name="hotelPurchaseContactBo.contactFax" value="${(hotelOrderVo.hotelPurchaseContactBo.contactFax)!} ">
	<input type="hidden" name="hotelPurchaseContactBo.contactEmail" value="${(hotelOrderVo.hotelPurchaseContactBo.contactEmail)!} ">

	<input type="hidden" name="totalBasicFeeYuan" value="${(hotelOrderVo.externalObject.totalBasicFeeYuan)!}"/>
	<input type="hidden" name="totalMarkupFeeYuan" value="${(hotelOrderVo.externalObject.totalMarkupFeeYuan)!}"/>
	<!--supplier供应商填充(以后优化放入redis中)-->
	<input type="hidden" name="hotelSupplierDetailBo.contractManagerName" value="${(hotelOrderVo.hotelSupplierDetailBo.contractManagerName)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.bookingContact" value="${(hotelOrderVo.hotelSupplierDetailBo.bookingContact)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.mobileno" value="${(hotelOrderVo.hotelSupplierDetailBo.mobileno)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.fax" value="${(hotelOrderVo.hotelSupplierDetailBo.fax)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.telephone" value="${(hotelOrderVo.hotelSupplierDetailBo.telephone)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.email" value="${(hotelOrderVo.hotelSupplierDetailBo.email)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.workTime" value="${(hotelOrderVo.hotelSupplierDetailBo.workTime)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.remark" value="${(hotelOrderVo.hotelSupplierDetailBo.remark)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.suppilerName" value="${(hotelOrderVo.hotelSupplierDetailBo.suppilerName)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.bankName" value="${(hotelOrderVo.hotelSupplierDetailBo.bankName)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.accountName" value="${(hotelOrderVo.hotelSupplierDetailBo.accountName)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.accountNo" value="${(hotelOrderVo.hotelSupplierDetailBo.accountNo)!}"/>
	<input type="hidden" name="policyFeeYuan" value="${hotelOrderVo.externalObject.policyFeeYuan!}"/>
	<input type="hidden" name="totalOrderFeeYuan" value="${(hotelOrderVo.externalObject.totalOrderFeeYuan)!}"/>
	<input type="hidden" name="hotelSupplierDetailBo.settlePeriod" value="${(hotelOrderVo.hotelSupplierDetailBo.settlePeriod)!}"/>

	<input type="hidden" name="state" value="${(hotelOrderVo.hotelOrder.state)!}"/>
	<input type="hidden" name="flowState" value="${(hotelOrderVo.hotelOrder.flowState)!}"/>
	<input type="hidden" name="flowOperator" value="${(hotelOrderVo.hotelOrder.flowOperator)!}"/>

	<input type="hidden" name="hotelDetailBo.hotelAddress" value="${(hotelOrderVo.hotelDetailBo.hotelAddress)!}" style="border:none; font-size:12px;width:120px;"/>
    <input type="hidden" name="hotelDetailBo.roomCat" value="${(hotelOrderVo.hotelDetailBo.roomCat)!}"  style="border:none; font-size:12px;width:120px;"/>
    <input type="hidden" name="hotelDetailBo.bedType" value="${(hotelOrderVo.hotelDetailBo.bedType)!}"  style="border:none; font-size:12px;width:120px;"/>


	<input id="isSms" type="hidden" name="confirmTypeDetailBo.isSms" value="true"/>
	<input id="isFax" type="hidden" name="confirmTypeDetailBo.isFax" />
	<input id="isEmail" type="hidden" name="confirmTypeDetailBo.isEmail" />

	<input type="hidden" id="booking_room_count" name="hotelDetailBo.roomNo" value="${(hotelOrderVo.hotelDetailBo.roomNo)!0}" style="border:none; font-size:14px; width:50px;"  />
	<input type="hidden" name="hotelDetailBo.checkinProvision" value="${(hotelOrderVo.hotelDetailBo.checkinProvision)!}"  style="border:none; font-size:12px;width:15px;"/>
	<input type="hidden" name="hotelDetailBo.cancleProvision" value="${(hotelOrderVo.hotelDetailBo.cancleProvision)!}" />
	<input type="hidden" name="hotelDetailBo.checkoutProvision" value="${(hotelOrderVo.hotelDetailBo.checkoutProvision)!}"  style="border:none; font-size:12px;width:15px;"/>
	<input id="giftPkg" type="hidden" name="hotelDetailBo.giftPkg" value="${(hotelOrderVo.hotelDetailBo.giftPkg)!}" />

	<#list hotelOrderVo.hotelPriceDetailBos as ls>
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].sellingFee" value="${(ls.sellingFee)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].basicFee" value="${(ls.basicFee)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].markupFee" value="${(ls.markupFee)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].addedServiceFee" value="${(ls.addedServiceFee)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].breakfastName" value="${(ls.breakfastName)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].breakfastNo" value="${(ls.breakfastNo)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].isWifi" value="${(ls.isWifi)?string}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].checkinDate" value="${(ls.checkinDate)?date}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].roomNo" value="${(ls.roomNo)!}" />
		<input type="hidden" name="hotelPriceDetailBos[${ls_index}].other" value="${(ls.other)!}" />
	</#list>

	<div class="flow clearfix">
		<h3><a href ="" onClick="parent.history.back(); return false;" >返回</a></h3>
		<div class="instr pull-right">
			<span class="highlight-fl">创建订单</span>
			<span>支付</span>
			<span style="margin-right:0px;">确认</span>
			<em style="left:55px;width:215px" class="line"><div style="width:0px"></div></em>
		</div>
	</div>

	<div class="create_order" >
    	<div class="create_outerblock">
    		<aside class="pull-left  side_info orange-border-top create_orderLeft" style="width:180px;">
        		<figure>
		        	<#if (defaultPhoto)??>
		            	<img src="${mediaserver}/imageservice?mediaImageId=${(defaultPhoto)!}&mediaType=image" onerror="javascript:this.src='${host}/images/hotel_noPic.jpg'" width="160" height="120" alt="">
		            <#else>
		            	<img src="${host}/images/hotel_noPic.jpg" width="160" height="120" alt="">
		            </#if>
		            <figcaption style="text-align:left;">${(hotelOrderVo.hotelDetailBo.hotelName)!}</figcaption>
		        </figure>

		        <table style="margin:0 auto;" class="build_hotel_overview">
		            <colgroup>
		                <col width="25%"/>
		                <col width=""/>
		            </colgroup>
            		<tbody>
                		<tr>
                    			<th>地址：</th>
         				<td>${(hotelOrderVo.hotelDetailBo.hotelAddress)!}</td>
                		</tr>
                		<tr>
                    			<th>电话：</th>
         				<td>${(hotelOrderVo.hotelDetailBo.mainPhone)!'-'}</td>
                		</tr>
                		<tr>
                    			<th>房型：</th>
            				<td>${(hotelOrderVo.hotelDetailBo.roomCat)!}</td>
                		</tr>
                		<tr>
	                    		<th>床型：</th>
	                    		<td>${(hotelOrderVo.hotelDetailBo.bedType)!}</td>
                		</tr>
            		</tbody>
				</table>
			</aside>

		    <div class="pull-left" id="h_order_info" style="width:783px;">
		        <div class="row create_orderRight h_order_infoDiv" style="margin-bottom: 0;">
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
		                        	<td>${(hotelOrderVo.hotelDetailBo.roomCat)!}</td>
		                        </tr>
		                        <tr>
		                            <th>入住时间：</th>
		                           <!--  <td>	<input type="hidden" id="checkinDate" name="hotelDetailBo.checkinDate" value="${hotelOrderVo.hotelDetailBo.checkinDate?date}" style="border: none;font-size: 14px;width:100px;" /> ${hotelOrderVo.hotelDetailBo.checkinDate?date}入住；  <input type="hidden" id="checkoutDate" name="hotelDetailBo.checkoutDate" value="${hotelOrderVo.hotelDetailBo.checkoutDate?date}" style="border: none;font-size: 14px;width:100px;" /> ${hotelOrderVo.hotelDetailBo.checkoutDate?date} 离店</td>
		                           -->
		                           <td><span id="span_CheckinDate">${hotelOrderVo.hotelDetailBo.checkinDate?date}</span>入住；
		                            	<span id="span_CheckoutDate">${hotelOrderVo.hotelDetailBo.checkoutDate?date}</span>
		                            	 离店
		                            	 <input type="hidden"  name="hotelDetailBo.checkinDate" value="${hotelOrderVo.hotelDetailBo.checkinDate?date}"  />
		                            	  <input type="hidden"  name="hotelDetailBo.checkoutDate" value="${hotelOrderVo.hotelDetailBo.checkoutDate?date}"  />
		                            	<a  id="change_date" class="t_orange" >修改时间</a>
		                            </td>

		                        </tr>
		                        <tr>
		                            <th>预订间数：</th>
		                        <td>${(hotelOrderVo.hotelDetailBo.roomNo)!0}间
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>房费合计：</th>
		                            <td><span class="trRow_price"><em>¥</em>${(hotelOrderVo.externalObject.totalOrderFeeYuan)!}</span><span class="trRow_room">房费信息</span></td>
		                        </tr>
		                        <tr>
		                            <th style="vertical-align:top;">住客姓名：</th>
		                            <td>旅客姓名应该和所持证件上的拼写一致。（中文：韩美美；英文：HAN/MEIMEI）。
		                                <div class="passenger">
							<div class="passengerBlock">
								<p class="passengerRowTitle"><span class="spanLeft">房间</span><span >中文：韩美美；英文：HAN/MEIMEI</span>
								<#list 1..hotelOrderVo.hotelDetailBo.roomNo as ls>
									<p class="passengerRow">
								 		<span class="spanLeft"><em>*</em>房间${ls_index+1}</span>
								 		<span>
					                                    		<input type="text" id="name${ls_index}"  name="hotelGuestBo[${ls_index}].name"  value=""  placeholder="至少填写一人，多人逗号分隔"  class="gray-border " style="height:23px;width:186px;"  />
					                                       	</span>
									</p>
				                                </#list>
		                            		</div>
						</div>
		                            	<!-- 常旅客 -->
						<#--
		                            	<div class="travelBlock"><label><input type="checkbox" style="vertical-align:middle;" name="save_passenger"/>保存到常旅客</label></div>
		                            	-->
		                        	</td>
		                        </tr>
		                        <#if hotelOrderVo.hotelDetailBo.giftPkg ?? && hotelOrderVo.hotelDetailBo.giftPkg != "">
		                        <tr>
		                            <th style="vertical-align:top;">礼包信息：</th>
		                            <td>${(hotelOrderVo.hotelDetailBo.giftPkg)}</td>
		                        </tr>
		                        </#if>
		                        <tr>
		                            <th style="vertical-align:top;">预订备注：</th>
		                            <td>
		                                <textarea  name="purchaseRemark"  cols="60" rows="3" placeholder="请填写发票相关备注信息;" class="gray-border block_gap"></textarea>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th style="vertical-align:top;">酒店备注：</th>
		                            <td>
		                                <textarea  name="hotelDetailBo.remark"  cols="60" rows="3" placeholder="请填写酒店预订相关备注信息;" class="gray-border block_gap"></textarea>
		                            </td>
		                        </tr>
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
		                              <select data-role="dropdownlist" style="width:162px; " name=""  id="confirmType">
		                                     <option value="0" selected>短信确认</option>
		              <!--      <option value="1">传真确认</option>-->
		                                    <option value="2">Email确认</option>
		                            </select>
		                        </td>
		                    </tr>
		                    <tr>
		                        <th><em>*</em>联系人姓名：</th>
		                      <td><input type="text" name="confirmTypeDetailBo.contactName" value="${(contact.name)!}"  class="gray-border" style="width:160px;"/></td>
		                    </tr>
		                    <tr>
		                        <th>手机号码：</th>
		                   		<td><input type="text" id="contactMobile" name="confirmTypeDetailBo.mobileno" value="${(contact.phone)!}"  class="gray-border" style="width:160px;"/></td>
		                    </tr>
		                    <tr>
		                        <th>固定电话：</th>

		                        <td><input type="text" id="contactTel" name="confirmTypeDetailBo.telephone" value="<#if (contact.zipCode)?? && (contact.zipCode)!="">${(contact.zipCode)!}-</#if>${(contact.tel)!}" class="gray-border" style="width:160px;" placeholder="区号-电话号码-分机号"/></td>

		                    </tr>
		                    <tr>
		                        <th>Email：</th>
		                     <td><input type="text" id="contactEmail" name="confirmTypeDetailBo.email" value="${(contact.emailAddr)!}"  class="gray-border" style="width:160px;"/></td>
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
		                        <td>
		                          <#if (hotelOrderVo.externalObject.pageGuestTypes)??>
				                            <#list hotelOrderVo.externalObject.pageGuestTypes as ls >
				                            ${(ls.identity)!}
				                            </#list>
			                            </#if>
			                            <input type="hidden" name="hotelDetailBo.guestType" value="${(hotelOrderVo.hotelDetailBo.guestType)!}" />
			                       </td>
		                    </tr>
		                    <tr>
		                        <th>入住时间：</th>
		                        <td>${(hotelOrderVo.hotelDetailBo.checkinProvision)!}点以后</td>
		                    </tr>
		                    <tr>
		                        <th>退房时间：</th>
		                        <td>${(hotelOrderVo.hotelDetailBo.checkoutProvision)!}点之前</td>
		                    </tr>
		                    <tr>
		                        <th style="vertical-align:top;">变更/取消：</th>
		                        <td style="line-height:20px;">${(hotelOrderVo.hotelDetailBo.cancleProvision)!}<br/>
		                        未入住且切事前未申请取消，将不会获得退款；<br/>
								已入住提前退房，请电话或邮件与客服联系，客服将尽快与酒店确认并为您办理。<br/>
		                        </td>
		                    </tr>
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
	</div>

     <!---日历显示部分 -->
<script type="text/x-kendo-template" id="salePriceTblTemplate">
    <div class="layerGrayContainer_noArrow">
	<#if (hotelOrderVo.hotelPriceDetailBos)??>
	<#assign Maps={"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9}>
	<#assign weekMaps={"0":"周日","1":"周一","2":"周二","3":"周三","4":"周四","5":"周五","6":"周六"} >
	<#assign hotelPriceDetailBos = hotelOrderVo.hotelPriceDetailBos />
	<#assign days=hotelPriceDetailBos?size>

        <p class="abroadhotelPriceBlock">
        <span><#-- 示例：2014/04/03 周四 至 2014/04/11 周五 8天 -->
				${(criteria.checkInDate?replace("-","/"))!}
				周${(criteria.checkInDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))}
				至 ${(criteria.checkOutDate?replace("-","/"))!}
				周${(criteria.checkOutDate?date("yyyy-MM-dd")?string("EEE")?substring(2,3))}
			</span>

        <span style="float:right;"><i>${(hotelOrderVo.hotelDetailBo.checkinDayNo)!0}</i>晚<i>${(hotelOrderVo.hotelDetailBo.roomNo)!0}</i>间</span></p>
		</p>

	<#assign start = 0 />
	<#assign end = (criteria.validDates?size)/7-1 />
	<#list start..end as i>
        <table class="abroadhotelSalePriceTbl">
            <thead>
            <tr>
                <th style="width:60px;"><b>第${i+1}周</b></th>
					<#list 7*i..(7*(i+1)-1) as index>
                    	<#if (criteria.validDates[index])??>
                    		 <#assign monthAndDay=(criteria.validDates[index])?substring(0,10)>
				             <#assign century=((monthAndDay)?substring(0,2))>
				             <#assign y=(Maps[monthAndDay?substring(2,3)]*10+Maps[monthAndDay?substring(3,4)])>
				             <#assign c=(Maps[monthAndDay?substring(0,1)]*10+Maps[monthAndDay?substring(1,2)])>
				             <#assign m=(Maps[monthAndDay?substring(5,6)]*10+Maps[monthAndDay?substring(6,7)])>
				             <#assign d=(Maps[monthAndDay?substring(8,9)]*10+Maps[monthAndDay?substring(9,10)])>
				             <#if m=1>
					              <#assign m=13>
					              <#assign y=y-1>
				             <#elseif m=2>
					              <#assign m=14>
					              <#assign y=y-1>
				              </#if>
				             <#assign week=(y+(y/4)?int+(c/4)?int-2*c+((26*(m+1))/10)?int+d-1)%7>
					         <#if (week<0)>
					         	<#assign week=week+7>
					         </#if>
					         <th  style="width:60px;"  <#if (week?c=="0")>class="weekend"</#if><#if (week?c=="6")>class="weekend"</#if>>
				             ${(m)!}/${(d)!}${(weekMaps[week?c])!}
				             </th>
                          </#if>
					</#list>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="first">
                    <div><b>价格</b></div>
                    <div><b>早餐</b></div>
                    <div><b>宽带</b></div>
                </td>
          		<#list 7*i..(7*(i+1)-1) as index>
	          		<td>
						<#if (index &lt; hotelPriceDetailBos?size)>
							<div class="price" ruleId="${(hotelPriceDetailBos[index].policyId)!"-"}">
								&yen;${(hotelPriceDetailBos[index].sellingFeeYuan)!"-"}
							</div>
							<div>
									<#if !(hotelPriceDetailBos[index].breakfastNo)?? || hotelPriceDetailBos[index].breakfastNo==0>
			                            无早
                          			<#elseif (hotelPriceDetailBos[index].breakfastNo <0)>
                          				含早
                          			<#else>
                          				${(hotelPriceDetailBos[index].breakfastNo)!0}份&nbsp;
                          			</#if>

							</div>
							<div>
							<#if (hotelPriceDetailBos[index].isWifi)??><#if (hotelPriceDetailBos[index].isWifi)?string == 'true'>包含<#else>无</#if><#else>-</#if>
							</div>
						<#else>
							<div class="price">-</div>
							<div>-</div>
							<div>-</div>
						</#if>
					</td>
				</#list>
            </tr>
            </tbody>
        </table>
        </#list>
        <div class="totlePriceBlock">
            <!--<span style="text-decoration:line-through;margin-right:15px;">原价：1800.30</span>-->
            <span style="font-weight:bold;">总价：<span class="totlePrice"><em>${(hotelOrderVo.externalObject.totalOrderFeeYuan)!}</em>元</span></span>
               <input type="hidden" id="oneRoomHotelFee" value="${(hotelOrderVo.externalObject.totalMarkupFeeYuan?eval) + (hotelOrderVo.externalObject.totalBasicFeeYuan?eval)}" />
        </div>
	</#if><#-- end if validDates -->
    </div>
</script>


<script id="nameRuleTemplate" type="text/x-kendo-template">
    <div class="stipulateCont" >中文示例：韩梅梅<br/>英文示例：需要在名与姓间加"/"：HAN/MEIMEI</div>
</script>

    </section> <!--bottom end-->

<!--模板文件-->
    <div class="accountInfoBlock" id="ac">
	    <div class="inner_accountInfoBlock">
	        <span class="accountCon">应付合计
		        <strong class="totlePrice">
		        <em style="font-size: 20px;"> ¥</em><b style="font-family: Arial">${(hotelOrderVo.externalObject.totalOrderFeeYuan)!}

		        </strong>
	        </span>
	        <span>
	              <a class="adaptiveButton brightRed_btn" style="margin-right: 10px;" id="l_btn">
	                  <span class="left"></span>
	                  <span class="center center_1">提交订单</span>
	                  <span class="right"></span>
	              </a>

	        </span>
	    </div>
	</div>
	</div>



<script>
//日历显示部分
    $(".checkin_info_table .trRow_room").kendoTooltip({
        position:"bottom", //left right top
        wrapperClass:"lineWeight",
        content:function(){
            return $("#salePriceTblTemplate").html();
        },
        callout:false,
        offsetY:-2,
        offsetX:135
    });

//底部浮动
    $('#ac').fixedBar({
        inverse:true
    });
</script>

</form>
<!--修改预定日期以及间数-->
<form action="/tops-front-purchaser-hotel/hotel/booking/search/detail" method="post" id="changeDateForm" >
	<input type="hidden" name="hotelId"  value="${(hotelOrderVo.hotelDetailBo.hotelId)!}" />
	<input type="hidden" id="change_checkinDate"  name="checkInDate" />
	<input type="hidden" id="change_checkoutDate" name="checkOutDate" />
	<input type="hidden" name="roomNo" value="${(hotelOrderVo.hotelDetailBo.roomNo)!0}" />

	<script id="edit-booking-date-template" type="text/x-kendo-template">
    <ul class="change_date" style="width:230px;">

        <li><label>入住日期:<input id="VcheckinDate" type="text" name="" value="${hotelOrderVo.hotelDetailBo.checkinDate?date}" class="datepicker gray-border" readonly/></label></li>
        <li><label>离店日期:<input id="VcheckoutDate" type="text" name="" value="${hotelOrderVo.hotelDetailBo.checkoutDate?date}" class="datepicker gray-border"  readonly/></label></li>
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
