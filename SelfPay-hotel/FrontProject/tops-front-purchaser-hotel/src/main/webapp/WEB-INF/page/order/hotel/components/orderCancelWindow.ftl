<!--  订单取消备注弹窗模板  -->
<script type="text/x-kendo-template" id="orderCancelRemarkTemplate">
    <div class="order-cancel-remark-win">
        <#--<p class="tip">取消规定：订单在2014年12月20日之前可免费取消</p>-->
        <form id="cancelOrderForm" action="">
	        <input type="hidden" name="hotelOrderId" />
	        <textarea name="cancelReason"></textarea>
        </form>
        <p class="win-oper-btn">
            <a class="adaptiveButton brightOrange_btn sure-cancel-order">
                 <span class="left"></span>
                 <span class="center center_1">取消预订</span>
                 <span class="right"></span>
             </a>
            <a href="javascript:void(0);" class="not-cancel-order">暂不取消</a>
        </p>
    </div>
</script>

<!--  订单取消中弹窗模板  -->
<script type="text/x-kendo-template" id="orderCancelProgressTemplate">
    <div class="loadingHint">
        <i class="gifLoadingIcon"></i> <em class="loadingHintFonts">订单取消中，请稍候...</em>
    </div>
</script>

<!--  订单取消成功弹窗模板  -->
<script type="text/x-kendo-template" id="orderCancelSuccessTemplate">
    <div class="order-cancel-win">
        <p class="tip-msg">
            <i class="large-success-icon"></i>
            <span>订单取消成功！</span>
        </p>
        <p class="win-oper-btn">
            <a class="adaptiveButton brightOrange_btn">
                 <span class="left"></span>
                 <span class="center center_1">确 定</span>
                 <span class="right"></span>
             </a>
        </p>
    </div>
</script>

<!--  订单取消失败弹窗模板  -->
<script type="text/x-kendo-template" id="orderCancelFailTemplate">
    <div class="order-cancel-win">
        <div class="tip-msg">
            <i class="large-fail-icon"></i>
            <p class="tip-cont">
                <span>订单取消失败，已超过订单取消时间！</span><br>
                <span class="gray-tip">取消规定：订单在2014年12月23日前可免费取消</span>
            </p>
        </div>
        <p class="win-oper-btn">
            <a class="adaptiveButton brightOrange_btn">
                <span class="left"></span>
                <span class="center center_1">确 定</span>
                <span class="right"></span>
            </a>
        </p>
    </div>
</script>
