<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="nju.iip.dto.Post"%>
<%@ page import="nju.iip.dao.impl.PostDaoImpl"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
		List<Post> post_list = PostDaoImpl.getAllPost();
		for (Post post:post_list) {
	%>
	<div class="bgfff form ov" id=<%=post.getId()%>>
		<div class="fb">
			<font size="3.5px"><%=post.getTitle()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div>
			<font size="3px"><%=post.getContent()%></font>
		</div>
		<hr />
		<div>
			<table width="100%">
				<tr>
					<td width="50%"><font size="2px" color="#337ab7"><%=post.getAuthor()%></font>&nbsp;&nbsp;<font
						size="1.5px" color="#C8C6C6"><%=post.getPostTime()%></font></td>
						
						<td style="text-align:right;">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;
						<font
						size="3px" color="#C8C6C6"><%=post.getReply()%></font>&nbsp;&nbsp;
						</td>
				</tr>
			</table>
		</div>
	</div>


	<%
		}
	%>

	<div style="margin: 10px 0 60px 0"></div>

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
			<div class="modal-content" id="show_reslut"
				style="text-align: center; margin: 5px auto 5px auto">
				<span class="return_msg"></span><span class="glyphicon glyphicon-ok"></span><br>
				<br> <br>
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

			$("div.bgfff").click(function() {
				var postId = $(this).attr("id");
				location.href = "ShowPostServlet?id=" + postId;
			});

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
							$("span.return_msg").html("<br><br>" + msg);
							$(".bs-example-modal-sm").modal('show');
							setTimeout(function() {
								$(".bs-example-modal-sm").modal('hide');
								location.href = "post_list.jsp";
							}, 2000);

						}
					});
				}
			});
		});
	</script>

</body>
</html>