<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.db.*" %>
<%@ page import="com.model.user.*" %>
<%@ page import="com.model.business.*" %>
<%@ page import="com.model.attribute.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.PreparedStatement" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<title>jquery+css3曲线图 - 站长素材</title>
	<link rel="stylesheet" href="zzsc/graph.css">
	<meta name="robots" content="noindex,follow" />
    <base href="<%=basePath%>">
    
    <title>My JSP 'compare.jsp' starting page</title>
    
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
  
<center>
<div id="graph-wrapper">
	<div class="graph-info">
		<a href="javascript:void(0)" class="visitors">Visitors</a>
		<a href="#" id="bars"><span></span></a>
		<a href="#" id="lines" class="active"><span></span></a></div>
	<div class="graph-container">
		<div id="graph-lines">
		</div>
		<div id="graph-bars">
		</div>
	</div>
</div></center>
<!-- end Graph HTML -->
<script src="zzsc/jquery.min.js"></script>
<script src="zzsc/jquery.flot.min.js"></script>
<script>
$(document).ready(function () {

	// Graph Data ##############################################
	var graphData = [{
			// Visits
			data: [ 
			
			<%ResultSet rs=(ResultSet)request.getAttribute("rs");
			if(rs.next()){
			out.print("["+rs.getString("start").substring(2)+","+Integer.parseInt(rs.getString("val"))+"]");
			}
			while(rs.next()){
				out.print(",["+rs.getString("start").substring(2)+","+Integer.parseInt(rs.getString("val"))+"]");
			}
  			%>],
			color: '#71c73e'
		}
	];

	// Lines Graph #############################################
	$.plot($('#graph-lines'), graphData, {
		series: {
			points: {
				show: true,
				radius: 5
			},
			lines: {
				show: true
			},
			shadowSize: 0
		},
		grid: {
			color: '#646464',
			borderColor: 'transparent',
			borderWidth: 20,
			hoverable: true
		},
		xaxis: {
			tickColor: 'transparent',
			tickDecimals: 2
		},
		yaxis: {
			tickSize: 1000
		}
	});

	// Bars Graph ##############################################
	$.plot($('#graph-bars'), graphData, {
		series: {
			bars: {
				show: true,
				barWidth: .9,
				align: 'center'
			},
			shadowSize: 0
		},
		grid: {
			color: '#646464',
			borderColor: 'transparent',
			borderWidth: 20,
			hoverable: true
		},
		xaxis: {
			tickColor: 'transparent',
			tickDecimals: 2
		},
		yaxis: {
			tickSize: 1000
		}
	});

	// Graph Toggle ############################################
	$('#graph-bars').hide();

	$('#lines').on('click', function (e) {
		$('#bars').removeClass('active');
		$('#graph-bars').fadeOut();
		$(this).addClass('active');
		$('#graph-lines').fadeIn();
		e.preventDefault();
	});

	$('#bars').on('click', function (e) {
		$('#lines').removeClass('active');
		$('#graph-lines').fadeOut();
		$(this).addClass('active');
		$('#graph-bars').fadeIn().removeClass('hidden');
		e.preventDefault();
	});

	// Tooltip #################################################
	function showTooltip(x, y, contents) {
		$('<div id="tooltip">' + contents + '</div>').css({
			top: y - 16,
			left: x + 20
		}).appendTo('body').fadeIn();
	}

	var previousPoint = null;

	$('#graph-lines, #graph-bars').bind('plothover', function (event, pos, item) {
		if (item) {
			if (previousPoint != item.dataIndex) {
				previousPoint = item.dataIndex;
				$('#tooltip').remove();
				var x = item.datapoint[0],
					y = item.datapoint[1];
					showTooltip(item.pageX, item.pageY, y + ' visitors at ' + 

x + '.00h');
			}
		} else {
			$('#tooltip').remove();
			previousPoint = null;
		}
	});

});
</script>
  
  -------------------
  	<%
  	 rs=(ResultSet)request.getAttribute("rs");
  	out.println(rs);
  	/*
	User user=(User)request.getSession().getAttribute("user");
	String userId=user.getId();
	String attributeName=request.getParameter("attributeName");
	String businessName=request.getParameter("businessName");
	Statement stmt=DbUtil.getConnection().createStatement();
	Connection conn=DbUtil.getConnection();
	PreparedStatement pstmt= conn.prepareStatement("select * from business,attribute where userName=? and business.businessName=? and business.id=attribute.businessId and attribute.attributeName=?");
	pstmt.setString(1, user.getUsername());
	pstmt.setString(2, businessName);
	pstmt.setString(3, attributeName);
	out.println(pstmt);
	ResultSet rs=pstmt.executeQuery();
	rs.last();
	out.println(rs.getRow());
	System.out.println("uid"+userId+"bName"+businessName+"aName"+attributeName);
	pstmt.close();
	conn.close();
	System.out.println("-------------close---------------");
	*/
//	stmt.executeQuery(sql);
 %>
  </body>
</html>