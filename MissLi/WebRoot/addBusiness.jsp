<%@ page import="java.sql.ResultSet" %><%--
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
<!---------下面是导航栏---->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <!--上面是导航栏---->

            <!-----下面是内联框架---
            <iframe id="Iframe1" src="login.jsp" name="mainIframe">

            </iframe>
            ---->
            <div id="Iframe1">
            </br></br></br></br>
            		<center><h1>添加事务</h1></center>
            	<form role="form" action="business.action" method="get">
					<div class="form-group">
						 <label for="exampleInputEmail1">事务名</label>
						 <input type="text" class="form-control" id="exampleInputEmail1" name="businessName" />
					</div>
					<div class="form-group">
						 <label for="exampleInputEmail1">开始时间</label>
						 <p class="datep">
						 <input class="datainp form-control" id="datebut" type="text" placeholder="未选择"  readonly>
						 <br><br>
						 <input type="button" 
onClick="jeDate({dateCell:'#datebut',isTime:true,format:'YYYY-MM-DD'})" value="选择时间" class="btn-default btn btn-success btn-block"></p>
					</div>
					<br><br>
					<div class="form-group">
						 <label for="exampleInputPassword1">说明</label>
						 <textarea rows="5"  name="introduce" class="form-control" id="text"></textarea>
					</div>
					 <input type="submit" class="btn-default btn btn-success btn-block" value="添加"/>
					 <br><br>
					 <p class="btn-default btn btn-success btn-block">返回</p>
				</form>
       		</div>
            <!--------上面是内联框架-------->
        </div>
    </div>
</div>
<!-------------下面是遮罩窗体--->
<script type="text/javascript" >

//下面是时间插件
    //jeDate.skin('gray');

    //alert("YYYY/MM".match(/\w+|d+/g).join("-"))
    //下面用于注销
    $("#logOut").click(function () {
    	$.cookie("userName",null);
        AV.User.logOut();
        // 现在的 currentUser 是 null 了
        $("#concel").modal('hide');
        window.location.href='index.jsp';
        //changeState();
        location.reload();
        // alert("注销成功");
    });
    var h=document.documentElement.clientHeight;
   // alert(h);
    var myframe=$("#Iframe1");
    var mynav=$("#nav1");
    var fheigh=h-$("#nav1").outerHeight();//$("#nav1").css("marginBottom");
    myframe.height(h-mynav.innerHeight()-5);
    //alert(mynav.innerHeight());
    myframe.css("margin-top",mynav.innerHeight());
    //myframe.marginTop($("#nav1").height());
</script>
</body>
</html>