var map
$(function() {
	initConfirm();
	kendo.culture("zh-CHS");
	
	CityAutocomplete({
		template : "#city_popup",
		input : "#city",
		// 以@分割热门非热门，以;分割条目，以|分割三字码拼音和中文
		url : basepath + "/autoComplete/hotelAllCitys",
		group : [ "热门", "ABCDEF", "GHJ", "KLMN", "PQSTW", "XYZ" ],
		autoCompleteUrl : basepath + "/autoComplete/hotelCityCode"
	});

	$("#city").blur(function() {
		linkageDistrict({
			sourceTrigger : "#cityCode",
			targetTrigger : "#location",
			url : basepath + "/autoComplete/linkage",
			group : [ "ABC", "DEF", "GHIJ", "KLMN", "OPQR", "STUW", "XYZ" ]
		}).init();
	});

	submitAjax();
	setTimeout(searchTip, 400);
})

function searchTip() {
	$.loadingbar({
		showClose: true,
		text:'正在为您查询，请稍候…',
		urls: [new RegExp("^" + basepath + "/hotel/booking/search/filterSearch")]
	});
}

function submitAjax(paqType) {
	
	var checkinDate = $("#checkinDate").val();
	var checkoutDate = $("#checkoutDate").val();
	var cityCode = $("#cityCode").val();
	var cityName = $("#city").val();
	var hotelName = $("#hotelName").val();
	var roomNo = $("#roomNo").val();
	
	var hotelRating=$("#hotelRating").val();
	$.post(basepath + '/hotel/booking/search/getFilterDiv',
		{
			checkInDate : checkinDate,
			checkOutDate : checkoutDate,
			cityIsoCode : cityCode,
			cityName : cityName,
			hotelName : hotelName,
			roomNo : roomNo,
			hotelRatings:hotelRating
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
			
			// 加事件
			$("#filterPanel input[type!=text]").click(function() {
				var paqType = 'selectFacility';
				if ($(this).is(":checked")) {
					// 给minPrice和maxPrice赋值
					if ($(this).attr("name") == "hotelPrice") {
						$("input[name=minPrice]").val($(this).attr("min"));
						$("input[name=maxPrice]").val($(this).attr("max"));
						paqType = 'selectPrice';
					}
					// 处理复选框不限条件
					if ($(this).attr("id") == "noLimitedhotelRating") {
						$("input[name='hotelRatings']").each(function() {
							$(this).attr("checked",false);
						});
						paqType = 'selectRating';
					} else if ($(this).attr("name") == "hotelRatings") {
						$("#noLimitedhotelRating").attr("checked",false);
						paqType = 'selectRating';
					}
					
					//位置
					if($(this).attr("id") == "noLimiteddistrictCodes") {
						$("input[name='districtCodes']").each(function() {
							$(this).attr("checked", false);
						});
						paqType = 'selectDistrict';
					} else if($(this).attr("name") == "districtCodes") {
						$("#noLimiteddistrictCodes").attr("checked", false);
						exchangeRadio(this);
						$("#more_district").hide();
						paqType = 'selectDistrict';
					}
					
					//品牌
					if ($(this).attr("id") == "noLimitedChainBrand") {
						$("input[name='chainBrandIds']").each(function() {
							$(this).attr("checked",false);
						});
						paqType = 'selectBrand';
					} else if ($(this).attr("name") == "chainBrandIds") {
						$("#noLimitedChainBrand").attr("checked",false);
						paqType = 'selectBrand';
					}

				}
				filterFun(paqType);
			});
			
			$("#more_district,#more_brand,#more_facility").hover(function() {
				$(this).show();
			}, function() {
				$(this).hide();
			})
			
			// 自定义价格输入
			var validator = $("#PriceByHand").kendoValidator({
				errorTemplate: '<span class="k-widget k-tooltip k-tooltip-validation">\
                    <span class="message_ct">\
                        <span class="arrow"></span>\
                        <span class="message_text">#=message#</span>\
                    </span>\
                </span>'
			}).data("kendoValidator");
			
			$("#setHotelPrice").click(function() {
				// 验证输入值
				if( parseInt($("#minPrice").val(), 10) > parseInt($("#maxPrice").val(), 10) ) {
					setValidator("#maxPrice", "pattern", "[0-9]{1000}", "最小值不能大于最大值");
				} else {
					delValidator("#maxPrice", "pattern", "[0-9]{1000}", "最小值不能大于最大值");
				}
				
				if(validator.validate()) {
					filterFun('selectPrice');
				}
				
				$("input[name=hotelPrice]").each(function() {
					$(this).attr("checked", false);
				})
			})
			
		}
	)
};

//置换选中的位置区
function exchangeRadio(obj) {
	if($("#more_district").css("display")!="none"){
		var that = $("input[name='districtCodes']").eq(3);
		var name = $(obj).attr("val");
		var value = $(obj).val();	
		$(obj).attr("val", that.attr("val")).val(that.val()).siblings().html(that.attr("val"));	
		that.attr("val", name).val(value).siblings().html(name);
		that.prop("checked", true);
	}
}

