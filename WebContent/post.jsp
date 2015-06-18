﻿<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="nju.iip.dto.Post"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<title>帖子详情</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/common.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

	<%
		Post post = (Post) request.getAttribute("post");
	%>

	<div class="bgfff form ov">
		<div>
			<img src=<%=post.getHeadImgUrl()%> alt="求真相" class="img-circle" width="15%">&nbsp;&nbsp;
			<font size="3px" color="#337ab7"><%=post.getAuthor()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div class="fb">
			<font size="3.5px" style="font-weight:bold;"><%=post.getTitle()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div>
			<font size="3px"><%=post.getContent()%></font>
		</div>
		<hr />
		<div>
			&nbsp;&nbsp;
			<font size="1.5px" color="#C8C6C6"><%=post.getPostTime()%></font>
		</div>
	</div>

</body>
</html>