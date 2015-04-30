var view;
var validator1;
var change_date;
function addServicePrice(price,degree,unit,booking_room) {
	 view= {
		    price:price,                       //每列的单价
		    unit_degree:degree,   //计算价格时的粒度 到人 还是到 房间
		    unit: unit,                          //份数
		    booking_room:booking_room,              //预订的房间数
		    people_num:1,                           //入住的人数
		    trigger:".addon",
		    rowShow:"input[name='sub_total']",      //行小计
		    totalShow:"input[name='total']",        //总计

		    init:function(){
		        //empty
		        this.col_enable = [];

		        for(var i = 0; i < this.price.length; i++){
		            this.col_enable[i] = false;
		        }
		        this.rowSubTotal = [];
		        this.total = 0;
		    },
		    refresh:function(){
		        var self = this;
		        $(this.trigger + " tbody > tr").each(function(index){
		            self.rowTotal(index,this);
		            $(self.rowShow,this).val(self.rowSubTotal[index]);
		        });

		        $(this.trigger + " " + this.totalShow).val(this.allRowTotal());
		    },
		    rowTotal:function(index,tr){
		        this.rowSubTotal[index] = 0;
		        for(var i = 0 ; i < this.price.length ; i++){
		            if(this.unit_degree[i] == "room"){
		                this.rowSubTotal[index] += (this.col_enable[i] == true ? this.price[i] * this.unit[i] * this.booking_room : 0);
		            }else{
		                this.rowSubTotal[index] += (this.col_enable[i] == true ? this.price[i] * this.unit[i] * this.people_num * this.booking_room : 0);
		            }
		        }
		    },
		    allRowTotal:function(){
		        this.total = 0;
		        for(var i = 0; i < this.rowSubTotal.length; i++){
		            this.total += this.rowSubTotal[i];
		        }

		        return this.total;
		    }
		};

		view.init();
}

//添加常用联系人
function addTraveller(traveller) {
	$(".passenger").find("input").each(function(index){
		if($(this).val() == '') {
			$(this).val(traveller.name);
			return false;
		}
	});
}


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

