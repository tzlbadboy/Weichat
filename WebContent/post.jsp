﻿<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>社区问答</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/common.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<%
		for (int i = 0; i < 7; i++) {
	%>
	<div class="bgfff form ov">
		<div class="fb">title：这里是标题</div>
		<hr />
		<div>这里是正文</div>
		<hr />
		<div>这里帖子相关信息</div>
	</div>


	<%
		}
	%>

	<div style="margin: 10px 0 60px 0">
		<button type="button" class="btn btn-info"
			style="margin: 0 auto; display: block;">下一页</button>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">请输入标题和内容：</h4>
				</div>
				<div class="modal-body">

					<div class="form-group" id="form-title">
						<label for="recipient-name" class="control-label">标题:</label> <input
							type="text" class="form-control" name="username"
							id="message-title">

					</div>
					<div class="form-group" id="form-content">
						<label for="message-text" class="control-label">内容:</label>
						<textarea class="form-control" id="message-text" name="message"></textarea>
					</div>
					<font color="red" id="error"></font>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="取消"> <input type="button" class="btn btn-primary"
							id="send" value="确认">
					</div>
				</div>

			</div>
		</div>
	</div>



	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content" id="show_reslut" style="text-align: center;margin: 5px auto 5px auto">
				<span class="glyphicon glyphicon-ok"></span>
			</div>
		</div>
	</div>



	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container"
			style="padding-right: 0; padding-left: 0; width: 100%;">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#myModal"
				style="width: 100%; border-radius: 1px; line-height: 1.5;">
				<span class="glyphicon glyphicon-edit"></span>发帖
			</button>
		</div>
	</nav>




	<script type="text/javascript">
		$(document).ready(function() {
			$("input#send").click(function() {
				var title = $("input#message-title").val();
				var content = $("textarea#message-text").val();
				if (title == "") {
					$("#form-title").addClass("has-error");
					$("#message-title").attr("placeholder", "请输入标题");
				}
				if (content == "") {
					$("#form-content").addClass("has-error");
					$("#message-text").attr("placeholder", "请输入内容");
				}
				if (title != "" && content != "") {
					$.ajax({
						type : 'POST',
						url : "ReceivePostServlet",
						data : {
							"title" : title,
							"content" : content
						},
						success : function(msg) {
							// alert(msg+"!");
							$("#myModal").modal('hide');
							$("div#show_reslut").html("<br><br>"+msg+"<br>");
							$(".bs-example-modal-sm").modal('show');
							setTimeout(function() {
								$(".bs-example-modal-sm").modal('hide');
							}, 2000);
						}
					});
				}
			});
		});
	</script>

</body>
</html>