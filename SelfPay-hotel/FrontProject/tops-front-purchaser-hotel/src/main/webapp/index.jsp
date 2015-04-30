<%@page import="com.travelzen.tops.member.authorization.common.MemberAuthConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String url = MemberAuthConfig.get("member.url.loginSuccess");
if (!url.startsWith("http://")) {
	url = request.getContextPath() + url;
}
response.sendRedirect(url);
%>
