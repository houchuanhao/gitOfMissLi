
<%@ page import="java.sql.*,javax.sql.DataSource,javax.naming.*" %><%--
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
        #center{
        background-color: white;
        }
    body{
    background-color: #F0F0F0;
    }
    
    </style>
</head>
<body>
<% 
	Context initCtx=new InitialContext();
	DataSource ds=(DataSource)initCtx.lookup("java:comp/env/jdbc/Struts2DB");
	Connection conn=ds.getConnection();
	Statement stmt=conn.createStatement();
	String sql="insert into user(username,password) values('222','333')";
	stmt.executeUpdate(sql);
	out.print(sql);
	//out.print("<h1>"+rs.getRow()+"</h1>");
 %>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-3 column">
		</div>
		<div class="col-md-6 column" id="center">
			<form class="form-horizontal" role="form" action="new/login.action">
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" name="username" />
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label" >Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3" name="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							 <label><input type="checkbox" />Remember me</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default btn-block ">Sign in</button>
						 没有账号?前去注册
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-3 column">
		</div>
	</div>
</div>
</body>
</html>