// 打开弹出层
function openMore(obj) {
	$(obj).css({
		left : $(obj).parent().position().left,
		top : $(obj).parent().position().top - 29
	}).show();
}

// 关闭弹出层
function closeMore(obj) {
	$(obj).hide();
}

// 删除某个筛选的条件
function delFilterItem(itemName) {
	//处理价格区间
	if($("#hotelPrice").attr("val") == itemName) {
		$("input[name=minPrice]").val("");
		$("input[name=maxPrice]").val("");
	}
	var objs = $("#filterPanel input[type='checkbox'],#filterPanel input[type='radio']");
	for ( var i = 0; i < objs.length; i++) {
		var obj = objs[i];
		if($(obj).attr("val") == itemName) {
			$(obj).prop("checked", false);
		}
	}
	filterFun('delFilter:'+itemName);
}

// 清空所有的筛选条件
function delModelAllItems() {
	submitAjax('clearAllSelects');
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
	var filterParam = basepath + '/hotel/booking/search/filterSearch';
	var param = getSearchParam();// $("#searchForm").serialize();
	$.post(filterParam, param, function(sortResultPageDiv) {
		var srpObj = $("#sortResultPage");
		if (srpObj.length > 0) {	
			$("#sortResultPage").replaceWith(sortResultPageDiv);
		} else {
			$("#main_body").append(sortResultPageDiv);
		}
		$('#fruitlessDiv').hide();
		resultDiv();
		// 初始化排序菜单分页
		initToolsBarPage();
		$(".backToTop").click();//查询后自动返回顶部

		if (isNotBlank(paqType)) {
			_paq.push(['trackSiteSearch',param,'HotelDoPreSearch:'+paqType,$('.hotel_resultNo').html()]);
		}
	}).error(function () {
		$('#fruitlessDiv').hide();
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
function submitOrderForm(hotelId, roomCatId, bookingClassId) {
	var obj = $("#"+hotelId+"_"+roomCatId+"_"+bookingClassId);
	var bq=obj.attr("bq");
	var nowDate = new Date();
	
	if(isNotBlank(bq)){
		//获取入住日期
		var checkinDate = $("input[name='checkInDate']").val();
		var checkoutDate = $("input[name='checkOutDate']").val();
		//获取当前日期
		var nowDateStr =nowDate.format("yyyy-MM-dd");
		//获取当前时间
		var nowTimeStr =nowDate.format("HH");
		var diff = calNightNo(nowDateStr,checkinDate);
		var diff2 = calNightNo(checkinDate,checkoutDate);
		var ad = obj.attr("ad");
		var at = obj.attr("at");
		var cd = obj.attr("cd");
		switch(bq) {
		case "ADVANCE":
			//不在提前期限内
			if(isNotBlank(ad)&&diff<parseInt(ad)||diff==parseInt(ad)&&isNotBlank(at)&&parseInt(nowTimeStr)>=parseInt(at.split(":")[0])){
				w.center().open();
				return;
			}
			break;
		case "SUCCESSIVE_LIVE":
			if(isNotBlank(cd)&&diff2<parseInt(cd)){
				w.center().open();
				return;
			}
			break;
		case "ADVANCE_AND_SUCCESSIVE_LIVE":
			if(isNotBlank(ad)&&diff<parseInt(ad)||diff==parseInt(ad)&&isNotBlank(at)&&parseInt(nowTimeStr)>=parseInt(at.split(":")[0])||isNotBlank(cd)&&diff2<parseInt(cd)){
				w.center().open();
				return;
			}
			break;
		default:
		}
	}
	
	$("#orderForm > input[name='hotelId']").val(hotelId);
	$("#orderForm > input[name='roomCatId']").val(roomCatId);
	$("#orderForm > input[name='bookingClassId']").val(bookingClassId);
	$("#orderForm").submit();
	_paq.push(['trackEvent','HotelCreateOrder','HotelDoPreListCrOrder','searchPage',$("#orderForm").serialize()]);
}

// 查看酒店详情
function submitDetailForm(hotelId) {
	$("#detailForm > input[name='hotelId']").val(hotelId);
	$("#detailForm").submit();
}

// 获取搜索的参数值
function getSearchParam() {
	var param = "1=1"
	// 客户租
	var customerKey = $("#customerKey").val();
	if (customerKey != null && typeof (customerKey) != "undefined"
			& customerKey !== "") {
		param = param + "&customerKey=" + customerKey;
	}

	// 城市
	var cityCode = $("#cityCode").val();
	if (cityCode != null && typeof (cityCode) != "undefined" & cityCode !== "") {
		param = param + "&cityIsoCode=" + cityCode;
	}
	var cityName = $("#city").val();
	if (cityName != null && typeof (cityName) != "undefined" & cityName !== "") {
		param = param + "&cityName=" + cityName;
	}

	// 入住时间
	var checkInDate = $("#checkinDate").val();
	if (checkInDate != null && typeof (checkInDate) != "undefined"
			& checkInDate !== "") {
		param = param + "&checkInDate=" + checkInDate;
	}

	// 离店时间
	var checkOutDate = $("#checkoutDate").val();
	if (checkOutDate != null && typeof (checkOutDate) != "undefined"
			& checkOutDate !== "") {
		param = param + "&checkOutDate=" + checkOutDate;
	}

	// 酒店名称
	var hotelName = $("#hotelName").val();
	if(hotelName == $("#hotelName").attr("placeholder")) {
		hotelName ="";
	}
	if (hotelName != null && typeof (hotelName) != "undefined"
			& hotelName !== "") {
		param = param + "&hotelName=" + hotelName;
	}

	// 位置—行政区
	var location_district = $("#location_district").val();
	if (location_district != null && typeof (location_district) != "undefined"
			& location_district !== "") {
		param = param + "&districtCodes=" + location_district;
	}

	// 位置—商圈
	var locatioin_commercial = $("#locatioin_commercial").val();
	if (locatioin_commercial != null
			&& typeof (locatioin_commercial) != "undefined"
			& locatioin_commercial !== "") {
		param = param + "&commercialCodes=" + locatioin_commercial;
	}

	// 星级
	var hotelRatings = $("input[name=hotelRatings]");
	for ( var i = 0; i < hotelRatings.length; i++) {
		if ($(hotelRatings[i]).is(":checked")) {
			param = param + "&hotelRatings=" + $(hotelRatings[i]).val();
			param = param + "&conditions=" + $(hotelRatings[i]).attr("val");
		}

	}
	
	// 类型
	var hotelCategoryIds = $("input[name=hotelCategoryIds]");
	for ( var i = 0; i < hotelCategoryIds.length; i++) {
		if ($(hotelCategoryIds[i]).is(":checked")) {
			param = param + "&hotelCategoryIds=" + $(hotelCategoryIds[i]).val();
		}

	}

	// 行政区
	var districtCodes = $("input[name=districtCodes]");
	for ( var i = 0; i < districtCodes.length; i++) {
		if ($(districtCodes[i]).is(":checked")) {
			param = param + "&districtCodes=" + $(districtCodes[i]).val();
			param = param + "&conditions=" + $(districtCodes[i]).attr("val");
		}

	}

	// 品牌
	var chainBrandIds = $("input[name=chainBrandIds]");
	for ( var i = 0; i < chainBrandIds.length; i++) {
		if ($(chainBrandIds[i]).is(":checked")) {
			param = param + "&chainBrandIds=" + $(chainBrandIds[i]).val();
			param = param + "&conditions=" + $(chainBrandIds[i]).attr("val");
		}

	}
	
	//设施
	var facilitys = $("input[name='facilitys']");
	for(var i=0; i<facilitys.length; i++) {
		if($(facilitys[i]).is(":checked")) {
			param = param + "&facilitys= "+ $(facilitys[i]).val();
			param = param + "&conditions=" + $(facilitys[i]).attr("val");
		}
	}

	// 价格
	var minPrice = $("#minPrice").val();
	var maxPrice = $("#maxPrice").val();
	
	
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
	
	// 房间数
	var roomNo = $("#roomNo").val();
	if(roomNo != null && typeof(roomNo) != "undefined" && roomNo != "") {
		param = param + "&roomNo=" + roomNo;
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
	$(".expand_hide").hide();
	
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
				});
			});
			_paq.push(['trackEvent','HotelButton','HotelRoomExpanded','HotelDoPre']);
		} else {
			$(".roomTypeDetailContainer", container).each(function() {
				var self = this;
				$(this).css({
					"overflow" : "hidden"
				}).animate({
					height : "45px"
				}, function() {
					$(".expand_hide", self).show();
				});
			});
			_paq.push(['trackEvent','HotelButton','HotelRoomFold','HotelDoPre']);
		}

	});
	
	$(".stipulate").kendoTooltip({
		position:"bottom", //left right top
		content:kendo.template($("#ruleTemplate").html()),
		offsetY:10,
		width:480
		});
	
	$(".hotelRoomPrice").each(function() {
		$(this).hover(function() {
			// alert(($(this).position());
			$("#" + $(this).attr("rateid")).css({
				left : ($(this).position().left - 380),
				top : ($(this).position().top + $(this).height())
			}).show();
		}, function() {
			$("#" + $(this).attr("rateid")).hide();
		})
	})
}

function initConfirm(){
	w = $("#confirmWindow").kendoWindow({
		title:"提示",
		width:500
		
	}).data("kendoWindow");
	$("#confirmWindow .updateDate").click(function(){
		$("#checkinDate").focus();
		w.close();
	});
	$("#confirmWindow .close").click(function(){
		w.close();
	});
}

//地图弹层
$(function(){
	FloatLayer({
		trigger:".hotel-map i",
		template:"#mapTemplate",
		toggle:false,
		open:function(){
			initMap(this.input.attr("longitude"), this.input.attr("latitude"));// 创建和初始化地图
		}
	})
})



