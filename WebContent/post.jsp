<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>社区问答</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
body {
	padding-top: 50px;
}

.starter-template {
	padding: 40px 15px;
	text-align: center;
}
</style>
</head>
<body>




	<div class="container">



		<div class="starter-template">
			<h1>Bootstrap starter template</h1>
			<p class="lead">
				Use this document as a way to quickly start any new project.<br>
				All you get is this text and a mostly barebones HTML document.
			</p>
		</div>
	</div>
	<!-- /.container -->



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

						<div class="form-group">
							<label for="recipient-name" class="control-label">标题:</label> <input
								type="text" class="form-control" name="username"
								id="recipient-name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">内容:</label>
							<textarea class="form-control" id="message-text" name="message"></textarea>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="取消"> 
								
							<input type="button" class="btn btn-primary" id="send" value="确认" data-toggle="modal" data-target=".bs-example-modal-sm">
						</div>
				</div>

			</div>
		</div>
	</div>
	
	
	
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content" style="text-align: center;">
      <br>发帖成功！<span class="glyphicon glyphicon-ok"></span><br><br>
    </div>
  </div>
</div>
	
	

	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container" style="padding-right: 0; padding-left: 0;width:100%;">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#myModal"
				style="width: 100%; border-radius: 1px; line-height: 1.5;">
				<span class="glyphicon glyphicon-edit"></span>发帖
			</button>
		</div>
	</nav>
	
	
	

<script type="text/javascript">
$(document).ready(function(){
  $("input#send").click(function(){
	  var title = $("input#recipient-name").val();
	  var content = $("textarea#message-text").val();
	    $.ajax({
            type: 'POST',
            url: "ReceivePostServlet",
            data: {"title":title,"content":content},
            success: function(msg){
               // alert(msg+"!");
               $("#myModal").modal('hide');
               setTimeout(function(){ $(".bs-example-modal-sm").modal('hide');},2000);
            }
        });
  });
});
</script>

</body>
</html>