$(function() {
	//$('#coin-slider').after('<div id="nav" class="slider_nav" style="left:45%;bottom:10px;">').cycle({fx:"fade",timeout: 2000,height:"300px",pause:true,pager:"#nav",pagerEvent: "click.cycle"});

	kendo.culture("zh-CHS");
	initDate();
	
	CityAutocomplete({
		template : "#city_popup",
		input : "#city",
		// 以@分割热门非热门，以;分割条目，以|分割三字码拼音和中文
		url : basepath + "/autoComplete/hotelAllCitys",
		group : [ "热门", "ABCDEF", "GHJ", "KLMN", "PQSTW", "XYZ" ],
		autoCompleteUrl : basepath + "/autoComplete/hotelCityCode"
	});
	CityAutocomplete({
		template : "#city_popup",
		input : "#city_1",
		// 以@分割热门非热门，以;分割条目，以|分割三字码拼音和中文
		url : basepath + "/autoComplete/hotelAllCitys",
		group : [ "热门", "ABCDEF", "GHJ", "KLMN", "PQSTW", "XYZ" ],
		autoCompleteUrl : basepath + "/autoComplete/hotelCityCode"
	});
	/*$("#city").blur(function() {

		linkageDistrict({
			sourceTrigger : "#cityCode",
			targetTrigger : "#location",
			url : basepath + "/autoComplete/linkage",
			group : [ "ABC", "DEF", "GHIJ", "KLMN", "OPQR", "STUW", "XYZ" ]
		}).init();
	});*/

	//首页tab切换
	$(".hotel_title li").each(function(index){
		$(this).click(function(){
			$(".hotel .tab").hide();
			$(".hotel .tab:eq(" + index + ")").show();
			$(".hotel_title li").removeClass("current");
			$(this).addClass("current");
		});
	});

	//首页热门酒店tab切换
	$(document).on("click", ".hotelPlace li", function(){
		$(".hotHotelContent .hotHotel_show").hide();
		$(".hotelPlace li").removeClass("li_current");
		$(this).addClass("li_current");
		$(".hotHotelContent .hotHotel_show:eq(" + $(this).index() + ")").show();
	});

	var errorTemplate = {
		errorTemplate: '<span class="invalid-text" style="z-index:100;white-space:nowrap;">\
				<span class="message_text" style="font-weight:normal;">#=message#</span>\
		</span>'
	};

	// 酒店首页搜索，验证初始化
	var validator = $('#hotelSearchForm').kendoValidator(errorTemplate).data("kendoValidator");
	var internationalValidator = $('#internationalHotelSearchForm').kendoValidator(errorTemplate).data("kendoValidator");
	var sellpayValidator = $('#selfpayhotelSearchForm').kendoValidator(errorTemplate).data("kendoValidator");
	// 表单验证添加验证条件
	setValidator("#city,#city_1,#internationalCity", "required", true, '城市不能为空',"right", "{top:0,left:-1}");
	setValidator("#roomNo,#roomNo", "required", true, '预订间数不能为空');

	$("#btn_search_first").click(function() {
		if (validator.validate()) {
			//解决ie兼容问题
			if($("input[name='hotelName']").val()=="酒店名称、商圈、地标"){
				$("input[name='hotelName']").val("");
			}

			$('#hotelSearchForm').submit();
			_paq.push(['trackSiteSearch',$('#hotelSearchForm').serialize(),'HotelDoPreSearch:mainSearch']);
		}
	})
	$("#btn_international_search_first").click(function() {
		if(internationalValidator.validate()) {
			//解决ie兼容问题
			if($("input[name='hotelName']").val()=="酒店名称、商圈、地标"){
				$("input[name='hotelName']").val("");
			}

			$("#internationalHotelSearchForm").submit();
			_paq.push(['trackSiteSearch',$('#internationalHotelSearchForm').serialize(),'HotelInPreSearch:mainSearch']);
		}
	})
	
	$("#btn_selfpay_search_first").click(function() {
		if(true) {
			//解决ie兼容问题
			if($("input[name='hotelName']").val()=="酒店名称、商圈、地标"){
				$("input[name='hotelName']").val("");
			}
			
		}
		$("#selfpayhotelSearchForm").submit();
		_paq.push(['trackSiteSearch',$('#selfpayhotelSearchForm').serialize(),'HotelDoSelfSearch:mainSearch']);
	})

	//热门城市酒店特惠
	$('.odds_show.pull-right').eq(0).show().siblings().hide();
	$('.headline ul li').click(function() {
		var int = $('.headline ul li').index(this);
		$('.odds_show.pull-right').eq(int).show().siblings().hide();
		$(this).addClass('pitch-on').siblings().removeClass();
	})

/*	//酒店链接查询提交
	$(".odds_show").find("a").click(function(){
		if($(this).find("img").length==0) {
			$("#hotelName").val($(this).text());
		} else {
			$("#hotelName").val($(this).find("img").attr("title"));
		}
		$("#hotelSearchForm").submit();
	});*/
})

