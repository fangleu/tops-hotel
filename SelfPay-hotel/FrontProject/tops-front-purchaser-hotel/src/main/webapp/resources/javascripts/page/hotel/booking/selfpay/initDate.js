/**
 * 应用于预付酒店和国际酒店的搜索页和详情页，初始化搜索条件中的入住日期/离店日期
 * @author xumeng
 * @since 2014-09-17
 */

$(function() {

	//用于详情页，初始化查询表单validator
	$('#detailForm').kendoValidator({
		errorTemplate: '<span class="k-widget k-tooltip k-tooltip-validation" style="z-index:1;">\
			<span class="message_ct">\
				<span class="arrow"></span>\
				<span class="message_text">#=message#</span>\
			</span>\
		</span>'
	}).data("kendoValidator");

	initSelfpayDate();
})

function initSelfpayDate() {
	var date = new Date();
	$("#checkinDate_selfpay").datepicker({
		css : {"z-index":2000},
		numberOfMonths:[1,2],
		minDate :date,
		showButtonPanel :true,
	//	showDay:true,
		onSelect:function(dateText,inst){
			var date = new Date(inst.selectedYear,inst.selectedMonth,inst.selectedDay);
			$("#checkoutDate_selfpay").datepicker( "option", "minDate", date.getNextDate());
			$("#checkoutDate_selfpay").datepicker( "setDate", dateText);
			selfpayValidateDate();
		}
	});
	
	$("#checkoutDate_selfpay").datepicker({
		css : {"z-index":2000},
		numberOfMonths:[1,2],
		minDate :date.getNextDate(),
		showButtonPanel :true,
	//	showDay:true,
		onSelect:function(dateText,inst){
			selfpayValidateDate();
		}
	});
}

function selfpayValidateDate(){
	//日期天数处理
	var checkinDateStr=$("#checkinDate_selfpay").val();
	var checkoutDateStr=$('#checkoutDate_selfpay').val();
	var checkinDate = new Date();
	checkinDate.setFullYear(checkinDateStr.split("-")[0]);
	checkinDate.setMonth(checkinDateStr.split("-")[1]);
	checkinDate.setDate(checkinDateStr.split("-")[2]);
	var checkoutDate = new Date();
	checkoutDate.setFullYear(checkoutDateStr.split("-")[0]);
	checkoutDate.setMonth(checkoutDateStr.split("-")[1]);
	checkoutDate.setDate(checkoutDateStr.split("-")[2]);
	var night = parseInt((checkoutDate.getTime() - checkinDate.getTime()) / (1000 * 60 * 60 * 24)); //工作天数

	if(night>19) {
		setValidator("#checkoutDate_selfpay", "pattern", "\\d{100}-\\d{100}-\\d{100}", "入住>=20天，请联系客服400-720-6666","bottom", "{top:5,left:-67}");
	}else{
		delValidator("#checkoutDate_selfpay", "pattern");
	}
}
