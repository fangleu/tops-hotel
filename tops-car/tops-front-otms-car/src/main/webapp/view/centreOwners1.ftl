    
    

<html>
<#import "js/common.ftl" as con />
<head>
<base href="<%=basePath%>"></base>
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
	<div class="head">车主中心</div>
	<div class="personalCenter">
		<div class="personalInfo">
			<div class="cf">
				<div style="background-image: url(${con.bathPath}images/personalHead.jpg);" class="bg-cover"></div>
				<div class="l">
					<div class="personalName">
						<strong>姓&nbsp;&nbsp;&nbsp;名</strong> ${name}
					</div>
					<div class="personalPhone">
						<strong>手机号</strong>  ${phone}
					</div>
				</div>
			</div>
			<div class="personalTitle">我的爱车</div>
			<ul class="carCx noMarginBottom">
				<li class="flexBox">
					<a href="javascript:;" style="background-image: url(${con.bathPath}images/car1.jpg);" class="bg-cover"></a>
					<div class="flexChild">
						<div>${brand}</div>
						<div>车牌号：${plate}</div>
						<div>车架号：${carId}</div>
					</div>
				</li>
			</ul>
			<div class="personalTitle">我的服务记录</div>
			<a class="personalContent" href="javascript:;">预约试驾记录</a>
			<a class="personalContent" href="javascript:;">在线询价记录</a>
			<div class="personalTitle">
				我的礼券
			</div>
			<a class="personalContent" href="javascript:;">
				<div>您共获得？？？礼券</div>
				<div>未使用？？？</div>
			</a>
			<div class="personalTitle">我的专属客服</div>
			<ul class="kfList nomarginTop">
				<#if preSales??>
				<#list preSales as salesPerson>
				<li>
					<div style="background-image: url(${con.bathPath}images/ren1.jpg);" class="bg-cover"></div>
					<div class="kfInfo">
						<div class="kfName">${salesPerson.name}</div>
						<div>售前经理</div>
						<div>电话  ${salesPerson.phone}</div>
					</div>
					<div class="r">
						<#assign telephone = "tel:" + salesPerson.phone>
						
						<a href=${(telephone)!} >在线联系</a> 
						<a href=${(telephone)!} >拨打电话</a> 	
					</div>
				</li>
				</#list>
				</#if>


			</ul>
		</div>
	</div>
</body>
</html>