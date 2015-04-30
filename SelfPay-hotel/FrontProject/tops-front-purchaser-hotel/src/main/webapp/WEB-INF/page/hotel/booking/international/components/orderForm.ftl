<#--
	点击预订按钮时提交的表单
-->
<form action="${(basepath)!}/pur/order/hotel/international/normal/orderView" method="post" id="orderForm">
	<input type="hidden" name="hotelId">
	<input type="hidden" name="roomCatId">
	<input type="hidden" name="bookingClassId">
	<input type="hidden" name="cityIsoCode" value="${(criteria.cityIsoCode)!}"/>
	<input type="hidden" name="checkInDate" value="${(criteria.checkInDate)!}">
	<input type="hidden" name="checkOutDate" value="${(criteria.checkOutDate)!}">
	<input type="hidden" name="roomNo" value="${(criteria.roomNo)!}">
	<input type="hidden" name="roomType" value="${(criteria.roomType)!}"/>
	<input type="hidden" name="nationality" value="${(criteria.nationality)!}" />
	<input type="hidden" name="nationalityName" value="${(criteria.nationalityName)!}" />
</form>