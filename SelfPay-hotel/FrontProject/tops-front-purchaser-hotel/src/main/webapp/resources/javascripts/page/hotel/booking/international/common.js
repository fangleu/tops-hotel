/**
 * 国际酒店城市自动补全
 * author：xumeng
 */
$(function(){

	var internationHotCitys = 'HKG_C|Hong Kong Island|香港;MFM_C|Macau|澳门;TPE_C|Taipei|台北;'
		+ 'SIN_C|Singapore|新加坡;SEL_C|Seoul|首尔;BKK_C|Bangkok|曼谷;HKT_A|Phuket|普吉岛;'
		+ 'TYO_C|Tokyo|东京;KUL_C|Kuala Lumpur_C|吉隆坡;CNX_C|Chiang Mai|清迈;BALI_A|Bali|巴厘岛;CJU_C|Jeju|济州岛;'
		+ 'LAN1_A|Langkawi|兰卡威;PATT_C|Pattaya Beach|芭堤雅;OSA_C|Osaka|大阪;KOSA_A|Koh Samui & Islands|苏梅岛;'
		+ 'PARR_A|Paris|巴黎;LON_C|London|伦敦;SIEM_C|Siem Reap|暹粒;SYD_C|Sydney|悉尼;NYC_C|New York|纽约;'
		+ 'KYOT_C|Kyoto|京都;LAX_C|Los Angeles|洛杉矶;@abC';

	CityAutocomplete({
		template : "#city_popup_IntHotel",
		input : "#internationalCity",
		// 以@分割热门非热门，以;分割条目，以|分割三字码拼音和中文
		data : internationHotCitys,
		group : ["热门"],
		dropdownTemplate : '<p>${data.cityNameCN}</p><span style="display:none;">(${data.cityCode})</span>',
		autoCompleteUrl : basepath + "/hotel/booking/international/searchCityByName"
	});

	/*initAutoComplete(
			"#internationalCity",
			basepath + "/hotel/booking/international/searchCityByName",
			"desc",
			function(selection){},
			"#internationalCityCode",
			{},
			function() {

			}
	);*/

	initAutoComplete(
			"#nationalityName",
			basepath + "/hotel/booking/international/searchCountryByName",
			"desc",
			function(selection){},
			"#nationality",
			{},
			null
	);

	// 国际房型，间数联动
	var roomTypeNumMap = {};
	roomTypeNumMap['TS'] = [1, 2, 3, 4, 5];
	roomTypeNumMap['DB'] = [1, 2, 3, 4];
	roomTypeNumMap['TB'] = [1, 2, 3, 4];
	roomTypeNumMap['SB'] = [1, 2, 3, 4, 5];
	roomTypeNumMap['TR'] = [1, 2, 3];
	roomTypeNumMap['Q'] = [1, 2];

	var sltRoomType = $('#internationalRoomType').val();
	var sltRoomNo = $('#internationalRoomNo').val();
	var sltRoomNoInx = 0;
	var interRoomNos = roomTypeNumMap[sltRoomType];
	var roomNoDataSource = [];
	for (var i = 0; i < interRoomNos.length; i++) {
		var roomNoOpt = { text : interRoomNos[i], value : interRoomNos[i]};
		roomNoDataSource.push(roomNoOpt);
		if (sltRoomNo == interRoomNos[i]) {
			sltRoomNoIdx = i;
		}
	}
	$('#internationalRoomNo').kendoDropDownList({
		dataTextField : "text",
		dataValueField : "value",
		dataSource : roomNoDataSource
	});
	var interRoomNoDropDownList = $("#internationalRoomNo").data("kendoDropDownList");
	interRoomNoDropDownList.select(sltRoomNoIdx);

	$('#internationalRoomType').change(function(){
		var interRoomType = $(this).val();
		var interRoomNos = roomTypeNumMap[interRoomType];
		var roomNoDataSource = [];
		for (var i = 0; i < interRoomNos.length; i++) {
			var roomNoOpt = { text : interRoomNos[i], value : interRoomNos[i]};
			roomNoDataSource.push(roomNoOpt);
		}
		$('#internationalRoomNo').kendoDropDownList({
			dataTextField : "text",
			dataValueField : "value",
			dataSource : roomNoDataSource
		});
		var interRoomNoDropDownList = $("#internationalRoomNo").data("kendoDropDownList");
		interRoomNoDropDownList.select(0);
	});
	
	
$("#internationalCity").attr("placeholder","城市名称");

});

function initAutoComplete(inputSelector, dataUrl, field, selectionSetter, keyElementSelector, fillMap, callback) {
	var $input = null;
	if (inputSelector instanceof jQuery) {
		$input = inputSelector;
	} else {
		$input = $(inputSelector);
	}
	return $input.kendoAutoComplete({
		minLength:1,
		dataTextField : field,
//		filter : "contains",
		template : '<p code=\"${data.code}\">${data.' + field + '}</p>',
		dataSource : {
			serverFiltering : true,
			serverPaging : true,
			pageSize : 10,
			transport : {
				read : {
					dataType : "jsonp",
					url : dataUrl
				}
			}
		},
		height : 370,
		delay: 400,
		select : function(t) {
			t.preventDefault();
			t.sender.value(t.item[0].children[0].innerHTML);
			var cid = $(t.item[0].children[0]).attr('code');
			var index = 0;
			var arr = this.ul.find('[code]');
			for ( ; index < arr.size(); index++) {
				if (cid == $(arr.get(index)).attr('code')) {
					break;
				}
			}
			if (index >= arr.size()) {
				return;
			}
			var selection = this.dataItem(index);
			if (typeof(selectionSetter) == "function") {
				selectionSetter(selection);
			}
			if (typeof(keyElementSelector) != "undefined" && keyElementSelector != null) {
				$(keyElementSelector).val(selection.code);
			}
			for ( var ppt in fillMap) {
				var returnVal;
				if(typeof(selection[fillMap[ppt]])=="object"){
					returnVal=JSON.stringify(selection[fillMap[ppt]]);
				}else{
					returnVal=selection[fillMap[ppt]];
				}
				if (ppt instanceof jQuery) {
					ppt.val(returnVal);
				} else {
					$(ppt).val(returnVal);
				}
			}
			if (callback) {
				callback(selection);
			}
		}
	}).data("kendoAutoComplete");
}
