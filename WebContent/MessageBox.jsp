<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="nju.iip.dto.Message"%>
<%@ page import="nju.iip.dao.impl.MessageDaoImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/common.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>消息盒子</title>
</head>
<body>

	<%
		String openId = (String) request.getSession().getAttribute("openId");
		MessageDaoImpl MDI = new MessageDaoImpl();
		List<Message> messages = MDI.getMessage(openId);
		for(Message message:messages) {
	%>
	<div class="bgfff form ov" style="line-height: 1.2">
		<table width="100%">
			<tr>
				<td width="30%">
					<div class="sendMessageToHim">
						<img
							src=<%=message.getFromHeadImgUrl()%>
							alt="求真相" class="img-circle" width="50%">&nbsp;&nbsp; 
							<font color="#337ab7"><%=message.getFromNickname()%></font>
					</div>
				</td>
				<td>
					<div style="text-align: right;">
						<font><%=message.getSendTime().substring(5)%></font>&nbsp;&nbsp; <span class="badge" style="background-color:#DB3333;">1</span>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<%} %>
</body>
</html>