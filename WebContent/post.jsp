﻿<%@ page language="java" pageEncoding="utf-8"%>
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
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no">
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
	    PostDaoImpl PDI = new PostDaoImpl();
		List<Comment> comment_list = PDI.getAllComment(post.getId());
		String openId = (String)request.getSession().getAttribute("openId");
		System.out.println("openId-------->"+openId);
		int postId = post.getId();
		boolean isLoved = PDI.isLove(openId, postId);
	%>

	<div class="bgfff form ov">
		<div class="sendMessageToHim" id=<%=post.getOpenId()%>>
			<img src=<%=post.getHeadImgUrl()%> alt="求真相" class="img-circle"
				width="15%">&nbsp;&nbsp; <font size="3px" color="#337ab7"><%=post.getAuthor()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div class="fb">
			<font size="3.5px" style="font-weight: bold;"><%=post.getTitle()%></font>
		</div>
		<hr style="border: 0; height: 0.1px;" />
		<div>
			<font size="3px"><%=post.getContent()%></font><br>
			<%if(post.getPictureUrl()!=null) {
				String url = "../Pictures/images/upload/"+post.getPictureUrl();
			%>
			<img src=<%=url%> width="80%" />
			<% }%>
		</div>
		<hr />
		<div>
			<table width="100%">
				<tr>
					<td width="60%"><font size="2px" color="#337ab7"><%=post.getAuthor()%></font>&nbsp;&nbsp;<font
						size="1.5px" color="#C8C6C6"><%=post.getPostTime().substring(5)%></font></td>

					<td style="text-align: right;"><span
						class="glyphicon love" aria-hidden="true"></span>&nbsp;
						<font id="love" size="3px" color="#C8C6C6"><%=post.getLove()%></font>&nbsp;&nbsp;

						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;
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
	<div class="bgfff form ov" style="line-height: 1.2">

		<table width="100%">
			<tr>
				<td width="70%">
					<div class="sendMessageToHim" id=<%=comment.getOpenId() %>>
						<img src=<%=comment.getHeadImgUrl()%> alt="求真相" class="img-circle"
							width="13%">&nbsp;&nbsp; <font size="3px" color="#337ab7"><%=comment.getAuthor()%></font>
					</div>
				</td>
				<td style="text-align: center;"><%=i%>楼</td>
			</tr>
		</table>
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
	
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">发送消息给:<font color="#337ab7"></font></h4>
				</div>
				<div class="modal-body">

					<div class="form-group" id="form-content">
						<label for="message-text" class="control-label">内容:</label>
						<textarea class="form-control" id="message-text" name="message"></textarea>
					</div>
                    
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="取消"> <input type="button" class="btn btn-primary"
							id="send" value="确认">
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


	<div style="margin: 10px 0 60px 0"></div>

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

</body>

<script type="text/javascript"> 
	$(document).ready(function() { 
		var loved = <%=isLoved%>;
		if(loved==false) {
			$("span.love").addClass("glyphicon-heart-empty");
		}
		else {
			$("span.love").addClass("glyphicon-heart");
		}
		
		var ToOpenId,ToNickname;
	
		//模态框发送按钮响应函数 
		$("div.sendMessageToHim").click(function() {
			var userOpenId = "<%=openId%>";
			ToOpenId =  $(this).attr("id");
			if(ToOpenId!=userOpenId) {
				$("#myModal").modal("show");
				ToNickname = $(this).children("font").text();
				$("h4.modal-title").children("font").html(ToNickname);
			}
			
		});
		
		
		$("input#send").click(function() {
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
						"ToOpenId" : ToOpenId,
						"ToNickname" : ToNickname
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
		
		
		//点赞处理逻辑 
		$("span.glyphicon").click(function() {
			if($("span.glyphicon").hasClass("glyphicon-heart-empty")) {
				$("span.glyphicon").removeClass("glyphicon-heart-empty");
				$("span.glyphicon").addClass("glyphicon-heart");
				$.get("AddLikeServlet?id="+<%=post.getId()%>,function(data,status){
				      //alert("状态：" + status);
			    });
				var n=$("font#love").text();
				$("font#love").text(Number(n)+1);
			}
		});
		
		
		//回复帖子处理 
		$("span#send").click(function() { 
			var comment = $("input.form-control").val(); 
			if (comment == "") {
				$("input.form-control").attr( "placeholder", "评论内容不能为空！"); 
				} 
			else { 
				$.ajax({ 
					type : 'POST',
					url : "AddCommentServlet",
					data : { "comment" : comment }, 
					success : function( msg) { 
						$( "span.return_msg").html( "<br>" + msg); 
						$( ".bs-example-modal-sm").modal( 'show'); 
						setTimeout( function() { 
							$( ".bs-example-modal-sm").modal( 'hide');
							location.href = "ShowPostServlet?id=" + <%=post.getId()%> ; 
							}, 2000); 
						} 
					}); 
				} 
			}); 
		
		
		
		}); 
	</script>

</html>