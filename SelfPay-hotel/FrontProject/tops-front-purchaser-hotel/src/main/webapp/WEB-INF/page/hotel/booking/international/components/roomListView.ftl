<#--
	展示房型列表，currentPage变量，可选值为：search/detail，分别代表在搜索页和详情页显示的差异内容
	author:	xumeng
	create:	2014-04-01
-->
<#macro roomListView hotel criteria currentPage="search">

<#import "priceCalendar.ftl" as priceCalendar /><#-- 引入 价格日历 -->
<#import "tooltipTemplate.ftl" as tooltipTemplate /><#-- roomlist tip -->

<div class="roomTypeListContainer clearfix">
	<table>
		<thead>
			<tr>
				<td class="roomTypeGroupFirstField" width="238px">房型</td>
				<td width="101px">床型</td>
				<td width="120px">餐食</td>
				<td width="36px"></td>
				<td width="50px">规定</td>
				<td width="136px"></td>
				<td width="40px"></td>
				<td width="100px">价格</td>
				<td width="107px"></td>
			</tr>
		</thead>
	</table>

	<#if (hotel.roomCats)??>
	<div class="roomTypeDetailContainer" style="">
		<table>
			<tbody>
			<#list (hotel.roomCats) as roomCat>
				<#if (roomCat.validRoomCat)?? && (roomCat.validRoomCat)?string == 'true'>
					<#list roomCat.bookingClass as bookingClass>
						<tr>
							<td width="254px" class="roomTypeFirsttd">
							<#if currentPage=="detail">
								<span class="moreRoomIcon-<#if roomCat_index!=0>un</#if>fold toggle_trigger"
										data-toggle='{"target":"#flowDetail-info${roomCat_index}","textFilter":"em","toggleClass":["moreRoomIcon-unfold","moreRoomIcon-fold"],"toggleText":["",""]}'>
									<em></em><i></i>
								</span>

							</#if>
								${(roomCat.name)!}
								<#if (bookingClass.advanceTime)?? && bookingClass.advanceTime?length gt 3>
									<label class="timeContent">
										<i class="timePic"/></i>
											<#if (bookingClass.advanceTime?substring(0,bookingClass.advanceTime?length-3)?eval lte 12)>
												上午${(bookingClass.advanceTime)!}前
											<#else>
												下午${(bookingClass.advanceTime)!}前
											</#if>
									</label>
								</#if>
							</td>
							<td width="106px">
								<span>
									<#assign roomType="">
									<#if (criteria.roomType)??>
										<#assign roomType=criteria.roomType>
									</#if>
									<#if roomType=="TS">单人用双人房
									<#elseif roomType=="DB">双人大床房
									<#elseif roomType=="TB">双人两床房
									<#elseif roomType=="SB">单人房
									<#elseif roomType=="TR">三人房
									<#elseif roomType=="Q">四人房
									<#else>-
									</#if>
									</span>
							</td>
							<td width="116px">
								<#if (bookingClass.bookingClassDailyRates[0].breakFastOptionalService)??>
									${(bookingClass.bookingClassDailyRates[0].breakFastOptionalService[0].remark)!}
								<#else>
								无
								</#if>
							</td>
							<td width="47px">
								<#if (roomCat.essentialInfos)??>
								<span class="importantFont">重要</span>
								<@tooltipTemplate.importantTemplate essentialInfos=roomCat.essentialInfos />
								</#if>
							</td>
							<td width="57px">
								<#if (bookingClass.gtaConditions)??>
								<em class="stipulateFont">规定</em>
								<@tooltipTemplate.stipulateTemplate gtaConditions=bookingClass.gtaConditions />
								</#if>
							</td>
							<td width="133px">
								<span class="greenFont fontsize12">
									立即确认
									<#-- Id 开头不是001的都是动态直联价格 -->
									<#if (bookingClass.id?split(":"))[0] != "001">
									<i class="assistTips"></i>
									</#if>
								</span>
							</td>
							<td width="36px">
								<#if (roomCat.markupFeeAddedItemPrice.includedOfferDiscount)?? && roomCat.markupFeeAddedItemPrice.includedOfferDiscount &gt; 0>
								<i class="iconFavor"></i>
								</#if>
							</td>
							<td width="111px">
								<#assign bookingId=("price_"+bookingClass.id)?replace(":","_")>
								<span class="hotelRoomPrice"  rateid="${(bookingId)!}">
									<em class="changeFont">&yen;</em>${(bookingClass.bookingClassDailyRates[0].sellingRateRMB)!}
								</span>
								<#if (criteria.validDates)??>
									<@priceCalendar.priceCalendar id=bookingId criteria=criteria itemPrice=roomCat.markupFeeAddedItemPrice bookingClassDailyRates=bookingClass.bookingClassDailyRates />
								</#if>
							</td>
							<td width="98px">
								<a id="${(hotel.id)!}_${(roomCat.id)!}_${(bookingClass.id)!}"
										href="javascript:submitOrderForm('${(hotel.id)!}','${(roomCat.id)!}','${(bookingClass.id)!}');"
										class="adaptiveButton mediumOrange_btn">
									<span class="left"></span>
									<span class="center center_1">预订</span>
									<span class="right"></span>
								</a>
							</td>
						</tr>
						<#if currentPage=="detail">
						<tr id="flowDetail-info${roomCat_index}" <#if roomCat_index!=0>style="display:none"</#if>>
							<td colspan="9" >
								<div class="moreRoomDetail">
									<div class="textDetail">
										<p>房型简介：${(bookingClass.remarks)!}
										</p>
										<p>房间设施：
										<#if (hotel.facilities)??>
											<#list hotel.facilities as f>
												<#if f.facilityTypeName=="roomFacility">
													${f.facilityName!},
												</#if>
											</#list>
										</#if>
										</p>
									</div>
								</div>
							</td>
						</tr>
						</#if>
					</#list><#-- BookingClassList end -->
				</#if>
			</#list><#-- RoomCat end -->
			</tbody>
		</table>
	</div>
	</#if>
	<#if currentPage=="search">
	<div class="roomTypeListBottomButtonContainer">
		<a href="javascript:void(0);" class="expand">收起全部房型<i class="ico_up"></i></a>
		<a href="javascript:void(0);" class="expand" style="display:none;" >展开全部房型<i class="ico_down"></i></a>
	</div>
	</#if>
</div>

</#macro>