function validateDate(){

/*	if(Date.parse($("#checkinDate").val()) > Date.parse($("#checkoutDate").val())) {
		setValidator("#checkoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "离店日期不能小于入住日期");
	} else {
		delValidator("#checkoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "离店日期不能小于入住日期");
	}*/
	
	//日期天数处理
	var checkinDateStr=$("#checkinDate").val();
	var checkoutDateStr=$('#checkoutDate').val();
	var checkinDate = new Date();
	checkinDate.setFullYear(checkinDateStr.split("-")[0]);
	checkinDate.setMonth(checkinDateStr.split("-")[1]);
	checkinDate.setDate(checkinDateStr.split("-")[2]);
	var checkoutDate = new Date();
	checkoutDate.setFullYear(checkoutDateStr.split("-")[0]);
	checkoutDate.setMonth(checkoutDateStr.split("-")[1]);
	checkoutDate.setDate(checkoutDateStr.split("-")[2]);
	var night = parseInt((checkoutDate.getTime() - checkinDate.getTime()) / (1000 * 60 * 60 * 24)); //工作天数

	if(night>30) {
		setValidator("#checkoutDate,#checkoutDate2", "pattern", "\\d{100}-\\d{100}-\\d{100}", "入住>=30天，请联系客服400-720-6666","right", "{top:0,left:-1}");
	}else{
		delValidator("#checkoutDate,#checkoutDate2", "pattern");
	}
//	validator.validate();

}

function detailSubmit(id) {
	$("input[name='hotelId']").val(id);
	$("#hotelSearchForm").attr("action",$("#hotelSearchForm").attr("val"));
	$("#hotelSearchForm").submit();
}

function citySearch(city,cityIsoCode) {
	var date = new Date();
	var checkin = date.format("yyyy-MM-dd");
	$("#city").val(city);
	$("#cityIsoCode").val(cityIsoCode);
	$("#checkinDate").val(checkin);
	$("#checkoutDate").val(date.getNextDate());
	$("#roomNo")[0].options.selectedIndex=0;

	$("#hotelName").val("");
	$("#hotelRatings")[0].options.selectedIndex=0;
	$("#hotelSearchForm").submit();
}

function interCitySearch(city,cityIsoCode) {
	var date = new Date();
	var checkin = date.format("yyyy-MM-dd");
	$("#internationalCity").val(city);
	$("#internationalCityCode").val(cityIsoCode);
	$("#checkinDate2").val(checkin);
	$("#checkoutDate2").val(date.getNextDate());
	$("#internationalRoomNo")[0].options.selectedIndex=0;

	$("#hotelName").val("");
	$("#internationalHotelSearchForm").submit();
}

function initDate() {
	var date = new Date();
	if(date.getHours()>=18){
		date = new Date(date.getNextDate());
	}
	$("#checkinDate,#checkinDate2").datepicker({
		css : {"z-index":2000},
		numberOfMonths:[1,2],
		minDate :date,
		showButtonPanel :true,
		showDay:true,
		onSelect:function(dateText,inst){
			var date = new Date(inst.selectedYear,inst.selectedMonth,inst.selectedDay);
			$("#checkoutDate,#checkoutDate2").datepicker( "option", "minDate", date.getNextDate());
			$("#checkoutDate,#checkoutDate2").datepicker( "setDate", dateText);
			if($(this).attr("id") == "checkinDate"){
		//		validateDate();
				$("#checkinDate2").datepicker( "setDate", dateText);
			}else if($(this).attr("id") == "checkinDate2"){
		//		validateDate();
				$("#checkinDate").datepicker( "setDate", dateText);
			}
		}
	});
	$("#checkoutDate,#checkoutDate2").datepicker({
		css : {"z-index":2000},
		numberOfMonths:[1,2],
		minDate :date.getNextDate(),
		showButtonPanel :true,
		showDay:true,
		onSelect:function(dateText,inst){
			if($(this).attr("id") == "checkoutDate"){
				$("#checkoutDate2").datepicker( "setDate", dateText);
			}else{
				$("#checkoutDate").datepicker( "setDate", dateText);
			}
			validateDate();
		}
	});
}
