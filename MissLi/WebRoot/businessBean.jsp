<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.model.business.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-12-07
  Time: 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        #left{
            border-style: solid;
            border-color: #0dc5dd;
            border-width: 0px 1px 0px 0px;
            height: 500px;
        }
        .w50{
            width: 100%;
        }
        iframe{
       		//height:100%;
            border: 0px;
            scrolling:no;
            width: 100%;
        }
    </style>


</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column" id="list">
			<div class="panel-group" id="panel-510651">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-510651" href="#panel-element-429205">文件管理</a>
					</div>
					<div id="panel-element-429205" class="panel-collapse collapse in">
						<div class="panel-body">
							<%
								Business bs=(Business)request.getAttribute("business");
								out.println("<a href='uploadFile.jsp?id="+bs.getId()+"' target='iframe'>");
								out.println("文件上传</a>");
							 %>
						</div>
						<div class="panel-body">
							<%
							out.println("<a href='uploadFile_download?id="+bs.getId()+"' target='iframe'> 文件下载/删除</a>");
							 %>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-510651" href="#panel-element-727566">进度管理</a>
					</div>
					<div id="panel-element-727566" class="panel-collapse collapse">
						<div class="panel-body">
							添加进度
						</div>
						<div class="panel-body">
							查看进度
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-510651" href="#panel-element-7275667">提醒管理</a>
					</div>
					<div id="panel-element-7275667" class="panel-collapse collapse">
						<div class="panel-body">
							添加提醒
						</div>
						<div class="panel-body">
							查看提醒
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-510651" href="#panel-element-72756689">事务对比</a>
					</div>
					<div id="panel-element-72756689" class="panel-collapse collapse">
						<div class="panel-body">
							属性1
						</div>
						<div class="panel-body">
							属性2
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-510651" href="#panel-element-1">属性管理</a>
					</div>
					<div id="panel-element-1" class="panel-collapse collapse">
						<div class="panel-body">
							添加属性
						</div>
						<div class="panel-body">
							属性2
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-10 column">
		<iframe name="iframe" id="iframe">
		</iframe>
		</div>
	</div>
</div>
<script>
/*
 	var h=document.documentElement.clientHeight;
   // alert(h);
    var myframe=$("#iframe");
    var leftHeight=$("#list").outerHeight();
    var fheigh=h-$("#nav1").outerHeight();//$("#nav1").css("marginBottom");
    myframe.height(leftHeight);
    */
     var h=document.documentElement.clientHeight;
   // alert(h);
    var myframe=$("#iframe");
    var leftHeight=$("#list").outerHeight();
    var fheigh=h-$("#nav1").outerHeight();//$("#nav1").css("marginBottom");
    myframe.height(h-5);
    //alert(mynav.innerHeight());
    //myframe.css("margin-top",mynav.innerHeight());
</script>

<!---------下面是导航栏---->
</body>
</html>