$(function(){
	initTravellerSelectPanel(addTraveller);


     change_date = new PopWindow("#change_date",{
        title:"修改入住日期",
        width:425,
        template:"#edit-booking-date-template",
        open:function(){
        	validator1 = $(".change_date").kendoValidator().data("kendoValidator");
        	initDate();

        }
    }).init();
    $("#submitDiv").click(function(){
    	//submitDiv();
    	setChangeDate();

    });
    $("#cancelDiv").click(function(){
    	change_date.close();
    });

    submitDiv();

    //入住人性名规则弹层
    $("#nameRule").kendoTooltip({
		position:"bottom", //left right top
		content:kendo.template($("#nameRuleTemplate").html())
		});

    // 订单页面输入验证
    var validator = $('#createNormalOrder').kendoValidator().data("kendoValidator");

    var obj = $(".passenger input");
    for(var i=0; i<obj.length; i++) {
    	setValidator("#" + "name" + i, "required", true, '姓名' + (i+1) + '不能为空');
    	setValidator("#" + "name" + i, "pattern", "^(?!.*至少填写一人，多人逗号分隔).*$", '请输入姓名' + (i+1));//IE9以下不支持placeholder属性，因此只要姓名中包含“至少填写一人，多人逗号分隔”这句话，便验证不通过
//    	setValidator("#" + "name" + i, "pattern", "([\u4E00-\u9FA5\·\-]{2,14})|([a-zA-Z\-]{3,20})", '姓名' +(i+1) + '格式不正确');
    }

    $("#confirmType").change(function(){
    	if($(this).val()=='0') {
    		setValidator("input[name='confirmTypeDetailBo.email']", "required", false, 'Email不能为空');
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

    setValidator("input[name='confirmTypeDetailBo.contactName']", "required", true, '联系人姓名不能为空');
//    setValidator("input[name='confirmTypeDetailBo.contactName']", "pattern", "([\u4E00-\u9FA5\·\-]{2,14})|([a-zA-Z\-]{3,20})", '联系人姓名格式不正确');
    setValidator("input[name='confirmTypeDetailBo.mobileno']", "required", true, '手机不能为空');
    setValidator("input[name='confirmTypeDetailBo.mobileno']", "pattern", "[0-9]{11}", '手机格式不正确');
    setValidator("input[name='confirmTypeDetailBo.telephone']", "pattern", "^([0-9]{3,4}[-]{0,1})?[0-9]{7,8}(([-]{0,1}[0-9]{3,4})?)$", '固定电话格式或位数不正确');
    setValidator("input[name='confirmTypeDetailBo.email']", "pattern", "^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$", 'Email格式不正确');

    $(".adaptiveButton.brightRed_btn").click(function() {
    	//IE处理placeholder
    	if($("input[name='confirmTypeDetailBo.telephone']").val() == '区号-电话号码-分机号'){
        	$("input[name='confirmTypeDetailBo.telephone']").val("");
        }
    	if($("textarea[name='purchaseRemark']").val() == '请填写发票相关备注信息;'){
    		$("textarea[name='purchaseRemark']").val("");
    	}
    	if($('textarea[name="hotelDetailBo.remark"]').val() == '请填写酒店预订相关备注信息;') {
    		$('textarea[name="hotelDetailBo.remark"]').val("")
    	}
	    if(validator.validate()) {
	    	$(this).unbind("click").removeClass("brightRed_btn").addClass("bigDisabled_btn");
	    	$('#createNormalOrder').submit();
	    	_paq.push(['trackEvent','HotelCreateOrder','HotelDoPreSubmitOrder','orderPage',$("#createNormalOrder").serialize()]);
	    }
    })

});



function sum () {
	$("#totalAddServiceFeeYuan").val($("#totalAddService").val());
	var sum=parseInt($("#totalAddServiceFeeYuan").val())+parseInt($("#totalRoomFee").text());
	$("#totalOrderFeeYuan").val(sum);
}
function submitDiv() {

	$.post(basepath + '/pur/order/hotel/getNormalOrderDiv', {
		checkInDate : $("#VcheckinDate").val(),
		checkOutDate : $("#VcheckoutDate").val(),
		hotelId : $("#hotelId").val(),
		roomCatId : $("#roomCatId").val(),
		bookingClassId : $("#bookingClassId").val(),
		roomNo : $("#booking_room_count").val()
	}, function(result) {
		$("#result").html(result);



		loadDiv();
	});
}

function loadDiv() {
	var price=[];
	var unit=[];
	$(".ttPrice").each(function(index){
		price[index]=parseInt($(this).text());
		unit[index]=1;
	});
	var degree=[];
	$("div[class='op'] input[type='checkbox']").each(function(index){
		degree[index] = $(this).attr("class");
	});
	addServicePrice(price,degree,unit,$("#booking_room_count").val());

    $(".op input[type='checkbox']",$(".addon")).click(function(){

        var that=$(this);
        var col_index = parseInt($(this).closest("th").index());
        var check_status = $(this).prop("checked");
        var table = $(this).closest("table");

        view.col_enable[col_index - 1] = check_status;
        var totalDay=0;//增值服务的金额
        var totalNo=0;//增值服务份数
        $("tbody tr",table).each(function(index){
            if(check_status){
                $("td:eq(" + col_index + ") .acell",this).show();
                //每日增值服务总计
                td= $("td:eq(" + col_index + ")",this);
                if(td.find("input[class='num_of_person']").length>0) {
                	totalDay =parseInt(td.find("input[class='num_of_price']").val())*parseInt(td.find("input[class='num_of_person']").val())*parseInt(td.find(" input[class='num_of_room']").val());
                	totalNo =parseInt(td.find("input[class='num_of_person']").val());
                }else {
                	totalDay =parseInt(td.find("input[class='num_of_price']").val())*parseInt(td.find("input[class='num_of_room']").val());
                	totalNo = parseInt(1);
                }
                if(index==0) {
                $("td:eq(" + col_index + ") input",this).last().val(totalDay);
                $("td:eq(" + col_index + ") input",this).last().prev().val(totalNo);
                $("td:eq(" + col_index + ") input",this).last().prev().prev().val(true);
                }
                that.parent().siblings().show();
            }else{
                $("td:eq(" + col_index + ") .acell",this).hide();
                that.parent().siblings().hide();
                if(index==0) {
                $("td:eq(" + col_index + ") input",this).last().val(0);
                $("td:eq(" + col_index + ") input",this).last().prev().val(0);
                $("td:eq(" + col_index + ") input",this).last().prev().prev().val(false);
                }
            }
        });
        view.refresh();
    	sum();
    });

    $("body").delegate("#booking_room_count","change",function(){

        $(".addon input[class='num_of_room']").val($(this).val());
        view.booking_room = parseInt($(this).val());

        view.refresh();
    	sum();
    });

    $("body").delegate(".add_up,.minus","click",function(){

        var col_index = $(this).closest("th").index();

        var input = $(this).closest("span").find("input");
        var num = parseInt(input.val());

        if($(this).hasClass("add_up")){
            if(num < 100) num += 1;
        }else{
            if(num > 1) num -= 1;
        }
        input.val(num);

        view.people_num = num;

        $("input[name='num_of_person']",$(".addon")).val(num);
        view.refresh();
        $(".op input[class='gray-border']").val(num);
    	sum();


    	var col_index = parseInt($(this).closest("th").index());
    	var table = $(this).closest("table");
    	$("tbody tr",table).each(function(index){
                //每日增值服务总计
                td= $("td:eq(" + col_index + ")",this);
                if(td.find("input[class='num_of_person']").length>0) {
                	totalDay =parseInt(td.find("input[class='num_of_price']").val())*parseInt(td.find("input[class='num_of_person']").val())*parseInt(td.find(" input[class='num_of_room']").val());
                	totalNo =parseInt(td.find("input[class='num_of_person']").val());
                }else {
                	totalDay =parseInt(td.find("input[class='num_of_price']").val())*parseInt(td.find("input[class='num_of_room']").val());
                	totalNo = parseInt(1);
                }
                if(index==0) {
                    $("td:eq(" + col_index + ") input",this).last().val(totalDay);
                    $("td:eq(" + col_index + ") input",this).last().prev().val(totalNo);
                }

	        });
	    });

    $("#booking_room_count").change(function(){
    	var fee = parseInt($("#oneRoomHotelFee").val()) * parseInt($(this).val());
    	$("#totalRoomFee").text(fee);
    	$("#hotelFee").text(fee);
    	sum();
    });


}