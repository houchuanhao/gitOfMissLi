<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'signUp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  这里是signUp.jsp
  <a href="index.jsp">首页</a>
  <s:actionerror/>
  <s:form action="register!register" method="post">
  		<s:token/>
  		<s:textfield name="userName" id="userName" key="label.text.userName"/>
  		<s:password name="password" key="label.text.password"/>
  		<s:submit name="submit" value="% {getText('label.text.register')}" />
  </s:form>
  
  
    This is my JSP page. <br>
  </body>
</html>
