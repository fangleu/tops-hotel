<#--
	房型展示中使用的Tip模板
	author:	xumeng
	create:	2014-04-01
-->

<#-- 对应 GTA 的 Essential Information -->
<#macro importantTemplate essentialInfos>
<script type="text/x-kendo-template" id="importantTemplate">
<div class="layerGrayBlock">
	<div class="abroadtooltipsCont" style="width:275px;">
		<div class="abroadtoolclickArrow"></div>
		<h4>重要信息<i class="close">&nbsp;</i></h4>
		<#list essentialInfos as essentialInfo>
		<p>${((essentialInfo.text)!)?replace('#','\\#')}</p>
		<#if (essentialInfo.fromDate)??>
		从${(essentialInfo.fromDate?date?string("yyyy年MM月dd日"))!}起生效
		</#if>
		<#if (essentialInfo.toDate)??>
		到${(essentialInfo.toDate?date?string("yyyy年MM月dd日"))!}结束
		</#if>
		</#list>
	</div>
</div>
</script>
</#macro>

<#-- 酒店规定 -->
<#macro stipulateTemplate gtaConditions>
<#if gtaConditions??>
<script type="text/x-kendo-template">
<div class="layerGrayBlock">
	<div class="abroadtooltipsCont" style="width:400px;">
		<div class="abroadtoolclickArrow"></div>
		<h4>酒店预订成功，不能变更预订，包含客人姓名；<i class="close">&nbsp;</i></h4>
		<#list (gtaConditions?size-1)..0 as i>
		<#assign gtaCondition=gtaConditions[i]>
		<#if (gtaCondition.conditionType)?? && gtaCondition.conditionType=="cancellation">
			<#if (gtaCondition.toDate)??>
				<#if (gtaCondition.charge)?? && !(gtaCondition.charge)>
					<#if gtaCondition.toDate==gtaCondition.fromDate>
					<p>${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 或之后 取消预订，不收取任何费用；</p>
					<#else>
					<p>${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 至 ${(gtaCondition.fromDate?date?string("yyyy年MM月dd日"))!}
						取消预订，不收取任何费用；</p>
					</#if>
				<#else>
					<#if gtaCondition.toDate==gtaCondition.fromDate>
					<p>${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 或之后 取消预订，需要收取 <span class="price">&yen; ${(gtaCondition.chargeAmountRMB)!}</span> 元。</p>
					<#else>
					<p>${(gtaCondition.toDate?date?string("yyyy年MM月dd日"))!} 至 ${(gtaCondition.fromDate?date?string("yyyy年MM月dd日"))!}
						取消预订，需要收取 <span class="price">&yen; ${(gtaCondition.chargeAmountRMB)!}</span> 元；</p>
					</#if>
				</#if>
			<#else>
				<#if (gtaCondition.charge)?? && !(gtaCondition.charge)>
					<p>${(gtaCondition.fromDate?date?string("yyyy年MM月dd日"))!} 或更早 取消预订，不收取任何费用；</p>
				<#else>
					<p>酒店预订一经确认，不能变更和取消预订。</p>
				</#if>
			</#if>
		</#if>
		</#list>
	</div>
</div>
</script>
</#if>
</#macro>

<#-- 直接被 include 进searchResultPage 和 detail 页面 -->
<script type="text/x-kendo-template" id="assistTipsTemplate">
<div class="layerGrayBlock">
	<div class="abroadtooltipsCont">
		<div class="layerGrayArrow"></div>
		<p style="color:#e94749;">(动态直连价格) 预定提交后只能取消，不能修改。</p>
	</div>
</div>
</script>

