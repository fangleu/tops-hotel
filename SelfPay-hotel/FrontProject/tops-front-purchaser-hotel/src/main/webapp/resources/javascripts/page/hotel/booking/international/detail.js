$(function() {
	if ($("#dituContent").length>0) {
		initMap($("#dituContent").attr("longitude"), $("#dituContent").attr("latitude"));// 创建和初始化地图
	}
	initImages();
	initChangeList();
	internationalRoomTypeAndNumDropDown();
});

function internationalRoomTypeAndNumDropDown() {
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
}

//提交订单
function submitOrderForm(hotelId, roomCatId, bookingClassId) {

	$("#orderForm > input[name='hotelId']").val(hotelId);
	$("#orderForm > input[name='roomCatId']").val(roomCatId);
	$("#orderForm > input[name='bookingClassId']").val(bookingClassId);
	$("#orderForm").submit();
	_paq.push(['trackEvent','HotelCreateOrder','HotelInPreDetailCrOrder','detailPage',$("#orderForm").serialize()]);
}

//标签切换
function changeTab(id,obj) {
	$("html, body").animate({scrollTop: $("#"+id).offset().top});
}

function initImages() {
	var image_window = new PopWindow(".more-pic", {
		title:$("#cityName").val() + "|" + $("#hotelName").val(),
		width:770,
		open:function(){
			image_window.wrapper.find(".k-content").css({"background-color":"#ffffff"});
		},
		content :{
			template:$("#imageTemplate").html()
		}
	}).init();

	$('#slideshow').before('<div id="nav_wrapper"><ul id="nav">').cycle({
		fx:     'scrollHorz',
		speed:  'fast',
		timeout: 0,
		prev : "#prev,#up",
		next: "#next,#down",
		pager:  '#nav',
		pagerAnchorBuilder: function(idx, slide) {
			return '<li><a href="#"><img src="' + slide.src + '" width="80" height="60" /></a></li>';
		},
		after:function(){
			$("#scrollText").html(this.alt);
		}
	});
}

function initChangeList(){

	var a_pos = [];
	var flag=true;
	$(".changeList a:gt(0)").each(function(index){
		var href= $(this).attr("href");
		a_pos.push($("a[name=" + href.substring(1) + "]").offset());

	});

	$(".changeList a").click(function(e){
		flag=false;
		e.preventDefault();
		var href= $(this).attr("href");
		$(this).closest("li").siblings().find("a").removeClass('currentLi');
		$(this).addClass("currentLi");
		setTimeout(function(){
			location.href=href;
		},100);
	});

	$(window).bind("scroll",function(e){
		if(flag){
			var that = $(".changeList");
			var currentPos = that.offset();
			var i = 0;
			console.dir(a_pos);
			for(i = a_pos.length - 1; i >=0 ; i--){
				if(currentPos.top >= a_pos[i].top){
					that.find("a").removeClass('currentLi');
					$("a:eq(" + (i+1) + ")" , that).addClass("currentLi");
					return false;
				}
			}
			that.find("a").removeClass('currentLi');
			$("a:first" , that).addClass("currentLi");
		}
		flag=true;
	});

	$(".changeList").fixedBar();
}
