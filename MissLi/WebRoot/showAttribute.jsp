<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-12-07
  Time: 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- jQuery (necessary JavaScript plugins) -->
    <!-- Custom Theme files -->
    <link href='css/font.css' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
     <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/av-min-1.2.1.js"></script>
    <!-- Custom Theme files -->
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <style>
        *{ font-family: Microsoft YaHei,'宋体' , Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
            margin: 0;
            padding: 0;
            border: 0;
        }
    </style>
    <script type="text/javascript" src="jedate/jedate.js"></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">	
			<table class="table">
				<thead>
						<tr>
							<th>
								属性名称
							</th>
							<th>
								类型
							</th>
							<th>
								值
							</th>
							<th>
								操作
							</th>
						</tr>
				</thead>
				<tbody>
				<s:iterator value="# request.attributeList" status="status">
				<tr class="success">
					<th>
						<s:property value="attributeName" /><!-- 属性名称 -->
					</th>
					<th>
						<s:property value="type" /><!-- 属性类型 -->
					</th>
					<th>
						<s:property value="val" /><!-- 属性值 -->
					</th>
					<th>
						编辑 删除
					</th>
					
				</tr>
				</s:iterator>	
				</tbody>
			</table>
</body>
</html>