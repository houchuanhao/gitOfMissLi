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
        #left{
            border-style: solid;
            border-color: #0dc5dd;
            border-width: 0px 1px 0px 0px;
            height: 500px;
        }
        .w50{
            width: 100%;
        }
        #Iframe1{
            border: 0px;
            scrolling:no;
            width: 50%;
            margin-left: auto;
            margin-right: auto;
        }
        #text{
        width:100%
        }
    </style>
    <script type="text/javascript" src="jedate/jedate.js"></script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">	
			<s:iterator value="# request.businessList" status="status">
			<s:if test="#status.first==true"><!-- 第一个 -->
				<div class="row">
			</s:if>
				<div class="col-md-4">
						<div class="thumbnail">
						
							<div class="caption">
								<h3>
									<s:property value="businessName" />
								----时间<s:property value="start"/>
								</h3>
								<p>
									<s:property value="introduce" />
								</p>
								<p>
									<s:a href="business_manage.action?id=%{id}&&businessName=%{businessName}">管理</s:a>
									 <a class="btn btn-primary" href="#">删除</a>
								</p>
							</div>
						</div>
					</div>
				<s:if test="#status.last==true"><!-- 最后一个 -->
					</div>
				</s:if>
				<s:else>
					<s:if test="#status.modulus(3)==0"><!-- 取余3==0 -->	
						<s:if test="#status.first==false">
						</div>
						<div class="row">
					</s:if>
					</s:if>
					
				</s:else>
			</s:iterator>
			
			
			
			
			
			
			
			
			
			
			
			
</body>

<script>
    var h=document.documentElement.clientHeight;
   // alert(h);
    var buttonHeight=h/3;
    $("#bottomButton").height(h/4);
   // alert(h);
    var myframe=$("#Iframe1");
    var mynav=$("#nav1");
    var fheigh=h-$("#nav1").outerHeight();//$("#nav1").css("marginBottom");
    myframe.height(h-mynav.innerHeight()-5);
    //alert(mynav.innerHeight());
    myframe.css("margin-top",mynav.innerHeight());
</script>
</html>