<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title> new document </title>
<meta name="generator" content="editplus" />
<meta name="author" content="" />
<meta name="keyWords" content="" />
<meta name="description" content="" />
<style type="text/CSS">
*
{
font-size:12px;
font-family:verdana;
margin:0px;
padding:0px;
}
a
{
text-decoration:none;
}
#header,#footer
{
width:85%;
margin:0 auto;
height:50px;
}
#header
{
height:70px;
margin-top:5px;
border:none 1px #000;
background:url() no-repeat right -20px;
}
#header h1
{
line-height:40px;
}
#body
{
position:relative;
width:85%;
margin:3px auto;
height:100%;
word-wrop:break-word;
word-break:break-all;
}
#sidebar
{
position:absolute;
left:0;
top:0;
width:200px;
}
#right
{
width:240px;
position:absolute;
right:0;
top:0;
}
#center
{
margin:0 241px 0 201px;
}
#body,#sidebar,#right,#center,#footer{height:750px;border:none 1px #000;}
#footer
{
height:50px;
}
#body
{
border:none;
}
#nav ul
{
float:left;
width:100%;
list-style-type:none;
border-bottom:none 6px #6666CC;
}
#nav ul li
{
float:left;
}
#nav li a
{
display:block;
text-decoration:none;
text-align:center;
width:50px;
padding:5px;
}
#nav li a:hover
{
background:#66c;
color:#fff;
font-weight:bold;
}
dt
{
border-bottom:dotted 1px #000066;
border-top:dotted 1px #006;
}
dt.first
{
border-top:none;
}
dt
{
padding:8px 0px 8px 4px;
}
dd a{
display:block;
text-indent:20px;
padding:10px;
}
dd a:hover
{
font-weight:bold;
color:#000;
background:#66FFCC;
}
a
{text-decoration:none}
#sub{
width:100%;}
</style>
  </head>
  
  <body>
    <div id="header">

<div id="nav">
<ul>
<div width:3000px;float:left;><li><a href="#1">1验证手机号</a></li>
</div>
<div>
<li><a href="#1">2填写用户名及密码</a></li>
</div>
<div><li><a href="#1">3注册成功</a></li>
</div>

</ul>
</div>
</div>
<div id="body">
<div id="sidebar">
</div>
<div id="center" text-align:center> 
<br/><br/><br/><br/>
&nbsp&nbsp &nbsp 手机号:&nbsp<input type="text" value="" /><br/><br/>
 图片验证码:&nbsp<input type="text" value="" /><br/><br/>
 手机验证码:&nbsp<input type="text" value="" /><br/><br/>
<input type="radio" name="" value=""checked="checked"/>我已仔细阅读并接受
		<a href = "indox.htm" target="_red">CSDN条款</a> <br/><br/>
<input id="sub"  type="submit" value="下一步" /><br/><br/>
<a href = "indox.htm" target="_red">还可以使用邮箱</a> <br/><br/>
</div>
<div id="right">
</div>
</div>

  </body>
</html>
