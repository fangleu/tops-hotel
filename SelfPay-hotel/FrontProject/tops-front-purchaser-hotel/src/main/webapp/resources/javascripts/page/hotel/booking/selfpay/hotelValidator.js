$(function(){
	// 酒店搜索结果页面，验证初始化
	var validator = $('.hotel_search').kendoValidator({
		errorTemplate: '<span class="k-widget k-tooltip k-tooltip-validation" style="z-index:1;">\
			<span class="message_ct">\
				<span class="arrow"></span>\
				<span class="message_text">#=message#</span>\
			</span>\
		</span>'
	}).data("kendoValidator");

	// 表单验证添加验证条件
	setValidator("#city_1", "required", true, '城市不能为空',"right", "{top:2,left:0}");

	// 搜索按钮
	$("#btn_search_second").click(function() {

/*		if(Date.parse($("#checkinDate").val()) > Date.parse($("#checkoutDate").val())) {
			setValidator("#checkoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "离店日期不能小于入住日期");
		} else {
			delValidator("#checkoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "离店日期不能小于入住日期");
		}*/

		delValidator("#maxPrice", "pattern", "[0-9]{1000}", "最小值不能大于最大值");

		if (validator.validate()) {
//			filterFun();
			submitAjax('searchPage');
		}
	})

})
