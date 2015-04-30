$(function() {
	initConfirm();
	if ($("#dituContent").length>0) {
		initMap($("#dituContent").attr("longitude"), $("#dituContent").attr("latitude"));// 创建和初始化地图
	}
	resultDiv();
	initImages();
    $(".expand_hide").hide();
    initChangeList();
    
});

//酒店查询结果，展开和收起
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

//提交订单
function submitOrderForm(hotelId, roomCatId, bookingClassId) {
	var obj = $("#"+hotelId+"_"+roomCatId+"_"+bookingClassId);
	var bq=obj.attr("bq");
	var nowDate = new Date();
	
	if(isNotBlank(bq)){
		//获取入住日期
		var checkinDate = $("#orderForm input[name='checkInDate']").val();
		var checkoutDate = $("#orderForm input[name='checkOutDate']").val();
		//获取当前日期
		var nowDateStr =nowDate.format("yyyy-MM-dd");
		//获取当前时间
		var nowTimeStr =nowDate.format("hh");
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
	_paq.push(['trackEvent','HotelCreateOrder','HotelDoPreDetailCrOrder','detailPage',$("#orderForm").serialize()]);
}

//标签切换
function changeTab(id,obj) {
	//页面位置定位
//	$("html, body").scrollTop(0).animate({scrollTop: $("#"+id).offset().top});
	$("html, body").animate({scrollTop: $("#"+id).offset().top});
/*	$("div[id^=tab_]").attr("style","display:none");
	$("#"+id).attr("style","display:block");
	$(".changeList").find("a").removeClass();
	$(obj).attr("class","currentLi");*/
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


