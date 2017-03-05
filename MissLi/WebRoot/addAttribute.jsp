<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-12-07
  Time: 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.model.business.*" %>
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
        #bName{
        color:blue;
        }
    </style>
    <script type="text/javascript" src="jedate/jedate.js"></script>
    <script>
    	function changeToDate(){
    		if($("#select").val()=="date"){
	    		$("#date").css("readonly",true);
	    		$("#date").css("class","datainp form-control");
	    		$("#date").click(function(){
	    		   if($("#select").val()=="date")
	    			jeDate({dateCell:'#date',isTime:true,format:'YYYY年MM月DD日 hh时mm分ss秒'})
	    		});
    		}
    		else{
    		$("#date").css("class","form-contral");
    		}
    	}
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="Iframe1">
           	<form role="form" action="attribute_doAdd" method="get">
            	<table class="table">
					<thead>
						<tr>
							<th>
								属性名
							</th>
							<th>
								类型
							</th>
							<th>
								属性值
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<td>											  
								<input type="text" class="form-control" name="attributeName" />
							</td>
							<td>
								<select name="type" onchange="changeToDate()" id="select"> 
								<option value="num">数值</option> 
								<option value="txt">文字</option>
								<option value="date">日期</option>
								</select> 
							</td>
							<td>
								<input type="text" class="form-control" name="val" id="date" />
							</td>
						</tr>
					</tbody>
				</table>
				
				
				<div class="form-group">
						 <label for="exampleInputPassword1">说明</label>
						 <textarea rows="5"  name="introduce" class="form-control" id="text"></textarea>
					</div>
					
					 <br><br>
					 <%
						Business bs=(Business)request.getAttribute("business");
						out.println(bs.getId());
					 %>
					 <input type="text"  name="businessName" value="<%out.println(bs.getBusinessName()); %>"  style="display:none;"/>
					 <input type="text"  name="businessId" 				value="<%out.println(bs.getId()); %>"  style="display:none;"/>
					 
					  <input type="submit" class="btn-default btn btn-success btn-block" value="添加"/>
			</form>
            </br></br></br></br>
            		<center><h1>为事务<span id="bName"><%out.println(bs.getBusinessName()); %></span>添加属性</h1></center>
           
       		</div>
            <!--------上面是内联框架-------->
        </div>
    </div>
</div>
<!-------------下面是遮罩窗体--->
</body>
</html>