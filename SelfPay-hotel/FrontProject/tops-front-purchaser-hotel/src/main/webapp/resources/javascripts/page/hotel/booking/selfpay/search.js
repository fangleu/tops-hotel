var map
$(function() {
	kendo.culture("zh-CHS");
	submitAjax();
	setTimeout(searchTip, 400);
})

function searchTip() {
	$.loadingbar({
		showClose: true,
		text:'正在为您查询，请稍候…',
		urls: [new RegExp("^" + basepath + "/hotel/booking/selfpay/filterSearch")]
	});
}


//刷新
function submitAjax(paqType) {

	var checkinDate = $("#checkinDate_selfpay").val();
	var checkoutDate = $("#checkoutDate_selfpay").val();
	var cityCode = $("#cityId").val();
	var cityName = $("city_1").val();
	var keywords = $("#keywords").val();
	var roomType = $("select[name='roomType']").val();
	var roomNo = $("#internationalRoomNo").val();

	$.post(basepath + '/hotel/booking/selfpay/getFilterPanel',
		{
			checkInDate : checkinDate,
			checkOutDate : checkoutDate,
			cityIsoCode : cityCode,
			cityName : cityName,
			keywords : keywords,
			roomType : roomType,
			roomNo : roomNo
		},
		function(filterDiv) {
			var filterObj = $("#filterPanel");

			if (filterObj.length > 0) {
				$("#filterPanel").replaceWith(filterDiv);//
			} else {
				$("#searchPanel").append(filterDiv);
			}
			conditionDiv();
			filterFun(paqType);

			// 设置文本框只能为数字
			$("input[name='minPrice']").keyup(function() { // keyup事件处理
				$(this).val($(this).val().replace(/[^0-9]/g, ''));
			}).bind("paste", function() { // CTR+V事件处理
				$(this).val($(this).val().replace(/[^0-9]/g, ''));
			}).css("ime-mode", "disabled"); // CSS设置输入法不可用

			$("input[name='maxPrice']").keyup(function() { // keyup事件处理
				$(this).val($(this).val().replace(/[^0-9]/g, ''));
			}).bind("paste", function() { // CTR+V事件处理
				$(this).val($(this).val().replace(/[^0-9]/g, ''));
			}).css("ime-mode", "disabled"); // CSS设置输入法不可用

			//加事件
			$("#filterPanel input[type!=text]").click(function() {
				var paqType = '';
				var objs = $("#filterPanel input[type='radio']");
				for ( var i = 0; i < objs.length; i++) {
					var obj = objs[i];
					$(obj).parent().attr("sel", "");
				}
				if ($(this).attr("name") == "hotelPrice") {
					$("input[name=minPrice]").val("");
					$("input[name=maxPrice]").val("");
					$("input[name='hotelPrice']").parent().removeClass("sel");
					$(this).parent().addClass("sel");
					$("#price").css("margin-right",30) ;
					$("#price").removeClass("sel").addClass("");
					paqType = 'selectPrice';
				}
				else if ($(this).attr("name") == "district") {
					$("input[name='district']").parent().removeClass("sel");
					$(this).parent().addClass("sel");
					$("#district").css("margin-right",30) ;
					$("#district").removeClass("sel");
					paqType = 'selectDistrict';
				}
				else if ($(this).attr("name") == "businessZone") {
					$("input[name='businessZone']").parent().removeClass("sel");
					$(this).parent().addClass("sel");
					$("#businessZone").css("margin-right",30) ;
					$("#businessZone").removeClass("sel").addClass("");
				}
				else if ($(this).attr("name") == "hotelRatings") {
					$("#tatings").css("margin-right",30) ;
					var objs = $("#filterPanel input[type='checkbox']");
					for ( var i = 0; i < objs.length; i++) {
						var obj = objs[i];
							$(obj).parent().attr("class", "");
					}	
					$("#tatings").removeClass("sel").addClass("");
					$("input[name='hotelRatings']:checked").parent().addClass("sel");
					paqType = 'selectRating';
				}
				if($("input[name='hotelRatings']:checked").size()==0){
					$("#tatings").removeClass("").addClass("sel");
				}

				filterFun(paqType);
			});

			$("#more_facility").hover(function() {
				$(this).show();
			}, function() {
				$(this).hide();
			})

			// 自定义价格输入
			var validator = $("#PriceByHand").kendoValidator({
				
			}).data("kendoValidator");

			$("#setHotelPrice").click(function() {
				// 验证输入值
				if( parseInt($("#minPrice").val(), 10) > parseInt($("#maxPrice").val(), 10) ) {
			//		setValidator("#maxPrice", "pattern", "[0-9]{1000}", "");
					return;
				} else {
					$("#price").removeClass("sel").addClass("");
	//				delValidator("#maxPrice", "pattern", "[0-9]{1000}", "");
				}

				if(validator.validate()) {
					filterFun('selectPrice');
				}

				$("input[name=hotelPrice]").each(function() {
					$(this).parent().attr("class", "");
					$(this).attr("checked", false);
				})
			});
		});
}
// 打开弹出层
function openMore(obj) {
	$(obj).css({
		left : $(obj).parent().position().left,
		top : $(obj).parent().position().top - 29
	}).show();
}

 //关闭弹出层
