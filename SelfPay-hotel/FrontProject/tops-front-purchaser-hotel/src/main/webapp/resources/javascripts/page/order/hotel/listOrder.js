$(function(){
	kendo.culture("zh-CHS");
	CityAutocomplete({
		template : "#city_popup",
		input : "#city",
		// 以@分割热门非热门，以;分割条目，以|分割三字码拼音和中文
		url : basepath + "/autoComplete/hotelAllCitys",
		group : [ "热门", "ABCDEF", "GHJ", "KLMN", "PQSTW", "XYZ" ],
		autoCompleteUrl : basepath + "/autoComplete/hotelCityCode"
	});
});

function submitForm(pageIndex){
	if(typeof(pageIndex) != "undefined" && pageIndex != ""){
		$("#page").val(pageIndex);
	}
	$('#orderForm').submit();
}