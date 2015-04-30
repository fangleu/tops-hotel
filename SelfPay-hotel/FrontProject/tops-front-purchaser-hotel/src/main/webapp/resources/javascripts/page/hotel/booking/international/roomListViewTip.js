/**
 * 国际酒店搜索页和详情页中的房型展示部分所需要的tip控件
 * author:	xumeng
 * create：	2014-04-01
 */

$(function(){
	initRoomListViewTip();
});

function initRoomListViewTip(){
	
	$(".roomTypeDetailContainer .importantFont").each(function(){
		FloatLayer({
			trigger:$(this),
			template:$(this).next(),
			offsetY:30,
			offsetX:-140,
		});
	});
	
	$(".roomTypeDetailContainer .assistTips").kendoTooltip({
		position:"bottom", //left right top
		content:function(e){
			return $("#assistTipsTemplate").html();
		},
		callout:false,
		offsetY:8,
		width:296
	});
	
	$(".roomTypeDetailContainer .stipulateFont").each(function(){
		FloatLayer({
			trigger:$(this),
			template:$(this).next(),
			offsetY:30,
			offsetX:-205
		});
	});
	
	$(".hotelRoomPrice").each(function() {
		$(this).hover(function() {
			$("#" + $(this).attr("rateid")).css({
				left : ($(this).position().left - 380),
				top : ($(this).position().top + $(this).height())
			}).show();
		}, function() {
			$("#" + $(this).attr("rateid")).hide();
		})
	})
}