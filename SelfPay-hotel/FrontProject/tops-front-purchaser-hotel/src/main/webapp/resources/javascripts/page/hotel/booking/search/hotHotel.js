$(function(){
	$.ajax({
		url : (purchaserLevel=='LEVEL1'?purchaserServer:basepath) + "/purchaserOp/getOpInfo",
		type : 'POST',
		dataType : "jsonp",
		data : {
			adLocation : "RECOMMEND_HOTEL"
		},
		success : function(result) {
			var data=result;
			for ( var key in data) {
				$("#hotCityBanner").find("ul.hotelPlace").append(getTemplateAsHtml("#cityNameTemplate", {
					"index" : key,
					"cityName" : data[key]["cityName"]
				}));
			}

			for ( var key in data) {
				addHotel(data[key]);
			}
		},
		error : function(xhr, status, thrown) {
			// alertWindow(xhr.responseText, "error");
			$("#hotHotelContent").text("获取热门酒店出错");
		}
	});
})

/**
 * may be kendo template is more suitable
 * @param hotel
 * @param shown
 */
function addHotel(hotel){
	$("#hotHotelContent").append(getTemplateAsHtml("#hotelImageTemplate", hotel));
}

function getStarClass(hotel){
	if(hotel["hotelStar"] == '6'){
		return "star02_5";
	}
	if(hotel["hotelStar"] == '7'){
		return "star03_5";
	}
	if(hotel["hotelStar"] == '8'){
		return "star04_5";
	}
	return "star0" + hotel["hotelStar"];
}

