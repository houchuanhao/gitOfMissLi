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
    
    <title>My JSP 'uploadFile.jsp' starting page</title>
    
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
  
  
  　　<form action="uploadFile" method="post" enctype="multipart/form-data">
    　　
        username: <input type="text" name="username"><br>
        file: <input type="file" name="file"><br>
        
        <input type="submit" value="submit">
    </form>
  
  
  
  
  
  
  
  
  
  
  <s:form action="uploadFile" method="post" >
	  <s:file name="file" label="选择文件"/>
	  <s:submit value="上传"/>
  </s:form>
  <s:if test="upload!=null">
  	<s:property value="uploadFileName"/> 
  	<s:property value="uploadContentType"/> 
  </s:if>
    This is my JSP page. <br>
  </body>
</html>