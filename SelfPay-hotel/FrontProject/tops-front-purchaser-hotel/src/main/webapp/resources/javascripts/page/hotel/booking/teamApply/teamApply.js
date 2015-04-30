/**
 * Created with IntelliJ IDEA.
 * User: lyy
 * Date: 13-9-16
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
$(function () {
	initCity();
	checkboxChange();
	
	// 设置文本框只能为数字
	$("input[name='fromPrice']").keyup(function() { // keyup事件处理
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).bind("paste", function() { // CTR+V事件处理
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).css("ime-mode", "disabled"); // CSS设置输入法不可用

	$("input[name='leaveDate']").keyup(function() { // keyup事件处理
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).bind("paste", function() { // CTR+V事件处理
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	}).css("ime-mode", "disabled"); // CSS设置输入法不可用

	//验证
	$('#teamApplyFormSubmit').click(function () {
		var validator = $('#teamApplyForm').kendoValidator().data("kendoValidator");
		delValidator('#fromDate', 'pattern');
		delValidator('#fromPrice', 'pattern');
		setValidator('#cityIsoCode', 'required', true, '城市不能为空');
		setValidator('#fromDate', 'required', true, '入住日期不能为空');
		setValidator('#fromDate', 'pattern', '\\d{4}-\\d{2}-\\d{2}', '入住日期格式错误');
		setValidator('#leaveDate', 'required', true, '离店日期不能为空');
		setValidator('#leaveDate', 'pattern', '\\d{4}-\\d{2}-\\d{2}', '离店日期格式错误');
		setValidator('#roomNumber1', 'pattern', '[6-9]|[1-9]+[0-9]+', '请填写大于5的整数');
		setValidator('#fromPrice', 'pattern', '[0-9]*', '起始价格请填写正整数');
		setValidator('#toPrice', 'pattern', '[0-9]*', '终止价格请填写正整数');
		var fromDate = $('#fromDate').val();
		var leaveDate = $('#leaveDate').val();
		if (Date.parse(fromDate) > Date.parse(leaveDate)) {
			setValidator('#fromDate', 'pattern', '\\d{100}-\\d{100}-\\d{100}', '入住日期不要大于离店日期');
		}
		var fromPrice = $('#fromPrice').val();
		var toPrice = $('#toPrice').val();
		if(parseInt(fromPrice) > parseInt(toPrice)) {
			setValidator('#fromPrice', 'pattern', '[a-z]{1000}', '起始价格不要大于终止价格');
		}
//		var ruleArray = new Array();
//		ruleArray[0] = new ruleBean("price", "起始价格不要大于终止价格", function (input) {
//			if (input.is("[id=fromPrice]") || input.is("[id=toPrice]")) {
//				var from = $('#fromPrice').val();
//				var to = $('#toPrice').val();
//				if (parseInt(from) > parseInt(to)) {
//					return false;
//				}
//			}
//			return true;
//		});
//		ruleArray[1] = new ruleBean("date", "入住日期不要大于离店日期", function (input) {
//			if (input.is("[id=fromDate]") || input.is("[id=leaveDate]")) {
//				var from = $('#fromDate').val();
//				var to = $('#leaveDate').val();
//				if (Date.parse(from) > Date.parse(to)) {
//					return false;
//				}
//			}
//			return true;
//		});
		setValidator('#clientName', 'required', true, '联系人姓名不能为空');
		setValidator("#clientName", "pattern", "([\u4E00-\u9FA5\·\-]{2,14})|([a-zA-Z]{3,20})", '联系人姓名格式不正确');
		setValidator('#clientPhone', 'required', true, '联系人电话不能为空');
		setValidator('#clientPhone', 'pattern', '[0-9]{11}', '联系人电话为11位数字');
//		var validator = customerRule('#teamApplyForm', ruleArray);
		if (validator.validate()) {
//			alert('lyy');
			$('#teamApplyForm').submit();
		}
	});
})

//实时验证
function validateInput(id) {
	var validator = $(id).kendoValidator().data("kendoValidator");
	if (!validator.validate()) {
		return false;
	}
}

//城市控件
function initCity() {
	CityAutocomplete({
		template : "#city_popup",
		input :".city_ac",
		// 以@分割热门非热门，以;分割条目，以|分割三字码拼音和中文
		url : basepath + "/autoComplete/hotelAllCitys",
		group : [ "热门", "ABCDEF", "GHJ", "KLMN", "PQSTW", "XYZ" ],
		autoCompleteUrl : basepath + "/autoComplete/hotelCityCode"
	});
}

//不限和其他的切换
function checkboxChange() {
	change('hotelLevel');
	change('hotelBrand');
}
function change(name) {
	var checks = $("input[name='" + name + "']");
	checks.click(function () {
		if ($(this).val() == 0) {
			//选择不限
			if ($(this).prop('checked')) {
				for (var i = 1; i < checks.length; ++i) {
					checks.eq(i).prop('checked', false);
				}
			} else {
				if (!exist(checks)) {
					$(checks.eq(0).prop('checked', true));
				}
			}
		} else {
			$(checks.eq(0)).prop('checked', false);
			if (exist(checks)) {
				$(checks.eq(0)).prop('checked', false);
			} else {
				$(checks.eq(0)).prop('checked', true);
			}
		}
	})
}
function exist(arr) {
	for (var i = 1; i < arr.length; ++i) {
		if (arr.eq(i).prop('checked')) {
			return true;
		}
	}
	return false;
}

function roomChange(id, name) {
	var text = $(id).val();
	if ($.trim(text) != '') {
		$("input[name='" + name + "']").each(function () {
			$(this).prop('disabled', true);
		})
	} else {
		$("input[name='" + name + "']").each(function () {
			$(this).prop('disabled', false);
		})
	}
}
