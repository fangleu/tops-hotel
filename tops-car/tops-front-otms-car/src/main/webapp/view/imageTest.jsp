<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common.jsp" %>    
<html>
<head>
<base href="<%=basePathUeditor%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" href="ueditor/themes/default/css/ueditor.css" rel="stylesheet"/>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.js"></script>
</head>
<body>
	
	<script type="text/javascript">
		var ue = UE.getEditor("container");
	</script>	
	rootPath: 	${rootPath}
 <form action="http://127.0.0.1:8233/component/common/uploadImg" method="post" enctype="multipart/form-data">
     头像：<input type="file" name="img" /><br/> 
     <input type="image" src="images/img_submit.gif" />
 <input type="submit" value="提交">
 </form>
	
	<br>
	
	
	<textarea id="container" name="container" style="width: 800px; height: 400px; margin: 0 auto;">
    </textarea>
	
	

</body>
</html>