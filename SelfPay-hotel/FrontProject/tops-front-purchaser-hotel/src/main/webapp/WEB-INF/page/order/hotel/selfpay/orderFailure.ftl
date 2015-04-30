<#import "/common/mainContainer.ftl" as mc/>
<@mc.container cssFiles=["/css/store_hotel.css"] menuItem="酒店">

<section class="main-body clearfix">
    <div class="flow clearfix">
        <h3><a href ="" onClick="parent.history.back(); return false;" >返回</a></h3>
        <div class="instr pull-right lensf_status">
            <span class="travel-instr highlight">创建订单</span>
            <span class="travel-instr highlight">提交</span>
            <span style="margin-right:0px;" class="highlight-hotel">确认</span>
        <em style="left:55px;width:215px" class="line"><div style="width:215px"></div></em></div> 
    </div>

    <div class="wrappingShell">
        <div class="operationHintInfo">
            <h3 class="hintContent blackFonts"><i class="hintIcon failIcon"></i>订单提交失败！</h3>
            <div class="grayFonts">
                <p>${errorInfo!}</p>
            </div>
            <#--
            <div class="fontsWeight blackFonts">
                <p>
                    <a href="${basepath}/hotel/booking/search">您还可以看看</a>
                </p>
            </div>
            -->
        </div>
    </div>
</section>
</@mc.container>
