var lvGrid = null;
var formId = "bookingRoomSearchForm";

$(function() {
	/**
	 * 页面基础属性
	 */
	initPageProperty();
	/**
	 * 初始化导出报表
	 */
	initExportExcel('#'+formId, '#exportExcelButton', basepath + "/order/report/hotel/exportExcel");
})
function initPageProperty() {
	/**
	 * 时间控件的操作修改
	 */
	$(".datepicker[dateStartNoStart]").datepicker("option", "onSelect", function(dateText, inst) {
		var date = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay);
		$(this).parent().find(".datepicker[dateStartNoEnd]").datepicker("option", "minDate", date);
	});
	$(".datepicker[dateStartNoEnd]").datepicker("option", "onSelect", function(dateText, inst) {
		var date = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay);
		$(this).parent().find(".datepicker[dateStartNoStart]").datepicker("option", "maxDate", date);
	});
	var coll_ele = new Collpase({
		limitHeight : 170, // 合拢后的高度
		duration : 0,
		container : '.us-query', // 需要定高的dom
		trigger : '.collapse-withtext', // 下拉按钮样式
		triggerexp : 'active'
	}).init();
	coll_ele.resetHeight();
	$("#searchButton").click(function() {
		doSearch();
	})
}

/**
 * 异步查询后设置各种状态单的数量
 * 
 * @param d
 */
function setAggNum(d) {
	if(typeof(d._pristine.countMap) == "undefined"){
		
	}else{
		$("#normalCount").html(typeof(d._pristine.countMap.normalCount) == "undefined"?"0":d._pristine.countMap.normalCount);
		$("#endorseCount").html(typeof(d._pristine.countMap.endorseCount) == "undefined"?"0":d._pristine.countMap.endorseCount);
		$("#refundCount").html(typeof(d._pristine.countMap.refundCount) == "undefined"?"0":d._pristine.countMap.refundCount);
	}
}

/**
 * 查询提交
 */
function doSearch() {
	$(".us-noresult").hide();
	initKendoTable("#searchResultTab", getFormJson('#'+formId));
}
/**
 * KENDO GRID的初始化
 * 
 * @param selector
 * @param data
 */
function initKendoTable(selector, data) {
	var td_ColumnItems = [ {
		field : "id",
		title : "订单号",
		width : 152
	}, {
		field : "hotelName",
		title : "洒店名称",
		width : 85
	}, {
		field : "cityName",
		title : "城市",
		width : 38
	}, {
		field : "roomNo",
		title : "房间数",
		width : 52
	}, {
		field : "roomNightNo",
		title : "间夜数",
		width : 52
	}, {
		field : "createDate",
		title : "下单时间",
		template: "#= kendo.toString(new Date(createDate), 'yyyy-MM-dd') #",
		width : 79
	}, {
		field : "totalRoomFeeYuan",
		title : "房费",
		width : 57
	}, {
		field : "totalAddServiceFeeYuan",
		title : "增值服务",
		width : 66
	}, {
		field : "totalOrderFeeYuan",
		title : "应收合计",
		width : 66
	}, {
		field : "pagePaymentType",
		title : "支付方式",
		width : 66
	}, {
		field : "creatorName",
		title : "操作员",
		width : 53
	} ];
	var data = getFormJson('#' + formId);
	lvGrid = initTable(selector, basepath + "/order/report/hotel/seachDetail", td_ColumnItems, null, data);
}

/**
 * 表单数据JSON转化
 * 
 * @param formSelector
 * @returns {___anonymous2763_2764}
 */
function getFormJson(formSelector) {
	var arr = $(formSelector).serializeArray();
	var result = {};
	for ( var i = 0; i < arr.length; i++) {
		result[arr[i].name] = arr[i].value;
	}
	return result;
}
/**
 * GRID属性初始化
 * 
 * @param selector
 * @param action
 * @param columnsTemplate
 * @param moreSettings
 * @param data
 * @returns
 */
function initTable(selector, action, columnsTemplate, moreSettings, data) {
	$(selector).html('');
	var settings = {
		dataSource : {
			transport : {
				read : {
					url : action,
					dataType : "json",
					data : data,
					method : "POST"
				}
			},
			schema : {
				data : "pageBean.data",
				total : "pageBean.totalCount"
			},
			serverPaging : true,
			serverSorting : true
		},
		pageable : {
			pageSize : 10,
			buttonCount : 6,
			input : true,
			messages : {
				display : " 共 <b>{2}</b> 条记录，<b>{0}</b> - <b>{1}</b> 条",
				empty : "共 <b>0</b> 条记录",
				page : "页",
				of : "共 <b>{0}</b> 页",
				itemsPerPage : "每页",
				first : "第一页",
				previous : "前一页",
				next : "后一页",
				last : "最后页",
				refresh : "刷新"
			}
		},
		dataBound : function(e) {
			setAggNum(e.sender.dataSource);
			$(".us-result").show();
		},
		columns : columnsTemplate
	};
	if (moreSettings) {
		$.extend(settings, moreSettings);
	}
	return $(selector).kendoGrid(settings).data("kendoGrid");
}
/**
 * 导出初始化
 * 
 * @param formSelector
 * @param buttonSelector
 * @param url
 */
function initExportExcel(formSelector, buttonSelector, url) {
	$('body').delegate(buttonSelector, "click", function() {
		$(formSelector).attr("method", "post");
		var oldUrl = $(formSelector).attr("action");
		$(formSelector).attr("action", url);
		$(formSelector).submit();
		$(formSelector).attr("action", oldUrl);
	});
}