function closeMore(obj) {
	$(obj).hide();
}

// 删除某个筛选的条件
function delFilterItem(itemName) {
	//处理行政区
	if($("input[name=district]:checked").attr("val") == itemName) {
	//	$("input[name=district]").val("");
		$("input[name='district']:checked").parent().removeClass("sel").addClass("");
		$("input[name=district]").prop("checked", false);
		$("#district").removeClass("").addClass("sel");
	}
	//处理商业区
//	if($("input[name=businessZone]:checked").attr("val") == itemName) {
//		$("input[name='businessZone']:checked").parent().removeClass("sel").addClass("");
//		$("input[name=businessZone]").prop("checked", false);
//		$("#businessZone").removeClass("").addClass("sel");
//	}
	//处理价格区间
	if($("#hotelPrice").attr("val") == itemName) {
		$("input[name=minPrice]").val("");
		$("input[name=maxPrice]").val("");
		$("input[name='hotelPrice']:checked").parent().removeClass("sel").addClass("");
		$("input[name=hotelPrice]").prop("checked", false);
		$("#price").removeClass("").addClass("sel");
	}
	//处理酒店星级
	var objs = $("#filterPanel input[type='checkbox']");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		if($(obj).attr("val") == itemName) {
			$(obj).prop("checked", false);
			$(obj).parent().removeClass("sel").addClass("");
		}
	}
	if($("input[name='hotelRatings']:checked").size()==0){
		$("#tatings").removeClass("").addClass("sel");
	}
	filterFun('delFilter:'+itemName);
}

// 清空所有的筛选条件
function delModelAllItems() {
	$("input[name=minPrice]").val("");
	$("input[name=maxPrice]").val("");
	var objs = $("#filterPanel input[type='checkbox'],#filterPanel input[type='radio']");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		$(obj).prop("checked", false);
		$(obj).parent().attr("class", "");
	}
	$("#price").removeClass("").addClass("sel");
	$("#tatings").removeClass("").addClass("sel");
	submitAjax('clearAllSelects');
}

//不限  行政区
function clearDistrict() {
	var objs = $("#filterPanel input[type='radio']");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		$(obj).prop("checked", false);
		$(obj).parent().attr("class", "");
	}
	$("#district").removeClass("").addClass("sel");
	
	filterFun('selectDistrict');
}

//不限  商业区
function clearBusinessZone() {
	var objs = $("#filterPanel input[type='radio']");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		$(obj).prop("checked", false);
		$(obj).parent().attr("class", "");
	}
	$("#businessZone").removeClass("").addClass("sel");
	
	filterFun();
}

