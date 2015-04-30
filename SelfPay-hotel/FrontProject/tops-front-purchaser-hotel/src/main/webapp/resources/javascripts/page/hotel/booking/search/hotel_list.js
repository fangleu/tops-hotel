var xmlhttp;
function createXHR() {
	if(window.ActiveXObject) {
		xmlhttp = new window.ActiveXObject();
	}
	if(window.XMLHttpRequest) {
		xmlhttp = new Microsoft.XMLHttpRequest();
	}
	if(xmlhttp == null || window.XMLHttpRequest == undefined) {
		alert("xxxx");
	}
}

$(function() {
	var name = document.getElementByName("tab").value;
	var msg = document.getElementById("msg");
	var url = "?/tab=escape(name)";
	xmlhttp.readstatechange = callback;
	xmlhttp.send(null);
});

function callback() {
	if(xmlhttp.statechange = 200) {
		if(xmlhttp.status == 4) {
			var result = xmlhttp.responseXML;
			var str = "<table><tr><td>房型</td><td>床型</td><td>早餐</td><td>宽带</td><td>其他服务</td><td>规定</td><td>销售价</td></tr>";
			for(int ) {
				str += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
			}
		}
	}
}