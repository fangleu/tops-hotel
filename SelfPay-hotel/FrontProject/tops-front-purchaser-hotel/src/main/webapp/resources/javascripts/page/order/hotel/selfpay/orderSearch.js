$(function(){
	submitForm();
	
	$("body").delegate("#selfpaySearchBtn","click",function(){
		submitForm();
		_paq.push(["trackSiteSearch",$("#selfpayOrderForm").serialize(),"HotelSelfListOrder:listSearch",$('.pagination em').html()]);
	});
})

function submitForm(pageIndex){
	if(typeof(pageIndex) == "undefined"){
		$("#pageIndex").val(1);
	}else{
		$("#pageIndex").val(pageIndex);
	}
	$.post(basepath + "/pur/hotel/ordermanage/getAllResult",$("#selfpayOrderForm").serialize(),
		function(result){
			$("#orderResult").html(result);
		}
	);
}