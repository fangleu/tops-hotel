$(function(){
	$("body").click(function(e){
	    $("#child_order").hide();
	});
	$(".associatedOrderContainer").mouseenter(function(e){
	    var pos = $(this).offset();
	    $("#child_order").css({left:pos.left-305,top:pos.top-13}).toggle();
	    $(this).toggleClass("expand")
	//            e.stopPropagation();
	});
	$("#child_order").click(function(e){
	    e.stopPropagation();
	});
	$(".btn_checkOriginalPriceDetail").click(function(){
	    $(".originalPriceDetail").slideToggle();
	    var t=$(this).text();
	    if(/查看/.test(t))
	    {
	        $(this).text(t.replace("查看","收起"));
	    }
	    else
	    {
	        $(this).text(t.replace("收起","查看"));
	    }
	})
	$(".btn_checkOriginOrderInfo").click(function(){
	    $(".originalOrderInfo").slideToggle();
	    var t=$(this).text();
	    if(/查看/.test(t))
	    {
	        $(this).text(t.replace("查看","收起"));
	    }
	    else
	    {
	        $(this).text(t.replace("收起","查看"));
	     }
	  })
	  
	//日志查看
    $(".orderFlexibleType").click(function(){
        $(".traceList .flexiBleContent").slideToggle();
        if($(this).hasClass("down"))
        {
            $(this).removeClass("down");
            $(this).text("查看全部");
        }
        else
        {
            $(this).addClass("down");
            $(this).text("收起更多");
        }
    });
	
	//关联订单
    $("#child_order_trigger").click(function(e){
        var pos = $(this).offset();
        $("#child_order").css({left:pos.left,top:pos.top+30}).toggle();
        e.stopPropagation();
        if($("#endorseSize").text() !='(0)'){
        	$(".relevanceOr").toggleClass("relevanceOr_click");
        }
    });
    $(window).click(function(){
        $(".relevanceOr").removeClass("relevanceOr_click");
    })

    //价格日历
    $("#priceDetail").hover(function(){
    	$("#priceCale").css({
    		left: ($(this).position().left ),
    		top: ($(this).position().top + $(this).height() + 5 )
    	}).show();
    },	function(){
    		$("#priceCale").hide();
    });
    $("#origpriceDetail").hover(function(){
    	$("#origpriceCale").css({
    		left: ($(this).position().left ),
    		top: ($(this).position().top + $(this).height() + 5 )
    	}).show();
    },	function(){
    	$("#origpriceCale").hide();
    });

    //地图
	if ($("#dituContent").length>0) {
		initMap($("#dituContent").attr("city"), $("#dituContent").attr("address"));// 创建和初始化地图
	}
    
    //地图展示 hotelName 用于预付，mapLocationIcon用于现付
    $("#hotelName,.mapLocationIcon").hover(function(){
    	$("#map").css({
    		left: ($(this).position().left ),
    		top: ($(this).position().top + $(this).height() )
    	}).show();
    }, function(){
        $("#map").hover("", function(){
        	$("#map").hide();
        });
    });
	
	//底部悬浮特殊处理
	$(".main-body").after($("#footFix").html());
	$(".main-body #footFix").remove();
	$('#ac').fixedBar({
	    inverse:true
	});
});

function toggle(divid){
	var v = $("[tid='" + divid +"']").is(":visible")
	if (v) {
		$("[tid='" + divid +"']").hide();
	} else {
		$("[tid='" + divid +"']").show();
	}
}
function hotelDetail(hotelId) {
	$("input[name='hotelId']").val(hotelId);
	var date = new Date();
	if(date.getHours()>=18){
		date = new Date(date.getTime()+ 1000*60*60*24);
	} 
	$("input[name='checkInDate']").val(date.format("yyyy-MM-dd"));
		
	$("input[name='checkOutDate']").val(date.getNextDate());
	$("#detailForm").submit();
}