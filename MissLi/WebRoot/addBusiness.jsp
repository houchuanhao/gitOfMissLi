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
    <script>
        //jQuery
        var APP_ID = 'nrQJolPEqzOK1qEKJM1feLON-9Nh9j0Va';

        // 应用 Key，用来校验权限（Web 端可以配置安全域名来保护数据安全）
        var APP_KEY = '7mpFcX7pBvw2gL4rmcJ81H3r';

        // 初始化
        AV.init({
            appId: APP_ID,
            appKey: APP_KEY
        });

        function changeState(){ //更改状态 登陆/登出
        	var userName=null;
            userName = $.cookie("userName");
            if (userName) { //转到登录状态
                var par_a = $("#loginDom").parent();//par1是链接到隐藏窗体的标签
                var par_li=par_a.parent();  //par_li是<li>标签
                $("#loginDom").remove();
                par_a.remove();
                par_li.attr("class","dropdown");
                if(userName=="houchuanhao"){  //是管理员
                    var child1=$("<li> <a href='addModular.html' target='mainIframe'>增加模块</a> </li>");
                    var child2=$("<li> <a href='updateModular.jsp' target='mainIframe'>编辑模块</a> </li>");
                    $("#nav").append(child1);
                    $("#nav").append(child2);
                }
/*
                var li_a=$("<a></a>");
                //"dropdown-toggle" data-toggle="dropdown"
                li_a.attr("class","dropdown-toggle");
                li_a.attr("data-toggle","dropdown");
                li_a.html(username);
                par_li.append(li_a);
*///<a href="frame3.jsp" target="mainIframe" id="myDiy">我的diy</a>
                var li_a=$("<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"+userName+"&nbsp;&nbsp;&nbsp;<strong class='caret'></strong></a>");
                var li_ul=$("<ul class='dropdown-menu'> <li> <a href='frame3.jsp' target='mainIframe' id='myDiy'>我的DIY</a> </li> <li> <a href='#shop' data-toggle='modal' >我的购物车</a> </li>     <li class='divider'> </li> <li> <a href='#concel' data-toggle='modal'>注销</a> </li> </ul>");
                par_li.append(li_a);
                par_li.append(li_ul);
               // par_a.html(username);
                // par_a.attr("href","#");
                //修改mydiy的连接，给它加上用户名
                $("#myDiy").attr("href","frame3.jsp?UserName="+userName);
            }
            else{  //登出
                $("#log").children(":first").remove();
                $("#log").children(":first").remove();
                var btn=$("<a href='#login' data-toggle='modal'> <button  id='loginDom' type='button' class='btn btn-default btn-success btn-block'>&nbsp;&nbsp;&nbsp;登陆&nbsp;&nbsp;&nbsp;</button> </a>")
                $("#log").append(btn);
            }
        }
        function signup() {  //注册
            var userName=$("#signUserName").val();
            var password=$("#signPassword").val();
            var isNameVaild=true;
            var email=$("#signEmail").val();
            $.ajax({
            type:"POST",
            url:"user_signUp.action",
            data:{"userName":userName,"password":password,"email":email},
            dataType:"json",
            success:function(data1,textStatus){
            		console.log(data1);
            		isNameVaild=data1['isNameVaild'];
            		if(!isNameVaild){  //
            			alert("用户名已被注册");
            		}

            }
            })
        }
        function forget(){
            var email=$("#forgetEmail").val();
            console.log(email);
            AV.User.requestPasswordReset(email).then(function (success) {
                alert("我们已经找回密码方式发送至"+email+"，请前往邮箱进行查看");
            }, function (error) {
                alert(error.message);
            });
        }
        function login() { //登陆
            var username=$("#logUserName").val();
            var password=$("#logPassword").val();
            $.ajax({
            type:"POST",
            url:"user_signIn.action",
            data:{"userName":username,"password":password},
            dataType:"json",
            success:function(data1,textStatus){
            		console.log(data1);
            		loginSuccess=data1['loginSuccess'];
            		if(!loginSuccess){  //
            			alert("用户名密码错误");
            		}else
            		{
            			$.cookie("userName",username);
            			alert("登录成功");
            		}

            }
            })
            changeState();
         /*   
            AV.User.logIn(username, password).then(function (loginedUser) {
                //登陆成功
                if(!loginedUser._serverData.emailVerified){ //登陆失败，未注册
                    alert("登陆失败，用户已注册，但邮箱未验证");
                    AV.User.logOut();
                    return;
                }
                $("#logSuccess").modal('show');
                $("#login").modal('hide');
                changeState();
                // setTimeout(function(){$("#logSuccess").modal('hide');$("#login").modal('hide');},500);
                //console.log(loginedUser);
                console.log(loginedUser._serverData.emailVerified);
               // AV.User.logOut()
                console.log(AV.User.current());
            }, function (error) {
                alert("登陆失败"+error.message);
            });
            */
            // $("#login").modal('hide');
        }
        function Alert(str) {
            var out=$("<div class='modal fade'  role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'><div class='modal-dialog'> <div class='modal-content'> <div class='modal-header'> <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button> <h4 class='modal-title' id='myModalLabel'>消息 </h4> </div> <div class='modal-body'>"+str+"</div><div class='modal-footer'><button type='button' class='btn btn-default btn-block' data-dismiss='modal'>关闭</button></div></div></div></div>")
            $("body").prepend(out);
            out.modal("show");
        }
        function delShop() {
            //var s=$("#shoper");
            //var q=$("#shoper").parent();
            $("#shoper").remove();
            //q.append(s);
        }
    </script>
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
						 <label for="exampleInputPassword1">说明</label>
						 <textarea rows="5"  name="introduce" class="form-control" id="text"></textarea>
					</div>
					 <input type="submit" class="btn-default btn btn-success btn-block" value="添加"/>
				</form>
       		</div>
            <!--------上面是内联框架-------->
        </div>
    </div>