//不限  清理星级
function clearRatings() {
	var objs = $("#filterPanel input[type='checkbox']");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		$(obj).prop("checked", false);
		$(obj).parent().attr("class", "");
	}
	$("#tatings").removeClass("").addClass("sel");
	
	filterFun('selectRating');
}

//不限  清理价格
function clearPrice() {
	var objs = $("#filterPanel input[type='radio']");
	$("input[name=minPrice]").val("");
	$("input[name=maxPrice]").val("");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		$(obj).prop("checked", false);
		$(obj).parent().attr("class", "");
	}
	$("#price").removeClass("").addClass("sel");
	filterFun('selectPrice');
}

// 初始化分页
function initToolsBarPage() {
	var pageIndex = null;
	var pageSize = null;
	/**
	 * page
	 */
	$("div.pagination ul li a").click(function() {
		var pageAttr = $(this).attr("name");
		var pageIndex = pageAttr.split("&")[0];
		var pageSize = pageAttr.split("&")[1];
		if (pageIndex == undefined || pageIndex == null || pageIndex == "") {
			alert("text is not work.");
		}
		if (pageSize == undefined || pageSize == null || pageSize == "") {
			alert("pageSize is null");
		}

		pagination(pageIndex, pageSize);
	});

	// 默认排序
	$(".moren").click(function() {
		$("input[name=sortItem]").val("");
		$("input[name=sortOrder]").val("");
		submitAjax('setDefaultSort');
	})

	// 初始化排序
	$(".sorttab i").click(function() {
		var sortItem = $(this).parent().attr("sortItem");
		var sortOrder = $(this).parent().attr("sortOrder");
		if (sortOrder == "" || sortOrder == "1") {
			sortOrder = 0;
		} else {
			sortOrder = 1;
		}
		$("input[name=sortItem]").val(sortItem);
		$("input[name=sortOrder]").val(sortOrder);
		filterFun('setSort:' + sortItem + '_' + sortOrder);
	});

}

// 搜索酒店
function filterFun(paqType) {
	var filterParam = basepath + '/hotel/booking/selfpay/filterSearch';
	var param = getSearchParam();// $("#searchForm").serialize();
	$.post(filterParam, param, function(sortResultPageDiv) {
//		var srpObj = $("#sortResultPage");
//		if (srpObj.length > 0) {
//			$("#sortResultPage").replaceWith(sortResultPageDiv);
//		} else {
//			$("#main_body").append(sortResultPageDiv);
//		}
		$("#sortResultPage").remove();
	    $("#main_body").append(sortResultPageDiv);
		$('#fruitlessDiv').hide();
		resultDiv();

		//初始化房型列表Tip控件
		initRoomListViewTip();

		// 初始化排序菜单分页
		initToolsBarPage();
		$(".backToTop").click();//查询后自动返回顶部

		if (isNotBlank(paqType)) {
			_paq.push(['trackSiteSearch',param,'HotelDoSelfSearch:'+paqType,$('.hotel_resultNo').html()]);
		}
	}).error(function () {
		$('#fruitlessDiv').hide();
//		$(".flight_result_list").empty();
//		$('.flight_result_list').append("<div class='searching gray-border'></div>");
//		$('.flight_result_list div').append("<div class='no_result_tip' id='errorTip'></div>");
//		$('#errorTip').append("<p>亲爱的用户:</p><p>没有符合查询条件的结果，请检查查询条件</p>");
//		refreshSelectedItems();
//		resetCriForm();
	});
}

// 底部分页
function pagination(pageIndex, pageSize) {
	$("input[name=pageNo]").val(pageIndex);
	if($("#priceOrder").attr("sortOrder") != null && $("#priceOrder").attr("sortOrder") !="") {
		$("input[name=sortItem]").val($("#priceOrder").attr("sortItem"));
		$("input[name=sortOrder]").val($("#priceOrder").attr("sortOrder"));
	} else if($("#startOrder").attr("sortOrder") != null && $("#startOrder").attr("sortOrder") !="") {
		$("input[name=sortItem]").val($("#startOrder").attr("sortItem"));
		$("input[name=sortOrder]").val($("#startOrder").attr("sortOrder"));
	}
	filterFun('changePageIndex');

};

