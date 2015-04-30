<#--采购商订单流程-->

<div class="flow clearfix" style=" padding: 0 10px; margin-bottom: 20px;">
	<h3><a href="${basepath}/pur/hotel/ordermanage/orders">返回</a></h3>
	<div class="instr pull-right lensf_status">
		<#list customerStage as stage>
			<#if stage='checkIn_complete'>
				<span class="highlight-hotel">入住</span>
			<#elseif (stage='checkIn_not_begin' ||stage='checkIn_processing' )>
				<span class="highlight">入住</span>
			</#if>
		</#list>
		
		<#list customerStage as stage>
			<#if stage='checkOut_complete'>
				<span class="highlight-hotel">结账</span>
			<#elseif (stage='checkOut_not_begin' ||stage='checkOut_processing' )>
				<span class="highlight">结账</span>
			</#if>
		</#list>
        
        <em style="left:55px;width:110px" class="line"><div style="width:0px"></div></em>
    </div>
	
	<div class="instr pull-right lensf_status">
		<#list customerStage as stage>
			<#if stage='init_complete'>
				<span class="highlight-hotel">建单</span>
			<#elseif (stage='init_not_begin' ||stage='init_processing' )>
				<span class="highlight">建单</span>
			</#if>
		</#list>
		
		<#list customerStage as stage>
			<#if stage='commit_complete'>
				<span class="highlight-hotel">提交</span>
			<#elseif (stage='commit_not_begin' ||stage='commit_processing' )>
				<span class="highlight">提交</span>
			</#if>
		</#list>
		
		<#list customerStage as stage>
			<#if stage='pay_complete'>
				<span class="highlight-hotel">支付</span>
			<#elseif (stage='pay_not_begin' ||stage='pay_processing' )>
				<span class="highlight">支付</span>
			</#if>
		</#list>
		
		<#list customerStage as stage>
			<#if stage='confirm_complete'>
				<span class="highlight-hotel">确认</span>
			<#elseif (stage='confirm_not_begin' ||stage='confirm_processing' )>
				<span class="highlight">确认</span>
			</#if>
		</#list>
		
		<em class="line" style="left:55px;width:220px"><div style="width:220px"></div></em>
	</div>
	
</div>