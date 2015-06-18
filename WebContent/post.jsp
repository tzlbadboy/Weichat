<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="nju.iip.dto.Post"%>
<%@ page import="nju.iip.dto.Comment"%>
<%@ page import="nju.iip.dao.impl.PostDaoImpl"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<title>帖子详情</title>
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/common.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>

	<%
		Post post = (Post) request.getAttribute("post");
		List<Comment> comment_list = PostDaoImpl.getAllComment(post.getId());
	%>

	<div class="bgfff form ov">
		<div>
			<img src=<%=post.getHeadImgUrl()%> alt="求真相" class="img-circle"
				width="15%">&nbsp;&nbsp; <font size="3px" color="#337ab7"><%=post.getAuthor()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div class="fb">
			<font size="3.5px" style="font-weight: bold;"><%=post.getTitle()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div>
			<font size="3px"><%=post.getContent()%></font>
		</div>
		<hr />
		<div>
			<table width="100%">
				<tr>
					<td width="60%"><font size="2px" color="#337ab7"><%=post.getAuthor()%></font>&nbsp;&nbsp;<font
						size="1.5px" color="#C8C6C6"><%=post.getPostTime().substring(5)%></font></td>

					<td style="text-align: right;"><span
						class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;
						<font size="3px" color="#C8C6C6"><%=post.getReply()%></font>&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
	</div>
	<hr />

	<%
		int i=1;
			for(Comment comment:comment_list) {
	%>
	<div class="bgfff form ov">
		<%=i%>楼
		<div>
			<img src=<%=comment.getHeadImgUrl()%> alt="求真相" class="img-circle"
				width="15%">&nbsp;&nbsp; <font size="3px" color="#337ab7"><%=comment.getAuthor()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div>
			<font size="3px"><%=comment.getComment_content()%></font>
		</div>
		<hr />
		<div>
			&nbsp;&nbsp; <font size="1.5px" color="#C8C6C6"><%=comment.getCommentTime()%></font>
		</div>

	</div>

	<%
		i++;
			}
	%>
	<div class="fix_footer" id="fix_footer"
		style="padding: 5px; left: 0px; color: rgb(255, 255, 255); height: 45px; width: 100%; position: fixed; bottom: 0%; font-size: 14px; display: block; background-color: #337ab7;">
		<table width="100%">
			<tr>
				<td><input type="text" class="form-control"
					placeholder="评论点什么吧"></td>
				<td style="text-align: center;"><span id="send">发送</span><span
					class="glyphicon glyphicon-send" aria-hidden="true"></span></td>
			</tr>
		</table>
	</div>





	<script type="text/javascript">
		$(document).ready(function() {

			$("span#send").click(function() {
				var comment = $("input.form-control").val();
				if (comment == "") {
					$("input.form-control").attr("placeholder", "评论内容不能为空！");
				} else {
					$.ajax({
						type : 'POST',
						url : "AddCommentServlet",
						data : {
							"comment" : comment
						},
						success : function(msg) {
							alert(msg);
						}
					});
				}
			});

		});
	</script>


</body>
</html>