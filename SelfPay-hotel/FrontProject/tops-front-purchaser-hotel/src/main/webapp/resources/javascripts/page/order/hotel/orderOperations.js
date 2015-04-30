var orderCancelRemarkWin;	//只在此js中使用，防止js全局变量污染扩散
/**
 * 订单操作JS
 * @author xumeng
 * @since 2014-12-02
 */
function hotelOrderExcute(operation, url, hotelOrderId) {
	if (operation == "purchaser_pay_order" || operation == "gta_purchaser_pay_order") {
		location.href=basepath + url + "?hotelOrderId=" + hotelOrderId;
		_paq.push(['trackEvent','HotelOrder','HotelPayOrder',hotelOrderId]);
	} else if (operation == "purchaser_cancel_order"
			|| operation == "gta_purchaser_cancel_order"
			|| operation == "selfpay_purchaser_cancel_order") {

		if (orderCancelRemarkWin == null) {
			orderCancelRemarkWin = new PopWindow({
				template:'#orderCancelRemarkTemplate',
				title:'请输入取消原因',
				width:'540'
			}).init();
			$(".not-cancel-order").click(function(){
				orderCancelRemarkWin.close();
			});
			$(".sure-cancel-order").click(function(){
				$("#cancelOrderForm").submit();
				_paq.push(['trackEvent','HotelOrder','HotelCancelOrder',$("#cancelOrderForm").serialize()]);
			});
		}
		orderCancelRemarkWin.open().center();
		$("#cancelOrderForm").attr("action",basepath + url)
		$("#cancelOrderForm input[name='hotelOrderId']").val(hotelOrderId);
	}

}
