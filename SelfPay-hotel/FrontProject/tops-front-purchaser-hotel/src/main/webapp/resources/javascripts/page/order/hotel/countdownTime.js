 //功能点：计算订单详情页面上的未支付订单的时间提醒

//计算倒计时，传入参数为要显示倒计时的元素（id或者class），以及剩余秒数
function iCounter(v_cal_id,rest_seconds) {
    var totalSeconds = removeDecimal(rest_seconds);
    timeGo(v_cal_id,totalSeconds);
}

function timeGo(v_cal_id,totalSeconds) {
    setInterval(function() {
        totalSeconds--;
        calculateTime(v_cal_id,totalSeconds);
    }, 1000);
}

function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
        return i;
    } else {
        return i
    }
}

function calculateTime(v_cal_id,totalSeconds) {
    if (totalSeconds > 0) {
        var leftDays = removeDecimal(totalSeconds / 60 / 60 / 24);//剩余天数
        var seconds = totalSeconds % (60 * 60 * 24);
        var leftHours = removeDecimal(seconds / 60 / 60);//剩余小时
        var leftMinutes = removeDecimal(seconds / 60 % 60);//剩余分
        var leftSeconds = removeDecimal(seconds % 60);//剩余秒
        leftDays = checkTime(leftDays);
        leftMinutes = checkTime(leftMinutes);
        leftSeconds = checkTime(leftSeconds);
        setLeftTime(v_cal_id,leftDays, leftHours, leftMinutes, leftSeconds);
    } else {
    	//秒数已经到0的情况
//    	$(v_cal_id+" .countdownContainer").remove();
    	$("#ac .countdownContainer").remove()
    }
}
function setLeftTime(v_cal_id,leftDays, leftHours, leftMinutes, leftSeconds) {
//var  html_str='请在'+ leftMinutes +'分钟'+leftSeconds+'秒内完成支付';
	var html_str="<div class=\"countdownContainer\"><i class=\"icon-clock\"></i>您还有 <span>"+leftMinutes+":"+leftSeconds+"</span> 完成支付</div>";
	$("#ac .countdownContainer").remove()
    $(v_cal_id).before(html_str);
}
//去除小数
function removeDecimal(number) {
    return Math.floor(number);
}