</div>
<!-------------下面是遮罩窗体--->
<div class="modal fade" id="signUp" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel1">
                    注册
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label >用户名</label><input type="text" class="form-control" id="signUserName" />
                    </div>
                    <div class="form-group">
                        <label >密码</label><input type="password" class="form-control" id="signPassword" />
                    </div>
                    <div class="form-group">
                        <label >邮箱</label><input type="text" class="form-control" id="signEmail" />
                    </div>
                    <p type="submit" class="btn-default btn btn-success btn-block"   onclick="signup()">注册</p>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="login" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    登陆
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label >用户名</label><input type="text" class="form-control" id="logUserName" />
                    </div>
                    <div class="form-group">
                        <label >密码</label><input type="password" class="form-control" id="logPassword" />
                    </div>
                    <div class="form-group">
                        <p  class="btn btn-default btn-success btn-block" onclick="login()">登陆</p>
                    </div>
                    <div class="form-group">

                        <div class="row clearfix">
                            <div class="col-md-6 column">
                                <a href="#forget" data-toggle="modal" data-dismiss="modal"><button class="btn btn-default btn-block btn-info">忘记密码</button></a>
                            </div>
                            <div class="col-md-6 column">
                                <a href="#signUp" data-toggle="modal" data-dismiss="modal"><button class="btn btn-default btn-block btn-info">前去注册</button></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="forget" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" >
                    忘记密码
                </h4>
            </div>
            <div class="modal-body">
                <form role="form">
                    <div class="form-group">
                        <label >注册邮箱</label><input type="email" class="form-control" id="forgetEmail" />
                    </div>
                    <p type="submit" class="btn btn-default btn-success btn-block" onclick="forget()">找回密码</p>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--下面是登陆成功-->
<div class="modal fade" id="concel" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <h1>确定要注销登陆吗?</h1>
                <p class="btn btn-default btn-block btn-info" id="logOut">注销</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-block btn-info" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" >
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