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
	setValidator("#city,#internationalCity", "required", true, '城市不能为空',"right", "{top:2,left:0}");
	setValidator("#roomNo", "required", true, '预订间数不能为空');
	
	// 搜索按钮
	$("#btn_search_second").click(function() {
		
/*		if(Date.parse($("#checkinDate").val()) > Date.parse($("#checkoutDate").val())) {
			setValidator("#checkoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "离店日期不能小于入住日期");
		} else {
			delValidator("#checkoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "离店日期不能小于入住日期");
		}*/
		
		delValidator("#maxPrice", "pattern", "[0-9]{1000}", "最小值不能大于最大值");
		
		$("#hotelRating").val("all");
		
		if (validator.validate()) {
			filterFun('searchPage');
		}
	})
	
	//改变城市时刷新filter
	$("#city").change(function(){
		if (validator.validate()) {
			//定时器是为了解决ajax异步请求城市传值延迟bug
			setTimeout('submitAjax()',100);
		}
	})
    
})