// 提交订单
function submitOrderForm(hotelId, roomId, roomTypeId, ratePlanId) {
	$("#orderForm > input[name='hotelId']").val(hotelId);
	$("#orderForm > input[name='roomId']").val(roomId);
	$("#orderForm > input[name='roomCatId']").val(roomTypeId);
	$("#orderForm > input[name='bookingClassId']").val(ratePlanId);
	$("#orderForm").submit();
	_paq.push(['trackEvent','HotelCreateOrder','HotelDoSelfListCrOrder','searchPage',$("#orderForm").serialize()]);
}

// 查看酒店详情
function submitDetailForm(hotelId) {
	$("#detailForm > input[name='hotelId']").val(hotelId);
	$("#detailForm").submit();
}

//展开和收起
function conditionDiv() {
	$("#btn_toggleFilterPanel").click(function() {
		$(".filter").slideToggle();
		if ($(this).hasClass("down")) {
			$(this).removeClass("down");
			$(this).text("收起");
		} else {
			$(this).addClass("down");
			$(this).text("展开");
		}
	})
}

// 酒店查询结果，展开和收起
function resultDiv() {
//	$(".expand_hide").hide();

	$(".expand").click(function() {
		var container = $(this).closest(".hotelPanel");
		$(this).hide();
		$(this).siblings().show();

		if ($(this).find(".ico_down").size() != 0) {
			// 展开
			$(".roomTypeDetailContainer", container).each(function() {
				var self = this;
				$(this).animate({
					height : this.scrollHeight
				}, function() {
					$(".expand_hide", self).hide();
					$(".ratePlan_roomNum", self).show();
				});
			});
			_paq.push(['trackEvent','HotelButton','HotelRoomExpanded','HotelDoSelf']);
		} else {
			$(".roomTypeDetailContainer", container).each(function() {
				var self = this;
				$(this).css({
					"overflow" : "hidden"
				}).animate({
					height : "45px"
				}, function() {
					$(".expand_hide", self).show();
					$(".ratePlan_roomNum", self).hide();
				});
			});
			_paq.push(['trackEvent','HotelButton','HotelRoomFold','HotelDoSelf']);
		}
	});
}


