<#import "/common/mainContainer.ftl" as mc/>
<@mc.container cssFiles=["/css/store_hotel.css"] menuItem="酒店">

<section class="main-body clearfix">
    <div class="flow clearfix">
        <h3><a href ="${basepath}/pur/hotel/ordermanage/orders" >返回</a></h3>
        <div class="instr pull-right lensf_status">
            <span class="travel-instr highlight">创建订单</span>
            <span class="travel-instr highlight">提交</span>
            <span style="margin-right:0px;" class="highlight-hotel">确认</span>
        <em style="left:55px;width:215px" class="line"><div style="width:215px"></div></em></div> 
    </div>

    <div class="wrappingShell">
        <div class="operationHintInfo">            
            <h3 class="hintContent blueFonts"><i class="hintIcon succeedIcon"></i>订单提交成功！</h3>
            <div class="grayFonts">
                <p>我们会尽快处理，预计1小时通知确认结果！</p>
                <p><#if isGuarantee?string == "true">
						${(hotelOrder.hotelSelfpayBookingInfo.guaranteeRulesContent)!}
                	<#else>
		                订单提交后，可随时免费取消/变更。
                	</#if>
                	如您不能如期入住请通知客服为您取消订单。
                </p>
            </div>
            <div class="orangeFonts">
                <p>订单号：${orderId!}</p>
                <p>${hotelName!} ${address!}</p>
            </div>
        </div>
    </div>
</section>
</@mc.container>
