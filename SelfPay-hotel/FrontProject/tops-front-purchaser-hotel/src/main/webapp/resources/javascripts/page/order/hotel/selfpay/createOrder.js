$(function(){

	validator = $("#createOrder").kendoValidator({
		errorTemplate: '<span class="" style="z-index:1;">\
            <span class="message_ct">\
				<span class="arrow"></span>\
                <span class="message_text">#=message#</span>\
            </span>\
        </span>'
	}).data("kendoValidator");
	
    guaranteeView();
    setInputCheck();
    searchTip();
	
	new FloatLayer({
		trigger:".modifyQuantity",
		template:"#modifyQuantityTemplate",
		offsetY:28
	});
	
	$("#createButton").bind("click",createButtonClick);

    $("input[name='startName']").click(function(){
    	guaranteeView();
	});
    
    $("body").delegate(".mdt_roombookingNum","click",function(){
		var roomNumber = $(this).html();
		$("input[name='roomNo']").val(roomNumber);
		$("#orderViewForm").submit();
    });

    $("body").delegate(".trRow_room","mouseenter mouseleave",function(event){
		if(event.type == "mouseenter"){
			$(".k-animation-container").show();
		}else{
			$(".k-animation-container").hide();
		}
	})

	$("#adaptiveButtonDate").click(function(){
		$("#orderViewForm > input[name='checkInDate']").val($("#checkinDate_selfpay").val());
		$("#orderViewForm > input[name='checkOutDate']").val($("#checkoutDate_selfpay").val());
		$("#orderViewForm").submit();
	});
    
    $(".bankGather").click(function(){
    	$(".floatLayer").show();
	});
    
    $(".close").click(function(){
    	$(".floatLayer").hide();
	});
    
    $("body").delegate(".bankClass","click",function(){
    	$(".bankGather").attr("value", $(this).find(".bankClass1").attr("val"));
    	$(".bankGather").attr("code", $(this).find(".bankClass1").attr("code"));
    	$(".floatLayer").hide();
    });
    
    $(".op input[type='checkbox']",$(".addon")).click(function(){
        var that=$(this);
        var col_index = parseInt($(this).closest("th").index());
        var check_status = $(this).prop("checked");
        var table = $(this).closest("table");

        view.col_enable[col_index - 1] = check_status;
        
        $("tbody tr",table).each(function(){
            if(check_status){
                $("td:eq(" + col_index + ") .acell",this).show();
                that.parent().siblings().show();
            }else{
                $("td:eq(" + col_index + ") .acell",this).hide();
                that.parent().siblings().hide();
            }
        });

        view.refresh();
    });

    $("body").delegate("#booking_room_count","change",function(){
        $(".addon input[name='num_of_room']").val($(this).val());
        view.booking_room = parseInt($(this).val());

        view.refresh();
    });

    $(".checkin_info_table .trRow_room").kendoTooltip({
        position:"bottom", //left right top
        wrapperClass:"lineWeight",
        content:function(){
            return $("#salePriceTblTemplate").html();
        },
        callout:false,
        offsetY:-2,
        offsetX:135
    });$("#name" + i).val() != ""

    $(".cardCVV").kendoTooltip({
        position:"bottom", //left right top
        wrapperClass:"lineWeight",
        content:function(){
            return $("#cardCVVTemplate").html();
        },
        callout:false,
        offsetX:100,
        width:450
    });
    
    $('#ac').fixedBar({
        inverse:true
    });
    
});

function createButtonClick(){
	if(validator.validate()){
		$("#createButton").unbind("click",createButtonClick);
		var roomNumber = $("input[name='roomNo']").val();
		var totalPrice = $("input[name='totalPrice']").val();
		var confirmType = $("#confirmType").val();
		$("#createOrder > input[name='roomNumber']").val(roomNumber);
		$("#createOrder > input[name='totalPrice']").val(totalPrice);
		
		if(confirmType == "短信确认"){
			$("input[name='hotelConfirmTypeDetail.isSms']").val('1');
		}
		else if(confirmType == "传真确认"){
			$("input[name='hotelConfirmTypeDetail.isFax']").val('1');
		}
		else if(confirmType == "Email确认"){
			$("input[name='hotelConfirmTypeDetail.isEmail']").val('1');
		}
		
		if($("#startName2").is(":checked")){
			$("input[name='isChooseTimeGuarantee']").val(1);
		}else{
			$("input[name='isChooseTimeGuarantee']").val(0);
		}
		
		var remarkString = "";
		if($("#purchaseRemark1").is(":checked")){
			remarkString = "尽量大床 ";
		}
		
		if($("#purchaseRemark2").is(":checked")){
			remarkString = "尽量双床 ";
		}
		
		var objs = $("#RemarkCheckbox input[type='checkbox']");
		for ( var i = 0; i < objs.length; i++) {
			if (objs[i].checked == true) {
				remarkString = remarkString + objs[i].value +" ";
			}
		}
		
		if(remarkString != ""){
			$("input[name='remarkToHotel']").val(remarkString);
		}
		
		if(isGuarantee()){
			$("input[name='selfPay.isGuarantee']").val(1);
		}else{
			$("#fillCardblock").remove();
		}
		
		$.ajax({
			url: basepath +"/pur/order/hotel/selfpay/createOrder",
			dataType: 'json',
			type : 'POST',
			data : $("#createOrder").serializeArray(),
			success:  function(result) {
				if(result.status == "notPass"){
					$("#cardTip").html(result.errorInfo);
					$("#cardTip").show();
				}else{
					if(result.status == "success"){
						window.location = basepath + "/pur/order/hotel/selfpay/orderResult?isSuccess="+result.status+"&orderId="+result.hotelOrderId;
					}else{
						$("input[name='isSuccess']").val(result.status);
						$("input[name='errorInfo']").val(result.errorInfo);
						$("#resultForm").submit();
					}
				}
				_paq.push(['trackEvent','HotelCreateOrder','HotelDoSelfSubmitOrder','orderPage',$("#createOrder").serialize()]);
			}
		});
		$("#createButton").bind("click",createButtonClick);
	}
}