//获取搜索的参数值
function getSearchParam() {
	var param = "1=1"

	// 城市
	var cityCode = $("#cityId").val();
	if (cityCode != null && typeof (cityCode) != "undefined" & cityCode !== "") {
		param = param + "&cityIsoCode=" + cityCode;
	}
	var cityName = $("#city_1").val();
	if (cityName != null && typeof (cityName) != "undefined" & cityName !== "") {
		param = param + "&cityName=" + cityName;
	}

	// 入住时间
	var checkInDate = $("#checkinDate_selfpay").val();
	if (checkInDate != null && typeof (checkInDate) != "undefined"
			& checkInDate !== "") {
		param = param + "&checkInDate=" + checkInDate;
	}

	// 离店时间
	var checkOutDate = $("#checkoutDate_selfpay").val();
	if (checkOutDate != null && typeof (checkOutDate) != "undefined"
			& checkOutDate !== "") {
		param = param + "&checkOutDate=" + checkOutDate;
	}

	// 酒店名称 搜索栏关键词
	var keywords = $("#keywords").val();
//	if(keyWords == $("#keyWords").attr("placeholder")) {
//		keyWords ="";
//	}
	if (keywords != null && typeof (keywords) != "undefined"
			& keywords !== "") {
		param = param + "&keywords=" + keywords;
	}

	// 行政区
	var districtCodes = $("input[name=district]:checked").attr("vale");
	if (districtCodes != null && typeof (districtCodes) != "undefined" && districtCodes !== "") {
		param = param + "&districtCodes=" + districtCodes;
	//	param = param + "&conditions=" + districtCodes;
	}
	var district = $("input[name=district]:checked").attr("val");
	if (district != null && typeof (district) != "undefined" && district !== "") {
		param = param + "&districtNames=" + district;
		param = param + "&conditions=" + district;
	}
	
	// 商业区
	var businessZone = $("input[name=businessZone]:checked").attr("val");
	if (businessZone != null && typeof (businessZone) != "undefined" && businessZone !== "") {
			param = param + "&commercialNames=" + businessZone;
			param = param + "&conditions=" + businessZone;
	}
	var commercialCodes = $("input[name=businessZone]:checked").attr("val");
	if (commercialCodes != null && typeof (commercialCodes) != "undefined" && commercialCodes !== "") {
			param = param + "&commercialCodes=" + commercialCodes;
	//		param = param + "&conditions=" + commercialCodes;
	}
	// 星级
	var hotelRatings = $("input[name=hotelRatings]");
	for ( var i = 0; i < hotelRatings.length; i++) {
		if ($(hotelRatings[i]).is(":checked")) {
			param = param + "&hotelRatings=" + $(hotelRatings[i]).val();
			param = param + "&conditions=" + $(hotelRatings[i]).attr("val");
		}
	}

	// 价格
	var minPrice;
	var maxPrice;
	if($("#minPrice").val()=="" && $("#maxPrice").val() ==""){
	 minPrice = $("input[name='hotelPrice']:checked").attr("min");
	 maxPrice =$("input[name='hotelPrice']:checked").attr("max");
	 if($("#price").attr('class')=="sel"){
		 minPrice="";
		 maxPrice="";
	 } 
	} else {
		minPrice = $("#minPrice").val();
		maxPrice = $("#maxPrice").val();
	}
	
	// 如果minPrice不为空，且maxPrice为空
	if(minPrice != '' && maxPrice == '') {
		param = param + "&minPrice=" + parseInt(minPrice, 10);
		param = param + "&conditions=" + parseInt(minPrice, 10) + "以上";
		$("#hotelPrice").attr("val",parseInt(minPrice, 10) + "以上");
	}
	// 如果minPrice为空或0，且maxPrice不为空
	if((minPrice == '' || parseInt(minPrice, 10) == 0) && maxPrice != '') {
		if(parseInt(minPrice, 10) == 0) {
			param = param + "&minPrice=" + parseInt(minPrice, 10);
		}
		param = param + "&maxPrice=" + parseInt(maxPrice, 10);
		param = param + "&conditions=" + parseInt(maxPrice, 10) + "以下";
		$("#hotelPrice").attr("val",parseInt(maxPrice, 10) + "以下");
	}
	// 如果minPrice大于0，且maxPrice不为空
	if( (minPrice != '' && (parseInt(minPrice, 10) != 0)) && (maxPrice != '')) {
		param = param + "&minPrice=" + parseInt(minPrice, 10) + "&maxPrice=" + parseInt(maxPrice, 10);
		param = param + "&conditions=" + parseInt(minPrice, 10) + "-" + parseInt(maxPrice, 10);
		$("#hotelPrice").attr("val",parseInt(minPrice, 10) + "-" + parseInt(maxPrice, 10));
	}

	// 排序
	var sortItem = $("#sortItem").val();
	if (sortItem != null && typeof (sortItem) != "undefined" & sortItem !== "") {
		param = param + "&sortItem=" + sortItem;
	}
	var sortOrder = $("#sortOrder").val();
	if (sortOrder != null && typeof (sortOrder) != "undefined"
			& sortOrder !== "") {
		param = param + "&sortOrder=" + sortOrder;
	}

	// 分页
	var pageSize = $("#pageSize").val();
	if (pageSize != null && typeof (pageSize) != "undefined" & pageSize !== "") {
		param = param + "&pageSize=" + pageSize;
	}
	var pageNo = $("#pageNo").val();
	if (pageNo != null && typeof (pageNo) != "undefined" & pageNo !== "") {
		param = param + "&pageNo=" + pageNo;
	}

	return param;
}
