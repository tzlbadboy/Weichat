<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="nju.iip.dto.WeixinUser;"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<title>已测量表记录</title>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<% 
		// 获取由OAuthServlet中传入的参数
		WeixinUser user = (WeixinUser)request.getAttribute("snsUserInfo"); 
		if(null != user) {
	%>
	<table>
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td><%=user.getOpenId()%></td></tr>
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><img src="<%=user.getHeadImgUrl()%>" width="20%"/></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
		<tr><td>姓名</td><td><%=user.getName()%></td></tr>
		<tr><td>病历号</td><td><%=user.getCardID()%></td></tr>
		<tr><td>电话</td><td><%=user.getPhone()%></td></tr>
	</table>
	<%
		}
		else 
			out.print("用户不同意授权,未获取到用户信息！");
	%>
</body>
</html>