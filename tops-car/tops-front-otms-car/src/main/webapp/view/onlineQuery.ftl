<!DOCTYPE html>
<html>
<#import "js/common.ftl" as con />
<head>
    <meta http-equiv="pragram" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>凯迪拉克经销商</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link href="${con.bathPath}css/swiper-3.0.8.css" rel="stylesheet" type="text/css">
    <link href="${con.bathPath}css/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${con.bathPath}css/style.css" rel="stylesheet" type="text/css">
    <script src="${con.bathPath}js/jquery-1.11.1.min.js"></script>
    <script src="${con.bathPath}js/swiper.min-3.1.0.js"></script>
</head>    
<body>
<div class="head">在线询价记录</div>
<div class="record">

	<ul class="recordList">
				<#if conRecord??>
				<#list conRecord as record>
		<li>
			<div class="cf">
				<div class="recordCondation">询价</div>
				<div class="l">
					<div>2016-6-23 16:21</div>
					<div>${record.salesStaff}</div>
					<div>电话：${record.phone} </div>
					<div>车型：${record.models}</div>
				</div>
				<#assign telephone = "tel:" + record.phone>
						
				<a href=${(telephone)!} >拨打电话</a> 	
			</div>

		</li>
				</#list>
				</#if>
	</ul>
</div>
</body>
</html>