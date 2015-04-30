var validator1;
var change_date;

//修改入住时间以及间数的form提交
function  submitChangeDateForm(){
 	$('#changeDateForm').submit();
}

function validateDate(){
	//日期天数处理
	var checkinDateStr=$("#VcheckinDate").val();
	var checkoutDateStr=$('#VcheckoutDate').val();
	
	var checkinDate = new Date();
	checkinDate.setFullYear(checkinDateStr.split("-")[0]);
	checkinDate.setMonth(checkinDateStr.split("-")[1]);
	checkinDate.setDate(checkinDateStr.split("-")[2]);
	var checkoutDate = new Date();
	checkoutDate.setFullYear(checkoutDateStr.split("-")[0]);
	checkoutDate.setMonth(checkoutDateStr.split("-")[1]);
	checkoutDate.setDate(checkoutDateStr.split("-")[2]);
	
	var night = parseInt((checkoutDate.getTime() - checkinDate.getTime()) / (1000 * 60 * 60 * 24)); //工作天数

	if(night>30) {
		setValidator("#VcheckoutDate", "pattern", "\\d{100}-\\d{100}-\\d{100}", "入住>=30天，请联系客服400-720-6666","bottom", "{top:0}");
	}else{
		delValidator("#VcheckoutDate", "pattern");
	}
}
//弹层的入住日期和离店日期的时间修改初始化
function  initDate(){
	var date = new Date();
	$("#VcheckinDate").datepicker({
	    css : {"z-index":20000},
	    numberOfMonths:[1,2],
	    minDate :date,
	    showButtonPanel :true,
	    onSelect:function(dateText,inst){
	        var date = new Date(inst.selectedYear,inst.selectedMonth,inst.selectedDay);
	        $("#VcheckoutDate").datepicker( "option", "minDate", date.getNextDate());
	        $("#VcheckoutDate").datepicker( "setDate", dateText);
	    }
	});

	$("#VcheckoutDate").datepicker({
		css : {"z-index":20000},
	    numberOfMonths:[1,2],
	    minDate :date,
	    showButtonPanel :true,
	    onSelect:function(dateText,inst){
	    	validateDate();
	    }
	});
}


$(function(){
	kendo.init('body');
	lensf();

     change_date = new PopWindow("#change_date",{
        title:"修改入住日期",
        width:425,
        template:"#edit-booking-date-template",
        open:function(){
        	validator1 = $(".change_date").kendoValidator().data("kendoValidator");
        	initDate();

        }
    }).init();

	var validator = $('#createGTANormalOrder').kendoValidator().data("kendoValidator");

	setValidator(".passenger input[type=text]","required",true,"姓名不能为空，单人入住请填写相同姓名","left:0");
	setValidator(".passenger input[type=text]","pattern","[a-zA-Z\d]+['/]?[a-zA-Zd*]+","请填写规范的英文名","left:0");
	setValidator("input[name='confirmTypeDetailBo.contactName']","required",true,"联系人姓名不能为空");
	setValidator("input[name='confirmTypeDetailBo.mobileno']","required",true,"联系人手机不能为空");
	setValidator("input[name='confirmTypeDetailBo.mobileno']","pattern","[0-9]{11}",'联系人手机格式不正确');
	setValidator("input[name='confirmTypeDetailBo.telephone']","pattern","^([0-9]{3,4}[-]{0,1})?[0-9]{7,8}(([-]{0,1}[0-9]{3,4})?)$",'固定电话格式或位数不正确');

	$("#confirmType").change(function(){
    	if($(this).val()=='0') {
    		delValidator("input[name='confirmTypeDetailBo.email']", "required");
    		$("#contactEmail").parent().prev().find("em").text("");
    		$("#isSms").val(true);
    		$("#isFax").val(false);
    		$("#isEmail").val(false);
    	}else if($(this).val()=='1') {
    	}else if($(this).val()=='2') {
    		setValidator("input[name='confirmTypeDetailBo.email']", "required", true, 'Email不能为空');
    		$("#contactEmail").parent().prev().find("em").text("*");
    		$("#isSms").val(false);
    		$("#isFax").val(false);
    		$("#isEmail").val(true);
    	}
    });

	//提交订单
	$(".adaptiveButton.brightRed_btn").click(function() {
    	//IE处理placeholder
    	if($("input[name='confirmTypeDetailBo.telephone']").val() == '区号-电话号码-分机号'){
        	$("input[name='confirmTypeDetailBo.telephone']").val("");
        }
	    if(validator.validate()) {
	    	$(this).unbind("click").removeClass("brightRed_btn").addClass("bigDisabled_btn");
	    	$('#createGTANormalOrder').submit();
	    	_paq.push(['trackEvent','HotelCreateOrder','HotelInPreSubmitOrder','orderPage',$("#createGTANormalOrder").serialize()]);
	    }
    })

    // 常旅客选择框初始化
    initTravellerSelectPanel(function addTraveller(traveller) {
		$(".passenger").find("input[type=text]").each(function(index){
			if($(this).val() == '') {
				$(this).val(traveller.name);
				return false;
			}
		});
    })

    //底部悬停
    $('#ac').fixedBar({
        inverse:true
    });

	//价格日历
	$(".trRow_room").hover(function() {
		$("#priceCale").css({
			left : ($(this).position().left - 100),
			top : ($(this).position().top + $(this).height() + 6)
		}).show();
	}, function() {
		$("#priceCale").hide();
	})


    $("#submitDiv").click(function(){
    	change_date.close();
    	setChangeDate();
    });
    $("#cancelDiv").click(function(){
    	change_date.close();
    });


})

function setChangeDate(){

	 //修改隐藏在form中入住时间和离店时间
	$("#span_CheckinDate").html($("#VcheckinDate").val());
	$("#span_CheckoutDate").html($("#VcheckoutDate").val());
	$("#change_checkinDate").val($("#VcheckinDate").val());
	$("#change_checkoutDate").val($("#VcheckoutDate").val());

	//验证时间有效性后进行form提交，并关闭弹层
	if(validator1.validate()){
		submitChangeDateForm();
		change_date.close();
	}
}


function checkPrimary(obj){
	var guestArray = $(".passenger input[type=radio]");
	for(var i=0; i < guestArray.length; ++i) {
		if(obj == guestArray[i]) {
			guestArray[i].checked = true;
		} else {
			guestArray[i].checked = false;
		}
	}
}