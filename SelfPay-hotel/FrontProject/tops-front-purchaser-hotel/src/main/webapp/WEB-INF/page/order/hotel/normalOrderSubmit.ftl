 <script type="text/javascript" src="${host}/js/jquery.min.js"></script>
<script>
$(function(){
	$("form").submit();
});
</script>

<form action="${purchaserServer}/finance/order/pay" method="post" />
<#--<form action="http://pch.op.com:8280/tops-front-purchaser/finance/order/pay" method="post" />-->
	<input type="hidden" name="hotelOrderId" value="${(hotelOrderId)!}"/>
	<input type="hidden" name="customerId" value="${(accountFrozenReq.customerId)!}"/>
	<input type="hidden" name="orderId" value="${(accountFrozenReq.orderId)!}"/>
	<input type="hidden" name="serialNumber" value="${(accountFrozenReq.serialNumber)!}"/>
	<input type="hidden" name="productType" value="${(accountFrozenReq.productType)!}"/>
	<input type="hidden" name="orderType" value="${(accountFrozenReq.orderType)!}"/>
	<input type="hidden" name="checkoutAmount" value="${(accountFrozenReq.actualCheckoutAmount)!}"/>
	<input type="hidden" name="actualCheckoutAmount" value="${(accountFrozenReq.actualCheckoutAmount)!}"/>
	<input type="hidden" name="checksum" value="${checksum!}"/>
	<input type="hidden" name="billDate" value="${(accountFrozenReq.billDate)!}"/>
	<input type="hidden" name="orderDetailUrl" value="${purchaserHotelServer}/pur/hotel/ordermanage/detail?hotelOrderId=${(hotelOrderId)!}" />
	<input type="hidden" name="orderManagementUrl" value="${purchaserHotelServer}/pur/hotel/ordermanage/orders" />
</form>
