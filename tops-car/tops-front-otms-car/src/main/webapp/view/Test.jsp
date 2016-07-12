<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=sjrDIrsK8cGgYctn5lUXUnqQtUBlUSrT"></script>
    <title>Hello, World</title>
    <style type="text/css">
      body, #allmap {position: fixed; bottom: -5%; width: 100%;height: 70%;overflow: hidden; align: center; }
     </style>
</head>
<body>
 aaaaa    {}
    <div id="allmap"></div>
    
</body>
</html>
<script type="text/javascript">
 
    var map = new BMap.Map("allmap");          
    var point = new BMap.Point(116.404, 39.915); 
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);
    map.centerAndZoom(point,15);                 
    map.enableScrollWheelZoom();               
</script>