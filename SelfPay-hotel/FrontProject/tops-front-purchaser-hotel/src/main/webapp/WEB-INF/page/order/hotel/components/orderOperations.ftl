<#-- 酒店订单采购商操作，
	此文件需和 orderOperations.js 配合工作
	orderOperations 是当前订单可进行的操作
	hotelOrderId 是当前的订单ID
-->
<#macro orderSearchPage orderOperations hotelOrderId>
<#if (orderOperations)?? && (orderOperations)?size!=0>
	<span class="hyper-link" style="cursor:pointer;">
		<u>操作</u>
		<i class="arrow-down"></i>
		<div class="hyper-text">
		<#list orderOperations as operation>
			<a href="javascript:hotelOrderExcute('${operation.name()}','${(operation.url)!}','${(hotelOrderId)!}')">
				${operation.desc}
			</a>
		</#list>
		</div>
	</span>
<#else>
-
</#if>
</#macro>

<#macro orderDetailPage orderOperations hotelOrderId>
<#if (orderOperations)?? && (orderOperations)?size!=0>
	<#list orderOperations as operation>
		<a class="adaptiveButton mediumOrange_btn" style="margin-right: 10px;"
				href="javascript:hotelOrderExcute('${operation.name()}','${(operation.url)!}','${(hotelOrderId)!}')">
			<span class="left"></span>
			<span class="center center_1">${(operation.desc)!}</span>
			<span class="right"></span>
		</a>
 	</#list>
</#if>
</#macro>