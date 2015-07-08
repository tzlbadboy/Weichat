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
<title></title>
</head>
<body>

	<%
		String openId = (String) request.getSession().getAttribute("openId");
		MessageDaoImpl MDI = new MessageDaoImpl();
		List<Message> messages = MDI.getMessage(openId);
		int i=0;
		for(Message message:messages) {
			int isRead = message.getIsRead();
	%>
	<div class="bgfff form ov" style="line-height: 1.2;width: 98%;border-radius:3px;margin-top:6px;" id=<%=message.getId()%>>
	<input id="content" type="text" style="display:none" value=<%=message.getContent()%>>
	<input id="fromOpenId" type="text" style="display:none" value=<%=message.getFromOpenId()%>>
	
		<table width="100%">
			<tr>
				<td width="40%">
					<div class="sendMessageToHim">
						<img
							src=<%=message.getFromHeadImgUrl()%>
							alt="求真相" class="img-circle" width="40%">&nbsp;&nbsp; 
							<font id="nickname" color="#337ab7"><%=message.getFromNickname()%></font>
					</div>
				</td>
				<td>
					<div style="text-align: right;">
						<font><%=message.getSendTime().substring(5)%></font>&nbsp;&nbsp; <%if(isRead==0) { i++;%><span class="badge" style="background-color:#C35839;">1</span><%} %>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<%} %>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">From:&nbsp;<font color="#337ab7"></font></h4>
				</div>
				<div class="modal-body">

					<div class="form-group" id="form-content">
					</div>
                    
					<div class="modal-footer">
					<textarea class="form-control" id="message-text" name="message" ></textarea>
					 <div style="margin: 5px"></div>
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="取消"> <button type="button" class="btn btn-primary"
							id="send" value="确认"><span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>回复
							</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- end -->
	
	
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content" id="show_reslut"
				style="text-align: center; margin: 5px auto 5px auto">
				<br>
				<span class="return_msg"></span><span class="glyphicon glyphicon-ok"></span><br>
				<br>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">

//将消息置为已读
function ReadMessage(id) {
	$.ajax({
		type : 'GET',
		url : "MessageServlet?id="+id,
		success : function(msg) {
		}
	});
}


$(document).ready(function() {
	var unReadMessage = <%=i%>;
	$("title").html("消息盒子("+unReadMessage+"条未读消息！)");
	
	$("div.bgfff").click(function() {
		$(this).find("span.badge").remove();
		var id = $(this).attr("id");
		ReadMessage(id);
		var fromNickname = $(this).find("font#nickname").text();
		var content = $(this).find("input#content").val();
		var fromOpenId = $(this).find("input#fromOpenId").val();
		$("h4.modal-title").children("font").html(fromNickname);
		$("div#form-content").html(content);
		$("#myModal").modal("show");
		$("button#send").click(function() {
			var message = $("textarea#message-text").val();
			if(message == "") {
				$("textarea#message-text").attr("placeholder","消息内容不能为空!");
			}
			else {
				$.ajax({
					type : 'POST',
					url : "MessageServlet",
					data : {
						"message" : message,
						"ToOpenId" : fromOpenId,
						"ToNickname" : fromNickname
					},
					success : function(msg) {
						$("#myModal").modal("hide");
						$("span.return_msg").html(msg); 
						$(".bs-example-modal-sm").modal('show');
						setTimeout(function() {
							$(".bs-example-modal-sm").modal('hide');
						}, 2000);
					}
				});
			}
		});
	});
	
});

</script>


</html>