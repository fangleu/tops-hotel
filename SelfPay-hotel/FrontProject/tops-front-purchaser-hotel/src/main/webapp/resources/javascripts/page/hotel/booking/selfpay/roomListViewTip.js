/**
 *现付酒店搜索页和详情页中的房型展示部分所需要的tip控件
 */

$(function(){
	initRoomListViewTip();
});

function initRoomListViewTip(){

	$("body").delegate(".regulations","mouseenter mouseleave",function(event){
		var rateId = $(this).attr("bkid");
		if(event.type == "mouseenter"){
			$("#"+rateId).css({left:$(this).position().right+$(this).outerWidth(),top:$(this).position().top}).show();
		}else{
			$("#"+rateId).hide();
		}
	})
	
	$("body").delegate(".hotelRoomPrice","mouseenter mouseleave",function(event){
		var rateId = $(this).attr("bki");
    		if(event.type == "mouseenter"){
    			$("#rate_"+rateId).show();
    		}else{
    			$("#rate_"+rateId).hide();
    	}
	})
   
	$("body").delegate(".hotel_ensure","mouseenter mouseleave",function(event){
		var rateId = $(this).attr("bki");
    		if(event.type == "mouseenter"){
    			$("#ruleDesc_"+rateId).show();
    		}else{
    			$("#ruleDesc_"+rateId).hide();
    	}
	})
	
	$("body").delegate(".warmTipsLayer","mouseenter mouseleave",function(event){
		var rateId = $(this).attr("bki");
    		if(event.type == "mouseenter"){
    			$("#gitf_"+rateId).show();
    		}else{
    			$("#gitf_"+rateId).hide();
    	}
	})
	
}