function searchTip() {
	$.loadingbar({
		showClose: false,
		text:'建单请求中，请稍等...',
		urls: [new RegExp("^" + basepath +"/pur/order/hotel/selfpay/createOrder")]
	});
}

function setInputCheck(){
    for(var i=1; i <= $("#hotelGuests input").length/2; i++) {
    	setValidator("#" + "name" + i, "required", true, '姓名'+i+'不能为空');
    }
    for(var i=1; i <= $("#hotelGuests input").length; i++) {
    	setValidator("#" + "name" + i, "pattern", "([\u4E00-\u9FA5]{2,14})|([a-zA-Z]{1,20}/?[a-zA-Z]{1,20})", '联系人姓名格式不正确');
    }
    if($("#confirmType").val() == "Email确认"){
    	setValidator("#email", "required", true, 'Email不能为空');
    	setValidator("#email", "pattern", "^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$", 'Email格式不正确');
    }
    setValidator("#contactName", "required", true, '联系人姓名不能为空');
    setValidator("#mobileno", "required", true, '手机号不能为空');
    setValidator("#mobileno", "pattern", "[0-9]{11}", '手机格式不正确');
    setValidator("#telephone", "pattern", "^([0-9]{3,4}[-]{0,1})?[0-9]{7,8}(([-]{0,1}[0-9]{3,4})?)$", '固定电话格式或位数不正确');
    
    $("#confirmType").change(function(){
    	if($("#confirmType").val() == "Email确认"){
    		$("#emailTip").show();
    		setValidator("#email", "required", true, 'Email不能为空');
    		setValidator("#email", "pattern", "^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$", 'Email格式不正确');
    	}else{
    		$("#emailTip").hide();
    		delValidator("#email", "required", true, 'Email不能为空');
    		delValidator("#email", "pattern");
    	}
    });
    
    $("#cardType").change(function(){
    	if($("#cardType").val() == "IdentityCard"){
    		setValidator("#cardNo", "pattern", "^\d{6}(18|19|20)?\d{2}(0[1-9]|1[10-12])(0[1-9]|[10-12]\d|3[01])\d{3}(\d|X)$", '身份证不合法, 15位或18位');
    	}else{
    		delValidator("#cardNo", "pattern");
    	}
    });
}

function setGuaranteeVld(){
	setValidator("#cardName", "required", true, '持卡人姓名不能为空');
	setValidator("#cardBank", "required", true, '发卡银行不能为空');
	setValidator("#cardNumber", "required", true, '卡号不能为空');
	setValidator("#cardMonth", "required", true, '月份不能为空');
	setValidator("#cardMonth", "pattern", "^[0-9]{2}$", '月份为两位数字');
	setValidator("#cardYear", "required", true, '年份不能为空');
	setValidator("#cardYear", "pattern", "^[0-9]{4}$", '年份为四位数字');
	setValidator("#cardNo", "required", true, '证件号不能为空');
//	setValidator("#cardNo", "pattern", "^\d{15}$)|(^\d{17}(\d|X)$", '身份证不合法, 15位或18位');
//	setValidator("#cardNo", "pattern", "^[0-9]{15}$)|(^[0-9]{17}([0-9]|X)$", '身份证不合法, 15位或18位');
	setValidator("#cardNo", "pattern", "^[0-9]{6}(18|19|20)?[0-9]{2}(0[1-9]|1[10-12])(0[1-9]|[10-12][0-9]|3[01])[0-9]{3}([0-9]|X)$", '身份证不合法, 15位或18位');
}

function delGuaranteeVld(){
	delValidator("#cardName", "required");
	delValidator("#cardBank", "required");
	delValidator("#cardNumber", "required");
	delValidator("#cardMonth", "required");
	delValidator("#cardMonth", "pattern");
	delValidator("#cardYear", "required");
	delValidator("#cardYear", "pattern");
	delValidator("#cardNo", "required");
	delValidator("#cardNo", "pattern");
}

function guaranteeView(){
    if(isGuarantee()){
    	setGuaranteeVld();
    	$(".fillCardblock").show();
    }else{
    	delGuaranteeVld();
    	$(".fillCardblock").hide();
    }
}

function isGuarantee(){
	var GRT = $("#guaranteeRule").val();  //GRT == "0" 为无担保
	var mount = $("#amount").val();  //房量担保数
	
	if(GRT == "00"){  //强制担保
		return true;
	}
	
	if(GRT == "10"){  //房量担保
		if(($("input[name='roomNo']").val() * 1) >= ($("#amount").val() * 1)){
			return true;
		}
	}
	
	if(GRT == "01"){  //时间担保
		if($("#startName2").is(":checked")){
			return true;
		}
	}
	
	if(GRT == "11"){  //房量时间担保
		if(($("input[name='roomNo']").val() * 1) >= ($("#amount").val() * 1)
				|| $("#startName2").is(":checked")){
			return true;
		}
	}
	
	return false;
}

function modifyDateContainerToggle() {
	if ($('.modifyDate').html() == "修改日期") {
		$('.modifyDate').html("收起日期");
	} else {
		$('.modifyDate').html("修改日期");
	}
	$(".modifyDateContainer").toggle();
}
