$(function() {
	initConfirm();
	initBaiduMap();
	resultDiv();
	initImages();
    initChangeList();
});

function initConfirm(){
	w = $("#confirmWindow").kendoWindow({
		title:"提示",
		width:500
		
	}).data("kendoWindow");
	$("#confirmWindow .updateDate").click(function(){
		$("#checkinDate_selfpay").focus();
		w.close();
	});
	$("#confirmWindow .close").click(function(){
		w.close();
	});
}

function initBaiduMap(){
	if ($("#dituContent").length>0) {
		initMap($("#dituContent").attr("longitude"), $("#dituContent").attr("latitude"));// 创建和初始化地图
	}
}

//酒店查询结果，展开和收起
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
				});
			});
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
		}

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
//			console.dir(a_pos);
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

//提交订单
function submitOrderForm(hotelId, roomId, roomTypeId, ratePlanId) {
	$("#orderForm > input[name='hotelId']").val(hotelId);
	$("#orderForm > input[name='roomId']").val(roomId);
	$("#orderForm > input[name='roomCatId']").val(roomTypeId);
	$("#orderForm > input[name='bookingClassId']").val(ratePlanId);
	$("#orderForm").submit();
	_paq.push(['trackEvent','HotelCreateOrder','HotelInSelfDetailCrOrder','detailPage',$("#orderForm").serialize()]);
}

//标签切换
function changeTab(id,obj) {
	//页面位置定位
	$("html, body").scrollTop(0).animate({scrollTop: $("#"+id).offset().top});
	$("html, body").animate({scrollTop: $("#"+id).offset().top});
	$("div[id^=tab_]").attr("style","display:none");
	$("#"+id).attr("style","display:block");
	$(".changeList").find("a").removeClass();
	$(obj).attr("class","currentLi");
}


